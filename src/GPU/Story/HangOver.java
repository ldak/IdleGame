package GPU.Story;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GPU.Game3D;
import GPU.MapType;
import components.MapConstructor;
import components.goldDiggers.Hole;
import components.player.Player;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class HangOver extends JFrame {

	private JPanel contentPane;
	public static int width=600;
	public static int height=500;
	private String message="With all of your money you bought a bunker and tons of vodka. "
			+ "But today when you go to get some more vodka you don't find any."
			+ "You go outside and see the Ice age has started again. "
			+ "You eat some snow and go to sleep because of the hangover."
			+ "But after 3 days you finally sober up and realise the Earth resourses have renewed "
			+ "and you can start digging again.";
	/**
	 * Create the frame.
	 */
	public HangOver(int x, int y) {
		
		setTitle("Hang Over");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(x, y, width, height);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Game3D.mapType=MapType.ICE;
				Game3D.player.setMoney(0);
				Game3D.minors=new ArrayList<>();
				Game3D.holes=new ArrayList<>();
				Random rnd=new Random();
				for (int i = MapConstructor.poleSize-1; i> MapConstructor.poleSize-6; i--) {
					Game3D.holes.add(new Hole(rnd.nextInt(MapConstructor.poleSize),i));
				}
				Game3D.player=new Player( rnd.nextInt(MapConstructor.poleSize),0);
				Game3D.stop=false;
				setVisible(false);
				dispose();
			}

		});

		JLabel lbl = new JLabel(convertToMultiline(message));
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl.setBounds(254, 13, 328, 439);
		contentPane.add(lbl);
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setForeground(Color.WHITE);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		drawPole(g);
		drawPlayer(g);

	}

	private void drawPlayer(Graphics g) {
		// TODO Auto-generated method stub
		int x = 140;
		int y = 370;
		int s = 50;

		g.setColor(new Color(0, 200, 0));

		g.fillPolygon(new int[] { x, x + s - s / 2, x + s - s / 2, x, x - s + s / 2, x - s + s / 2 },
				new int[] { y - s / 4, y - s / 2, y - s * 3 / 2, y - s * 2 + s / 4, y - s * 3 / 2, y - s / 2, y }, 6);
		g.setColor(new Color(0, 255, 0));
		g.drawLine(x, y - s / 4, x, y - s * 5 / 4);
		g.drawLine(x - s / 2, y - s * 3 / 2, x, y - s * 5 / 4);
		g.drawLine(x + s / 2, y - s * 3 / 2, x, y - s * 5 / 4);
		g.drawLine(x - s / 2, y - s * 3 / 2, x, y - s * 7 / 4);
		g.drawLine(x + s / 2, y - s * 3 / 2, x, y - s * 7 / 4);
	}

	private void drawPole(Graphics g) {
		int centerX = -60;
		int centerY = 470;
		int sqrtSize = 50;
		int poleSize = 6;

		// Pole
		g.setColor(new Color(50, 50, 255));

		int minus = 7;

		int[] x = { centerX - sqrtSize * poleSize, centerX - sqrtSize * poleSize, centerX,
				centerX + sqrtSize * poleSize, centerX + sqrtSize * poleSize, centerX,

		};
		int[] y = { centerY - sqrtSize / 2 * poleSize, centerY - sqrtSize / 2 * poleSize + sqrtSize - minus,
				centerY + sqrtSize, centerY - sqrtSize / 2 * poleSize + sqrtSize - minus,
				centerY - sqrtSize / 2 * poleSize, centerY, };
		g.fillPolygon(x, y, x.length);
		g.setColor(new Color(100, 0, 0));
		g.drawLine(centerX, centerY, centerX, centerY + sqrtSize);
		g.drawLine(centerX - sqrtSize * poleSize, centerY - sqrtSize / 2 * poleSize, centerX - sqrtSize * poleSize,
				centerY - sqrtSize / 2 * poleSize + sqrtSize - minus);
		g.drawLine(centerX + sqrtSize * poleSize, centerY - sqrtSize / 2 * poleSize, centerX + sqrtSize * poleSize,
				centerY - sqrtSize / 2 * poleSize + sqrtSize - minus);
		g.drawLine(centerX, centerY + sqrtSize, centerX - sqrtSize * poleSize,
				centerY - sqrtSize / 2 * poleSize + sqrtSize - minus);
		g.drawLine(centerX, centerY + sqrtSize, centerX + sqrtSize * poleSize,
				centerY - sqrtSize / 2 * poleSize + sqrtSize - minus);

		g.setColor(new Color(100, 200, 255));
		for (int i = 0; i <= poleSize; i++) {
			g.drawLine(centerX - sqrtSize * i, centerY - sqrtSize / 2 * i, centerX - sqrtSize * i + sqrtSize * poleSize,
					centerY - sqrtSize / 2 * i - sqrtSize / 2 * poleSize);
		}
		for (int i = 0; i <= poleSize; i++) {
			g.drawLine(centerX + sqrtSize * i, centerY - sqrtSize / 2 * i, centerX + sqrtSize * i - sqrtSize * poleSize,
					centerY - sqrtSize / 2 * i - sqrtSize / 2 * poleSize);
		}
		// steni //basi tupoto
		g.fillPolygon(new int[] { centerX, centerX, centerX + sqrtSize * poleSize, centerX + sqrtSize * poleSize },
				new int[] { centerY - poleSize * sqrtSize, centerY - poleSize * sqrtSize * 3 / 2,
						centerY - poleSize * sqrtSize, centerY - poleSize * sqrtSize / 2 },
				4);
		g.fillPolygon(new int[] { centerX, centerX, centerX - sqrtSize * poleSize, centerX - sqrtSize * poleSize },
				new int[] { centerY - poleSize * sqrtSize, centerY - poleSize * sqrtSize * 3 / 2,
						centerY - poleSize * sqrtSize, centerY - poleSize * sqrtSize / 2 },
				4);
		g.setColor(Color.BLACK);
		g.drawLine(centerX, centerY - poleSize * sqrtSize, centerX, centerY - poleSize * sqrtSize * 3 / 2);

	}
	
	private String convertToMultiline(String orig)
	{
	    return "<html>" + orig.replaceAll("\n", "<br>");
	}
}
