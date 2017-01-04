package platformerBase.ImplementedProjectiles;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import platformerBase.DrawableClasses.Projectile;
import platformerBase.DrawableClasses.Sprite;

public class Arrow extends Projectile{

	public Arrow(int direction, int spawnX, int spawnY, boolean heroProjectile) {
		super(direction, spawnX, spawnY, heroProjectile);
		try{
			setGraphic(rotate((ImageIO.read(new File("Drawable_Images/Arrow.png"))), 90));
		}
		catch(IOException ex){
			
		}
		
		setActionSequence(0);
		setActionStep(0);
		setTileWidth(getGraphic().getWidth());
		setTileHeight(getGraphic().getHeight());
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
		
		setSpeed(30);
		setDamage(15);
	}

	public void doLogic() {

	}
	
	public void attack(Sprite beingAttacked){
		int newHealth = beingAttacked.getHealth() - getDamage();
		if(newHealth < 0){
			System.out.println(beingAttacked.getClass().getSimpleName() + " died to an arrow!");
		}
		beingAttacked.setHealth(newHealth);
	}

}
