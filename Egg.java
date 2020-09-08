import java.util.Random;

public class Egg {
	int size = Player.getSizeOf();
	int width = Board.boardWidth;
	int height = Board.boardHeight;
	int xSlots = (width / size) - 1;
	int ySlots = (height / size) - 1;
	int leftBorderWidth = Board.leftBorderWidth;
	int topBorderHeight = Board.topBorderHeight;
	Coord position;
	Random random;
	
	//adjust spawns within bounds of board
	
	public Egg () {
		this.random = new Random();
		this.place();
	}
	
	public void place() {
		int x = random.nextInt(xSlots) * size + leftBorderWidth;
		int y = random.nextInt(ySlots) * size + topBorderHeight;
		
		boolean corner = checkCorners(x, y);
		if (!corner) {
			this.position = new Coord(x, y);
		}
		else
			this.place();
	}
	
	public Coord getPosition() {
		return position;
	}
	
	public void printPosition() {
		System.out.println(position.getX() + " " + position.getY());
	}
	
	private boolean checkCorners(int x, int y) {
		boolean corner = false;
		
		if (x == leftBorderWidth && y == topBorderHeight) 
			corner = true;
		else if (x == leftBorderWidth && y == height)
			corner = true;
		else if (x == width && y == topBorderHeight)
			corner = true;
		else if (x == width && y == height)
			corner = true;
		
		return corner;
	}
}
