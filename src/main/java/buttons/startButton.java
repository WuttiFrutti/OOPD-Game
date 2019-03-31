package buttons;

import game.Game;
import nl.han.ica.oopg.objects.Sprite;

public class startButton extends Button{
	private Game game;
	
	public startButton( Game game, Sprite sprite) {
		super(sprite);
		this.game = game;
	}

	@Override
	public void clickedButton() {
		game.startGame();
	}

}
