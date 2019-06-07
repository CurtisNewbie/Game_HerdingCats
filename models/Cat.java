package models;

import java.util.Random;
import javax.swing.JOptionPane;

/**
 * A Cat class that models the individual cat.
 * 
 * @author Curtis Last modified on 25/May/2019
 *
 */
public class Cat {

    /**
     * The square that the cat is on.
     */
    private int squareCat;

    /**
     * The width of each square of the game board.
     */
    private int squareWidth;

    /**
     * The height of each square of the game baord.
     */
    private int squareHeight;

    /**
     * The X coordinate of the cat's position.
     */
    private double positionX;

    /**
     * The Y coordinate of the cat's position.
     */
    private double positionY;

    /**
     * It refers to the relative position of dog to cat. For example, TOPLEFT refers
     * to the dog is on the top left of the cat. It is used to identify where the
     * cat should move.
     */
    enum DogRelativePosition {
	TOP_LEFT, TOP, TOP_RIGHT, RIGHT, BOTTOM_RIGHT, BOTTOM, BOTTOM_LEFT, LEFT, SAME_SQUARE
    }

    /**
     * It determines how the cat moves (the rules) 1 - 5. The rules are: If the
     * number is 1-3, then the cat moves away from the dog. This should be to the
     * square directly away from the dog, unless this would take the Cat to the edge
     * of the grid, in which case it should stay still. If the number is 4 or 5, the
     * Cat should move beside the Dog, i.e. it should move to a square adjacent to
     * its start square and adjacent to the Dog. If the number is a 6 then the Cat
     * should move to the same square as the Dog. If the Dog is on the same square
     * as the Cat at the start of the turn, then the cat can move randomly to any
     * adjacent square.
     */
    private int rule;

    /**
     * Constructor of class Cat
     * 
     * @param x x coordinate
     * @param y y coordinate
     * @param z z rule (that determines how the cat should react)
     */
    public Cat(int x, int y, int z) {
	positionX = x;
	positionY = y;
	rule = z;
    }

    /**
     * Check which square that the cat is on through checking the positionX and
     * positionY.
     */
    public void checkSquare() {
	if (positionY >= squareHeight * 2 && positionY <= squareHeight * 3 && positionX >= 0
		&& positionX < squareWidth) {
	    this.squareCat = 1;
	} else if ((positionY >= squareHeight * 2 && positionY <= squareHeight * 3)
		&& (positionX >= squareWidth && positionX < squareWidth * 2)) {
	    this.squareCat = 2;
	} else if ((positionY >= squareHeight * 2 && positionY <= squareHeight * 3)
		&& (positionX >= squareWidth * 2 && positionX <= squareWidth * 3)) {
	    this.squareCat = 3;
	} else if ((positionY >= squareHeight && positionY < squareHeight * 2)
		&& (positionX >= 0 && positionX < squareWidth)) {
	    this.squareCat = 4;
	} else if ((positionY >= squareHeight && positionY < squareHeight * 2)
		&& (positionX >= squareWidth && positionX < squareWidth * 2)) {
	    this.squareCat = 5;
	} else if ((positionY >= squareHeight && positionY < squareHeight * 2)
		&& (positionX >= squareWidth * 2 && positionX <= squareWidth * 3)) {
	    this.squareCat = 6;
	} else if ((positionY >= 0 && positionY < squareHeight) && (positionX >= 0 && positionX < squareWidth)) {
	    this.squareCat = 7;
	} else if ((positionY >= 0 && positionY < squareHeight)
		&& (positionX >= squareWidth && positionX < squareWidth * 2)) {
	    this.squareCat = 8;
	} else if ((positionY >= 0 && positionY < squareHeight)
		&& (positionX >= squareWidth * 2 && positionX <= squareWidth * 3)) {
	    this.squareCat = 9;
	} else {
	}
    }

    /**
     * Return squareCat.
     * 
     * @return The square that the cat is on
     */
    public int getSquareCat() {
	return squareCat;
    }

