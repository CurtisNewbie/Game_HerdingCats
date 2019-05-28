package controllerClasses;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import guiView.GameBoardPanel;
import guiView.GameView;
import models.Cat;
import models.Dog;

public class ViewModelController {

    private final int MAX_RULE = 6;

    private GameView gameView;
    private ArrayList<Cat> cats;
    private Dog dog;
    private Dimension gameBoardSize;

    public ViewModelController(GameView gameView) {
	this.gameView = gameView;
	gameBoardSize = gameView.getGameFrame().getSize();
	this.initiateGame();

	// Associate listeners:
	gameView.addResizingComponentListener(new WindowResizingHandler());
	gameView.addKeyboardControlListener(new KeyControlHandler());
    }

    private void initiateGame() {

	// new cats
	cats = new ArrayList<>();
	for (int x = 0; x < 10; x++) {
	    cats.add(new Cat(
		    GameBoardPanel.CAT_WIDTH + new Random().nextInt(gameBoardSize.width - GameBoardPanel.CAT_WIDTH + 1),
		    GameBoardPanel.CAT_HEIGHT
			    + new Random().nextInt(gameBoardSize.height + GameBoardPanel.CAT_HEIGHT + 1),
		    1 + new Random().nextInt(MAX_RULE)));
	}
	ArrayList<double[]> catsPosition = new ArrayList<>();
	for (Cat eachCat : cats) {
	    eachCat.setSquareHeight(gameBoardSize.height / 3);
	    eachCat.setSquareWidth(gameBoardSize.width / 3);
	    eachCat.checkSquare();
	    catsPosition.add(new double[] { eachCat.getPositionX(), eachCat.getPositionY() });
	}
	// New dog
	int dogSquare = new Random().nextInt(8) + 1;
	dog = new Dog(dogSquare);
	int dogX = getCentrePositionX(dogSquare, gameBoardSize.width);
	int dogY = getCentrePositionY(dogSquare, gameBoardSize.height);

	gameView.setUpGameBoard(catsPosition, dogX, dogY);
	gameView.getGameFrame().repaint();
    }

    private int getCentrePositionX(int square, int gameBoardWidth) {
	switch (square) {
	case 1:
	    return (gameBoardWidth / 6) * 1;
	case 2:
	    return (gameBoardWidth / 6) * 3;
	case 3:
	    return (gameBoardWidth / 6) * 5;
	case 4:
	    return (gameBoardWidth / 6) * 1;
	case 5:
	    return (gameBoardWidth / 6) * 3;
	case 6:
	    return (gameBoardWidth / 6) * 5;
	case 7:
	    return (gameBoardWidth / 6) * 1;
	case 8:
	    return (gameBoardWidth / 6) * 3;
	case 9:
	    return (gameBoardWidth / 6) * 5;
	default:
	    return 0;
	}
    }

    private int getCentrePositionY(int square, int gameBoardHeigth) {
	switch (square) {
	case 1:
	    return (gameBoardHeigth / 6) * 5;
	case 2:
	    return (gameBoardHeigth / 6) * 5;
	case 3:
	    return (gameBoardHeigth / 6) * 5;
	case 4:
	    return (gameBoardHeigth / 6) * 3;
	case 5:
	    return (gameBoardHeigth / 6) * 3;
	case 6:
	    return (gameBoardHeigth / 6) * 3;
	case 7:
	    return (gameBoardHeigth / 6) * 1;
	case 8:
	    return (gameBoardHeigth / 6) * 1;
	case 9:
	    return (gameBoardHeigth / 6) * 1;
	default:
	    return 0;
	}
    }

    private class WindowResizingHandler extends ComponentAdapter {

	@Override
	public void componentResized(ComponentEvent e) {
	    super.componentResized(e);

	    int widthPrev = gameBoardSize.width;
	    int heightPrev = gameBoardSize.height;
	    gameBoardSize = gameView.getPanel().getSize();

	    // update dog displayed positions
	    gameView.getPanel().setDogX(getCentrePositionX(dog.getSquare(), gameBoardSize.width));
	    gameView.getPanel().setDogY(getCentrePositionY(dog.getSquare(), gameBoardSize.height));

	    // the changing rates of height and width.
	    double yChange = ((double) gameBoardSize.height / (double) heightPrev);
	    double xChange = ((double) gameBoardSize.width / (double) widthPrev);
	    if (!(xChange == 1.0 && yChange == 1.0)) {
		for (Cat c : cats) {
		    // original positions of the cat
		    double catX = c.getPositionX();
		    double catY = c.getPositionY();

		    // the centre position of the square that the cat is on
		    int thisCatSquare = c.getSquareCat();

		    // calculate the x and y distance between the cat and the centre point, and
		    // update the distance according to the changing rates.
		    double xDistanceToCentre = ((double) getCentrePositionX(thisCatSquare, widthPrev) - catX) * xChange;
		    double yDistanceToCentre = ((double) getCentrePositionY(thisCatSquare, heightPrev) - catY)
			    * yChange;
		    // update the new positions of the cat based on the distance between the cat and
		    // the centre point.
		    c.setPositionX((getCentrePositionX(thisCatSquare, gameBoardSize.width) - xDistanceToCentre));
		    c.setPositionY((getCentrePositionY(thisCatSquare, gameBoardSize.height) - yDistanceToCentre));
		}

		// update cats displayed positions
		ArrayList<double[]> newCatsPositions = new ArrayList<>();
		for (Cat c : cats) {
		    newCatsPositions.add(new double[] { c.getPositionX(), c.getPositionY() });
		}
		gameView.getPanel().updateCatPositions(newCatsPositions);
	    }
	}
    }

    private class KeyControlHandler extends KeyAdapter {
	@Override
	public void keyPressed(KeyEvent e) {
	    // TODO Auto-generated method stub
	    super.keyPressed(e);
	    int dogCurSquare = dog.getSquare();

	    if (e.getKeyCode() == KeyEvent.VK_UP) {
		if (dogCurSquare != 7 && dogCurSquare != 8 && dogCurSquare != 9) {
		    dog.setSquare(dogCurSquare + 3);
		}
	    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
		if (dogCurSquare != 1 && dogCurSquare != 2 && dogCurSquare != 3) {
		    dog.setSquare(dogCurSquare - 3);
		}
	    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
		if (dogCurSquare != 1 && dogCurSquare != 4 && dogCurSquare != 7) {
		    dog.setSquare(dogCurSquare - 1);
		}
	    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
		if (dogCurSquare != 3 && dogCurSquare != 6 && dogCurSquare != 9) {
		    dog.setSquare(dogCurSquare + 1);
		}
	    }
	    int dogNewX = getCentrePositionX(dog.getSquare(), gameBoardSize.width);
	    int dogNewY = getCentrePositionY(dog.getSquare(), gameBoardSize.height);
	    gameView.getPanel().setDogX(dogNewX);
	    gameView.getPanel().setDogY(dogNewY);

	    // Update cats positions
	    for (Cat c : cats) {
		c.setSquareHeight(gameBoardSize.height);
		c.setSquareWidth(gameBoardSize.width);
		c.move(dog.getSquare());
	    }
	    ArrayList<double[]> newCatsPositions = new ArrayList<>();
	    // displayed cats positions
	    for(Cat c: cats) {
		newCatsPositions.add(new double[] {c.getPositionX(),c.getPositionY()});
	    }
	    gameView.getPanel().updateCatPositions(newCatsPositions);
	    gameView.getGameFrame().repaint();
	}
    }
}
