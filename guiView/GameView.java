package guiView;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import models.Cat;

public class GameView {

    // default size of this game's frame
    private final Dimension frameDim;

    private JFrame gameFrame;
    private JPanel gameBoardPanel;

    public GameView() {

	// set up basic components
	gameFrame = new JFrame("Herding Cats!!!!!!");

	// set screen size
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	frameDim = new Dimension(screenSize.width / 2, screenSize.height / 2);
	gameFrame.setSize(frameDim);

	gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	gameFrame.setVisible(true);
	gameFrame.repaint();
    }

    public Dimension getGameBoardSize() {
	return gameBoardPanel.getSize();
    }

    public void setUpGameBoard(ArrayList<int[]> cats, int dogX, int dogY) {
	this.gameBoardPanel = new GameBoardPanel(cats, dogX, dogY);
	gameFrame.setContentPane(this.gameBoardPanel);
    }

}
