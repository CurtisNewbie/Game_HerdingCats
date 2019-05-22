package guiView;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * This class is a JPanel that represents the game board where cat and dog
 * objects will be moving on it.
 * 
 * @author Curtis
 *
 */
public class GameBoardPanel extends JPanel {

    /*
     * These constants will determine how big the 'dog' shape is.
     */
    public static final int DOG_WIDTH = 15;
    public static final int DOG_HEIGHT = 30;

    /*
     * These constants will determine how big the 'cat' shape is.
     */
    public static final int CAT_WIDTH = 25;
    public static final int CAT_HEIGHT = 50;

    /** A bunch of cats */
    private ArrayList<int[]> cats;

    /** Only has one dog */
    private int dogX;
    private int dogY;

    public GameBoardPanel(ArrayList<int[]> cats, int dogX, int dogY) {
	this.cats = cats;
	this.dogX = dogX;
	this.dogY = dogY;
    }

    // For testing the game borad looking only
    public GameBoardPanel() {
	// TODO Auto-generated constructor stub
	this.cats = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {

	// TODO Auto-generated method stub
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D) g;

	// draw the board lines first
	this.drawGrid(g2);

	// draw the one dog
	this.drawDog(g2);

	// draw all the cats
	this.drawCat(g2);
    }

    /**
     * This method generates a list of x coordinates of dog based on the given x
     * coordinate. It should be used in conjunction with the drawDogShapeY method.
     * 
     * @param x
     */
    private int[] generateDogShapeXList(int x) {
	int[] newDogShapeX = { x, x, x + DOG_WIDTH, x + DOG_WIDTH, x };
	return newDogShapeX;
    }

    /**
     * This method generates a list of y coordinates of dog based on the given y
     * coordinate. It should be used in conjunction with the drawDogShapeX method.
     * 
     * @param y
     */
    private int[] generateDogShapeYList(int y) {
	int[] newDogShapeY = { y, y + DOG_HEIGHT, y + (DOG_HEIGHT / 3) * 2, y + DOG_HEIGHT / 3, y };
	return newDogShapeY;
    }

    private int[] generateCatShapeXList(int x) {
	int[] newCatShapeX = { x + CAT_WIDTH, x + (CAT_WIDTH / 2), x, x, x, x + (CAT_WIDTH / 2), x + CAT_WIDTH };
	return newCatShapeX;
    }

    private int[] generateCatShapeYList(int y) {
	int[] newCatShapeY = { y - (CAT_HEIGHT / 2), y - (CAT_HEIGHT / 2), y - (CAT_HEIGHT / 4), y,
		y + (CAT_HEIGHT / 4), y + (CAT_HEIGHT / 2), y + (CAT_HEIGHT / 2) };
	return newCatShapeY;
    }

    /**
     * This method draw the grid of the game board panel.
     * 
     * @param g2 Graphics2D of the GameBoardPanel object
     */
    private void drawGrid(Graphics2D g2) {
	Dimension frameSize = this.getSize();
	g2.drawLine(0, frameSize.height / 3, frameSize.width, frameSize.height / 3);
	g2.drawLine(0, (frameSize.height / 3) * 2, frameSize.width, (frameSize.height / 3) * 2);
	g2.drawLine(frameSize.width / 3, 0, frameSize.width / 3, frameSize.height);
	g2.drawLine((frameSize.width / 3) * 2, 0, (frameSize.width / 3) * 2, frameSize.height);
    }

    /**
     * Draw the dog on the game board panel. This method uses the
     * generateDogShapeXList and the generateDogShapeYList method to generate the x
     * and y coordinates of the dog shape, it then uses the GeneralPath object to
     * draw the dog at the right position.
     * 
     * @param g2 Graphics2D of the GameBoardPanel object
     */
    private void drawDog(Graphics2D g2) {
	int[] xCoor = this.generateDogShapeXList(dogX);
	int[] yCoor = this.generateDogShapeYList(dogY);
	GeneralPath gp = new GeneralPath(GeneralPath.WIND_EVEN_ODD, xCoor.length); // initial capacity = 5

	gp.moveTo(xCoor[0], yCoor[0]);
	for (int i = 1; i < xCoor.length; i++) {
	    gp.lineTo(xCoor[i], yCoor[i]);
	}
	g2.draw(gp);
    }

    /**
     * Draw the cat on the game board panel. This method uses the
     * generateDogShapeXList and the generateDogShapeYList method to generate the x
     * and y coordinates of the cat shape, it then uses the GeneralPath object to
     * draw the dog at the right position.
     * 
     * @param g2 Graphics2D of the GameBoardPanel object
     */
    private void drawCat(Graphics2D g2) {
	for (int[] eachCat : cats) {
	    int[] xCoor = this.generateCatShapeXList(eachCat[0]);
	    int[] yCoor = this.generateCatShapeYList(eachCat[1]);
	    GeneralPath gp = new GeneralPath(GeneralPath.WIND_EVEN_ODD, xCoor.length); // initial capacity = 5

	    gp.moveTo(xCoor[0], yCoor[0]);
	    for (int i = 1; i < xCoor.length; i++) {
		gp.lineTo(xCoor[i], yCoor[i]);
	    }
	    g2.draw(gp);
	}
    }

    /**
     * set the x coordinate of the dog
     * 
     * @param x x coordinate of the dog
     */
    public void setDogX(int x) {
	this.dogX = x;
    }

    /**
     * set the y coordinate of the dog
     * 
     * @param y y coordinate of the dog
     */
    public void setDogY(int y) {
	this.dogY = y;
    }

    /**
     * update the ArrayList of cats' positions.
     * 
     * @param cats an ArrayList of int[] which represents the x coordinate and y
     *             coordinate for all the cats. Within the int[], the [0] is the x
     *             coordinate and the [1] is the y coordinate.
     */
    public void updateCatPositions(ArrayList<int[]> cats) {
	this.cats = cats;
    }

}
