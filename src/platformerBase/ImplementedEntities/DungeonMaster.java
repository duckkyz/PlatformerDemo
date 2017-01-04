package platformerBase.ImplementedEntities;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import platformerBase.Game;
import platformerBase.DrawableClasses.Hero;
import platformerBase.ImplementedProjectiles.Fireball;
import platformerBase.ImplementedProjectiles.Icicle;
import platformerBase.ImplementedProjectiles.Shock;

public class DungeonMaster extends Hero{
	public DungeonMaster(int direction, int spawnX, int spawnY) {
		super(direction, spawnX, spawnY);
		setTileWidth(64);
		setTileHeight(64);
		setSpeed(12);
		setTopHitBox(15);
		setBotHitBox(2);
		setLeftHitBox(17);
		setRightHitBox(17);
		setAttackRange(16);
		setAttackDamage(10);
		
		try {
			setGraphic(ImageIO.read(new File("Drawable_Images/Guildmaster.png")));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void ability1Setup() {
		setActionStep(0);
		setMoveCasting(true);								//whether the hero can move while using this ability
		setcompletingSequence(true);
		// (0)Spell-cast, (1)Thrusting, (2)NA, (3)Slashing, (4)Shooting
		abilitySetupHelper(3);
		setAttackDamage((int)(Math.random() * 20));
	}

	@Override
	public void ability2Setup() {
		setActionStep(0);
		setMoveCasting(false);								//whether the hero can move while using this ability
		setcompletingSequence(true);
		// (0)Spell-cast, (1)Thrusting, (2)NA, (3)Slashing, (4)Shooting
		abilitySetupHelper(0);
	}

	@Override
	public void ability3Setup() {
		// TODO Auto-generated method stub

	}

	@Override
	public void ability4Setup() {
		// TODO Auto-generated method stub

	}

	@Override
	public void ability1Execute(int direction) {
		if(this.getMana() > 5){
			boolean isHero = false;
			if(this == Game.getHero()){
				isHero = true;
			}
			Game.addDrawable(new Fireball(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isHero));
			Game.addDrawable(new Icicle(direction+30, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isHero));
			Game.addDrawable(new Icicle(direction-30, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isHero));
			Game.addDrawable(new Fireball(direction + 60, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isHero));
			Game.addDrawable(new Fireball(direction - 60, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isHero));
			Game.addDrawable(new Icicle(direction + 90, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isHero));
			Game.addDrawable(new Icicle(direction - 90, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isHero));
			Game.addDrawable(new Fireball(direction + 120, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isHero));
			Game.addDrawable(new Fireball(direction - 120, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isHero));
			Game.addDrawable(new Icicle(direction + 180, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isHero));
			Game.addDrawable(new Icicle(direction - 180, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isHero));
			this.setMana(this.getMana() - 5);
		}
	}

	@Override
	public void ability2Execute(int direction) {
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
		
	}

	@Override
	public void ability3Execute(int direction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ability4Execute(int direction) {
		// TODO Auto-generated method stub

	}	
}
