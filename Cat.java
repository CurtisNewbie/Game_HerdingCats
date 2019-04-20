/**
 * @author Curtis (Yongjie Zhuang)
 *  Date of submission: 2018/11/11
 */


import java.util.Random;
import sheffield.EasyGraphics;

//A Cat class that models the individual cat. 
public class Cat {

	/*
	 * These constants will determine how big the 'cat' symbol is. See method -
	 * drawCat()
	 */
	public static final int CAT_WIDTH = 5;
	public static final int CAT_HEIGHT = 15;

	// The square that the cat is on.
	private int squareCat = 0;

	// The position X and Y of the cat. These refer to the position of the cat on
	// the grid
	private int positionX = 0;
	private int positionY = 0;

	/*
	 * It's used in method - checkRelativePosition() for checking where is the
	 * relative position of dog to cat. For example, TOPLEFT refers to the dog is on
	 * the top left of the cat, then which is used to identify where should the cat
	 * move. The number doesn't mean anything, it's for the method.
	 */
	private final int TOPLEFT = 1;
	private final int TOP = 2;
	private final int TOPRIGHT = 3;
	private final int RIGHT = 4;
	private final int BOTTOMRIGHT = 5;
	private final int BOTTOM = 6;
	private final int BOTTOMLEFT = 7;
	private final int LEFT = 8;
	private final int SAMESQUARE = 10;

	// It determines how the cat moves (the rules) 1 - 6. See method - move().
	private int rule;

	/*
	 * Check which square that the cat is on through checking the positionX and
	 * positionY.
	 */
	public void checkSquare(int SQUARE_SIZE) {
		if (positionX >= 0 && positionX <= SQUARE_SIZE && positionY >= 0 && positionY <= SQUARE_SIZE) {
			squareCat = 1;
		} else if (positionX >= (SQUARE_SIZE) && positionX <= (SQUARE_SIZE*2) && positionY >= 0 && positionY <= SQUARE_SIZE) {
			squareCat = 2;
		} else if (positionX >= (SQUARE_SIZE*2) && positionX <= SQUARE_SIZE*3 && positionY >= 0 && positionY <= SQUARE_SIZE) {
			squareCat = 3;
		} else if (positionX >= 0 && positionX <= (SQUARE_SIZE) && positionY >= (SQUARE_SIZE) && positionY <= (SQUARE_SIZE*2)) {
			squareCat = 4;
		} else if (positionX >= (SQUARE_SIZE) && positionX <= (SQUARE_SIZE*2) && positionY >= (SQUARE_SIZE) && positionY <= (SQUARE_SIZE*2)) {
			squareCat = 5;
		} else if (positionX >= (SQUARE_SIZE*2) && positionX <= SQUARE_SIZE*3 && positionY >= (SQUARE_SIZE) && positionY <= (SQUARE_SIZE*2)) {
			squareCat = 6;
		} else if (positionX >= 0 && positionX <= (SQUARE_SIZE) && positionY >= (SQUARE_SIZE*2) && positionY <= SQUARE_SIZE*3) {
			squareCat = 7;
		} else if (positionX >= (SQUARE_SIZE) && positionX <= (SQUARE_SIZE*2) && positionY >= (SQUARE_SIZE*2) && positionY <= SQUARE_SIZE*3) {
			squareCat = 8;
		} else if (positionX >= (SQUARE_SIZE*2) && positionX <= SQUARE_SIZE*3 && positionY >= (SQUARE_SIZE*2) && positionY <= SQUARE_SIZE*3) {
			squareCat = 9;
		} else {
			// error message
			System.out.println("Error - Cat Class - checkSquare()");
		}
		
		System.out.println("Rule: " + rule + "| x:" + positionX + "| y:" + positionY + "| square: " + squareCat );
	}// checkSquare()

	// Return squareCat.
	public int getSquareCat() {
		return squareCat;
	}

