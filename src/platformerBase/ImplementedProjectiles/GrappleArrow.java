package platformerBase.ImplementedProjectiles;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import platformerBase.Game;
import platformerBase.DrawableClasses.*;
import platformerBase.ImplementedModifiers.MindControl;

public class GrappleArrow extends Arrow {
	public GrappleArrow(int direction, int spawnX, int spawnY, boolean heroProjectile) {
		super(direction, spawnX, spawnY, heroProjectile);
		setDamage(5);
	}
	
	public void doLogic() {

	}
	
	public void attack(Drawable temp){
		if(temp instanceof Sprite){
			Sprite beingAttacked = (Sprite) temp;
			int newHealth = beingAttacked.getHealth() - getDamage();
			if(newHealth < 0){
				System.out.println(beingAttacked.getClass().getSimpleName() + " died to an arrow!");
			}
			beingAttacked.setHealth(newHealth);
		}
		if(heroProjectile == true){
			MindControl pull = new MindControl(0,this.getXPos(), this.getYPos(), true);
			pull.activate(Game.getHero());
			Game.addDrawable(pull);
		}
	}
}
