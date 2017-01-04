package platformerBase.ImplementedModifiers;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import platformerBase.DrawableClasses.Modifier;

public class Speedup extends Modifier {
	
	private int amountSpeedup = 8;

	public Speedup(int wavesDuration, int xPos, int yPos, boolean heroOnly) {						//Uses default +8 speed modifier
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
	
	public Speedup(int wavesDuration, int xPos, int yPos, boolean heroOnly, int speedup) {			//If you want a specific speed modifier
		super(wavesDuration, xPos, yPos, heroOnly);
		try{
			setGraphic(ImageIO.read(new File("Drawable_Images/Modifiers.png")));
		}
		catch(IOException ex){
			
		}
		setTileWidth(32);
		setTileHeight(32);
		amountSpeedup = speedup;
		setActionStep(0);
		setActionSequence(0);
	}

	public void addModifier() {
		getTarget().setSpeed(getTarget().getSpeed() + amountSpeedup);
	}

	public void removeModifier() {
		if(getTarget() != null){
			getTarget().setSpeed(getTarget().getSpeed() - amountSpeedup);
		}
	}

}
