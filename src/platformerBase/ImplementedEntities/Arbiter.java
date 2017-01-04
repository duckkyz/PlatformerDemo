package platformerBase.ImplementedEntities;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import platformerBase.Game;
import platformerBase.DrawableClasses.*;
import platformerBase.Interfaces.willAttack;

public class Arbiter extends Villain {

	public Arbiter(int direction, int spawnX, int spawnY) {
		super(direction, spawnX, spawnY);
		setTileWidth(64);
		setTileHeight(64);
		setSpeed(8);
		maxHealth = 150;
		setHealth(150);
		setTopHitBox(15);
		setBotHitBox(2);
		setLeftHitBox(17);
		setRightHitBox(17);
		setAttackRange(30);
		setAttackDamage(20);
		
		int tempNum = (int) (Math.random() * 100);
		int counter = 0;
		for(int x = 0; Game.getDrawables().size() > x; x++){
			Drawable d = Game.getDrawables().get(x);
			if(d instanceof Sprite){
				if(d instanceof Projectile){
					continue;
				}
				++counter;
			}
		}
		if((tempNum > 75) | (counter <= 1)){
			markedForDeath = Game.getHero();
		}
		else{
			Drawable temp = Game.getDrawables().get((int)(Math.random() * Game.getDrawables().size()));
			while((!(temp instanceof Sprite)) & (temp != this)){
				temp = Game.getDrawables().get((int)(Math.random() * Game.getDrawables().size()));
			}
			markedForDeath = (Sprite)temp;
		}
		
		
		try {
			setGraphic(ImageIO.read(new File("Drawable_Images/Arbiter.png")));
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
		ability1Ready = true;
	}

	@Override
	public void ability2Setup() {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	@Override
	public void ability2Execute(int direction) {
		// TODO Auto-generated method stub

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
