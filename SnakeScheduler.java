import java.util.Timer;
import java.util.TimerTask;

/* Starts/pauses functions that need to be repeated constantly for
 * handling of game logic.
 */

public class SnakeScheduler extends TimerTask implements Cloneable {
	
	// Game objects
	Board board;
	Player player;
	Egg egg;
	Timer timer;
	
	// Whether scheduler is running or paused
	boolean running;
	
	// Constructor
	public SnakeScheduler(Board board, Player player, Egg egg) {
		this.board = board;
		this.player = player;
		this.egg = egg;
		this.running = false;
	}

	// Functions that are repeated on a timer
	@Override
	public void run() {
		player.moveSnake();
		player.eatEgg();
		player.checkDeath();
		board.repaint();
	}
	
	// Assign functions in run() to timer.
	public void start() {
		timer = new Timer();
		try {
			timer.schedule((TimerTask) this.clone(), 0, 200);
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("Error: Could not clone SnakeScheduler.");
		}
		running = true;
	}
	
	// Pauses the pre-defined TimerTask and brings up a pause menu
	public void stop() {
		board.drawPauseWindow();
		timer.cancel();
		running = false;
	}
	
	public boolean getRunning() {
		return running;
	}
	
	// Exception handler for when TimerTask isn't able to be created
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
