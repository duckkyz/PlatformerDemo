package platformerBase.DrawableClasses;

import java.awt.event.KeyEvent;
import java.util.Set;

public abstract class Hero extends Sprite{
	
	private boolean spaceBar = false;																							//while held, space prevents the hero from changing directional actionSequences. Note the direction is still changing.
	
	public Hero(int direction, int spawnX, int spawnY) {
		super(direction, spawnX, spawnY);
		setTopHitBox(15);
		setBotHitBox(2);
		setLeftHitBox(17);
		setRightHitBox(17);
	}
}