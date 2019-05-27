package models;
/**
 * @author Curtis (Yongjie Zhuang)
 * Date of submission: 2018/11/11
 */

import sheffield.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import guiView.GameBoardPanel;

public class HerdingCats {

    /*
     * The grid is a square, so it only needs to define the width or height. The
     * SIZE here refers to the width/height. It determines the size of the grid. In
     * this game the grid is 3*3.
     */
    public static final int GRID_SIZE = 450;
    public static final int SQUARE_SIZE = GRID_SIZE / 3;

    // The largest or smallest number can be entered.
    private static final int LARGEST_NUM_ENTER = 9;
    private static final int SMALLEST_NUM_ENTER = 1;

    /*
     * These are the center positions of each square. These are for the dog, because
     * the dog only appears at the center of the square.
     */
    private static final int CENTER_SQUARE1_X = (GRID_SIZE / 6) * 1;
    private static final int CENTER_SQUARE1_Y = (GRID_SIZE / 6) * 1;
    private static final int CENTER_SQUARE2_X = (GRID_SIZE / 6) * 3;
    private static final int CENTER_SQUARE2_Y = (GRID_SIZE / 6) * 1;
    private static final int CENTER_SQUARE3_X = (GRID_SIZE / 6) * 5;
    private static final int CENTER_SQUARE3_Y = (GRID_SIZE / 6) * 1;
    private static final int CENTER_SQUARE4_X = (GRID_SIZE / 6) * 1;
    private static final int CENTER_SQUARE4_Y = (GRID_SIZE / 6) * 3;
    private static final int CENTER_SQUARE5_X = (GRID_SIZE / 6) * 3;
    private static final int CENTER_SQUARE5_Y = (GRID_SIZE / 6) * 3;
    private static final int CENTER_SQUARE6_X = (GRID_SIZE / 6) * 5;
    private static final int CENTER_SQUARE6_Y = (GRID_SIZE / 6) * 3;
    private static final int CENTER_SQUARE7_X = (GRID_SIZE / 6) * 1;
    private static final int CENTER_SQUARE7_Y = (GRID_SIZE / 6) * 5;
    private static final int CENTER_SQUARE8_X = (GRID_SIZE / 6) * 3;
    private static final int CENTER_SQUARE8_Y = (GRID_SIZE / 6) * 5;
    private static final int CENTER_SQUARE9_X = (GRID_SIZE / 6) * 5;
    private static final int CENTER_SQUARE9_Y = (GRID_SIZE / 6) * 5;

    // It refers to the maximum number of the rule. (1-6)
    private static final int MAX_RULE = 6;

