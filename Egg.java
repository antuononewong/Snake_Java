import java.util.Random;

/* Handles game logic for egg piece that is "eaten" by
 * the player snake. Places the egg at random position 
 * on the game board but avoid placing on the 4 corner 
 * slots.
 */

public class Egg {
	// Egg properties
	int size = Player.getSizeOf();
	Coord position;
	Random random;

	// Board properties
	int width = Board.boardWidth;
	int height = Board.boardHeight;
	int xSlots = (width / size) - 1;
	int ySlots = (height / size) - 1;
	int leftBorderWidth = Board.leftBorderWidth;
	int topBorderHeight = Board.topBorderHeight;
	
	// Constructor
	public Egg () {
		this.random = new Random();
		this.place();
	}
	
	// Set (x, y) of Egg to randomly generated coordinate within
	// bounds of game board
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
	
	// Helper function to check validity of place() method
	public void printPosition() {
		System.out.println(position.getX() + " " + position.getY());
	}
	
	// Iterate over 4 corners of game board using (x, y) of egg piece.
	// Egg will not be positioned and place() method will be re-run if 
	// this function returns false.
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
