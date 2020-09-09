/* Simple class for storage of x, y coordinates, so that the coordinates
 * can be used for various game logic functions and a representation of position
 * of various game objects.
 */

public class Coord {
	int x;
	int y;
	
	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
}
