package guiView;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameView {
	
	//default size of this game's frame
	private final Dimension frameDim;
	
	private JFrame gameFrame;
	private JPanel gameBoradPanel;

	public GameView() {
		
		// set up basic components
		gameFrame = new JFrame("Herding Cats!!!!!!");
		gameBoradPanel = new JPanel();
		gameFrame.setContentPane(gameBoradPanel);
		
		// set screen size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frameDim = new Dimension(screenSize.width/2, screenSize.height/2);
		gameFrame.setSize(frameDim);
		
		
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);
	}
	
}
