package polenSegmentation;

import java.awt.FileDialog;
import java.awt.image.BufferedImage;

import javax.swing.JFileChooser;

import boofcv.core.image.ConvertBufferedImage;
import boofcv.io.image.UtilImageIO;
import boofcv.struct.image.ImageFloat32;


public class PolenSegmentation {
	FitBinaryImageInterface fbii;
	FBIIParametersInterface fpi;
	ImageFloat32 input;

	public static void main(String args[]) {
		new PolenSegmentation();
	}

	public PolenSegmentation() {
		fbii = new FitBinaryImageInterface();
		fpi = new FBIIParametersInterface(this);

		JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(null);
		
		//BufferedImage image = UtilImageIO.loadImage("C:\\Users\\Hugo\\Dropbox\\surfExtractor\\diversosGraosJuntos\\5A\\Img00133.jpg");
		BufferedImage image = UtilImageIO.loadImage(fc.getSelectedFile().getAbsolutePath());
		input = ConvertBufferedImage.convertFromSingle(image, null, ImageFloat32.class);

		fbii.setImage(input);

		fbii.setVisible(true);
		fpi.setVisible(true);

		System.out.println("done");
	}

	public void update() {
		System.out.println("Updating parameters");
		this.fbii.setToleranceAngle( (fpi.getTAValue() * Math.PI));
		this.fbii.setToleranceDist((int) (fpi.getTDValue() * 10));
		this.fbii.setErodeTimes((int)(fpi.getErodeValue() * 10));;
		this.fbii.setDilateTimes((int)(fpi.getDilateValue() * 10));
		this.fbii.setMean((double) fpi.getThresholdValue() * 255);
		this.fbii.repaint();

	}

	public void newImage() {
		JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(null);
		BufferedImage image = UtilImageIO.loadImage(fc.getSelectedFile().getAbsolutePath());
		input = ConvertBufferedImage.convertFromSingle(image, null, ImageFloat32.class);
		this.fbii.setImage(input);
		this.fbii.repaint();
		
	}
}
