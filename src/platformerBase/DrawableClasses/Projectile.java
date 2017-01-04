package platformerBase.DrawableClasses;
import java.awt.Graphics;

public class Projectile extends Sprite{
	
	protected boolean heroProjectile;					//if projectile belongs to hero
	private int damage;
	
	public Projectile(int direction, int spawnX, int spawnY, boolean heroProjectile) {
		super(direction, spawnX, spawnY);
		this.heroProjectile = heroProjectile;
	}
	
	public boolean getIsFromHero(){
		return heroProjectile;
	}
	
	public int getDamage(){
		return damage;
	}
	
	public void setDamage(int damage){
		this.damage = damage;
	}
	
	public void doLogic(){
		
	}
	
	public void attack(Sprite beingAttacked){
	
	}	
	
	public void paint(Graphics imageGraphics) {			//this code works when tileWidth=tileHeight
		imageGraphics.drawImage(getGraphic(), getXPos(), getYPos(), getXPos()+getTileWidth(), getYPos()+getTileHeight(), getActionStep()*getTileWidth(), getActionSequence()*getTileHeight(), getActionStep()*getTileWidth()+getTileWidth(), getActionSequence()*getTileHeight()+getTileHeight(), null);
		//imageGraphics.drawImage(getGraphic(), getXPos(), getYPos(), null);
	}
	
}