    /**
     * It moves the cat in response to the position of dog according to its rule (1
     * - 5). If the cat is on the same square as the dog, the cat moves randomly to
     * the adjacent squares excluding the square that it's currently on.
     * <p>
     * If rule equals 1, 2 or 3, the cat moves directly away from the dog. Cat will
     * stay if it reaches the edge.
     * <p>
     * If the rule equals 4 or 5, the cat randomly moves beside the dog, while it
     * will not be on the same square as the dog.
     */
    public void move(int squareDog) {

	DogRelativePosition relativePosition = checkRelativePosition(squareDog);

	if (squareCat == squareDog) {
	    moveToAdjacentSquare(squareDog);
	} else {
//	    if (rule == 6) {
//		moveToDog(squareDog);
//	    } else if
	    if (rule == 1 || rule == 2 || rule == 3) {
		if (relativePosition == DogRelativePosition.TOP_LEFT) {
		    moveBottomRight();
		} else if (relativePosition == DogRelativePosition.TOP) {
		    moveDown();
		} else if (relativePosition == DogRelativePosition.TOP_RIGHT) {
		    moveBottomLeft();
		} else if (relativePosition == DogRelativePosition.RIGHT) {
		    moveLeft();
		} else if (relativePosition == DogRelativePosition.BOTTOM_RIGHT) {
		    moveTopLeft();
		} else if (relativePosition == DogRelativePosition.BOTTOM) {
		    moveUp();
		} else if (relativePosition == DogRelativePosition.BOTTOM_LEFT) {
		    moveTopRight();
		} else if (relativePosition == DogRelativePosition.LEFT) {
		    moveRight();
		} else {

		}
	    } else if (rule == 4 || rule == 5) {
		// It refers to where the dog's relative position to cat.
		if (relativePosition == DogRelativePosition.TOP_LEFT) {
		    // If it will not be on the same square as the dog after next move, cat moves
		    // top left.
		    if (squareDog - squareCat > 2) {
			moveTopLeft();
		    } else {
			// Random number generated (0-1) For random movement of the cat.
			int n = new Random().nextInt(2);
			if (n == 0) {
			    moveUp();
			} else {
			    moveLeft();
			}
		    }
		} else if (relativePosition == DogRelativePosition.TOP) {
		    if (squareDog - squareCat > 3) {
			moveUp();
		    } else {
			int n = new Random().nextInt(4);
			if (n == 0) {
			    moveTopLeft();
			} else if (n == 1) {
			    moveLeft();
			} else if (n == 2) {
			    moveTopRight();
			} else if (n == 3) {
			    moveRight();
			}
		    }
		} else if (relativePosition == DogRelativePosition.TOP_RIGHT) {
		    if (squareDog - squareCat > 4) {
			moveTopRight();
		    } else {
			int n = new Random().nextInt(2);
			if (n == 0) {
			    moveUp();
			} else {
			    moveRight();
			}
		    }
		} else if (relativePosition == DogRelativePosition.RIGHT) {
		    if (squareDog - squareCat > 1) {
			moveRight();
		    } else {
			int n = new Random().nextInt(4);
			if (n == 0) {
			    moveTopRight();
			} else if (n == 1) {
			    moveUp();
			} else if (n == 2) {
			    moveBottomRight();
			} else if (n == 3) {
			    moveDown();
			}
		    }
		} else if (relativePosition == DogRelativePosition.BOTTOM_RIGHT) {
		    if (squareDog - squareCat < -2) {
			moveBottomRight();
		    } else {
			int n = new Random().nextInt(2);
			if (n == 0) {
			    moveRight();
			} else {
			    moveDown();
			}
		    }
		} else if (relativePosition == DogRelativePosition.BOTTOM) {
		    if (squareDog - squareCat > -3) {
			moveDown();
		    } else {
			int n = new Random().nextInt(4);
			if (n == 0) {
			    moveRight();
			} else if (n == 1) {
			    moveLeft();
			} else if (n == 2) {
			    moveBottomRight();
			} else if (n == 3) {
			    moveBottomLeft();
			}
		    }
		} else if (relativePosition == DogRelativePosition.BOTTOM_LEFT) {
		    if (squareDog - squareCat < -4) {
			moveBottomLeft();
		    } else {
			int n = new Random().nextInt(2);
			if (n == 0) {
			    moveLeft();
			} else {
			    moveDown();
			}
		    }
		} else if (relativePosition == DogRelativePosition.LEFT) {
		    if (squareDog - squareCat > -1) {
			moveLeft();
		    } else {
			int n = new Random().nextInt(4);
			if (n == 0) {
			    moveTopLeft();
			} else if (n == 1) {
			    moveUp();
			} else if (n == 2) {
			    moveDown();
			} else if (n == 3) {
			    moveBottomLeft();
			}
		    }
		} else {
//		    System.out.println("Error2 - Cat Class - move()" + squareDog + " " + squareCat);
		}
	    }
	}
    }

