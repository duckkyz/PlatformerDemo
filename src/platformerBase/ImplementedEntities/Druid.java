package platformerBase.ImplementedEntities;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import platformerBase.Game;
import platformerBase.DrawableClasses.Hero;
import platformerBase.ImplementedProjectiles.*;

public class Druid extends Hero{
	private int animationCounter = 0;
	
	public Druid(int direction, int spawnX, int spawnY) {
		super(direction, spawnX, spawnY);
		setTileWidth(64);
		setTileHeight(64);
		setSpeed(12);
		setTopHitBox(15);
		setBotHitBox(2);
		setLeftHitBox(17);
		setRightHitBox(17);
		setAttackRange(16);
		setAttackDamage(25);
		
		try {
			setGraphic(ImageIO.read(new File("Drawable_Images/Druid.png")));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		if(!(this == Game.getHero())){
			setAttackRange(2000);
		}
	}

	@Override
	public void ability1Setup() {
		setActionStep(0);
		setMoveCasting(true);								//whether the hero can move while using this ability
		setcompletingSequence(true);
		// (0)Spell-cast, (1)Thrusting, (2)NA, (3)Slashing, (4)Shooting
		abilitySetupHelper(3);
	}

	@Override
	public void ability2Setup() {
		setActionStep(0);
		setMoveCasting(true);								//whether the hero can move while using this ability
		setcompletingSequence(true);
		// (0)Spell-cast, (1)Thrusting, (2)NA, (3)Slashing, (4)Shooting
		abilitySetupHelper(0);
	}

	@Override
	public void ability3Setup() {
		setActionStep(0);
		setMoveCasting(true);								//whether the hero can move while using this ability
		setcompletingSequence(true);
		// (0)Spell-cast, (1)Thrusting, (2)NA, (3)Slashing, (4)Shooting
		abilitySetupHelper(3);
	}

	@Override
	public void ability4Setup() {
		setActionStep(0);
		setMoveCasting(true);								//whether the hero can move while using this ability
		setcompletingSequence(true);
		// (0)Spell-cast, (1)Thrusting, (2)NA, (3)Slashing, (4)Shooting
		abilitySetupHelper(3);
	}

	@Override
	public void ability1Execute(int direction) {
		if(this.getMana() > 10){
			boolean isHero = false;
			if(this == Game.getHero()){
				isHero = true;
			}
			Game.addDrawable(new Leaf(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isHero));	//math gives the created object a reference to the center of the enemy
			this.setMana(this.getMana() - 10);
		}
	}

	@Override
	public void ability2Execute(int direction) {
		if(this.getMana() > 90){
			setHealth(getHealth() + 10);
			Leaf tempLeaf = new Leaf(0, this.getXPos() - 10, this.getYPos() - 10, true);
			tempLeaf.setIsMoving(false);
			Game.addDrawable(tempLeaf);
			this.setMana(this.getMana() - 90);
		}
	}

	@Override
	public void ability3Execute(int direction) {
		if(this.getMana() > 20){
			boolean isFromHero = false;
			if(this == Game.getHero()){
				isFromHero = true;
			}
			Fireball heat = new Fireball(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isFromHero);
			Leaf damage = new Leaf(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isFromHero);
			heat.setDamage(5);
			heat.setSpeed(damage.getSpeed());
			Game.addDrawable(heat);	//math gives the created object a reference to the center of the hero
			Game.addDrawable(damage);	//math gives the created object a reference to the center of the hero
			this.setMana(this.getMana() - 20);
		}	
	}

	@Override
	public void ability4Execute(int direction) {
		if(this.getMana() > 20){
			boolean isFromHero = false;
			if(this == Game.getHero()){
				isFromHero = true;
			}
			Icicle slow = new Icicle(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isFromHero);
			Leaf damage = new Leaf(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isFromHero);
			slow.setDamage(0);
			slow.setSpeed(damage.getSpeed());
			Game.addDrawable(slow);	//math gives the created object a reference to the center of the hero
			Game.addDrawable(damage);	//math gives the created object a reference to the center of the hero
			this.setMana(this.getMana() - 20);
		}	
	}	

	public void move(){
		super.move();
		if((getIsMoving() == true) & (animationCounter%7 == 0)){
			Leaf leafTrailLeft = new Leaf(0, this.getXPos() + (this.getLeftHitBox()), this.getYPos() + (this.getTileHeight() - this.getBotHitBox()), true);
			Leaf leafTrailRight = new Leaf(0, this.getXPos() + (this.getTileWidth() - this.getRightHitBox()), this.getYPos() + (this.getTileHeight() - this.getBotHitBox()), true);
			leafTrailLeft.setIsMoving(false);
			leafTrailRight.setIsMoving(false);
			Game.addDrawable(leafTrailLeft);
			Game.addDrawable(leafTrailRight);
			animationCounter++;
		}
		else if(animationCounter > 100){
			animationCounter = 0;
		}
		else{
			animationCounter++;
		}
	}
}
