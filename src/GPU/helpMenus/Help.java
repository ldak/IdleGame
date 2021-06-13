package GPU.helpMenus;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Help extends JFrame {

	private JPanel contentPane;

	
	public Help(int x,int y) {
		setTitle("Help");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(x, y, 448, 431);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Move -");
		lblNewLabel.setBounds(50, 50, 90, 30);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setForeground(Color.WHITE);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("W, A, S, D");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_1.setBounds(214, 50, 130, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblUpdateMenu = new JLabel("Update Menu -");
		lblUpdateMenu.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblUpdateMenu.setForeground(Color.WHITE);
		lblUpdateMenu.setBounds(50, 130, 184, 30);
		contentPane.add(lblUpdateMenu);
		
		JLabel lblM = new JLabel("M");
		lblM.setHorizontalAlignment(SwingConstants.CENTER);
		lblM.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblM.setForeground(Color.WHITE);
		lblM.setBounds(277, 130, 56, 30);
		contentPane.add(lblM);
		
		JLabel lblEnter = new JLabel("Place -");
		lblEnter.setForeground(Color.WHITE);
		lblEnter.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblEnter.setBounds(50, 210, 184, 30);
		contentPane.add(lblEnter);
		
		JLabel lblEnter_1 = new JLabel("Enter");
		lblEnter_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnter_1.setForeground(Color.WHITE);
		lblEnter_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblEnter_1.setBounds(252, 210, 81, 30);
		contentPane.add(lblEnter_1);
		
		JLabel lblExit = new JLabel("Exit -");
		lblExit.setForeground(Color.WHITE);
		lblExit.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblExit.setBounds(50, 280, 184, 30);
		contentPane.add(lblExit);
		
		JLabel lblEsc = new JLabel("Esc");
		lblEsc.setHorizontalAlignment(SwingConstants.CENTER);
		lblEsc.setForeground(Color.WHITE);
		lblEsc.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblEsc.setBounds(252, 280, 81, 30);
		contentPane.add(lblEsc);
	}
}
