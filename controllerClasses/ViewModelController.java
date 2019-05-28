package controllerClasses;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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

//    private HerdingCats mainGameModel;

    public ViewModelController(GameView gameView) {
	this.gameView = gameView;
	gameBoardSize = gameView.getGameFrame().getSize();
	this.initiateGame();

	// Associate listeners:
	gameView.addResizingComponentListener(new WindowResizingHandler());
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
	    catsPosition.add(new double[] { eachCat.getPositionX(), eachCat.getPositionY() });
	}
	// New dog
	int dogSquare = new Random().nextInt(9) + 1;
	dog = new Dog(dogSquare);
	int dogX = getCentrePositionX(dogSquare, gameBoardSize.width);
	int dogY = getCentrePositionY(dogSquare, gameBoardSize.height);

	gameView.setUpGameBoard(catsPosition, dogX, dogY);
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

	    double yChange = ((double) gameBoardSize.height / (double) heightPrev);
	    double xChange = ((double) gameBoardSize.width / (double) widthPrev);
	    if (!(xChange == 1.0 && yChange == 1.0)) {
		for (Cat c : cats) {
		    double catX = c.getPositionX();
		    double catY = c.getPositionY();
		    // Update cats

		    int thisCatSquare = c.getSquareCat();
		    int eachSquareWidth = gameBoardSize.width / 3;
		    int eachSquareHeight = gameBoardSize.height / 3;

		    double xDistanceToCentre = ((double) getCentrePositionX(thisCatSquare, widthPrev) - catX) * xChange;
		    double yDistanceToCentre = ((double) getCentrePositionY(thisCatSquare, heightPrev) - catY)
			    * yChange;

		    c.setPositionX((getCentrePositionX(thisCatSquare, gameBoardSize.width) - xDistanceToCentre));
		    c.setPositionY((getCentrePositionY(thisCatSquare, gameBoardSize.height) - yDistanceToCentre));
		}

		// update cots displayed positions
		ArrayList<double[]> newCatsPositions = new ArrayList<>();
		for (Cat c : cats) {
		    newCatsPositions.add(new double[] { c.getPositionX(), c.getPositionY() });
		}
		gameView.getPanel().updateCatPositions(newCatsPositions);
	    }
	}
    }

}
