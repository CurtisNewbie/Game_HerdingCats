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
    private int squareCat = 0;

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
    private int positionX = 0;

    /**
     * The Y coordinate of the cat's position.
     */
    private int positionY = 0;

    /**
     * It refers to the relative position of dog to cat. For example, TOPLEFT refers
     * to the dog is on the top left of the cat. It is used to identify where the
     * cat should move.
     */
    enum DogRelativePosition {
	TOP_LEFT, TOP, TOP_RIGHT, RIGHT, BOTTOM_RIGHT, BOTTOM, BOTTOM_LEFT, LEFT, SAME_SQUARE
    }

    /**
     * It determines how the cat moves (the rules) 1 - 6. The rules are: If the
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
    public void checkSquare(int SQUARE_SIZE) {
	if (positionX >= 0 && positionX <= SQUARE_SIZE && positionY >= 0 && positionY <= SQUARE_SIZE) {
	    squareCat = 1;
	} else if (positionX >= (SQUARE_SIZE) && positionX <= (SQUARE_SIZE * 2) && positionY >= 0
		&& positionY <= SQUARE_SIZE) {
	    squareCat = 2;
	} else if (positionX >= (SQUARE_SIZE * 2) && positionX <= SQUARE_SIZE * 3 && positionY >= 0
		&& positionY <= SQUARE_SIZE) {
	    squareCat = 3;
	} else if (positionX >= 0 && positionX <= (SQUARE_SIZE) && positionY >= (SQUARE_SIZE)
		&& positionY <= (SQUARE_SIZE * 2)) {
	    squareCat = 4;
	} else if (positionX >= (SQUARE_SIZE) && positionX <= (SQUARE_SIZE * 2) && positionY >= (SQUARE_SIZE)
		&& positionY <= (SQUARE_SIZE * 2)) {
	    squareCat = 5;
	} else if (positionX >= (SQUARE_SIZE * 2) && positionX <= SQUARE_SIZE * 3 && positionY >= (SQUARE_SIZE)
		&& positionY <= (SQUARE_SIZE * 2)) {
	    squareCat = 6;
	} else if (positionX >= 0 && positionX <= (SQUARE_SIZE) && positionY >= (SQUARE_SIZE * 2)
		&& positionY <= SQUARE_SIZE * 3) {
	    squareCat = 7;
	} else if (positionX >= (SQUARE_SIZE) && positionX <= (SQUARE_SIZE * 2) && positionY >= (SQUARE_SIZE * 2)
		&& positionY <= SQUARE_SIZE * 3) {
	    squareCat = 8;
	} else if (positionX >= (SQUARE_SIZE * 2) && positionX <= SQUARE_SIZE * 3 && positionY >= (SQUARE_SIZE * 2)
		&& positionY <= SQUARE_SIZE * 3) {
	    squareCat = 9;
	} else {
	    // error message
	    JOptionPane.showMessageDialog(null, "Error - Cat Class - checkSquare()", "Error",
		    JOptionPane.WARNING_MESSAGE);
//	    System.out.println("Error - Cat Class - checkSquare()");
	}

//	System.out.println("Rule: " + rule + "| x:" + positionX + "| y:" + positionY + "| square: " + squareCat);
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
     * - 6). If the cat is on the same square as the dog, the cat moves randomly to
     * the adjacent squares excluding the square that it's currently on. If rule =
     * 6, cat moves to the same square as the dog. If rule equals 1, 2 or 3, the cat
     * moves directly away from the dog. Cat will stay if it reachs the edge. If the
     * rule equals 4 or 5, the cat randomly moves beside the dog, while it will not
     * be on the same square as the dog.
     */
    public void move(int squareDog, int dogPositionX, int dogPositionY) {

	DogRelativePosition relativePosition = checkRelativePosition(dogPositionX, dogPositionY);

	if (squareCat == squareDog) {
	    moveToAdjacentSquare(squareDog);
	} else {
	    if (rule == 6) {
		moveToDog(squareDog, dogPositionX, dogPositionY);
	    } else if (rule == 1 || rule == 2 || rule == 3) {
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
		    System.out.println("Error 1 - Cat Class - move()" + squareDog + " " + squareCat);
		}
	    } else if (rule == 4 || rule == 5) {
		// It refers to where the dog is at to cat.
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
	if (squareCat != 7 && squareCat != 4 && squareCat != 1) {
	    positionX -= squareWidth;
	}
    }

    /**
     * Move to the square on the right. Square 9,6, and 3 are on the edge of the
     * grid.
     */
    private void moveRight() {
	if (squareCat != 9 && squareCat != 6 && squareCat != 3) {
	    positionX += squareWidth;
	}
    }

    /**
     * Move to the square on the top. Square 7,8, and 9 are on the edge of the grid.
     */
    private void moveUp() {
	if (squareCat != 7 && squareCat != 8 && squareCat != 9) {
	    positionY -= squareHeight;
	}
    }

    /**
     * Move to the square below. Square 1,2, and 3 are on the edge of the grid.
     */
    private void moveDown() {
	if (squareCat != 1 && squareCat != 2 && squareCat != 3) {
	    positionY += squareHeight;
	}
    }

    /**
     * Move to the square on the top left. Square 1,4,7,8, and 9 are on the edge of
     * the grid.
     */
    private void moveTopLeft() {
	if (squareCat != 1 && squareCat != 4 && squareCat != 7 && squareCat != 8 && squareCat != 9) {
	    positionX -= squareWidth;
	    positionY -= squareHeight;
	}
    }

    /**
     * Move to the square on the top right. Square 7,8,9,6, and 3 are on the edge of
     * the grid.
     */
    private void moveTopRight() {
	if (squareCat != 7 && squareCat != 8 && squareCat != 9 && squareCat != 6 && squareCat != 3) {
	    positionX += squareWidth;
	    positionY -= squareHeight;
	}
    }

    /**
     * Move to the square on the bottom left. Square 7,4,1,2,and 3 are on the edge
     * of the grid.
     */
    private void moveBottomLeft() {
	if (squareCat != 7 && squareCat != 4 && squareCat != 1 && squareCat != 2 && squareCat != 3) {
	    positionX -= squareWidth;
	    positionY += squareHeight;
	}
    }

    /**
     * Move to the square on the bottom right. Square 1,2,3,6, and 9 are on the edge
     * of the grid.
     */
    private void moveBottomRight() {
	if (squareCat != 1 && squareCat != 2 && squareCat != 3 && squareCat != 6 && squareCat != 9) {
	    positionX += squareWidth;
	    positionY += squareHeight;
	}
    }

    /**
     * When the cat is on the same square as the dog, the cat randomly moves to the
     * adjacent squares. It uses .nextInt() method to randomly generate a number.
     * The number than is used to choose where the cat moves. If the cat is going to
     * the square that the Dog is on, the cat stays.
     * 
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
		    squareCat = 4;
		    positionY += squareHeight;
		}
		break;
	    case 1:
		if (squareDog != 5) {
		    squareCat = 5;
		    positionX += squareWidth;
		    positionY -= squareHeight;
		}
		break;
	    case 2:
		if (squareDog != 2) {
		    squareCat = 2;
		    positionX += squareWidth;
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
		    squareCat = 1;
		    positionX -= squareWidth;
		}
		break;
	    case 1:
		if (squareDog != 4) {
		    squareCat = 4;
		    positionX -= squareWidth;
		    positionY -= squareHeight;
		}
		break;
	    case 2:
		if (squareDog != 5) {
		    squareCat = 5;
		    positionY -= squareHeight;
		}
		break;
	    case 3:
		if (squareDog != 6) {
		    squareCat = 6;
		    positionX += squareWidth;
		    positionY -= squareHeight;
		}
		break;
	    case 4:
		if (squareDog != 3) {
		    squareCat = 3;
		    positionX += squareWidth;
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
		    squareCat = 2;
		    positionX -= squareWidth;
		}
		break;
	    case 1:
		if (squareDog != 5) {
		    squareCat = 5;
		    positionX -= squareWidth;
		    positionY -= squareHeight;
		}
		break;
	    case 2:
		if (squareDog != 6) {
		    squareCat = 6;
		    positionY -= squareHeight;
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
		    squareCat = 7;
		    positionY -= squareHeight;
		}
		break;
	    case 1:
		if (squareDog != 8) {
		    squareCat = 8;
		    positionY -= squareHeight;
		    positionX += squareWidth;
		}
		break;
	    case 2:
		if (squareDog != 5) {
		    squareCat = 5;
		    positionX += squareWidth;
		}
		break;
	    case 3:
		if (squareDog != 2) {
		    squareCat = 2;
		    positionX += squareWidth;
		    positionY += squareHeight;
		}
		break;
	    case 4:
		if (squareDog != 1) {
		    squareCat = 1;
		    positionY += squareHeight;
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
		    squareCat = 1;
		    positionX -= squareWidth;
		    positionY += squareHeight;
		}
		break;
	    case 1:
		if (squareDog != 2) {
		    squareCat = 2;
		    positionY += squareHeight;
		}
		break;
	    case 2:
		if (squareDog != 3) {
		    squareCat = 3;
		    positionX += HerdingCats.SQUARE_SIZE;
		    positionY += squareHeight;
		}
		break;
	    case 3:
		if (squareDog != 4) {
		    squareCat = 4;
		    positionX -= squareWidth;
		}
		break;
	    case 4:
		if (squareDog != 6) {
		    squareCat = 6;
		    positionX += squareWidth;
		}
		break;
	    case 5:
		if (squareDog != 7) {
		    squareCat = 7;
		    positionX -= squareWidth;
		    positionY -= squareHeight;
		}
		break;
	    case 6:
		if (squareDog != 8) {
		    squareCat = 8;
		    positionY -= squareHeight;
		}
		break;
	    case 7:
		if (squareDog != 9) {
		    squareCat = 9;
		    positionX += squareWidth;
		    positionY -= squareHeight;
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
		    squareCat = 9;
		    positionY -= squareHeight;
		}
		break;
	    case 1:
		if (squareDog != 8) {
		    squareCat = 8;
		    positionX -= squareWidth;
		    positionY -= squareHeight;
		}
		break;
	    case 2:
		if (squareDog != 5) {
		    squareCat = 5;
		    positionX -= squareWidth;
		}
		break;
	    case 3:
		if (squareDog != 2) {
		    squareCat = 2;
		    positionX -= squareWidth;
		    positionY += squareHeight;
		}
		break;
	    case 4:
		if (squareDog != 3) {
		    squareCat = 3;
		    positionY += squareHeight;
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
		    squareCat = 8;
		    positionX += squareWidth;
		}
		break;
	    case 1:
		if (squareDog != 5) {
		    squareCat = 5;
		    positionX += squareWidth;
		    positionY += squareHeight;
		}
		break;
	    case 2:
		if (squareDog != 4) {
		    squareCat = 4;
		    positionY += squareHeight;
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
		    squareCat = 7;
		    positionX -= squareWidth;
		}
		break;
	    case 1:
		if (squareDog != 4) {
		    squareCat = 4;
		    positionX -= squareWidth;
		    positionY += squareHeight;
		}
		break;
	    case 2:
		if (squareDog != 5) {
		    squareCat = 5;
		    positionY += squareHeight;
		}
		break;
	    case 3:
		if (squareDog != 6) {
		    squareCat = 6;
		    positionX += squareWidth;
		    positionY += squareHeight;
		}
		break;
	    case 4:
		if (squareDog != 9) {
		    squareCat = 9;
		    positionX += squareWidth;
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
		    squareCat = 8;
		    positionX -= squareWidth;
		}
		break;
	    case 1:
		if (squareDog != 5) {
		    squareCat = 5;
		    positionX -= squareWidth;
		    positionY += squareHeight;
		}
		break;
	    case 2:
		if (squareDog != 6) {
		    squareCat = 6;
		    positionY += squareHeight;
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
     * @param dogPositionX x coordinate of the dog
     * @param dogPositionY y coordinate of the dog
     * @return the relative position of the dog. (e.g., the dog is on the top left
     *         of the cat)
     */
    private DogRelativePosition checkRelativePosition(int dogPositionX, int dogPositionY) {

	// It checks relative position by comparing dog positions and cat positions.

	if (positionX < dogPositionX - squareWidth / 2 && positionY < dogPositionY - squareHeight / 2) {
	    // dog is on the bottom right
	    return DogRelativePosition.BOTTOM_RIGHT;
	} else if ((positionX == dogPositionX || positionX - dogPositionX > -squareWidth
		|| positionX - dogPositionX < squareWidth) && positionY < dogPositionY - squareHeight / 2) {
	    // dog is on the square bellow
	    return DogRelativePosition.BOTTOM;
	} else if (positionX > dogPositionX + squareWidth / 2 && positionY < dogPositionY - squareHeight / 2) {
	    // dog is on the bottom left
	    return DogRelativePosition.BOTTOM_LEFT;
	} else if (positionX > dogPositionX + squareWidth / 2 && (positionY == dogPositionY
		|| positionY - dogPositionY > -squareHeight / 2 || positionY - dogPositionY < squareHeight / 2)) {
	    // dog is on the square on the left
	    return DogRelativePosition.LEFT;
	} else if (positionX > dogPositionX + squareWidth / 2 && positionY > dogPositionY + squareHeight / 2) {
	    // dog is on the top left
	    return DogRelativePosition.TOP_LEFT;
	} else if ((positionX == dogPositionX || positionX - dogPositionX > -squareWidth
		|| positionX - dogPositionX < squareWidth) && positionY >= dogPositionY + squareHeight / 2) {
	    // dog is on the square on the top
	    return DogRelativePosition.TOP;
	} else if (positionX < dogPositionX - squareWidth / 2 && positionY > dogPositionY + squareHeight / 2) {
	    // dog is on the top right
	    return DogRelativePosition.TOP_RIGHT;
	} else if (positionX < dogPositionX - squareWidth / 2 && (positionY == dogPositionY
		|| positionY - dogPositionY > -squareHeight / 2 || positionY - dogPositionY < squareHeight / 2)) {
	    // on the right
	    return DogRelativePosition.RIGHT;
	} else {
	    // the dog is at the same square as the dog.
	    return DogRelativePosition.SAME_SQUARE;
	}
    }

    /**
     * This method moves the cat until the cat is on the same square as the dog. The
     * methods such as moveTopLeft(), moveUp() and so on, they only update the
     * positionX and positionY for the drawCat() method to display the cat on the
     * grid.
     * 
     * @param squareDog    the square that the dog is on
     * @param dogPositionX the x coordinate of the dog
     * @param dogPositionY the y coordinate of the dog
     */
    private void moveToDog(int squareDog, int dogPositionX, int dogPositionY) {
	if (squareCat != squareDog) {
	    DogRelativePosition relativePosition = checkRelativePosition(dogPositionX, dogPositionY);
	    if (relativePosition == DogRelativePosition.TOP_LEFT) {
		moveTopLeft();
		squareCat += 2;
	    } else if (relativePosition == DogRelativePosition.TOP) {
		moveUp();
		squareCat += 3;
	    } else if (relativePosition == DogRelativePosition.TOP_RIGHT) {
		moveTopRight();
		squareCat += 4;
	    } else if (relativePosition == DogRelativePosition.RIGHT) {
		moveRight();
		squareCat++;
	    } else if (relativePosition == DogRelativePosition.BOTTOM_RIGHT) {
		moveBottomRight();
		squareCat -= 2;
	    } else if (relativePosition == DogRelativePosition.BOTTOM) {
		moveDown();
		squareCat -= 3;
	    } else if (relativePosition == DogRelativePosition.BOTTOM_LEFT) {
		moveBottomLeft();
		squareCat -= 4;
	    } else if (relativePosition == DogRelativePosition.LEFT) {
		moveLeft();
		squareCat--;
	    } else {
		JOptionPane.showMessageDialog(null, "Error - Cat Class moveToDog()\" + squareDog + \" \" + squareCat",
			"Error", JOptionPane.WARNING_MESSAGE);
//		System.out.println("Error - Cat Class moveToDog()" + squareDog + " " + squareCat);
	    }
	}
    }

    /**
     * Get the x coordinate of the cat
     * 
     * @return x coordinate of the cat
     */
    public int getPositionX() {
	return this.positionX;
    }

    /**
     * Get the y coordinate of the cat
     * 
     * @return y coordinate of the cat
     */
    public int getPositionY() {
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
}
