package buttons;

import game.Game;

public class startButton extends Button {


	public startButton(String text, int fontSize, Game game) {
		super(text, fontSize, game);
	}
	
	public startButton(String text, int fontSize, int xSize, int ySize, Game game) {
		super(text, fontSize, xSize, ySize, game);

	}


	@Override
	public void clickedButton(Game game) {
		game.levelSelector();
		
	}
	
}
