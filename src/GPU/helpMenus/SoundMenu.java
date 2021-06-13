package GPU.helpMenus;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sound.Music;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.Icon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SoundMenu extends JFrame {

	private JPanel contentPane;
	private final JLabel lblSound = new JLabel("Sound:");

	
	/**
	 * Create the frame.
	 */
	public SoundMenu(int x ,int y) {
		setTitle("Sound");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(x, y, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblSound.setHorizontalAlignment(SwingConstants.CENTER);
		lblSound.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSound.setForeground(Color.WHITE);
		lblSound.setBackground(Color.BLACK);
		lblSound.setBounds(91, 95, 126, 36);
		contentPane.add(lblSound);
		
		JButton button = new JButton(setButtonText());
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Music.isOn()) {
					Music.setSoundOff();
				}else {
					Music.setSoundOn();
				}
				button.setText(setButtonText());
			}
		});
		button.setBounds(228, 104, 97, 25);
		contentPane.add(button);
	}


	private String setButtonText() {
		// TODO Auto-generated method stub
		return Music.isOn()?"On":"Off";
	}

}
