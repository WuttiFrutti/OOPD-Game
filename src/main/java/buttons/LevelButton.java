package buttons;

import game.Game;

public class LevelButton extends Button{
	private int level;


	public LevelButton(Game game, String text, int fontSize, int xSize, int ySize, int level) {
		super(text, fontSize, xSize, ySize, game);
		this.level = level;
	}

	@Override
	public void clickedButton(Game game) {
		game.startGame(level);
	}

	
}