    /**
     * Move to the square on the left. Square 7,4, and 1 are on the edge of the
     * grid.
     */
    private void moveLeft() {
	if (positionX - squareWidth >= 0) {
	    positionX -= squareWidth;
	    this.checkSquare();
	}
    }

    /**
     * Move to the square on the right. Square 9,6, and 3 are on the edge of the
     * grid.
     */
    private void moveRight() {
	if (positionX + squareWidth <= squareWidth * 3) {
	    positionX += squareWidth;
	    this.checkSquare();
	}
    }

    /**
     * Move to the square on the top. Square 7,8, and 9 are on the edge of the grid.
     */
    private void moveUp() {
	if (positionY - squareHeight >= 0) {
	    positionY -= squareHeight;
	    this.checkSquare();
	}
    }

    /**
     * Move to the square below. Square 1,2, and 3 are on the edge of the grid.
     */
    private void moveDown() {
	if (positionY + squareHeight <= squareHeight * 3) {
	    positionY += squareHeight;
	    this.checkSquare();
	}
    }

    /**
     * Move to the square on the top left. Square 1,4,7,8, and 9 are on the edge of
     * the grid.
     */
    private void moveTopLeft() {
	if (positionX - squareWidth >= 0 && positionY - squareHeight >= 0) {
	    positionX -= squareWidth;
	    positionY -= squareHeight;
	    this.checkSquare();
	}
    }

    /**
     * Move to the square on the top right. Square 7,8,9,6, and 3 are on the edge of
     * the grid.
     */
    private void moveTopRight() {
	if (positionX + squareWidth <= squareWidth * 3 && positionY - squareHeight >= 0) {
	    positionX += squareWidth;
	    positionY -= squareHeight;
	    this.checkSquare();
	}
    }

    /**
     * Move to the square on the bottom left. Square 7,4,1,2,and 3 are on the edge
     * of the grid.
     */
    private void moveBottomLeft() {
	if (positionX - squareWidth >= 0 && positionY + squareHeight <= squareHeight * 3) {
	    positionX -= squareWidth;
	    positionY += squareHeight;
	    this.checkSquare();
	}
    }

    /**
     * Move to the square on the bottom right. Square 1,2,3,6, and 9 are on the edge
     * of the grid.
     */
    private void moveBottomRight() {
	if (positionX + squareWidth <= squareHeight * 3 && positionY + squareHeight <= squareHeight * 3) {
	    positionX += squareWidth;
	    positionY += squareHeight;
	    this.checkSquare();
	}
    }

