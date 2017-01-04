package platformerBase.DrawableClasses;

import java.awt.Graphics;

import platformerBase.Game;

public abstract class Modifier extends Sprite{

	protected Sprite target;		//The unit the Modifier is modifying
	private int duration;			//The number of waves the target stays modified
	private boolean activated = false;		//The modifier has been given a target
	private boolean heroOnly;		//If true, only the hero should be able to activate the modifier
	private int removalWave;		//The wave that the modifier should end
	
	public Modifier(int wavesDuration, int xPos, int yPos, boolean heroOnly) {
		super(0, xPos, yPos);
		this.duration = wavesDuration;
		this.heroOnly = heroOnly;
		removalWave = Game.getCurrentWave() + duration;

	}
	
	public abstract void addModifier();									//Add modifier to target
	public abstract void removeModifier();								//Reverse modifier to target if necessary
	
	public void activate(Sprite target) {				//Call this method when a Sprite collides with the modifier and satisfies the heroOnly condition
		this.target = target;
		activated = true;
		this.addModifier();
	}
	
	public void move() {
		if(Game.getCurrentWave() > removalWave) {
			this.removeModifier();										
			
			//Removes all references so modifier is garbage collected
			target = null;
			Game.removeDrawable(this);
		}
	}
	
	public void doLogic() {
		
	}
	
	public void paint(Graphics imageGraphics) {	
		if(activated == false){
			imageGraphics.drawImage(getGraphic(), getXPos(), getYPos(), getXPos()+getTileWidth(), getYPos()+getTileHeight(), getActionStep()*getTileWidth(), getActionSequence()*getTileHeight(), getActionStep()*getTileWidth()+getTileWidth(), getActionSequence()*getTileHeight()+getTileHeight(), null);
		}
	}
	
	public Sprite getTarget() {
		return target;
	}
	
	public boolean getIsActivated(){
		return activated;
	}
	public boolean getHeroOnly() {										//Used for when Sprite collide with modifier
		return heroOnly;
	}
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	
}
