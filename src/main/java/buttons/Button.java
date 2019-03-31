package buttons;

import game.Game;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import processing.core.PGraphics;

public abstract class Button extends SpriteObject{
	private Sprite sprite = new Sprite(Game.MEDIA_URL.concat("Start_Game.png"));
	private Sprite hoverSprite = new Sprite(Game.MEDIA_URL.concat("Start_Game_Hover.png"));
	private boolean hover;
	private String text;
	private int fontSize, xSize, ySize;

	public Button(String text, int fontSize, int xSize, int ySize) {
		super(new Sprite(Game.MEDIA_URL.concat("Start_Game.png")));
		this.text = text;
		this.fontSize = fontSize;
		sprite.resize(xSize, ySize);
		hoverSprite.resize(xSize, ySize);
	}
	
	public Button(String text, int fontSize) {
		super(new Sprite(Game.MEDIA_URL.concat("Start_Game.png")));
		this.text = text;
		this.fontSize = fontSize;
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
		if(hover) {
			g.image(hoverSprite.getPImage(), x, y);
		}else {
			g.image(sprite.getPImage(), x, y);
		}
	        g.textSize(fontSize);
	        g.textAlign(CENTER);
	        g.text(text, x + (sprite.getWidth() / 2), y + (sprite.getHeight() / 2) + (fontSize / 4));
	}

	@Override
	public void mouseReleased(int mouseX, int mouseY, int button) {
		if(mouseX < x + sprite.getWidth() && mouseX > x && mouseY > y && mouseY < y + sprite.getHeight()) {
			clickedButton();
		}
    }
	
	public void mouseHover(int mouseX, int mouseY) {
		if(mouseX < x + sprite.getWidth() && mouseX > x && mouseY > y && mouseY < y + sprite.getHeight()) {
			hover = true;
		}else {
			hover = false;
		}
	}
	
	@Override
	public void mouseMoved(int mouseX, int mouseY) {
		mouseHover(mouseX,mouseY);
    }

    @Override
    public void mouseDragged(int mouseX, int mouseY, int button) {
    	mouseHover(mouseX,mouseY);
    }
	
}
