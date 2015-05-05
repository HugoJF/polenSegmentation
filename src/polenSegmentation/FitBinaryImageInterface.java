package polenSegmentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import boofcv.struct.image.ImageFloat32;

public class FitBinaryImageInterface extends JFrame {

	private ImageJPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FitBinaryImageInterface frame = new FitBinaryImageInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param polenSegmentation
	 */
	public FitBinaryImageInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new ImageJPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	public void setImage(ImageFloat32 image) {
		contentPane.setImage(image);
		setSize(contentPane.getSize());
	}

	public void setToleranceDist(double toleranceDist) {
		contentPane.setToleranceDist(toleranceDist);
	}

	public void setToleranceAngle(double toleranceAngle) {
		contentPane.setToleranceAngle(toleranceAngle);
	}

	public void setErodeTimes(int i) {
		contentPane.setErodeTimes(i);
	}

	public void setDilateTimes(int i) {
		contentPane.setDilateTimes(i);
	}
	
	public void setMean(double i) {
		contentPane.setMean(i);
	}
}
