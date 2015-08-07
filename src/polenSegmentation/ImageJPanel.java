package polenSegmentation;

import georegression.struct.point.Point2D_I32;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import boofcv.abst.feature.detdesc.DetectDescribePoint;
import boofcv.struct.ConnectRule;
import boofcv.struct.PointIndex_I32;
import boofcv.struct.feature.SurfFeature;
import boofcv.struct.image.ImageFloat32;
import boofcv.struct.image.ImageUInt8;
import boofcv.alg.feature.shapes.ShapeFittingOps;
import boofcv.alg.filter.binary.BinaryImageOps;
import boofcv.alg.filter.binary.Contour;
import boofcv.alg.filter.binary.ThresholdImageOps;
import boofcv.alg.misc.ImageStatistics;
import boofcv.core.image.ConvertBufferedImage;
import boofcv.gui.feature.VisualizeShapes;
import boofcv.gui.image.ShowImages;

public class ImageJPanel extends JPanel {

	private ImageFloat32 image = null;

	private int rectSize = 5;

	private float factor = -1;

	private List<Contour> contours;

	private double toleranceDist = 1;

	private double toleranceAngle = Math.PI / 10;
	
	private int erodeTimes = 1;
	
	private int dilateTimes = 1;

	private double mean = -1;

	/**
	 * Create the panel.
	 */
	public ImageJPanel() {
		setLayout(null);
		setSize(500, 500);

	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null) {
			/*
			 * factor = -1; if (this.getHeight() <= this.getWidth()) { maxSize =
			 * this.getHeight(); factor = (float) maxSize / (float)
			 * image.getHeight(); } else { maxSize = this.getWidth(); factor =
			 * (float) maxSize / (float) image.getWidth(); }
			 */

			this.resize(image.getWidth(), image.getHeight());
			// g.drawImage(ConvertBufferedImage.convertTo(this.image, null), 0,
			// 0, (int) ((float) image.getWidth() * factor), (int) ((float)
			// image.getHeight() * factor), this);
			g.drawImage(ConvertBufferedImage.convertTo(this.image, null), 0, 0, image.getWidth(), image.getHeight(), this);
		}
		if (contours != null) {
			((Graphics2D) g).setStroke(new BasicStroke(2));
			for (Contour c : contours) {
				// Fit the polygon to the found external contour. Note loop =
				// true
				List<PointIndex_I32> vertexes = ShapeFittingOps.fitPolygon(c.external, true, toleranceDist, toleranceAngle, 100);

				g.setColor(Color.RED);
				VisualizeShapes.drawPolygon(vertexes, true, (Graphics2D) g);
				
				Point lowBound = new Point();
				Point uppBound = new Point();
				
				lowBound.x = vertexes.get(0).x;
				lowBound.y = vertexes.get(0).y;
				uppBound.x = vertexes.get(0).x;
				uppBound.y = vertexes.get(0).y;
				
				for(Point2D_I32 p : vertexes) {
					if(p.x < lowBound.x) lowBound.x = p.x;
					if(p.y < lowBound.y) lowBound.y = p.y;
					
					if(p.x > uppBound.x) uppBound.x = p.x;
					if(p.y > uppBound.y) uppBound.y = p.y;
				}
				int width = uppBound.x - lowBound.x;
				int height = uppBound.y - lowBound.y;
				int pad = 5;
				
				((Graphics2D) g).setColor(Color.GREEN);
				
				System.out.println("Drawing @" + lowBound.x + "x" + lowBound.y + " to " + uppBound.x + "x" + uppBound.y + " with " + width + "px width and " + height + "px height");
				
				((Graphics2D) g).drawRect(lowBound.x - pad, lowBound.y - pad, width + pad, height + pad);
				
				

				// handle internal contours now
				/*g.setColor(Color.BLUE);
				((Graphics2D) g).setStroke(new BasicStroke(2));
				for (List<Point2D_I32> internal : c.internal) {
					vertexes = ShapeFittingOps.fitPolygon(internal, true, toleranceDist, toleranceAngle, 100);
					VisualizeShapes.drawPolygon(vertexes, true, (Graphics2D) g);
				}*/
			}
		}
	}

	public void setImage(ImageFloat32 image) {
		this.image = image;
		generateContour(this.image);
	}

	private void generateContour(ImageFloat32 input) {
		//
		ImageUInt8 binary = new ImageUInt8(input.width, input.height);
		//BufferedImage polygon = new BufferedImage(input.width, input.height, BufferedImage.TYPE_INT_RGB);
		
		// the mean pixel value is often a reasonable threshold when creating a
		// binary image
		if(this.mean == -1) this.mean = ImageStatistics.mean(input);
		
		System.out.println("Image mean: " + mean);

		// create a binary image by thresholding
		ThresholdImageOps.threshold(input, binary, (float) this.mean, true);
		
		// reduce noise with some filtering
		ImageUInt8 filtered = BinaryImageOps.erode8(binary, erodeTimes, null);
		filtered = BinaryImageOps.dilate8(filtered, dilateTimes, null);
		
		// Find the contour around the shapes
		List<Contour> contours = BinaryImageOps.contour(filtered, ConnectRule.EIGHT, null);

		this.contours = contours;

	}

	public void setContours(ArrayList<Contour> contours) {
		this.contours = contours;
	}

	public void setToleranceDist(double toleranceDist) {
		this.toleranceDist = toleranceDist;
		generateContour(this.image);
		System.out.println("new tolerance distance = " + toleranceDist);
	}

	public void setToleranceAngle(double toleranceAngle) {
		this.toleranceAngle = toleranceAngle;
		generateContour(this.image);
		System.out.println("new tolerance angle = " + toleranceAngle);
	}

	public void setErodeTimes(int erodeTimes) {
		this.erodeTimes = erodeTimes;
		generateContour(this.image);
		System.out.println("new erode = " + erodeTimes);
	}

	public void setDilateTimes(int dilateTimes) {
		this.dilateTimes = dilateTimes;
		generateContour(this.image);
		System.out.println("new dilate = " + dilateTimes);
	}
	
	public void setMean(double mean) {
		this.mean = mean;
		generateContour(this.image);
		System.out.println("new threshold value: " + mean);
	}

}
