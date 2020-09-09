import javax.swing.JFrame;

/* Class that draws play space, player, and eggs into pre-defined window. 
 * Sets up game defaults like player, board size, and scheduler.
 * Sets up defaults of game window.
 */

public class GameBoard {
	JFrame frame = new JFrame("Snake");
	int height = 800;
	int width = 800;

	public void createGameWindow() {
		Egg egg = new Egg();
		Player player = new Player(egg);
		Board board = new Board(player, egg);
		
		SnakeScheduler scheduler = new SnakeScheduler(board, player, egg);
		player.setScheduler(scheduler);
		scheduler.start();
		
		board.add(player);
		frame.add(board);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		frame.pack();
		frame.setVisible(true);	
	}

}
