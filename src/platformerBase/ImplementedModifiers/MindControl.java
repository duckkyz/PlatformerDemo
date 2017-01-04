
package platformerBase.ImplementedModifiers;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import platformerBase.Game;
import platformerBase.DrawableClasses.Modifier;

public class MindControl extends Modifier {
	
	private int speedBoost = 10;
	private boolean doneEffected = false;
	private int effectCounter = 0;

	public MindControl(int wavesDuration, int xPos, int yPos, boolean heroOnly) {						//Uses default -8 speed modifier
		super(wavesDuration, xPos, yPos, heroOnly);
		try{
			setGraphic(ImageIO.read(new File("Drawable_Images/Modifiers.png")));
		}
		catch(IOException ex){
			
		}
		setTileWidth(32);
		setTileHeight(32);
		setActionStep(0);
		setActionSequence(2);
	}

	public void addModifier() {
		getTarget().setSpeed(getTarget().getSpeed() + speedBoost);
	}

	public void removeModifier() {
		if(getTarget() != null){
			getTarget().setSpeed(getTarget().getSpeed() - speedBoost);
		}
		target = null;
		Game.removeDrawable(this);
	}

	public void doLogic(){
		if(getIsActivated()){
			if(getTarget() == null){
				Game.removeDrawable(this);
				return;
			}
			int xDist = this.getXPos() - getTarget().getXPos();
			int yDist = this.getYPos() - getTarget().getYPos();
			
			int cyclesLeft = 0;
			
			if(xDist > getTarget().getTileWidth()){
				getTarget().setDirection(90);
				cyclesLeft = xDist/getTarget().getSpeed();
			}
			else if(xDist < (-1)*getTarget().getTileWidth()){
				getTarget().setDirection(270);
				cyclesLeft = (-1 * xDist)/getTarget().getSpeed();

			}
			else{
				if(yDist > 0){
					getTarget().setDirection(180);
					cyclesLeft = yDist/getTarget().getSpeed();
				}
				else if(yDist < 0){
					getTarget().setDirection(0);
					cyclesLeft = (-1 * yDist)/getTarget().getSpeed();
				}
			}
			
			getTarget().setIsMoving(true);
			
			if(yDist == 0 & xDist == 0){
				getTarget().setIsMoving(false);
			}
			
			if(getTarget().getCollisionCounter() > 2){
				removeModifier();
				Game.removeDrawable(this);
				return;				
			}
			if((effectCounter > 45) | (cyclesLeft < 2)){
				removeModifier();
				Game.removeDrawable(this);
				return;
			}
			++effectCounter;
		}
	}
}