	/*
	 * It moves the cat in response to the position of dog according to its rule (1
	 * - 6). If the cat is on the same square as the dog, the cat moves randomly to
	 * the adjacent squares excluding the square that it's currently on. If rule =
	 * 6, cat moves to the same square as the dog. If rule equals 1, 2 or 3, the cat
	 * moves directly away from the dog. Cat will stay if it reachs the edge. If the
	 * rule equals 4 or 5, the cat randomly moves beside the dog, while it will not
	 * be on the same square as the dog.
	 */
	public void move(int squareDog, int dogPositionX, int dogPositionY) {

		if (squareCat == squareDog) {
			moveToAdjacentSquare(squareDog);
		} else {
			if (rule == 6) {
				moveToDog(squareDog, dogPositionX, dogPositionY);
			} else if (rule == 1 || rule == 2 || rule == 3) {
				if (checkRelativePosition(dogPositionX, dogPositionY) == TOPLEFT) {
					moveBottomRight();
				} else if (checkRelativePosition(dogPositionX, dogPositionY) == TOP) {
					moveDown();
				} else if (checkRelativePosition(dogPositionX, dogPositionY) == TOPRIGHT) {
					moveBottomLeft();
				} else if (checkRelativePosition(dogPositionX, dogPositionY) == RIGHT) {
					moveLeft();
				} else if (checkRelativePosition(dogPositionX, dogPositionY) == BOTTOMRIGHT) {
					moveTopLeft();
				} else if (checkRelativePosition(dogPositionX, dogPositionY) == BOTTOM) {
					moveUp();
				} else if (checkRelativePosition(dogPositionX, dogPositionY) == BOTTOMLEFT) {
					moveTopRight();
				} else if (checkRelativePosition(dogPositionX, dogPositionY) == LEFT) {
					moveRight();
				} else {
					System.out.println("Error 1 - Cat Class - move()" + squareDog + " " + squareCat);
				}
			} else if (rule == 4 || rule == 5) {
				// It refers to where the dog is at to cat.
				if (checkRelativePosition(dogPositionX, dogPositionY) == TOPLEFT) {
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
				} else if (checkRelativePosition(dogPositionX, dogPositionY) == TOP) {
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
				} else if (checkRelativePosition(dogPositionX, dogPositionY) == TOPRIGHT) {
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
				} else if (checkRelativePosition(dogPositionX, dogPositionY) == RIGHT) {
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
				} else if (checkRelativePosition(dogPositionX, dogPositionY) == BOTTOMRIGHT) {
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
				} else if (checkRelativePosition(dogPositionX, dogPositionY) == BOTTOM) {
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
				} else if (checkRelativePosition(dogPositionX, dogPositionY) == BOTTOMLEFT) {
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
				} else if (checkRelativePosition(dogPositionX, dogPositionY) == LEFT) {
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
					System.out.println("Error2 - Cat Class - move()" + squareDog + " " + squareCat);
				}
			}
		}
	}// End - move()

	// Constructor of class Cat
	public Cat(int x, int y, int z) {
		positionX = x;
		positionY = y;
		rule = z;
	}

	/*
	 * Draw the cat on the object of class - 'EasyGraphics' according to its
	 * attributes - positionX, positionY. The predefined constants CAT_WIDTH and
	 * CAT_HEIGHT determine the size of it.
	 */
	public void drawCat(EasyGraphics grid) {
		grid.moveTo(positionX, positionY);
		grid.lineTo(positionX - CAT_WIDTH, positionY - CAT_HEIGHT / 3);
		grid.lineTo(positionX - CAT_WIDTH, positionY - (CAT_HEIGHT / 3) * 2);
		grid.lineTo(positionX, positionY - CAT_HEIGHT);
	}// end - drawCat()
	
	// Move to the square on the left. Square 7,4,1 are on the edge of the grid.
	private void moveLeft() {
		if (squareCat != 7 && squareCat != 4 && squareCat != 1) {
			positionX -= HerdingCats.SQUARE_SIZE;
		}
	}

	// Move to the square on the right. Square 9,6,3 are on the edge of the grid.
	private void moveRight() {
		if (squareCat != 9 && squareCat != 6 && squareCat != 3) {
			positionX += HerdingCats.SQUARE_SIZE;
		}
	}

	// Move to the square on the top. Square 7,8,9 are on the edge of the grid.
	private void moveUp() {
		if (squareCat != 7 && squareCat != 8 && squareCat != 9) {
			positionY += HerdingCats.SQUARE_SIZE;
		}
	}

