import java.util.Timer;
import java.util.TimerTask;

public class SnakeScheduler extends TimerTask implements Cloneable {
	Board board;
	Player player;
	Egg egg;
	Timer timer;
	boolean running;
	
	public SnakeScheduler(Board board, Player player, Egg egg) {
		this.board = board;
		this.player = player;
		this.egg = egg;
		this.running = false;
	}

	@Override
	public void run() {
		player.moveSnake();
		player.eatEgg();
		player.checkDeath();
		board.repaint();
	}
	
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
	
	public void stop() {
		board.drawPauseWindow();
		timer.cancel();
		running = false;
	}
	
	public boolean getRunning() {
		return running;
	}
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
