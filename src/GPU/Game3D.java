package GPU;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GPU.Story.ChooseMenu;
import GPU.Story.GoMoney;
import GPU.Story.OverHeat;
import GPU.Story.Win;
import components.Components;
import components.MapConstructor;
import components.goldDiggers.GoldDiger;
import components.goldDiggers.Hole;
import components.goldDiggers.Minor;
import components.moveable.Creaters;
import components.player.Player;
import sound.Music;

public class Game3D extends JFrame implements Runnable, KeyListener {

	private JPanel contentPane;
	static BufferStrategy bs;
	/**
	 * Launch the application.
	 */
	private static Game3D frame = new Game3D();
	public static boolean stop = false;

	public static void start() {
		// TODO Auto-generated method stub
		frame.setVisible(true);
		frame.createBufferStrategy(2);
		Game3D.bs = frame.getBufferStrategy();
		new Thread(frame).start();
		frame.setTitle("Tap Tap game");
		new Music().start();
	}

	public static Game3D getInstance() {
		return frame;
	}

	public static List<Hole> holes = new ArrayList<>();
	public static List<Minor> minors = new ArrayList<>();
	public static Player player = new Player(3, 0);
	public static Creaters creater = null;
	public static MapType mapType = MapType.ICE;

	public Game3D() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 800, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(Color.BLACK);
		setContentPane(contentPane);
		contentPane.setVisible(true);
		contentPane.addKeyListener(this);
		contentPane.setFocusable(true);
		contentPane.setFocusTraversalKeysEnabled(false);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});

	}

	public void paint() {
		Graphics2D g2 = null;

		do {
			try {
				g2 = (Graphics2D) bs.getDrawGraphics();
				pain(g2);
			} finally {
				g2.dispose();
			}
			bs.show();
		} while (bs.contentsLost());
	}

	public void pain(Graphics2D g) {
		MapConstructor.drawPole(g);
		for (Hole hole : holes) {
			hole.paint(g);
		}

		for (int i = minors.size() - 1; i >= 0; i--) {
			if (i == minors.size() - 1) {
				if (creater != null)
					if (MapConstructor.sumIndex(minors.get(i)) <= MapConstructor.sumIndex(creater)) {
						creater.paint(g);
					}

				if (MapConstructor.sumIndex(minors.get(i)) <= MapConstructor.sumIndex(player)) {
					player.paint(g);
				}

			}
			minors.get(i).paint(g);
			if (i == 0) {
				if (creater != null)
					if (MapConstructor.sumIndex(minors.get(i)) >= MapConstructor.sumIndex(creater)) {
						creater.paint(g);
					}

				if (MapConstructor.sumIndex(minors.get(i)) >= MapConstructor.sumIndex(player)) {
					player.paint(g);
				}
				continue;
			}

			if (MapConstructor.sumIndex(minors.get(i)) >= MapConstructor.sumIndex(player)
					&& MapConstructor.sumIndex(minors.get(i - 1)) < MapConstructor.sumIndex(player)) {
				player.paint(g);
			}
			if (creater != null) {
				if (MapConstructor.sumIndex(minors.get(i)) >= MapConstructor.sumIndex(creater)
						&& MapConstructor.sumIndex(minors.get(i - 1)) < MapConstructor.sumIndex(creater)) {
					creater.paint(g);
				}
			}
		}
		if (minors.size() == 0) {
			if (creater != null) {
				creater.paint(g);
			}
			player.paint(g);

		}

		g.setFont(new Font("sherif", Font.BOLD, 30));
		g.drawString("$ " + MapConstructor.convertMoney(player.getMoney()), 30, 80);
		if (creater != null) {
			creater.paintCost(g, 70, 120);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
			if (creater != null) {
				creater.moveUP();
			} else {
				player.moveUP();
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (creater != null) {
				creater.moveDown();
			} else {
				player.moveDown();
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (creater != null) {
				creater.moveLeft();
			} else {
				player.moveLeft();
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (creater != null) {
				creater.moveRight();
			} else {
				player.moveRight();
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (creater != null) {
				creater.create();
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_M) {
			if (creater != null) {
				creater = null;
				return;
			}
			UpdateMenu updateMenu = new UpdateMenu(
					this.getLocationOnScreen().x + this.getWidth() / 2 - UpdateMenu.width / 2,
					this.getLocationOnScreen().y + this.getHeight() / 2 - UpdateMenu.height / 2);
			setFocusable(false);
			updateMenu.setVisible(true);

		}

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if (creater != null) {
				creater = null;
			} else {
				int a = JOptionPane.showConfirmDialog(null, "Do you really want to leave?", "Exit?",
						JOptionPane.YES_NO_OPTION);

				if (a == JOptionPane.YES_OPTION) {
					exit();
				}

			}

		}

	}

	public void exit() {
		MapConstructor.save();
		System.exit(0);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		while (true) {
			if (!stop) {
				paint();
				if (mapType == MapType.ICE && player.getMoney() >= 1024 * 1024) {
					mapType = MapType.MONEY;
					GoMoney frame = new GoMoney(this.getLocationOnScreen().x + this.getWidth() / 2 - GoMoney.width / 2,
							this.getLocationOnScreen().y + this.getHeight() / 2 - GoMoney.height / 2);
					frame.setVisible(true);
				}
				if (mapType == MapType.MONEY && player.getMoney() >= 1000 * 1024 * 1024) {
					player.setMoney(1000 * 1024 * 1024);
					paint();
					ChooseMenu menu=new ChooseMenu(this.getLocationOnScreen().x + this.getWidth() / 2 - ChooseMenu.width / 2,
							this.getLocationOnScreen().y + this.getHeight() / 2 - ChooseMenu.height / 2);
					menu.setVisible(true);
					stop=true;
				}
				if (mapType == MapType.VOLCAN &&player.getMoney()>=1023*1024*1024) {
					player.setMoney(1023 * 1024 * 1024);
					paint();
					OverHeat dead=new OverHeat(this.getLocationOnScreen().x + this.getWidth() / 2 - OverHeat.width / 2,
							this.getLocationOnScreen().y + this.getHeight() / 2 - OverHeat.height / 2);
					dead.setVisible(true);
					stop=true;
				}
				if (mapType==MapType.CRISTAL&&player.getMoney()>=1024*1024*1024) {
					Win dead=new Win(this.getLocationOnScreen().x + this.getWidth() / 2 - Win.width / 2,
							this.getLocationOnScreen().y + this.getHeight() / 2 - Win.height / 2);
					dead.setVisible(true);
					stop=true;
				}
			}
		}
	}

	public static List<Hole> getHoles() {
		return holes;
	}

	public static List<Minor> getMinors() {
		return minors;
	}

}
