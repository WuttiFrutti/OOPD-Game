package buttons;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import processing.core.PGraphics;

public abstract class Button extends SpriteObject{
	private Sprite sprite;

	public Button(Sprite sprite) {
		super(sprite);
		this.sprite = sprite;
	}
	
	public abstract void clickedButton();

	@Override
	public void update() {
		
	}
	
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	@Override
	public void draw(PGraphics g) {
		g.image(sprite.getPImage(), x, y);
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY, int button) {
		
		if(mouseX < x + sprite.getWidth() && mouseX > x && mouseY > y && mouseY < y + sprite.getHeight()) {
			clickedButton();
		}
    }

}
