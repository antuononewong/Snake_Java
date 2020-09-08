import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Board extends JPanel {
	static int boardWidth = 720;
	static int boardHeight = 720;
	static int borderOffSet = 100;
	static int topBorderHeight = 80;
	static int leftBorderWidth = 20;
	static int rightBorderOffSet = 10;
	Player player;
	Egg egg;
	
	public Board(Player player, Egg egg) {
		this.setPreferredSize(new Dimension(boardWidth + (borderOffSet / 2) - rightBorderOffSet, boardHeight + borderOffSet));
		this.player = player;
		this.egg = egg;
		this.setLayout(new GridBagLayout());
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawUI(g);
		drawSnake(g);
		drawEgg(g);
	}
	
	public void drawUI(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(leftBorderWidth, topBorderHeight, boardWidth, boardHeight);
	}
	
	public void drawSnake(Graphics g) {
		ArrayList<Coord> pieces = player.getPieces();
		int size = player.getSizeOfPiece();
		for (int i = 0; i < pieces.size(); i++) {
			g.setColor(Color.green);
			Coord coord = pieces.get(i);
			g.fillRect(coord.getX(), coord.getY(), size, size);
		}
	}
	
	public void drawEgg(Graphics g) {
		Coord position = egg.getPosition();
		int size = player.getSizeOfPiece();
		
		g.setColor(Color.white);
		g.fillRect(position.getX(), position.getY(), size, size);
	}
	
	public void drawPauseWindow() {
		JPanel menu = new JPanel();
		JLabel menuTitle = new JLabel("Menu");
		JTextArea menuText = new JTextArea("W - Move up \nA - Move Left \nS - Move Down \nD - Move Right \n   Space - Pause/Start");
		
		menu.setLayout(new BoxLayout(menu, BoxLayout.PAGE_AXIS));
		menu.setPreferredSize(new Dimension(300, 205));
		menu.setBackground(new Color(255, 255, 255, 60));
		menu.setOpaque(true);
		
		menuText.setBackground(new Color(255, 255, 255, 60));
		menuText.setOpaque(false);
		menuText.setForeground(Color.white);
		menuText.setFont(new Font("Verdana", 1, 20));
		menuText.setBorder(BorderFactory.createEmptyBorder(0, 32, 0, 0));
		
		menuTitle.setBackground(new Color(255, 255, 255, 60));
		menuTitle.setOpaque(false);
		menuTitle.setForeground(Color.white);
		menuTitle.setFont(new Font("Verdana", 1, 30));
		menuTitle.setAlignmentX(CENTER_ALIGNMENT);
		menuTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		menu.add(menuTitle);
		menu.add(menuText);
		this.add(menu);
		this.revalidate();
		this.repaint();
	}
	
}
