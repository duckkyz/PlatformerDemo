package platformerBase.Items;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import platformerBase.Game;
import platformerBase.DrawableClasses.*;
import platformerBase.ImplementedModifiers.*;

public class Bomb extends Modifier{
	public Bomb(int direction, int spawnX, int spawnY, boolean heroOnly) {
		super(direction, spawnX, spawnY, heroOnly);
		try{
			setGraphic(ImageIO.read(new File("Drawable_Images/Modifiers.png")));
		}
		catch(IOException ex){
			
		}
		setTileWidth(32);
		setTileHeight(32);
		setActionStep(2);
		setActionSequence(2);
	}
	
	public void doLogic() {

	}
	
	public void move(){
		
	}
	
	public void activate(Sprite target) {				//Call this method when a Sprite collides with the modifier and satisfies the heroOnly condition
		if(!(target instanceof Bomb) & !(target instanceof Projectile)){
			this.target = target;
			setActivated(true);
			this.addModifier();
			setXPos(target.getXPos());
			setYPos(target.getYPos()+target.getTileHeight()-getTileHeight());
			removeModifier();
		}
	}

	public void addModifier() {
		Knockback push = new Knockback(0,this.getXPos(), this.getYPos(), false);
		push.activate(target);
		Game.addDrawable(push);
		
		int newHealth = target.getHealth() - 25;
		if(newHealth < 0){
			System.out.println(target.getClass().getSimpleName() + " died to an arrow!");
		}
		target.setHealth(newHealth);

	}

	public void removeModifier() {
		Game.removeDrawable(this);
		target = null;
	}
}