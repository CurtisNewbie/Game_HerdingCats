/**
 * @author Curtis (Yongjie Zhuang)
 *  Date of submission: 2018/11/11
 */


import sheffield.EasyGraphics;

//Dog class that models every individual dog.
public class Dog {

	// The square that the dog is on.
	private int squareDog = 0;
	
	// It's exactly where the dog is at.
	private int dogPositionX;
	private int dogPositionY;
	
	/*
	 * These constants will determine how
	 * big the 'dog' symbol is. See method - drawDog()
	 */
	public static final int DOG_WIDTH = 5;
	public static final int DOG_HEIGHT = 15;

	/*
	 * Move the dog to a new square. It also checks whether the new position
	 * is the squares that are adjacent to current square, because the dog is only
	 * allowed to move to the adjacent squares. The boolean value refers to whether
	 * the dog has moved. This boolean variable is used in a while loop in the main 
	 * method to make sure that the dog is correctly moved to the adjacent squares.
	 * 
	 * If newSquareDog for the dog is less than 1 and larger than 9, it moves the dog 
	 * to this illegitimate square. In this case, the main method will directly skip the rest 
	 * of the code and display "existing!".
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

	// Return the square that the dog is on.
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
	
	// Draw the dog based on the dogPositionX and dogPositionY. DOG_WIDTH and DOG_HEIGHT determine its size.
	public void drawDog(EasyGraphics grid) {

		grid.moveTo(dogPositionX, dogPositionY);
		grid.lineTo(dogPositionX, dogPositionY - DOG_HEIGHT);
		grid.lineTo(dogPositionX + DOG_WIDTH, dogPositionY - (DOG_HEIGHT/3)*2);
		grid.lineTo(dogPositionX + DOG_WIDTH, dogPositionY - DOG_HEIGHT/3);
		grid.lineTo(dogPositionX, dogPositionY);
	}// End - drawDog()

	/*
	 * This method is used to verify if the dog is moving to the adjacent squares.
	 * For example, if the dog is on square 1, the adjacent squares will be 4, 5 and 2.
	 */
	private boolean isAdjacentSquare(int newSquare) {
		boolean isAdjacentSquare;
		if (squareDog == 1 && (newSquare == 4 || newSquare == 5 || newSquare == 2)) {
			isAdjacentSquare = true;
		} else if (squareDog == 2 && (newSquare == 1 || newSquare == 4 || newSquare == 5 || newSquare == 6 || newSquare == 3)) {
			isAdjacentSquare = true;
		} else if (squareDog == 3 && (newSquare == 2 || newSquare == 5 || newSquare == 6)) {
			isAdjacentSquare = true;
		} else if (squareDog == 4 && (newSquare == 7 || newSquare == 8 || newSquare == 5 || newSquare == 2 || newSquare == 1)) {
			isAdjacentSquare = true;
		} else if (squareDog == 5 && (newSquare == 1 || newSquare == 2 || newSquare == 3 || newSquare == 4 || newSquare == 6 || newSquare == 7 || newSquare == 8 || newSquare == 9)) {
			isAdjacentSquare = true;
		} else if (squareDog == 6 && (newSquare == 9 || newSquare == 8 || newSquare == 5 || newSquare == 2 || newSquare == 3)) {
			isAdjacentSquare = true;
		} else if (squareDog == 7 && (newSquare == 8 || newSquare == 5 || newSquare == 4)) {
			isAdjacentSquare = true;
		} else if (squareDog == 8 && (newSquare == 7 || newSquare == 4 || newSquare == 5 || newSquare == 6 || newSquare == 9)) {
			isAdjacentSquare = true;
		} else if (squareDog == 9 && (newSquare == 8 || newSquare == 5 || newSquare == 6)) {
			isAdjacentSquare = true;
		} else {
			isAdjacentSquare = false;
		}
		return isAdjacentSquare;
	}// End - isAdjacentSquare()
	
	// Constructor for Dog class.
	public Dog() { }

}// Class - Dog
