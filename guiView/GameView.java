package guiView;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.KeyListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import models.Cat;

public class GameView {

    // default size of this game's frame
    private final Dimension frameDim;

    private JFrame gameFrame;
    private GameBoardPanel gameBoardPanel;

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

    /**
     * Set up the content pane.
     * 
     * @param cats An arraylist of int[]. Each array of int refers to a cat, wherein
     *             the array: [0] and [1] refer to the x and y coordinate of each
     *             cat.
     * @param dogX the x coordinate of the dog.
     * @param dogY the y coordinate of the dog.
     */
    public void setUpGameBoard(ArrayList<double[]> cats, int dogX, int dogY) {
	this.gameBoardPanel = new GameBoardPanel(cats, dogX, dogY);
	gameFrame.setContentPane(this.gameBoardPanel);
    }

    /**
     * Get the content pane.
     * 
     * @return gameBoardPanel
     */
    public GameBoardPanel getPanel() {
	return gameBoardPanel;
    }
    
    /**
     * Associate a component listener to the gameBoardPanel/ content pane.
     * @param wl a window listener
     */
    public void addResizingComponentListener(ComponentListener cl) {
	gameBoardPanel.addComponentListener(cl);;
    }
    
    /**
     * Associate a key listener to the gameBoardPanel/ content pane.
     * @param kl a key listener
     */
    public void addKeyboardControlListener(KeyListener kl) {
	gameFrame.addKeyListener(kl);
    }
    
    /**
     * Get the JFrame of this game.
     * @return A JFrame represents the 'window' of this game.
     */
    public JFrame getGameFrame() {
	return this.gameFrame;
    }

}
