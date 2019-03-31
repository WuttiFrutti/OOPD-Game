package buttons;

import game.Game;

public class LevelButton extends Button{
	private int level;
	private Game game;

	public LevelButton(Game game, String text, int fontSize, int xSize, int ySize, int level) {
		super(text, fontSize, xSize, ySize);
		this.level = level;
		this.game = game;
	}

	@Override
	public void clickedButton() {
		game.startGame(level);
	}

	
}