    /*
     * This is a game called HerdingCats. Players will control the dog to move in
     * order to herd the cats into a square. The cats move in response to the dog's
     * movement according to a rule. Each cat will be given a rule ranging from 1 to
     * 6. For 1-3, cats directly move away from the dog. For 4-5, cats move beside
     * the dog. For 6, cats move to the same square as the dog. For cats that are
     * already on the same square as the dog, they move randomly excluding the
     * squares that they are currently on. Dog is only allowed to move to the
     * adjacent squares. When all the cats are on the same square, players win.
     * Players must enter the number ranging from 1 to 9, or else the game stops and
     * displays "Existing".
     */
    public static void main(String[] args) {

	// It's used to check whether the game wins. By default, it's false.
	// When win == true, players win and program terminates.
	boolean win = false;

	// This is used to check whether the square entered for dog is illegitimate. If
	// so, the program exists.
	boolean illegalDogPosition = false;

//		// Create an instance of class EasyGraphics to display the game.
//		EasyGraphics grid = new EasyGraphics(GRID_SIZE, GRID_SIZE);

	// Input - keyboard
	Scanner keyboard = new Scanner(System.in);

	/*
	 * Constructor - Cat (int positionX, int positionY, int rule)
	 * 
	 * Create 10 instances of class Cat and create new objects of Random class to
	 * randomly generate numbers for cats' positions and cats' rules. The rules
	 * determine how the cats move, ranging from 1 to 6. To ensure the cats are
	 * created within the grid, the random numbers for positionX is ranging from
	 * CAT_WIDTH to GRID_SIZE, and for positionY, it ranges from CAT_HEIGHT to
	 * GRID_SIZE.
	 */
	Cat cat1 = new Cat(Cat.CAT_WIDTH + new Random().nextInt(GRID_SIZE - Cat.CAT_WIDTH + 1),
		Cat.CAT_HEIGHT + new Random().nextInt(GRID_SIZE - Cat.CAT_HEIGHT + 1),
		1 + new Random().nextInt(MAX_RULE));
	Cat cat2 = new Cat(Cat.CAT_WIDTH + new Random().nextInt(GRID_SIZE - Cat.CAT_WIDTH + 1),
		Cat.CAT_HEIGHT + new Random().nextInt(GRID_SIZE - Cat.CAT_HEIGHT), 1 + new Random().nextInt(MAX_RULE));
	Cat cat3 = new Cat(Cat.CAT_WIDTH + new Random().nextInt(GRID_SIZE - Cat.CAT_WIDTH + 1),
		Cat.CAT_HEIGHT + new Random().nextInt(GRID_SIZE - Cat.CAT_HEIGHT), 1 + new Random().nextInt(MAX_RULE));
	Cat cat4 = new Cat(Cat.CAT_WIDTH + new Random().nextInt(GRID_SIZE - Cat.CAT_WIDTH + 1),
		Cat.CAT_HEIGHT + new Random().nextInt(GRID_SIZE - Cat.CAT_HEIGHT), 1 + new Random().nextInt(MAX_RULE));
	Cat cat5 = new Cat(Cat.CAT_WIDTH + new Random().nextInt(GRID_SIZE - Cat.CAT_WIDTH + 1),
		Cat.CAT_HEIGHT + new Random().nextInt(GRID_SIZE - Cat.CAT_HEIGHT), 1 + new Random().nextInt(MAX_RULE));
	Cat cat6 = new Cat(Cat.CAT_WIDTH + new Random().nextInt(GRID_SIZE - Cat.CAT_WIDTH + 1),
		Cat.CAT_HEIGHT + new Random().nextInt(GRID_SIZE - Cat.CAT_HEIGHT), 1 + new Random().nextInt(MAX_RULE));
	Cat cat7 = new Cat(Cat.CAT_WIDTH + new Random().nextInt(GRID_SIZE - Cat.CAT_WIDTH + 1),
		Cat.CAT_HEIGHT + new Random().nextInt(GRID_SIZE - Cat.CAT_HEIGHT), 1 + new Random().nextInt(MAX_RULE));
	Cat cat8 = new Cat(Cat.CAT_WIDTH + new Random().nextInt(GRID_SIZE - Cat.CAT_WIDTH + 1),
		Cat.CAT_HEIGHT + new Random().nextInt(GRID_SIZE - Cat.CAT_HEIGHT), 1 + new Random().nextInt(MAX_RULE));
	Cat cat9 = new Cat(Cat.CAT_WIDTH + new Random().nextInt(GRID_SIZE - Cat.CAT_WIDTH + 1),
		Cat.CAT_HEIGHT + new Random().nextInt(GRID_SIZE - Cat.CAT_HEIGHT), 1 + new Random().nextInt(MAX_RULE));
	Cat cat10 = new Cat(Cat.CAT_WIDTH + new Random().nextInt(GRID_SIZE - Cat.CAT_WIDTH + 1),
		Cat.CAT_HEIGHT + new Random().nextInt(GRID_SIZE - Cat.CAT_HEIGHT), 1 + new Random().nextInt(MAX_RULE));

	// Draw the cats based on their random positions.
	
	
	// create an instance of Dog
	Dog dog = new Dog();

	System.out.println(
		"Please enter number to control the dog:(Number must be: 1-9)\nRemember dog can only move to adjacent sqaures.");
	System.out.println("When all the cats are herded into the same square you win.\n");

	// It's a loop that checks if the game wins and if the square number entered for
	// dog is illegitimate.
	// As long as one of them (the boolean variables) becomes true, while loop won't
	// be executed.
	while (!win && !illegalDogPosition) {

	    // Check which square they are on.
	    System.out.print("Cat1: ");
	    cat1.checkSquare(SQUARE_SIZE);
	    System.out.print("Cat2: ");
	    cat2.checkSquare(SQUARE_SIZE);
	    System.out.print("Cat3: ");
	    cat3.checkSquare(SQUARE_SIZE);
	    System.out.print("Cat4: ");
	    cat4.checkSquare(SQUARE_SIZE);
	    System.out.print("Cat5: ");
	    cat5.checkSquare(SQUARE_SIZE);
	    System.out.print("Cat6: ");
	    cat6.checkSquare(SQUARE_SIZE);
	    System.out.print("Cat7: ");
	    cat7.checkSquare(SQUARE_SIZE);
	    System.out.print("Cat8: ");
	    cat8.checkSquare(SQUARE_SIZE);
	    System.out.print("Cat9: ");
	    cat9.checkSquare(SQUARE_SIZE);
	    System.out.print("Cat10: ");
	    cat10.checkSquare(SQUARE_SIZE);

	    /*
	     * This moves the dog to the square entered. The move() method in Dog class
	     * returns false when the number entered is ranging from 1 to 9, and is also not
	     * the adjacent squares of the current square. If the number entered >=1 and <=9
	     * and is a adjacent square. The dog is successfully moved. If this method
	     * returns false, this 'while' loop will run again until the number entered is a
	     * legitimate and adjacent square.
	     * 
	     * If the number entered is less than 1 and larger than 9, the dog moves to the
	     * illegitimate position. Then the loop below will skip the code; change the
	     * boolean variable - 'illegalDogPosition' for the while loop above; displays
	     * "Existing!"; and 'terminates' the program.
	     */
	    while (!dog.move(keyboard.readInt())) {

	    }

	    /*
	     * If the number entered for dog's square is larger than LARGEST_NUM_ENTER(9)
	     * and less than SMALLEST_NUM_ENTER(0), the program displays "Existing" and
	     * stops reacting. Probably because the object of the EasyGraphic is not closed,
	     * the console is not truly terminated, though the console has stopped
	     * responding. ()
	     */
	    if (dog.getSquareDog() < SMALLEST_NUM_ENTER || dog.getSquareDog() > LARGEST_NUM_ENTER) {
		System.out.println("Existing!");
		illegalDogPosition = true;
	    } else {

		/*
		 * Set dog positionX and positionY based on its square. Further, the use of
		 * methods - getCenterPositionX() and getCenterPositionY() is because the dog
		 * only appears at the center of the square.
		 */
		dog.setDogPositions(getCenterPositionsX(dog.getSquareDog()), getCenterPositionsY(dog.getSquareDog()));

		// Draw the dog on the object - grid.
		dog.drawDog(grid);

		// The cats move in response to the dog's position.
		cat1.move(dog.getSquareDog(), dog.getDogPositionX(), dog.getDogPositionY());
		cat2.move(dog.getSquareDog(), dog.getDogPositionX(), dog.getDogPositionY());
		cat3.move(dog.getSquareDog(), dog.getDogPositionX(), dog.getDogPositionY());
		cat4.move(dog.getSquareDog(), dog.getDogPositionX(), dog.getDogPositionY());
		cat5.move(dog.getSquareDog(), dog.getDogPositionX(), dog.getDogPositionY());
		cat6.move(dog.getSquareDog(), dog.getDogPositionX(), dog.getDogPositionY());
		cat7.move(dog.getSquareDog(), dog.getDogPositionX(), dog.getDogPositionY());
		cat8.move(dog.getSquareDog(), dog.getDogPositionX(), dog.getDogPositionY());
		cat9.move(dog.getSquareDog(), dog.getDogPositionX(), dog.getDogPositionY());
		cat10.move(dog.getSquareDog(), dog.getDogPositionX(), dog.getDogPositionY());

		// Redraw the grid
		drawGrid(grid);

		// Redraw the cats based on the new positions.
		cat1.drawCat(grid);
		cat2.drawCat(grid);
		cat3.drawCat(grid);
		cat4.drawCat(grid);
		cat5.drawCat(grid);
		cat6.drawCat(grid);
		cat7.drawCat(grid);
		cat8.drawCat(grid);
		cat9.drawCat(grid);
		cat10.drawCat(grid);

		// Redraw the dog on the grid.
		dog.drawDog(grid);

		/*
		 * Check if all the cats are on the same square. This way to verify this may
		 * save some time, because as long as one of them is true, then the rest won't
		 * be calculated, the result will be true and the variable - win equals 'false'
		 * (the game is not finished).
		 */
		if (cat1.getSquareCat() != cat2.getSquareCat() || cat1.getSquareCat() != cat3.getSquareCat()
			|| cat1.getSquareCat() != cat4.getSquareCat() || cat1.getSquareCat() != cat5.getSquareCat()
			|| cat1.getSquareCat() != cat6.getSquareCat() || cat1.getSquareCat() != cat7.getSquareCat()
			|| cat1.getSquareCat() != cat8.getSquareCat() || cat1.getSquareCat() != cat9.getSquareCat()
			|| cat1.getSquareCat() != cat10.getSquareCat()) {
		    win = false;
		    // The game continues
		    System.out.println("Try again!\n");
		} else {
		    win = true;
		    // Player wins, the game exists.
		    System.out.println("You've won!");
		    System.out.println("Game existing...");
		}

	    } // End - else

	} // End - while (!win && !illegalDogPosition)

	System.out.println("Game existed. Please close the 'graphics window' to completely terminate the game.");
    }// End - main method

