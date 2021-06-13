package GPU.Story;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GPU.Game3D;
import GPU.MapType;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class ChooseMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static int width=700;
	public static int height=700;
	

	/**
	 * Create the frame.
	 */
	public ChooseMenu(int x,int y) {
		setTitle("Choose");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(x, y, width, height);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				new ChooseMenu(getLocationOnScreen().x, getLocationOnScreen().y).setVisible(true);
				dispose();
			}

		});
		
		JLabel lblNewLabel = new JLabel("Oh, Nooo!!");
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(12, 13, 670, 84);
		contentPane.add(lblNewLabel);
		String str="You were this close to your dream, \n"
				+ "but the earth has started to run out of recourse. \n"
				+ "The mines are stopping. \n"
				+ "You have 3 choices: ";
		JLabel label = new JLabel(convertToMultiline(str));
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(12, 138, 670, 143);
		contentPane.add(label);
		
		JButton btnContinue = new JButton("Continue diging and hope");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Game3D.mapType=MapType.VOLCAN;
				Game3D.stop=false;
				dispose();
			}
		});
		btnContinue.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnContinue.setBounds(228, 294, 262, 50);
		contentPane.add(btnContinue);
		
		JButton btnGoDrink = new JButton("Drown your in pain in vodka");
		btnGoDrink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HangOver frame=new HangOver(getLocationOnScreen().x + getWidth() / 2 - HangOver.width / 2,
						getLocationOnScreen().y + getHeight() / 2 - HangOver.height / 2);
				frame.setVisible(true);
				dispose();
			}
		});
		btnGoDrink.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGoDrink.setBounds(228, 380, 262, 50);
		contentPane.add(btnGoDrink);
		
		JButton btnRest = new JButton("Go rest a litle bit");
		btnRest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GirlNoCome frame=new GirlNoCome(getLocationOnScreen().x + getWidth() / 2 - GirlNoCome.width / 2,
						getLocationOnScreen().y + getHeight() / 2 - GirlNoCome.height / 2);
				frame.setVisible(true);
				dispose();
			}
		});
		btnRest.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnRest.setBounds(228, 460, 262, 50);
		contentPane.add(btnRest);
		
	}
	
	private String convertToMultiline(String orig)
	{
	    return "<html>" + orig.replaceAll("\n", "<br>");
	}
}