	// Move to the square below. Square 1,2,3 are on the edge of the grid.
	private void moveDown() {
		if (squareCat != 1 && squareCat != 2 && squareCat != 3) {
			positionY -= HerdingCats.SQUARE_SIZE;
		}
	}

	// Move to the square on the top left. Square 1,4,7,8,9 are on the edge of the
	// grid.
	private void moveTopLeft() {
		if (squareCat != 1 && squareCat != 4 && squareCat != 7 && squareCat != 8 && squareCat != 9) {
			positionX -= HerdingCats.SQUARE_SIZE;
			positionY += HerdingCats.SQUARE_SIZE;
		}
	}

	// Move to the square on the top right. Square 7,8,9,6,3 are on the edge of the
	// grid.
	private void moveTopRight() {
		if (squareCat != 7 && squareCat != 8 && squareCat != 9 && squareCat != 6 && squareCat != 3) {
			positionX += HerdingCats.SQUARE_SIZE;
			positionY += HerdingCats.SQUARE_SIZE;
		}
	}

	// Move to the square on the bottom left. Square 7,4,1,2,3 are on the edge of
	// the grid.
	private void moveBottomLeft() {
		if (squareCat != 7 && squareCat != 4 && squareCat != 1 && squareCat != 2 && squareCat != 3) {
			positionX -= HerdingCats.SQUARE_SIZE;
			positionY -= HerdingCats.SQUARE_SIZE;
		}
	}

	// Move to the square on the bottom right. Square 1,2,3,6,9 are on the edge of
	// the grid.
	private void moveBottomRight() {
		if (squareCat != 1 && squareCat != 2 && squareCat != 3 && squareCat != 6 && squareCat != 9) {
			positionX += HerdingCats.SQUARE_SIZE;
			positionY -= HerdingCats.SQUARE_SIZE;
		}
	}

