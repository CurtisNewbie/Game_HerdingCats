package controllerClasses;

import guiView.GameBoardPanel;
import guiView.GameView;
import models.Cat;
import models.Dog;
import models.HerdingCats;

public class ViewModelController {

    private GameBoardPanel gameBoard;
    private GameView gameFrame;

    private Cat cat;
    private Dog dog;
    private HerdingCats mainGameModel;

    public ViewModelController(Cat cat, Dog dog, HerdingCats mainGameModel, GameBoardPanel gameBoard,
	    GameView gameFrame) {
	this.gameBoard = gameBoard;
	this.gameFrame = gameFrame;
	
	this.cat = cat;
	this.dog = dog;
	this.mainGameModel = mainGameModel;
    }
    
    
}