    // Method used to get the positionX of the center of the square
    public static int getCenterPositionsX(int square) {
	switch (square) {
	case 1:
	    return CENTER_SQUARE1_X;
	case 2:
	    return CENTER_SQUARE2_X;
	case 3:
	    return CENTER_SQUARE3_X;
	case 4:
	    return CENTER_SQUARE4_X;
	case 5:
	    return CENTER_SQUARE5_X;
	case 6:
	    return CENTER_SQUARE6_X;
	case 7:
	    return CENTER_SQUARE7_X;
	case 8:
	    return CENTER_SQUARE8_X;
	case 9:
	    return CENTER_SQUARE9_X;
	default:
	    return 0;
	// Because it has been verified in the main method that dog.getSqaureDog() can
	// only range from 1-9.
	// This won't be truly executed.
	}
    }// End - getCenterPositionsX

    // Method used to get the positionY of the center of the square
    public static int getCenterPositionsY(int square) {
	switch (square) {
	case 1:
	    return CENTER_SQUARE1_Y;
	case 2:
	    return CENTER_SQUARE2_Y;
	case 3:
	    return CENTER_SQUARE3_Y;
	case 4:
	    return CENTER_SQUARE4_Y;
	case 5:
	    return CENTER_SQUARE5_Y;
	case 6:
	    return CENTER_SQUARE6_Y;
	case 7:
	    return CENTER_SQUARE7_Y;
	case 8:
	    return CENTER_SQUARE8_Y;
	case 9:
	    return CENTER_SQUARE9_Y;
	default:
	    return 0;
	// Because it has been verified in the main method that dog.getSqaureDog() can
	// only range from 1-9.
	// This won't be truly executed.
	}
    }// End - getCenterPositionsY

}// Class - HerdingCats
