package GPU.Story;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class GirlNoCome extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	public static int width=500;
	public static int height=500;
	private String message = "You calmed down and decided to go on a date. "
			+ "But your girl didn't come. Now you are mad. "
			+ "It's just too much for one day. "
			+ "Now you can choose to drink a glass of vodka or to go to sleep. "
			+ "What's your choice?";
	/**
	 * Create the frame.
	 */
	public GirlNoCome(int x,int y) {
		setTitle("The Girl");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(x, y, 500, 407);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				new GirlNoCome(getLocationOnScreen().x, getLocationOnScreen().y).setVisible(true);
				setVisible(false);
				dispose();
			}

		});
		JLabel lbl = new JLabel(convertToMultiline(message));
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lbl.setForeground(Color.WHITE);
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setBounds(46, 13, 398, 245);
		contentPane.add(lbl);
		
		JButton btnVodka = new JButton("vodka");
		btnVodka.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnVodka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HangOver frame=new HangOver(getLocationOnScreen().x + getWidth() / 2 - HangOver.width / 2,
						getLocationOnScreen().y + getHeight() / 2 - HangOver.height / 2);
				frame.setVisible(true);
				dispose();
			}
		});
		btnVodka.setBounds(70, 280, 150, 50);
		contentPane.add(btnVodka);
		
		JButton btnSleep = new JButton("sleep");
		btnSleep.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSleep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewPlanet frame=new NewPlanet(getLocationOnScreen().x + getWidth() / 2 - NewPlanet.width / 2,
						getLocationOnScreen().y + getHeight() / 2 - NewPlanet.height / 2);
				frame.setVisible(true);
				dispose();
			}
		});
		btnSleep.setBounds(280, 280, 150, 50);
		contentPane.add(btnSleep);
	}
	private String convertToMultiline(String orig)
	{
	    return "<html>" + orig.replaceAll("\n", "<br>");
	}
}
