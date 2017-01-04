package platformerBase.ImplementedEntities;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import platformerBase.Game;
import platformerBase.DrawableClasses.*;
import platformerBase.ImplementedProjectiles.Fireball;
import platformerBase.ImplementedProjectiles.Icicle;
import platformerBase.ImplementedProjectiles.Shock;

public class OrcWizard extends Villain {

	public OrcWizard(int direction, int spawnX, int spawnY) {
		super(direction, spawnX, spawnY);
		setTileWidth(64);
		setTileHeight(64);
		setSpeed(8);
		setTopHitBox(15);
		setBotHitBox(2);
		setLeftHitBox(17);
		setRightHitBox(17);
		setAttackRange(2000);
		setAttackDamage(0);
		
		try {
			setGraphic(ImageIO.read(new File("Drawable_Images/OrcWizard.png")));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ability1Setup() {							//sets the ActionSequence that ability1 invokes (based off which direction hero is currently facing)
		setActionStep(0);
		setMoveCasting(true);
		setcompletingSequence(true);
		// (0)Spell-cast, (1)Thrusting, (2)NA, (3)Slashing, (4)Shooting
		abilitySetupHelper(3);
	}

	public void ability2Setup() {							
		setActionStep(0);
		setMoveCasting(true);
		setcompletingSequence(true);
		// (0)Spell-cast, (1)Thrusting, (2)NA, (3)Slashing, (4)Shooting
		abilitySetupHelper(3);	}
	
	public void ability3Setup() {	
		setActionStep(0);
		setMoveCasting(true);
		setcompletingSequence(true);
		// (0)Spell-cast, (1)Thrusting, (2)NA, (3)Slashing, (4)Shooting
		abilitySetupHelper(0);
	}
	
	public void ability4Setup() {
		setActionStep(0);
		setMoveCasting(false);
		setcompletingSequence(true);
		// (0)Spell-cast, (1)Thrusting, (2)NA, (3)Slashing, (4)Shooting
		abilitySetupHelper(0);
	}

	public void ability1Execute(int direction) {
		if(this.getMana() > 15){
			boolean isHero = false;
			if(this == Game.getHero()){
				isHero = true;
			}
			int temp = (int) (Math.random() * 100);
			if(temp%3 == 0){
				Game.addDrawable(new Fireball(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isHero));	//math gives the created object a reference to the center of the enemy
			}
			else if(temp%3 == 1){
				Game.addDrawable(new Fireball(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isHero));	//math gives the created object a reference to the center of the enemy
				//Game.addDrawable(new Icicle(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isHero));	//math gives the created object a reference to the center of the enemy
			}
			else if(temp%3 == 2){
				Game.addDrawable(new Shock(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isHero));	//math gives the created object a reference to the center of the enemy
			}
			this.setMana(this.getMana() - 15);
		}
	}

	public void ability2Execute(int direction) {
		if(this.getMana() > 5){
			boolean isHero = false;
			if(this == Game.getHero()){
				isHero = true;
			}
			Game.addDrawable(new Icicle(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isHero));	//math gives the created object a reference to the center of the enemy
			this.setMana(this.getMana() - 5);
		}		
	}
	
	public void ability3Execute(int direction) {
		if(this.getMana() > 15){
			boolean isHero = false;
			if(this == Game.getHero()){
				isHero = true;
			}
			Game.addDrawable(new Fireball(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isHero));
			Game.addDrawable(new Icicle(direction+30, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isHero));
			Game.addDrawable(new Shock(direction-30, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isHero));
			this.setMana(this.getMana() - 15);
		}
	}
	
	public void ability4Execute(int direction) {
		if(this.getMana() > 20){
			int blinkDist = (int) ((Math.random() * 128) + 64);
			int temp = this.getEffectiveDirection();
			//TODO: finish this
			if(temp == 0){
				this.setYPos(this.getYPos() - blinkDist);
				if(this.getYPos() < 128){
					this.setYPos(128);
				}
				while(Game.checkCanSpawn(this) == false){
					this.setYPos(this.getYPos() + 10);
				}
			}
			else if(temp == 90){
				this.setXPos(this.getXPos() + blinkDist);
				if(this.getXPos() > (Game.getMapWidth() - 64)){
					this.setXPos(Game.getMapWidth() - 64);
				}
				while(Game.checkCanSpawn(this) == false){
					this.setXPos(this.getXPos() - 10);
				}
			}
			else if(temp == 180){
				this.setYPos(this.getYPos() + blinkDist);
				if(this.getYPos() > (Game.getMapHeight() - 64)){
					this.setYPos(Game.getMapHeight() - 64);
				}
				while(Game.checkCanSpawn(this) == false){
					this.setYPos(this.getYPos() - 10);
				}
			}
			else if(temp == 270){
				this.setXPos(this.getXPos() - blinkDist);
				if(this.getXPos() < 32){
					this.setXPos(32);
				}
				while(Game.checkCanSpawn(this) == false){
					this.setXPos(this.getXPos() + 10);
				}
			}
			this.setMana(this.getMana() - 20);
		}
	}
}
