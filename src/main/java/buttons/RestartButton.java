package buttons;

import game.Game;
import game.PauseMenu;

public class RestartButton extends Button{
	private PauseMenu pauseMenu;

	public RestartButton(String text, int fontSize, Game game, PauseMenu pauseMenu) {
		super(text, fontSize, game);
		this.pauseMenu = pauseMenu;
	}

	@Override
	public void clickedButton(Game game) {
		game.resetPlayer();
		game.getView().getViewport().setX(0);
		pauseMenu.setDrawPauseMenu(false);
		
	}

}
