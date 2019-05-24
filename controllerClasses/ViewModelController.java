package controllerClasses;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

import guiView.GameBoardPanel;
import guiView.GameView;
import models.Cat;
import models.Dog;
import models.HerdingCats;

public class ViewModelController {

    private final int MAX_RULE = 6;

    private GameView gameFrame;
    private ArrayList<Cat> cats;
    private Dog dog;
//    private HerdingCats mainGameModel;

    public ViewModelController(GameView gameFrame) {
	this.gameFrame = gameFrame;
	this.initiateGame();
    }

    private void initiateGame() {
	Dimension gameBoardSize = gameFrame.getGameBoardSize();
	cats = new ArrayList<>();
	for (int x = 0; x < 10; x++) {
	    cats.add(new Cat(
		    GameBoardPanel.CAT_WIDTH + new Random().nextInt(gameBoardSize.width - GameBoardPanel.CAT_WIDTH + 1),
		    GameBoardPanel.CAT_HEIGHT
			    + new Random().nextInt(gameBoardSize.height + GameBoardPanel.CAT_HEIGHT + 1),
		    1 + new Random().nextInt(MAX_RULE)));
	}
	int dogSquare = new Random().nextInt(9) + 1;
	int dogX = getCentrePositionX(dogSquare, gameBoardSize.width);
	int dogY = getCentrePositionY(dogSquare, gameBoardSize.height);

	dog = new Dog(dogSquare, dogX, dogY);

	ArrayList<int[]> catsPosition = new ArrayList<>();
	for (Cat eachCat : cats) {
	    catsPosition.add(new int[] { eachCat.getPositionX(), eachCat.getPositionY() });
	}
	gameFrame.setUpGameBoard(catsPosition, dogX, dogY);
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
}