    /**
     * When the cat is on the same square as the dog, the cat randomly moves to the
     * adjacent squares. It uses .nextInt() method to randomly generate a number.
     * The number than is used to choose where the cat moves. If the cat is going to
     * the square that the Dog is on, the cat stays.
     * <p>
     * E.g: In first situation, if the cat is on square 1. It generates a random
     * number from 0 to 2. If the random number is 0, checks if the dog is on square
     * 4, if not the cat moves to square 4.
     */
    private void moveToAdjacentSquare(int squareDog) {
	if (squareCat == 1) {
	    int n = new Random().nextInt(3);
	    switch (n) {
	    case 0:
		if (squareDog != 4) {
		    this.moveUp();
		}
		break;
	    case 1:
		if (squareDog != 5) {
		    this.moveTopRight();
		}
		break;
	    case 2:
		if (squareDog != 2) {
		    this.moveRight();
		}
		break;
	    default:
		break;
	    }
	} else if (squareCat == 2) {
	    int n = new Random().nextInt(5);
	    switch (n) {
	    case 0:
		if (squareDog != 1) {
		    this.moveLeft();
		}
		break;
	    case 1:
		if (squareDog != 4) {
		    this.moveTopLeft();
		}
		break;
	    case 2:
		if (squareDog != 5) {
		    this.moveUp();
		}
		break;
	    case 3:
		if (squareDog != 6) {
		    this.moveTopRight();
		}
		break;
	    case 4:
		if (squareDog != 3) {
		    this.moveBottomRight();
		}
		break;
	    default:
		break;
	    }
	} else if (squareCat == 3) {
	    int n = new Random().nextInt(3);
	    switch (n) {
	    case 0:
		if (squareDog != 2) {
		    this.moveLeft();
		}
		break;
	    case 1:
		if (squareDog != 5) {
		    this.moveUp();
		}
		break;
	    case 2:
		if (squareDog != 6) {
		    this.moveTopRight();
		}
		break;
	    default:
		break;
	    }
	} else if (squareCat == 4) {
	    int n = new Random().nextInt(5);
	    switch (n) {
	    case 0:
		if (squareDog != 7) {
		    this.moveUp();
		}
		break;
	    case 1:
		if (squareDog != 8) {
		    this.moveTopRight();
		}
		break;
	    case 2:
		if (squareDog != 5) {
		    this.moveRight();
		}
		break;
	    case 3:
		if (squareDog != 2) {
		    this.moveBottomRight();
		}
		break;
	    case 4:
		if (squareDog != 1) {
		    this.moveDown();
		}
		break;
	    default:
		break;
	    }
	} else if (squareCat == 5) {
	    int n = new Random().nextInt(8);
	    switch (n) {
	    case 0:
		if (squareDog != 1) {
		    this.moveBottomLeft();
		}
		break;
	    case 1:
		if (squareDog != 2) {
		    this.moveDown();
		}
		break;
	    case 2:
		if (squareDog != 3) {
		    this.moveBottomRight();
		}
		break;
	    case 3:
		if (squareDog != 4) {
		    this.moveLeft();
		}
		break;
	    case 4:
		if (squareDog != 6) {
		    this.moveRight();
		}
		break;
	    case 5:
		if (squareDog != 7) {
		    this.moveTopLeft();
		}
		break;
	    case 6:
		if (squareDog != 8) {
		    this.moveUp();
		}
		break;
	    case 7:
		if (squareDog != 9) {
		    this.moveTopRight();
		}
		break;
	    default:
		break;
	    }
	} else if (squareCat == 6) {
	    int n = new Random().nextInt(5);
	    switch (n) {
	    case 0:
		if (squareDog != 9) {
		    this.moveUp();
		}
		break;
	    case 1:
		if (squareDog != 8) {
		    this.moveTopLeft();
		}
		break;
	    case 2:
		if (squareDog != 5) {
		    this.moveLeft();
		}
		break;
	    case 3:
		if (squareDog != 2) {
		    this.moveBottomLeft();
		}
		break;
	    case 4:
		if (squareDog != 3) {
		    this.moveDown();
		}
		break;
	    default:
		break;
	    }
	} else if (squareCat == 7) {
	    int n = new Random().nextInt(3);
	    switch (n) {
	    case 0:
		if (squareDog != 8) {
		    this.moveRight();
		}
		break;
	    case 1:
		if (squareDog != 5) {
		    this.moveBottomRight();
		}
		break;
	    case 2:
		if (squareDog != 4) {
		    this.moveDown();
		}
		break;
	    default:
		break;
	    }
	} else if (squareCat == 8) {
	    int n = new Random().nextInt(5);
	    switch (n) {
	    case 0:
		if (squareDog != 7) {
		    this.moveLeft();
		}
		break;
	    case 1:
		if (squareDog != 4) {
		    this.moveBottomLeft();
		}
		break;
	    case 2:
		if (squareDog != 5) {
		    this.moveDown();
		}
		break;
	    case 3:
		if (squareDog != 6) {
		    squareCat = 6;
		    this.moveBottomRight();
		}
		break;
	    case 4:
		if (squareDog != 9) {
		    this.moveRight();
		}
		break;
	    default:
		break;
	    }
	} else if (squareCat == 9) {
	    int n = new Random().nextInt(3);
	    switch (n) {
	    case 0:
		if (squareDog != 8) {
		    this.moveLeft();
		}
		break;
	    case 1:
		if (squareDog != 5) {
		    this.moveBottomLeft();
		}
		break;
	    case 2:
		if (squareDog != 6) {
		    this.moveDown();
		}
		break;
	    default:
		break;
	    }
	} else {
	    JOptionPane.showMessageDialog(null, "Error - moveToAdjacentSquare()", "Error", JOptionPane.WARNING_MESSAGE);
//	    System.out.println("Error - moveToAdjacentSquare()");
	}
    }

