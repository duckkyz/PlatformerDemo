package platformerBase.ImplementedModifiers;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import platformerBase.Game;
import platformerBase.DrawableClasses.Modifier;

public class SlowDown extends Modifier {
	
	private int amountSlowDown = 8;
	private int timeSlowedDown = 500;
	private int effectCounter = 0;

	public SlowDown(int wavesDuration, int xPos, int yPos, boolean heroOnly) {						//Uses default -8 speed modifier
		super(wavesDuration, xPos, yPos, heroOnly);
		try{
			setGraphic(ImageIO.read(new File("Drawable_Images/Modifiers.png")));
		}
		catch(IOException ex){
			
		}
		setTileWidth(32);
		setTileHeight(32);
		setActionStep(0);
		setActionSequence(0);
	}
	
	public SlowDown(int wavesDuration, int xPos, int yPos, boolean heroOnly, int slowDown) {			//If you want a specific speed modifier
		super(wavesDuration, xPos, yPos, heroOnly);
		try{
			setGraphic(ImageIO.read(new File("Drawable_Images/Modifiers.png")));
		}
		catch(IOException ex){
			
		}
		setTileWidth(32);
		setTileHeight(32);
		amountSlowDown = slowDown;
		setActionStep(1);
		setActionSequence(0);
	}

	public void addModifier() {
		if(getTarget().getSpeed() > (amountSlowDown)){
			getTarget().setSpeed(getTarget().getSpeed() - amountSlowDown);
		}
		else{
			target = null;	
			Game.removeDrawable(this);
		}
	}

	public void removeModifier() {
		if(getTarget() != null){
			getTarget().setSpeed(getTarget().getSpeed() + amountSlowDown);
		}
		target = null;
		Game.removeDrawable(this);
	}

	public void doLogic(){
		if(getIsActivated()){
			if(effectCounter > timeSlowedDown){
				removeModifier();
				Game.removeDrawable(this);
				return;
			}
			++effectCounter;
		}
	}
}
