package GPU.helpMenus;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MessageBox extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public static int width=450;
	public static int height=230;
	/**
	 * Create the dialog.
	 */
	public MessageBox(int x,int y,String title,String messege) {
		setTitle(title);
		setResizable(false);
		setBounds(x, y, width, height);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.BLACK);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setFocusable(true);
		contentPanel.setLayout(null);
		
		JLabel lblMessage = new JLabel(convertToMultiline(messege),SwingConstants.CENTER);
		lblMessage.setBounds(12, 13, 408, 103);
		lblMessage.setForeground(Color.WHITE);
		lblMessage.setBackground(Color.BLACK);
		lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPanel.add(lblMessage);
		
		JButton btnOk = new JButton("ok");
		btnOk.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnOk.setBounds(170, 129, 97, 25);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPanel.add(btnOk);
		
	}
	
	private String convertToMultiline(String orig)
	{
	    return "<html>" + orig.replaceAll("\n", "<br>");
	}
}