    /**
     * Check the relative position between cat and dog for the move() method to
     * decide where the cat should the go.
     * 
     * @param squareDog
     * @return the relative position of the dog. (e.g., the dog is on the top left
     *         of the cat)
     */
    private DogRelativePosition checkRelativePosition(int squareDog) {

	// It checks relative position by comparing dog positions and cat positions.

	if (squareCat - squareDog == 2 && (squareDog != 7 && squareCat != 9) && (squareDog != 4 && squareCat != 6)
		&& (squareDog != 1 && squareCat != 3)) {
	    return DogRelativePosition.BOTTOM_RIGHT;
	} else if (squareCat - squareDog == 3) {
	    return DogRelativePosition.BOTTOM;
	} else if (squareCat - squareDog == 4 && (squareCat != 7 && squareDog != 3)) {
	    return DogRelativePosition.BOTTOM_LEFT;
	} else if (squareCat - squareDog == 1 && (squareCat != 7 && squareDog != 6)
		&& (squareCat != 4 && squareDog != 3)) {
	    return DogRelativePosition.LEFT;
	} else if (squareDog - squareCat == 2 && (squareCat != 7 && squareDog != 9)
		&& (squareCat != 4 && squareDog != 6) && (squareCat != 1 && squareDog != 3)) {
	    return DogRelativePosition.TOP_LEFT;
	} else if (squareDog - squareCat == 3) {
	    return DogRelativePosition.TOP;
	} else if (squareDog - squareCat == 4 && (squareDog != 7 && squareCat != 3)) {
	    return DogRelativePosition.TOP_RIGHT;
	} else if (squareDog - squareCat == 1 && (squareDog != 7 && squareCat != 6)
		&& (squareDog != 4 && squareCat != 3)) {
	    return DogRelativePosition.RIGHT;
	} else {
	    // the dog is at the same square as the dog.
	    return DogRelativePosition.SAME_SQUARE;
	}
    }

//    /**
//     * This method moves the cat until the cat is on the same square as the dog. The
//     * methods such as moveTopLeft(), moveUp() and so on, they only update the
//     * positionX and positionY for the drawCat() method to display the cat on the
//     * grid.
//     * 
//     * @param squareDog the square that the dog is on
//     */
//    private void moveToDog(int squareDog) {
//	if (squareCat != squareDog) {
//	    DogRelativePosition relativePosition = checkRelativePosition(squareDog);
//	    if (relativePosition == DogRelativePosition.TOP_LEFT) {
//		moveTopLeft();
//
//	    } else if (relativePosition == DogRelativePosition.TOP) {
//		moveUp();
//
//	    } else if (relativePosition == DogRelativePosition.TOP_RIGHT) {
//		moveTopRight();
//
//	    } else if (relativePosition == DogRelativePosition.RIGHT) {
//		moveRight();
//
//	    } else if (relativePosition == DogRelativePosition.BOTTOM_RIGHT) {
//		moveBottomRight();
//
//	    } else if (relativePosition == DogRelativePosition.BOTTOM) {
//		moveDown();
//
//	    } else if (relativePosition == DogRelativePosition.BOTTOM_LEFT) {
//		moveBottomLeft();
//
//	    } else if (relativePosition == DogRelativePosition.LEFT) {
//		moveLeft();
//
//	    } else {
//
//	    }
//	}
//    }

    /**
     * Get the x coordinate of the cat
     * 
     * @return x coordinate of the cat
     */
    public double getPositionX() {
	return this.positionX;
    }

    /**
     * Get the y coordinate of the cat
     * 
     * @return y coordinate of the cat
     */
    public double getPositionY() {
	return this.positionY;
    }

    /**
     * Set the square width of the game board
     * 
     * @param width the width of each square on the game board
     */
    public void setSquareWidth(int width) {
	this.squareWidth = width;
    }

    /**
     * Set the square height of the game board
     * 
     * @param height the height of each square on the game board
     */
    public void setSquareHeight(int height) {
	this.squareHeight = height;
    }

    /**
     * Set position x.
     * 
     * @param x x coordinate
     */
    public void setPositionX(double x) {
	this.positionX = x;
    }

    /**
     * Set position y.
     * 
     * @param y y coordinate
     */
    public void setPositionY(double y) {
	this.positionY = y;
    }

    /**
     * get the rule of this cat's movement.
     * 
     * @return rule the rule of this cat's movement.
     */
    public int getRule() {
	return this.rule;
    }
}
