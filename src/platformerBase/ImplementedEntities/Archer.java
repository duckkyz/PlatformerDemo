/*Get more sprites at : http://gaurav.munjal.us/Universal-LPC-Spritesheet-Character-Generator/
 * 
 * 
 * 
 * 
 * 
 */


package platformerBase.ImplementedEntities;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import platformerBase.Game;
import platformerBase.DrawableClasses.Hero;
import platformerBase.DrawableClasses.Modifier;
import platformerBase.ImplementedModifiers.BearTrap;
import platformerBase.ImplementedModifiers.SlowDown;
import platformerBase.ImplementedProjectiles.Arrow;
import platformerBase.ImplementedProjectiles.Fireball;
import platformerBase.ImplementedProjectiles.Icicle;


public class Archer extends Hero{
	
	public Archer(int direction, int xPos, int yPos) {
		super(direction, xPos, yPos);
		setTileWidth(64);
		setTileHeight(64);
		try {
			setGraphic(ImageIO.read(new File("Drawable_Images/Archer.png")));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		if(!(this == Game.getHero())){
			setAttackRange(2000);
		}
	}

	public void ability1Setup() {							//sets the ActionSequence that ability_1 invokes (based off which direction hero is currently facing)
		setActionStep(0);
		setMoveCasting(false);								//whether the hero can move while using this ability
		setcompletingSequence(true);
		// (0)Spell-cast, (1)Thrusting, (2)NA, (3)Slashing, (4)Shooting
		abilitySetupHelper(4);
	}
	
	public void ability2Setup() {							
		setActionStep(0);
		setMoveCasting(false);								//whether the hero can move while using this ability
		setcompletingSequence(true);
		// (0)Spell-cast, (1)Thrusting, (2)NA, (3)Slashing, (4)Shooting
		abilitySetupHelper(4);
	}
	
	public void ability3Setup() {	
		setActionStep(0);
		Game.addDrawable(new BearTrap(1, getXPos(), getYPos(), false));
	}
	
	public void ability4Setup() {
		setActionStep(0);
		setMoveCasting(false);								//whether the hero can move while using this ability
		setcompletingSequence(true);
		// (0)Spell-cast, (1)Thrusting, (2)NA, (3)Slashing, (4)Shooting
		abilitySetupHelper(4);
	}
	
	
	public void ability1Execute(int direction) {
		if(this.getMana() > 5){
			boolean isFromHero = false;
			if(this == Game.getHero()){
				isFromHero = true;
			}
			Game.addDrawable(new Arrow(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isFromHero));	//math gives the created object a reference to the center of the enemy
			this.setMana(this.getMana() - 5);
		}
	}

	public void ability2Execute(int direction) {
		if(this.getMana() > 15){
			boolean isFromHero = false;
			if(this == Game.getHero()){
				isFromHero = true;
			}
			Game.addDrawable(new Arrow(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isFromHero));
			Game.addDrawable(new Arrow(direction+45, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isFromHero));
			Game.addDrawable(new Arrow(direction-45, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isFromHero));
			this.setMana(this.getMana() - 15);
		}
	}
	
	public void ability3Execute(int direction) {
		//TODO implement this
	}
	
	public void ability4Execute(int direction) {
		if(this.getMana() > 20){
			boolean isFromHero = false;
			if(this == Game.getHero()){
				isFromHero = true;
			}
			Icicle slow = new Icicle(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isFromHero);
			Arrow damage = new Arrow(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isFromHero);
			slow.setDamage(0);
			slow.setSpeed(damage.getSpeed());
			Game.addDrawable(damage);	//math gives the created object a reference to the center of the hero
			Game.addDrawable(slow);	//math gives the created object a reference to the center of the hero
			this.setMana(this.getMana() - 20);
		}	
	}
}
