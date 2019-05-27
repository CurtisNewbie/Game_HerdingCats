package models;

/**
 * Dog class that models every individual dog.
 * @author Curtis
 */
public class Dog {

    /**
     * The square that the dog is on.
     */
    private int squareDog = 0;

    /**
     * The x coordinate of the dog.
     */
    private int dogPositionX;
    
    /**
     * The y coordinate of the dog.
     */
    private int dogPositionY;
    
    /**
     * Constructor for Dog class.
     * @param square the square that the dog is on.
     * @param dogX the x coordinate of the dog.
     * @param dogY the y coordinate of the dog.
     */
    public Dog(int square, int dogX, int dogY) {
	this.squareDog = square;
	this.dogPositionX = dogX;
	this.dogPositionY = dogY;
    }

    /**
     * Move the dog to a new square. It also checks whether the new position is the
     * squares that are adjacent to current square, because the dog is only allowed
     * to move to the adjacent squares. The boolean value refers to whether the dog
     * has moved. This boolean variable is used in a while loop in the main method
     * to make sure that the dog is correctly moved to the adjacent squares.
     * 
     * If newSquareDog for the dog is less than 1 and larger than 9, it moves the
     * dog to this illegitimate square. In this case, the main method will directly
     * skip the rest of the code and display "existing!".
     * 
     * @param newSquareDog the new square of the dog.
     */
    public boolean move(int newSquareDog) {
	boolean moved;
	if (newSquareDog > 0 && newSquareDog < 10) {
	    if (squareDog == 0) {
		squareDog = newSquareDog;
		moved = true;
	    } else if (isAdjacentSquare(newSquareDog)) {
		squareDog = newSquareDog;
		moved = true;
	    } else {
		System.out.println("The dog can only move to the square adjacent!");
		moved = false;
	    }
	} else {
	    // Moves the dog to this illegitimate position.
	    System.out.println("It's an illegitimate position!");
	    moved = true;
	    squareDog = newSquareDog;
	}
	return moved;
    }// End - move()

    /**
     * Return the square that the dog is on.
     * @return
     */
    public int getSquareDog() {
	return squareDog;
    }

    // Return dogPositionX
    public int getDogPositionX() {
	return dogPositionX;
    }

    // Return dogPositionY
    public int getDogPositionY() {
	return dogPositionY;
    }

    public void setDogPositions(int newPositionX, int newPositionY) {
	dogPositionX = newPositionX;
	dogPositionY = newPositionY;
    }

}// Class - Dog
