package components;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import GPU.Game3D;
import GPU.MapType;
import components.goldDiggers.GoldDiger;
import components.goldDiggers.Hole;
import components.goldDiggers.Minor;
import components.player.Player;

public class MapConstructor {

	private static Path savePath = Paths.get(System.getProperty("user.home") + File.separator + "AppData"
			+ File.separator + "roaming" + File.separator + ".coolgame");
	private static File save = new File(savePath.toString() + File.separator + "save.mvm");

	static String[][] pole = { { "", "", "", "", "", "", }, { "", "", "hole", "", "", "", },
			{ "", "hole", "", "", "", "", }, { "", "", "", "", "hole", "", }, { "", "", "", "", "hole", "", },
			{ "", "", "", "", "", "hole", } };

	public static int poleSize = pole.length;
	public static int sqrtSize = 40;
	public static int centerX = 400;
	public static int centerY = 600;

	public static void creatPole() {
		for (int i = 0; i < pole.length; i++) {
			for (int j = 0; j < pole[0].length; j++) {
				switch (pole[i][j]) {
				case "hole":
					Game3D.holes.add(new Hole(i, j));
					break;

				default:
					break;
				}
			}
		}

	}

	public static void loadSaved() {
		// TODO Auto-generated method stub
		try {
			Scanner sc = new Scanner(save);
			while (sc.hasNext()) {
				switch (sc.next()) {
				case "-player":
					Game3D.player = new Player(sc.nextInt(), sc.nextInt());
					Game3D.player.setMoney(sc.nextLong());
					break;
				case "-HOLE":
				case "-Hole":
				case "-hole":
					Hole h = new Hole(sc.nextInt(), sc.nextInt());
					Game3D.holes.add(h);
					break;
				case "-MINOR":
				case "-Minor":
				case "-minor":
					Game3D.minors.add(new Minor(sc.nextInt(), sc.nextInt(), sc.nextInt()));
					break;
				case "-ICE":
					Game3D.mapType = MapType.ICE;
					break;
				case "-VOLCAN":
					Game3D.mapType = MapType.VOLCAN;
					break;
				case "-MONEY":
					Game3D.mapType = MapType.MONEY;
					break;
				case "-CRISTAL":
					Game3D.mapType = MapType.CRISTAL;
					break;
					
				default:
					System.err.println("undefined operation");
					break;
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			creatPole();
		}
	}

	public static String convertMoney(long l) {
		double m = l;
		int stepen = 0;
		while (m >= 1024) {
			m /= 1024;
			stepen++;
		}
		String str = "";
		m = (int) (m * 100) / 100d;
		if (m % 1 == 0) {
			str += (int) (m) + "";
		} else {
			str += m + "";
		}
		if (stepen == 0) {
			return str;
		}
		if (stepen == 1) {
			return str + " *1024";
		}
		return str + "*1024^" + stepen;
	}

	public static void save() {
		try {
			if (!save.exists()) {
				Files.createDirectories(savePath);
				save.createNewFile();
			}
			PrintWriter in = new PrintWriter(save);
			in.println("-player " + Game3D.player.getI() + " " + Game3D.player.getJ() + " " + Game3D.player.getMoney());
			in.println("-" + Game3D.mapType);
			for (Hole goldDiger : Game3D.holes) {
				in.println("-" + "Hole" + " " + goldDiger.getI() + " " + goldDiger.getJ());
			}
			for (Minor goldDiger : Game3D.minors) {
				in.println(
						"-" + "Minor" + " " + goldDiger.getI() + " " + goldDiger.getJ() + " " + goldDiger.getLevel());
			}

			in.flush();
			in.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error occured during saving");
		}
	}

	public static int sumIndex(Components c) {

		return c.getI() + c.getJ();
	}

	public static void drawPole(Graphics2D g) {
		// fon
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1000, 1000);

		// Pole
		switch (Game3D.mapType) {
		case ICE:
				g.setColor(new Color(50, 50, 255));
				break;
		case VOLCAN:
				g.setColor(new Color(255, 50, 50));
				break;
		case MONEY:
				g.setColor(new Color(100, 200, 100));
				break;
		case CRISTAL:
				g.setColor(new Color(200, 0, 200));
				break;
		}

		int minus = 7;

		int[] x = { centerX - sqrtSize * poleSize, centerX - sqrtSize * poleSize, centerX,
				centerX + sqrtSize * poleSize, centerX + sqrtSize * poleSize, centerX,

		};
		int[] y = { centerY - sqrtSize / 2 * poleSize, centerY - sqrtSize / 2 * poleSize + sqrtSize - minus,
				centerY + sqrtSize, centerY - sqrtSize / 2 * poleSize + sqrtSize - minus,
				centerY - sqrtSize / 2 * poleSize, centerY, };
		g.fillPolygon(x, y, x.length);
		g.setColor(new Color(150, 150, 150));
		g.drawLine(centerX, centerY, centerX, centerY + sqrtSize);
		g.drawLine(centerX - sqrtSize * poleSize, centerY - sqrtSize / 2 * poleSize, centerX - sqrtSize * poleSize,
				centerY - sqrtSize / 2 * poleSize + sqrtSize - minus);
		g.drawLine(centerX + sqrtSize * poleSize, centerY - sqrtSize / 2 * poleSize, centerX + sqrtSize * poleSize,
				centerY - sqrtSize / 2 * poleSize + sqrtSize - minus);
		g.drawLine(centerX, centerY + sqrtSize, centerX - sqrtSize * poleSize,
				centerY - sqrtSize / 2 * poleSize + sqrtSize - minus);
		g.drawLine(centerX, centerY + sqrtSize, centerX + sqrtSize * poleSize,
				centerY - sqrtSize / 2 * poleSize + sqrtSize - minus);
		switch (Game3D.mapType) {
		case ICE:
				g.setColor(new Color(100, 200, 255));
				break;
		case VOLCAN:
				g.setColor(new Color(255, 50, 50));
				break;
		case MONEY:
				g.setColor(new Color(150, 250, 150));
				break;
		case CRISTAL:
				g.setColor(new Color(255, 50, 250));
				break;
		}
		int poleSize = MapConstructor.poleSize;
		int sqrtSize = MapConstructor.sqrtSize;
		int centerX = MapConstructor.centerX;
		int centerY = MapConstructor.centerY;
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

	public static boolean hasSaved() {
		// TODO Auto-generated method stub
		return save.exists();
	}

	public static void deleteSave() {
		// TODO Auto-generated method stub
		save.delete();
	}

}
