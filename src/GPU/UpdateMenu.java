package GPU;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import components.MapConstructor;
import components.goldDiggers.Hole;
import components.goldDiggers.Minor;
import components.moveable.FullBoardException;
import components.moveable.NoMinorsException;
import components.moveable.PlaceHole;
import components.moveable.PlaceMinor;
import components.moveable.UpdateMinor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class UpdateMenu extends JFrame implements KeyListener{

	private JPanel contentPane;
	static int width=500;
	static int height=500;
	
	public UpdateMenu(int x,int y) {
		setTitle("Update Menu");
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(x, y, width, height);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		addKeyListener(this);
		setFocusable(true);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.BLACK);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});
		
		JLabel lblHole = new JLabel("Hole");
		lblHole.setForeground(Color.WHITE);
		lblHole.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHole.setBounds(100, 40, 75, 45);
		contentPane.add(lblHole);
		
		JButton btnBuyHole = new JButton(MapConstructor.convertMoney(new PlaceHole(0,0).getCostValue()));
		btnBuyHole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Game3D.getHoles().size()==MapConstructor.poleSize*MapConstructor.poleSize-2) {
					JOptionPane.showMessageDialog(null, "Your board is full.");
				}
				
				try {
					Game3D.creater=new PlaceHole();
					exit();
				} catch (FullBoardException e) {
					System.err.println("FULL BOARD EXCEPTION");
				}
			}
		});
		btnBuyHole.setBounds(300, 50, 100, 25);
		contentPane.add(btnBuyHole);
		
		JLabel lblMinor = new JLabel("Minor");
		lblMinor.setForeground(Color.WHITE);
		lblMinor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMinor.setBounds(100, 140, 75, 45);
		contentPane.add(lblMinor);
		
		JButton btnBuyMinor = new JButton(MapConstructor.convertMoney(new PlaceMinor(0,0).getCostValue()));
		btnBuyMinor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Game3D.creater=new PlaceMinor();
					exit();
				} catch (FullBoardException e) {
					System.err.println("FULL BOARD EXCEPTION");
					JOptionPane.showMessageDialog(null, "There aren't free holes.");
				}
			}
		});
		btnBuyMinor.setBounds(300, 150, 100, 25);
		contentPane.add(btnBuyMinor);
		
		JLabel lblUpdateMinor = new JLabel("Update minor");
		lblUpdateMinor.setForeground(Color.WHITE);
		lblUpdateMinor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUpdateMinor.setBounds(100, 240, 175, 45);
		contentPane.add(lblUpdateMinor);
		
		JButton btnUpdateMinor = new JButton("update");
		btnUpdateMinor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Game3D.creater=new UpdateMinor();
					exit();
				} catch (NoMinorsException e) {
					System.err.println("No minors");
					JOptionPane.showMessageDialog(null, "There aren't any minors.");
				}
			}
		});
		btnUpdateMinor.setBounds(300, 250, 100, 25);
		contentPane.add(btnUpdateMinor);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		new Hole(0, 0).paint((Graphics2D)g, 60, 120);
		new Minor(0, 0,1).paint((Graphics2D)g, 60, 220);
		new Minor(0, 0,4).paint((Graphics2D)g, 60, 340);
		
	}
	
	private void exit(){
		Game3D.getInstance().setFocusable(true);
		setVisible(false);
		dispose();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_M||e.getKeyCode()==KeyEvent.VK_ESCAPE) {
			exit();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