	/*
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
					positionY += HerdingCats.SQUARE_SIZE;
				}
				break;
			case 1:
				if (squareDog != 5) {
					squareCat = 5;
					positionX += HerdingCats.SQUARE_SIZE;
					positionY += HerdingCats.SQUARE_SIZE;
				}
				break;
			case 2:
				if (squareDog != 2) {
					squareCat = 2;
					positionX += HerdingCats.SQUARE_SIZE;
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
					positionX -= HerdingCats.SQUARE_SIZE;
				}
				break;
			case 1:
				if (squareDog != 4) {
					squareCat = 4;
					positionX -= HerdingCats.SQUARE_SIZE;
					positionY += HerdingCats.SQUARE_SIZE;
				}
				break;
			case 2:
				if (squareDog != 5) {
					squareCat = 5;
					positionY += HerdingCats.SQUARE_SIZE;
				}
				break;
			case 3:
				if (squareDog != 6) {
					squareCat = 6;
					positionX += HerdingCats.SQUARE_SIZE;
					positionY += HerdingCats.SQUARE_SIZE;
				}
				break;
			case 4:
				if (squareDog != 3) {
					squareCat = 3;
					positionX += HerdingCats.SQUARE_SIZE;
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
					positionX -= HerdingCats.SQUARE_SIZE;
				}
				break;
			case 1:
				if (squareDog != 5) {
					squareCat = 5;
					positionX -= HerdingCats.SQUARE_SIZE;
					positionY += HerdingCats.SQUARE_SIZE;
				}
				break;
			case 2:
				if (squareDog != 6) {
					squareCat = 6;
					positionY += HerdingCats.SQUARE_SIZE;
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
					positionY += HerdingCats.SQUARE_SIZE;
				}
				break;
			case 1:
				if (squareDog != 8) {
					squareCat = 8;
					positionY += HerdingCats.SQUARE_SIZE;
					positionX += HerdingCats.SQUARE_SIZE;
				}
				break;
			case 2:
				if (squareDog != 5) {
					squareCat = 5;
					positionX += HerdingCats.SQUARE_SIZE;
				}
				break;
			case 3:
				if (squareDog != 2) {
					squareCat = 2;
					positionX += HerdingCats.SQUARE_SIZE;
					positionY -= HerdingCats.SQUARE_SIZE;
				}
				break;
			case 4:
				if (squareDog != 1) {
					squareCat = 1;
					positionY -= HerdingCats.SQUARE_SIZE;
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
					positionX -= HerdingCats.SQUARE_SIZE;
					positionY -= HerdingCats.SQUARE_SIZE;
				}
				break;
			case 1:
				if (squareDog != 2) {
					squareCat = 2;
					positionY -= HerdingCats.SQUARE_SIZE;
				}
				break;
			case 2:
				if (squareDog != 3) {
					squareCat = 3;
					positionX += HerdingCats.SQUARE_SIZE;
					positionY -= HerdingCats.SQUARE_SIZE;
				}
				break;
			case 3:
				if (squareDog != 4) {
					squareCat = 4;
					positionX -= HerdingCats.SQUARE_SIZE;
				}
				break;
			case 4:
				if (squareDog != 6) {
					squareCat = 6;
					positionX += HerdingCats.SQUARE_SIZE;
				}
				break;
			case 5:
				if (squareDog != 7) {
					squareCat = 7;
					positionX -= HerdingCats.SQUARE_SIZE;
					positionY += HerdingCats.SQUARE_SIZE;
				}
				break;
			case 6:
				if (squareDog != 8) {
					squareCat = 8;
					positionY += HerdingCats.SQUARE_SIZE;
				}
				break;
			case 7:
				if (squareDog != 9) {
					squareCat = 9;
					positionX += HerdingCats.SQUARE_SIZE;
					positionY += HerdingCats.SQUARE_SIZE;
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
					positionY += HerdingCats.SQUARE_SIZE;
				}
				break;
			case 1:
				if (squareDog != 8) {
					squareCat = 8;
					positionX -= HerdingCats.SQUARE_SIZE;
					positionY += HerdingCats.SQUARE_SIZE;
				}
				break;
			case 2:
				if (squareDog != 5) {
					squareCat = 5;
					positionX -= HerdingCats.SQUARE_SIZE;
				}
				break;
			case 3:
				if (squareDog != 2) {
					squareCat = 2;
					positionX -= HerdingCats.SQUARE_SIZE;
					positionY -= HerdingCats.SQUARE_SIZE;
				}
				break;
			case 4:
				if (squareDog != 3) {
					squareCat = 3;
					positionY -= HerdingCats.SQUARE_SIZE;
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
					positionX += HerdingCats.SQUARE_SIZE;
				}
				break;
			case 1:
				if (squareDog != 5) {
					squareCat = 5;
					positionX += HerdingCats.SQUARE_SIZE;
					positionY -= HerdingCats.SQUARE_SIZE;
				}
				break;
			case 2:
				if (squareDog != 4) {
					squareCat = 4;
					positionY -= HerdingCats.SQUARE_SIZE;
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
					positionX -= HerdingCats.SQUARE_SIZE;
				}
				break;
			case 1:
				if (squareDog != 4) {
					squareCat = 4;
					positionX -= HerdingCats.SQUARE_SIZE;
					positionY -= HerdingCats.SQUARE_SIZE;
				}
				break;
			case 2:
				if (squareDog != 5) {
					squareCat = 5;
					positionY -= HerdingCats.SQUARE_SIZE;
				}
				break;
			case 3:
				if (squareDog != 6) {
					squareCat = 6;
					positionX += HerdingCats.SQUARE_SIZE;
					positionY -= HerdingCats.SQUARE_SIZE;
				}
				break;
			case 4:
				if (squareDog != 9) {
					squareCat = 9;
					positionX += HerdingCats.SQUARE_SIZE;
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
					positionX -= HerdingCats.SQUARE_SIZE;
				}
				break;
			case 1:
				if (squareDog != 5) {
					squareCat = 5;
					positionX -= HerdingCats.SQUARE_SIZE;
					positionY -= HerdingCats.SQUARE_SIZE;
				}
				break;
			case 2:
				if (squareDog != 6) {
					squareCat = 6;
					positionY -= HerdingCats.SQUARE_SIZE;
				}
				break;
			default:
				break;
			}
		} else {
			System.out.println("Error - moveToAdjacentSquare()");
		}
	}// End - moveToAdjacentSquare()

	// Check the relative position between cat and dog for the move() method to
	// decide where the cat should the go.
	private int checkRelativePosition(int dogPositionX, int dogPositionY) {

		// It checks relative position by comparing dog positions and cat positions.

		if (positionX < dogPositionX - HerdingCats.SQUARE_SIZE / 2
				&& positionY > dogPositionY + HerdingCats.SQUARE_SIZE / 2) {
			// dog is on the bottom right
			return BOTTOMRIGHT;
		} else if (positionX >= dogPositionX - HerdingCats.SQUARE_SIZE / 2
				&& positionX <= dogPositionX + HerdingCats.SQUARE_SIZE / 2
				&& positionY >= dogPositionY + HerdingCats.SQUARE_SIZE / 2) {
			// dog is on the square bellow
			return BOTTOM;
		} else if (positionX > dogPositionX + HerdingCats.SQUARE_SIZE / 2
				&& positionY > dogPositionY + HerdingCats.SQUARE_SIZE / 2) {
			// dog is on the bottom left
			return BOTTOMLEFT;
		} else if (positionX > dogPositionX + HerdingCats.SQUARE_SIZE / 2
				&& positionY >= dogPositionY - HerdingCats.SQUARE_SIZE / 2
				&& positionY <= dogPositionY + HerdingCats.SQUARE_SIZE / 2) {
			// dog is on the square on the left
			return LEFT;
		} else if (positionX > dogPositionX + HerdingCats.SQUARE_SIZE / 2
				&& positionY < dogPositionY - HerdingCats.SQUARE_SIZE / 2) {
			// dog is on the top left
			return TOPLEFT;
		} else if (positionX >= dogPositionX - HerdingCats.SQUARE_SIZE / 2
				&& positionX <= dogPositionX + HerdingCats.SQUARE_SIZE / 2
				&& positionY <= dogPositionY - HerdingCats.SQUARE_SIZE / 2) {
			// dog is on the square on the top
			return TOP;
		} else if (positionX < dogPositionX - HerdingCats.SQUARE_SIZE / 2
				&& positionY < dogPositionY - HerdingCats.SQUARE_SIZE / 2) {
			// dog is on the top right
			return TOPRIGHT;
		} else if (positionX < dogPositionX - HerdingCats.SQUARE_SIZE / 2
				&& positionY >= dogPositionY - HerdingCats.SQUARE_SIZE / 2
				&& positionY <= dogPositionY + HerdingCats.SQUARE_SIZE / 2) {
			// on the right
			return RIGHT;
		} else {
			// the dog is at the same square as the dog.
			return SAMESQUARE;
		}
	}// End - checkRelativePosition()

	/*
	 * This method moves the cat until the cat is on the same square as the dog. The
	 * methods such as moveTopLeft(), moveUp() and so on, they only update the
	 * positionX and positionY for the drawCat() method to display the cat on the
	 * grid.
	 */
	private void moveToDog(int squareDog, int dogPositionX, int dogPositionY) {
		while (squareCat != squareDog) {
			if (checkRelativePosition(dogPositionX, dogPositionY) == TOPLEFT) {
				moveTopLeft();
				squareCat += 2;
			} else if (checkRelativePosition(dogPositionX, dogPositionY) == TOP) {
				moveUp();
				squareCat += 3;
			} else if (checkRelativePosition(dogPositionX, dogPositionY) == TOPRIGHT) {
				moveTopRight();
				squareCat += 4;
			} else if (checkRelativePosition(dogPositionX, dogPositionY) == RIGHT) {
				moveRight();
				squareCat++;
			} else if (checkRelativePosition(dogPositionX, dogPositionY) == BOTTOMRIGHT) {
				moveBottomRight();
				squareCat -= 2;
			} else if (checkRelativePosition(dogPositionX, dogPositionY) == BOTTOM) {
				moveDown();
				squareCat -= 3;
			} else if (checkRelativePosition(dogPositionX, dogPositionY) == BOTTOMLEFT) {
				moveBottomLeft();
				squareCat -= 4;
			} else if (checkRelativePosition(dogPositionX, dogPositionY) == LEFT) {
				moveLeft();
				squareCat--;
			} else {
				System.out.println("Error - Cat Class moveToDog()" + squareDog + " " + squareCat);
			}
		}
	}// End - moveToDog()

}// End class - Cat
