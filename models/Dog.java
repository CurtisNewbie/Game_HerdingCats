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
     * 
     * @param square the square that the dog is on.
     * @param dogX   the x coordinate of the dog.
     * @param dogY   the y coordinate of the dog.
     */
    public Dog(int square, int dogX, int dogY) {
	this.squareDog = square;
	this.dogPositionX = dogX;
	this.dogPositionY = dogY;
    }

    /**
     * move the dog to a new square.
     * 
     * @param newSquareDog the new square of the dog.
     */
    public void setSquare(int newSquareDog) {
	this.squareDog = newSquareDog;
    }

    /**
     * Return the square that the dog is on.
     * 
     * @return
     */
    public int getSquareDog() {
	return squareDog;
    }

    /**
     * get dogPositionX
     * @return x coordinate of the dog.
     */
    public int getDogPositionX() {
	return dogPositionX;
    }

    /**
     * get dogPositionY
     * @return y coordinate of the dog.
     */
    public int getDogPositionY() {
	return dogPositionY;
    }

    /**
     * set the positions of the dog.
     * @param newPositionX new x coordinate of the dog.
     * @param newPositionY new y coordinate of the dog.
     */
    public void setDogPositions(int newPositionX, int newPositionY) {
	dogPositionX = newPositionX;
	dogPositionY = newPositionY;
    }

}// Class - Dog
