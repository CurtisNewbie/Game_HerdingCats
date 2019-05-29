package guiView;

import controllerClasses.ViewModelController;

// Text GUI here
public class MainExe {

	public static void main(String args[]) {
		GameView view = new GameView();
		ViewModelController controller = new ViewModelController(view);
		view.getGameFrame().setVisible(true);
	}
}
