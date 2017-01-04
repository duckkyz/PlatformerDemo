package platformerBase.ImplementedModifiers;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import platformerBase.Game;
import platformerBase.DrawableClasses.Modifier;
import platformerBase.DrawableClasses.Sprite;

public class BearTrap extends Modifier {
	
	private int trapDuration = 500;
	private int timeTrapped = 40;
	private int counter = 0;
	private int oldSpeed = 0;

	public BearTrap(int wavesDuration, int xPos, int yPos, boolean heroOnly) {
		super(wavesDuration, xPos, yPos, heroOnly);
		try{
			setGraphic(ImageIO.read(new File("Drawable_Images/BearTrap.png")));
		}
		catch(IOException ex){
			
		}
		setTileWidth(64);
		setTileHeight(40);
		setActionStep(0);
		setActionSequence(0);
		//LOL this isnt a bug, you intentionally set this.
		setTopHitBox(18);
	}
	
	public BearTrap(int wavesDuration, int xPos, int yPos, boolean heroOnly, int timeTrapped) {
		super(wavesDuration, xPos, yPos, heroOnly);
		try{
			setGraphic(ImageIO.read(new File("Drawable_Images/Modifiers.png")));
		}
		catch(IOException ex){
			
		}
		this.timeTrapped = timeTrapped;
		setTileWidth(64);
		setTileHeight(40);
		setActionStep(0);
		setActionSequence(0);
		setTopHitBox(18);
	}
	
	public void doLogic() {
		if(getIsActivated() == false) {
			if(counter >= trapDuration) {
				Game.removeDrawable(this);
			}
			else{
				counter++;
			}
		}
		else {
			if(counter <= timeTrapped) {
				if(getActionStep() < 3){
					setActionStep(getActionStep()+1);
				}
				target.setIsMoving(false);
				counter++;
			}
			else {
				removeModifier();
			}
		}
	}
	
	public void activate(Sprite target) {				//Call this method when a Sprite collides with the modifier and satisfies the heroOnly condition
		this.target = target;
		setActivated(true);
		this.addModifier();
		counter = 0;
		setXPos(target.getXPos());
		setYPos(target.getYPos()+target.getTileHeight()-getTileHeight());
	}

	public void addModifier() {
		target.setIsMoving(false);
	}

	public void removeModifier() {
		Game.removeDrawable(this);
		target = null;
	}
	
	public void paint(Graphics imageGraphics) {	
		imageGraphics.drawImage(getGraphic(), getXPos(), getYPos(), getXPos()+getTileWidth(), getYPos()+getTileHeight(), getActionStep()*getTileWidth(), getActionSequence()*getTileHeight(), getActionStep()*getTileWidth()+getTileWidth(), getActionSequence()*getTileHeight()+getTileHeight(), null);
	}

}
