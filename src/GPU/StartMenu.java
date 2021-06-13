package GPU;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GPU.Story.Spawn;
import GPU.helpMenus.Help;
import GPU.helpMenus.MessageBox;
import GPU.helpMenus.SoundMenu;
import components.MapConstructor;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class StartMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartMenu frame = new StartMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StartMenu() {
		setTitle("Tap Tap King");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(200, 200, 500, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.BLACK);

		int buttonDistacne = 80;
		int buttonX = 150;
		int buttonY = 150;
		Font font = new Font("Tahoma", Font.BOLD, 20);
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (MapConstructor.hasSaved()) {
					Game3D.start();
					MapConstructor.loadSaved();
					setVisible(false);
					dispose();
				} else {
					MessageBox m = new MessageBox(
							getLocationOnScreen().x - 20, 
							getLocationOnScreen().y + 100,
							"Error","There is no saved games");
					m.setVisible(true);
				}
			}
		});
		btnContinue.setFont(font);
		btnContinue.setBounds(buttonX, buttonY, 200, 40);
		contentPane.add(btnContinue);

		JButton btnNewGame = new JButton("New game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Game3D.start();
				MapConstructor.creatPole();
				new Spawn(getLocationOnScreen().x-2,getLocationOnScreen().y-20).setVisible(true);
				setVisible(false);
				dispose();
				
			}
		});
		btnNewGame.setFont(font);
		btnNewGame.setBounds(buttonX, buttonY + 1 * buttonDistacne, 200, 40);
		contentPane.add(btnNewGame);

		JButton btnSettings = new JButton("Sound");
		btnSettings.setFont(font);
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SoundMenu sound=new SoundMenu(getLocationOnScreen().x-2,getLocationOnScreen().y+20);
				sound.setVisible(true);
			}
		});
		btnSettings.setBounds(buttonX, buttonY + 2 * buttonDistacne, 200, 40);
		contentPane.add(btnSettings);

		JButton btnHelp = new JButton("Help");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Help help=new Help(getLocationOnScreen().x-2,getLocationOnScreen().y+20);
				help.setVisible(true);
			}
		});
		btnHelp.setFont(font);
		btnHelp.setBounds(buttonX, buttonY + 3 * buttonDistacne, 200, 40);
		contentPane.add(btnHelp);

	}
}
