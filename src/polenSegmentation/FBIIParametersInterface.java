package polenSegmentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FBIIParametersInterface extends JFrame {

	private JPanel contentPane;

	public JSlider slider;
	
	public JSlider slider_1;
	private JLabel lblDilateTimes;
	private JSlider slider_2;
	private JLabel lblErodeTimes;
	private JSlider slider_3;
	private JLabel lblThresholdValue;
	private JSlider slider_4;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FBIIParametersInterface frame = new FBIIParametersInterface(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param polenSegmentation 
	 * @param polenSegmentation 
	 * @param polenSegmentation 
	 */
	public FBIIParametersInterface(PolenSegmentation polenSegmentation) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 259);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblToleranceDistance = new JLabel("Tolerance Distance");
		lblToleranceDistance.setBounds(20, 11, 120, 14);
		contentPane.add(lblToleranceDistance);
		
		JLabel lblToleranceAngle = new JLabel("Tolerance Angle");
		lblToleranceAngle.setBounds(20, 45, 120, 14);
		contentPane.add(lblToleranceAngle);
		
		slider = new JSlider();
		slider.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				if(polenSegmentation != null) polenSegmentation.update();
			}
		});
		slider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(polenSegmentation != null) polenSegmentation.update();
			}
		});
		slider.setMaximum(1000);
		slider.setMinimum(0);
		slider.setBounds(150, 11, 274, 23);
		contentPane.add(slider);
		
		slider_1 = new JSlider();
		slider_1.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				if(polenSegmentation != null) polenSegmentation.update();
			}
		});
		slider_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(polenSegmentation != null) polenSegmentation.update();
			}
		});
		slider_1.setMaximum(1000);
		slider_1.setMinimum(0);
		slider_1.setBounds(150, 45, 274, 23);
		contentPane.add(slider_1);
		
		lblDilateTimes = new JLabel("Erode Times");
		lblDilateTimes.setBounds(20, 82, 120, 14);
		contentPane.add(lblDilateTimes);
		
		slider_2 = new JSlider();
		slider_2.setMinimum(0);
		slider_2.setMaximum(1000);
		slider_2.setBounds(150, 73, 274, 23);
		slider_2.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				if(polenSegmentation != null) polenSegmentation.update();
			}
		});
		contentPane.add(slider_2);
		
		lblErodeTimes = new JLabel("Dilate Times");
		lblErodeTimes.setBounds(20, 107, 120, 14);
		contentPane.add(lblErodeTimes);
		
		slider_3 = new JSlider();
		slider_3.setMinimum(0);
		slider_3.setMaximum(1000);
		slider_3.setBounds(150, 107, 274, 23);
		slider_3.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				if(polenSegmentation != null) polenSegmentation.update();
			}
		});
		contentPane.add(slider_3);
		
		lblThresholdValue = new JLabel("Threshold Value");
		lblThresholdValue.setBounds(20, 152, 120, 14);
		contentPane.add(lblThresholdValue);
		
		slider_4 = new JSlider();
		slider_4.setMinimum(0);
		slider_4.setMaximum(1000);
		slider_4.setBounds(150, 143, 274, 23);
		slider_4.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				if(polenSegmentation != null) polenSegmentation.update();
			}
		});
		contentPane.add(slider_4);
		
		JButton btnNewButton = new JButton("New image");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(polenSegmentation != null) polenSegmentation.newImage();
			}
		});
		btnNewButton.setBounds(20, 187, 404, 23);
		contentPane.add(btnNewButton);
	}
	
	public float getTDValue() {
		return (float) slider.getValue() / (float)slider.getMaximum();
	}
	
	public float getTAValue() {
		return (float)slider_1.getValue() / (float)slider_1.getMaximum();
	}
	
	public float getErodeValue() {
		return (float) slider_2.getValue() / (float)slider_2.getMaximum();
	}
	
	public float getDilateValue() {
		return (float) slider_3.getValue() / (float)slider_3.getMaximum();
	}
	
	public float getThresholdValue() {
		return (float) slider_4.getValue() / (float)slider_4.getMaximum();
	}
}
