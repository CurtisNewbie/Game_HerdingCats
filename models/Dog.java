package models;

/**
 * Dog class that models every individual dog.
 * 
 * @author Curtis
 */
public class Dog {

    /**
     * The square that the dog is on.
     */
    private int square = 0;

    /**
     * Constructor for Dog class.
     * 
     * @param square the square that the dog is on.
     */
    public Dog(int square) {
	this.square = square;
    }

    /**
     * move the dog to a new square.
     * 
     * @param newSquareDog the new square of the dog.
     */
    public void setSquare(int newSquareDog) {
	this.square = newSquareDog;
    }

    /**
     * Return the square that the dog is on.
     * 
     * @return
     */
    public int getSquare() {
	return square;
    }

}// Class - Dog
