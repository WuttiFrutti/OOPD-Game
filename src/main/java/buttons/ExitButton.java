package buttons;

import game.Game;
import game.PauseMenu;

public class ExitButton extends Button{
	private PauseMenu pauseMenu;
	
	public ExitButton(String text, int fontSize, Game game, PauseMenu pauseMenu) {
		super(text, fontSize, game);
		this.pauseMenu = pauseMenu;
	}

	@Override
	public void clickedButton(Game game) {
		pauseMenu.setDrawPauseMenu(false);
		game.deleteAllGameOBjects();
		game.setupGame();
		game.resetTileMap();
		
	}

}
