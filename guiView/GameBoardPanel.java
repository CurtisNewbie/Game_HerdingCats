package guiView;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.WindowListener;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This class is a JPanel that represents the game board where cat and dog
 * objects will be moving on it.
 * 
 * @author Curtis
 *
 */
public class GameBoardPanel extends JPanel {

    /**
     * 
     */
    private BufferedImage imageDog;
    private BufferedImage imageCat;

    /*
     * These constants will determine how big the 'dog' shape is.
     */
    public static final int DOG_WIDTH = 50;
    public static final int DOG_HEIGHT = 50;

    /*
     * These constants will determine how big the 'cat' shape is.
     */
    public static final int CAT_WIDTH = 50;
    public static final int CAT_HEIGHT = 50;

    /** A bunch of cats */
    private ArrayList<double[]> cats;

    /** Only has one dog */
    private int dogX;
    private int dogY;

    /**
     * Constructor
     * 
     * @param cats an ArrayList of double[] which represents the x coordinate and y
     *             coordinate for all the cats. Within the double[], the [0] is the
     *             x coordinate and the [1] is the y coordinate.
     * @param dogX x coordinate of the dog.
     * @param dogY y coordinate of the dog.
     */
    public GameBoardPanel(ArrayList<double[]> cats, int dogX, int dogY) {
	this.cats = cats;
	this.dogX = dogX;
	this.dogY = dogY;

	try {
	    imageDog = ImageIO.read(new File("doggy.png"));
	    imageCat = ImageIO.read(new File("Cat.png"));
	} catch (Exception e) {
	    JOptionPane.showMessageDialog(null,
		    "Failed to read images for the game, please validate the completeness of the file.",
		    "Images Missing", JOptionPane.WARNING_MESSAGE);
	    System.exit(0);
	}
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

////    /**
////     * This method generates a list of x coordinates of dog based on the given x
////     * coordinate. It should be used in conjunction with the drawDogShapeY method.
////     * 
////     * @param x
////     */
////    private double[] generateDogShapeXList(double x) {
////	double[] newDogShapeX = { x, x, x + DOG_WIDTH, x + DOG_WIDTH, x };
////	return newDogShapeX;
////    }
//
//    /**
//     * This method generates a list of y coordinates of dog based on the given y
//     * coordinate. It should be used in conjunction with the drawDogShapeX method.
//     * 
//     * @param y
//     */
//    private double[] generateDogShapeYList(double y) {
//	double[] newDogShapeY = { y, y + DOG_HEIGHT, y + (DOG_HEIGHT / 3) * 2, y + DOG_HEIGHT / 3, y };
//	return newDogShapeY;
//    }
//
//    private double[] generateCatShapeXList(double eachCat) {
//	double[] newCatShapeX = { eachCat + CAT_WIDTH, eachCat + (CAT_WIDTH / 2), eachCat, eachCat, eachCat, eachCat + (CAT_WIDTH / 2), eachCat + CAT_WIDTH };
//	return newCatShapeX;
//    }
//
//    private double[] generateCatShapeYList(double y) {
//	double[] newCatShapeY = { y - (CAT_HEIGHT / 2), y - (CAT_HEIGHT / 2), y - (CAT_HEIGHT / 4), y,
//		y + (CAT_HEIGHT / 4), y + (CAT_HEIGHT / 2), y + (CAT_HEIGHT / 2) };
//	return newCatShapeY;
//    }

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

//    /**
//     * Draw the dog on the game board panel. This method uses the
//     * generateDogShapeXList and the generateDogShapeYList method to generate the x
//     * and y coordinates of the dog shape, it then uses the GeneralPath object to
//     * draw the dog at the right position.
//     * 
//     * @param g2 Graphics2D of the GameBoardPanel object
//     */
//    private void drawDog(Graphics2D g2) {
//	double[] xCoor = this.generateDogShapeXList(dogX);
//	double[] yCoor = this.generateDogShapeYList(dogY);
//	GeneralPath gp = new GeneralPath(GeneralPath.WIND_EVEN_ODD, xCoor.length); // initial capacity = 5
//
//	gp.moveTo(xCoor[0], yCoor[0]);
//	for (int i = 1; i < xCoor.length; i++) {
//	    gp.lineTo(xCoor[i], yCoor[i]);
//	}
//	g2.draw(gp);
//    }

    private void drawDog(Graphics2D g2) {
	g2.drawImage(imageDog, dogX - DOG_WIDTH / 2, dogY - DOG_HEIGHT / 2, DOG_WIDTH, DOG_HEIGHT, null);

    }

//    /**
//     * Draw the cat on the game board panel. This method uses the
//     * generateDogShapeXList and the generateDogShapeYList method to generate the x
//     * and y coordinates of the cat shape, it then uses the GeneralPath object to
//     * draw the dog at the right position.
//     * 
//     * @param g2 Graphics2D of the GameBoardPanel object
//     */
//    private void drawCat(Graphics2D g2) {
//	for (double[] eachCat : cats) {
//	    double[] xCoor = this.generateCatShapeXList(eachCat[0]);
//	    double[] yCoor = this.generateCatShapeYList(eachCat[1]);
//	    GeneralPath gp = new GeneralPath(GeneralPath.WIND_EVEN_ODD, xCoor.length); // initial capacity = 5
//
//	    gp.moveTo(xCoor[0], yCoor[0]);
//	    for (int i = 1; i < xCoor.length; i++) {
//		gp.lineTo(xCoor[i], yCoor[i]);
//	    }
//	    g2.draw(gp);
//	}
//    }

    private void drawCat(Graphics2D g2) {
	for(double[] c : cats) {
	    g2.drawImage(imageCat, (int) c[0] - CAT_WIDTH/ 2, (int) c[1] - CAT_HEIGHT / 2, CAT_WIDTH, CAT_HEIGHT, null);
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
     * @param cats an ArrayList of double[] which represents the x coordinate and y
     *             coordinate for all the cats. Within the double[], the [0] is the
     *             x coordinate and the [1] is the y coordinate.
     */
    public void updateCatPositions(ArrayList<double[]> cats) {
	this.cats = cats;
    }

}
