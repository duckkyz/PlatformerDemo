package platformerBase.ImplementedModifiers;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import platformerBase.Game;
import platformerBase.DrawableClasses.Modifier;

public class Knockback extends Modifier {
	
	private int speedBoost = 10;
	private boolean doneEffected = false;
	private int effectCounter = 0;

	public Knockback(int wavesDuration, int xPos, int yPos, boolean heroOnly) {						//Uses default -8 speed modifier
		super(wavesDuration, xPos, yPos, heroOnly);
	}

	public void addModifier() {
		getTarget().setSpeed(getTarget().getSpeed() + speedBoost);
	}

	public void removeModifier() {
		if(getTarget() != null){
			getTarget().setSpeed(getTarget().getSpeed() - speedBoost);
			getTarget().setStaticMovement(false);
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
			
			getTarget().setIsMoving(true);
			int direction = getTarget().getEffectiveDirection();
			if(direction == 0){
				getTarget().setDirection(180);
			}
			else if(direction == 90){
				getTarget().setDirection(270);
			}
			else if(direction == 180){
				getTarget().setDirection(0);
			}
			else if(direction == 270){
				getTarget().setDirection(90);
			}
			getTarget().setStaticMovement(true);
			if((effectCounter > 10)){
				removeModifier();
				Game.removeDrawable(this);
				return;
			}
			++effectCounter;
		}
	}
	public void paint(Graphics imageGraphics) {	
		
	}
}
