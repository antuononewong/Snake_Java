import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Player extends JPanel {
	int width = Board.boardWidth;
	int height = Board.boardHeight;
	int leftBorderWidth = Board.leftBorderWidth;
	int topBorderHeight = Board.topBorderHeight;
	int x = width / 2;
	int y = height / 2;
	static int size = 20;
	int initial = 0;
	int speed = 20;
	char direction = 'x';
	Egg egg;
	SnakeScheduler scheduler;
	ArrayList<Coord> pieces = new ArrayList<Coord>();
	
	public Player(Egg egg) {
		this.egg = egg;
		this.setPreferredSize(new Dimension(0, 0));
        setMovement();
        pieces.add(new Coord(x - (size * 2), y));
        pieces.add(new Coord(x - size, y));
        pieces.add(new Coord(x, y));
	}
	
	public void setMovement() {
		Action moveRight = new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                changeDirection('x', "right");
                System.out.println("Pressed D");
            }
        };
        Action moveLeft = new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                changeDirection('x', "left");
                System.out.println("Pressed A");
            }
        };
        Action moveUp = new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                changeDirection('y', "up");
                System.out.println("Pressed W");
            }
        };
        Action moveDown = new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
            	changeDirection('y', "down");
                System.out.println("Pressed S");
            }
        };
        Action pause = new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
            	boolean running = scheduler.getRunning();
            	if (!running) 
            		scheduler.start();
            	else 
            		scheduler.stop();
                System.out.println("Pressed Spacebar");
            }
        };
        
        InputMap inputMap = this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = this.getActionMap();
        
        inputMap.put(KeyStroke.getKeyStroke("D"), "moveRight");
        actionMap.put("moveRight", moveRight);
        inputMap.put(KeyStroke.getKeyStroke("A"), "moveLeft");
        actionMap.put("moveLeft", moveLeft);
        inputMap.put(KeyStroke.getKeyStroke("W"), "moveUp");
        actionMap.put("moveUp", moveUp);
        inputMap.put(KeyStroke.getKeyStroke("S"), "moveDown");
        actionMap.put("moveDown", moveDown);
        inputMap.put(KeyStroke.getKeyStroke("SPACE"), "pause");
        actionMap.put("pause", pause);
	}
	
	public void changeDirection(char c, String direction) { 
		if (direction.equals("up") && speed > 0 && this.direction == 'y') {} //do nothing
		else if (direction.equals("down") && speed < 0 && this.direction == 'y') {}
		else if (direction.equals("left") && speed > 0 && this.direction == 'x') {}
		else if (direction.equals("right") && speed < 0 && this.direction == 'x') {}
		
		else if (direction.equals("up") && speed > 0)
			speed *= -1;
		else if (direction.equals("down") && speed < 0)
			speed *= -1;
		else if (direction.equals("left") && speed > 0)
			speed *= -1;
		else if (direction.equals("right") && speed < 0)
			speed *= -1;

		switch (c) {
			case 'x':
				this.direction = 'x';
				return;
			case 'y':
				this.direction = 'y';
				return;
		}
	}
	
	public void moveSnake() {
		int headIndex = pieces.size() - 1;
		Coord head = pieces.get(headIndex);
		Coord current = new Coord (head.getX(), head.getY());
		
		for (int i = headIndex - 1; i > -1; i--) {
			Coord temp = pieces.get(i);
			Coord previous = new Coord(temp.getX(), temp.getY());
			pieces.get(i).setX(current.getX());
			pieces.get(i).setY(current.getY());
			current.setX(previous.getX());
			current.setY(previous.getY());
		}
		
		switch (direction) {
			case 'x':
				pieces.get(headIndex).setX(head.getX() + speed);
				return;
			case 'y':
				pieces.get(headIndex).setY(head.getY() + speed);
				return;
		}
	}
	
	public void eatEgg() {
		Coord position = egg.getPosition();
		Coord head = pieces.get(pieces.size() - 1);
		if (position.getX() == head.getX() && position.getY() == head.getY()) {
			if (direction == 'x') 
				pieces.add(new Coord(head.getX() +  speed, head.getY()));
			else if (direction == 'y')
				pieces.add(new Coord(head.getX(), head.getY() + speed));
			egg.place();
		}
	}
	
	public void checkDeath() {
		Coord head = pieces.get(pieces.size() - 1);
		int x = head.getX();
		int y = head.getY();
		
		if (x < leftBorderWidth || x > width) {
			scheduler.stop();
		}
		else if (y < topBorderHeight || y > height + topBorderHeight - size) {
			scheduler.stop();
		}
	}
	
	public void printPieces() {
		for (int i = 0; i < pieces.size(); i++) {
			Coord coord = pieces.get(i);
			System.out.println(coord.getX() + " " + coord.getY());
		}
	}
	
	public int getSizeOfPiece() {
		return size;
	}
	
	public ArrayList<Coord> getPieces() {
		return pieces;
	}
	
	static public int getSizeOf() {
		return size;
	}
	
	public void setScheduler(SnakeScheduler scheduler) {
		this.scheduler = scheduler;
	}
	
}