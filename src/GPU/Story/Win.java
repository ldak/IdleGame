package GPU.Story;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import components.MapConstructor;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Win extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static int width=500;
	public static int height=500;
	/**
	 * Create the frame.
	 */
	public Win(int x,int y) {
		setTitle("Win");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(x, y, width, height);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				MapConstructor.deleteSave();
				System.exit(0);
			}

		});
		
		JLabel lblYouWin = new JLabel("YOU WIN");
		lblYouWin.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblYouWin.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouWin.setForeground(Color.ORANGE);
		lblYouWin.setBounds(12, 13, 458, 427);
		contentPane.add(lblYouWin);
	}

}
