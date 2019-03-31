package buttons;

import game.Game;

public class startButton extends Button {
	private Game game;

	public startButton(Game game, String text, int fontSize) {
		super(text, fontSize);
		this.game = game;
	}
	
	public startButton(Game game, String text, int fontSize, int xSize, int ySize) {
		super(text, fontSize, xSize, ySize);
		this.game = game;
	}

	@Override
	public void clickedButton() {
		game.levelSelector();
	}
	
}
