package platformerBase.ImplementedProjectiles;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import platformerBase.Game;
import platformerBase.DrawableClasses.Projectile;
import platformerBase.DrawableClasses.Sprite;

public class Leaf extends Projectile {
	
	int animationCounter = 0;
	
	public Leaf(int direction, int spawnX, int spawnY, boolean heroProjectile) {
		super(direction, spawnX, spawnY, heroProjectile);
		try{
			setGraphic((ImageIO.read(new File("Drawable_Images/Leaves.png"))));
		}
		catch(IOException ex){
			
		}
		
		setActionSequence((int)Math.floor(Math.random() * 4));
		setActionStep((int)Math.floor(Math.random() * 4));
		setTileWidth(16);
		setTileHeight(14);
		setIsMoving(true);
		
		setXPos(getXPos()-getTileWidth()/2);
		setYPos(getYPos()-getTileHeight()/2);
		
		if(direction >= 0 & direction <= 90) {
			setTopHitBox(1);		
			setBotHitBox(1+32*(int)Math.abs(Math.cos(Math.toRadians(direction))));		
			setLeftHitBox(1+32*(int)Math.abs(Math.sin(Math.toRadians(direction))));		
			setRightHitBox(1);		
		}
		else if(direction >= 90 & direction <= 180) {
			setTopHitBox(1+32*(int)Math.abs(Math.cos(Math.toRadians(direction))));		
			setBotHitBox(1);		
			setLeftHitBox(1+32*(int)Math.abs(Math.sin(Math.toRadians(direction))));	
			setRightHitBox(1);		
		}
		else if(direction >= 180 & direction <= 270) {
			setTopHitBox(1+32*(int)Math.abs(Math.cos(Math.toRadians(direction))));		
			setBotHitBox(1);		
			setLeftHitBox(1);		
			setRightHitBox(1+32*(int)Math.abs(Math.sin(Math.toRadians(direction))));	
		}
		else if(direction >= 270 & direction <= 360) {
			setTopHitBox(0);		
			setBotHitBox(1+32*(int)Math.abs(Math.cos(Math.toRadians(direction))));		
			setLeftHitBox(0);	
			setRightHitBox(1+32*(int)Math.abs(Math.sin(Math.toRadians(direction))));	
		}
		
		setSpeed(20);
		setDamage(20);
	}

	public void doLogic() {
		if(getIsMoving() == false){
			if((animationCounter%10) == 0){
				setActionSequence((int)Math.floor(Math.random() * 4));
				setActionStep((int)Math.floor(Math.random() * 4));
				animationCounter++;
			}
			else if(animationCounter == 101){
				animationCounter = 0;
				Game.removeDrawable(this);
			}
			else{
				animationCounter++;
			}
		}
	}
	
	public void attack(Sprite beingAttacked){
		if(getIsMoving() == true){
			int newHealth = beingAttacked.getHealth() - getDamage();
			if(newHealth < 0){
				System.out.println(beingAttacked.getClass().getSimpleName() + " died to a leaf! lol, nerd.");
			}
			beingAttacked.setHealth(newHealth);
		}
	}
}
