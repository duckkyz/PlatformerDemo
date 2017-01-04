package platformerBase.ImplementedEntities;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import platformerBase.Game;
import platformerBase.DrawableClasses.Drawable;
import platformerBase.DrawableClasses.Hero;
import platformerBase.DrawableClasses.Projectile;
import platformerBase.DrawableClasses.Sprite;
import platformerBase.ImplementedModifiers.*;
import platformerBase.Items.*;

public class Bard extends Hero{
	
	private boolean isAttack1 = false;
	
	public Bard(int direction, int spawnX, int spawnY) {
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
		this.setAbility2Cooldown(0);
		
		try {
			setGraphic(ImageIO.read(new File("Drawable_Images/Bard.png")));
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
		isAttack1 = true;
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
		Game.addDrawable(new Bomb(0, this.getXPos() + 32, this.getYPos() + 32, false) );

	}

	@Override
	public void ability3Execute(int direction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ability4Execute(int direction) {
		// TODO Auto-generated method stub

	}	
	
	public void attack(){
		int newX = ((this.getXPos() + this.getLeftHitBox()));
		int newY = ((this.getYPos() + this.getTopHitBox()));
		int newMaxX = newX + (this.getTileWidth() - this.getLeftHitBox() - this.getRightHitBox());	//Right
		int newMaxY = newY + (this.getTileHeight() - this.getTopHitBox() - this.getBotHitBox());	//Bottom
		
		int dX;
		int dY;
		int dMaxX;
		int dMaxY;
		
		switch(this.getEffectiveDirection()){
		case 0:
			newY = newY - getAttackRange();
			break;
		case 90:
			newMaxX = newMaxX + getAttackRange();
			break;
		case 180:
			newMaxY = newMaxY + getAttackRange();
			break;
		case 270:
			newX = newX - getAttackRange();
			break;
		}
		
		Sprite s = new Sprite(0,0,0);
		
		boolean heroChecked = false;
		boolean isAttacked = false;
		for(int x = 0; Game.getDrawables().size() > x; x++){
			Drawable d = Game.getDrawables().get(x);
			if(!(d instanceof Sprite)){
				if(heroChecked == false){
					d = Game.getHero();
					heroChecked = true;
				}
				else{
					continue;
				}
			}
			if(d == this){
				continue;
			}
			if(d instanceof Projectile){
				continue;
			}
			
			isAttacked = false;
			
			s = (Sprite)d;
			
			dX = ((d.getXPos() + d.getLeftHitBox()));
			dY = ((d.getYPos() + d.getTopHitBox()));
			dMaxX = dX + (d.getTileWidth() - d.getLeftHitBox() - d.getRightHitBox());	//Right
			dMaxY = dY + (d.getTileHeight() - d.getTopHitBox() - d.getBotHitBox());	//Bottom
			

			if((newX >= dX) & (newX <= dMaxX)){ 			//Collision from the left
				if((newY >= dY) & (newY <= dMaxY)){		//Collision from the bottom
					isAttacked = true;
				}
				
				else if((newMaxY >= dY) & (newMaxY <= dMaxY)){	//Collision from top
					isAttacked = true;
				}
			}
			
			else if ((newMaxX >= dX) & (newMaxX <= dMaxX)){	//Collision from the right
				if((newY >= dY) & (newY <= dMaxY)){		//Collision from the bottom
					isAttacked = true;
				}
				
				else if((newMaxY >= dY) & (newMaxY <= dMaxY)){	//Collision from top
					isAttacked = true;
				}
			}
			if(isAttacked){
				s.setHealth(s.getHealth() - this.getAttackDamage());
				if(isAttack1){
					Knockback push = new Knockback(0, s.getXPos(), s.getYPos(), false);
					push.activate(s);
					Game.addDrawable(push);
					isAttack1 = false;
				}
			}
		}
	}
}
