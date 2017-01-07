package platformerBase.DrawableClasses;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Set;

import platformerBase.Game;
import platformerBase.ImplementedEntities.*;
import platformerBase.Interfaces.willAttack;

public class Sprite extends Drawable implements willAttack{
	private boolean moveCasting = false;
	private boolean staticMovement = false;
	private boolean isMoving = false;
	private boolean completingSequence = false;	//if the sprite needs to finish performing current action before performing others
	private int invokedAbility = 0;					//used to keep track of which ability was issued for cases when multiple abilities use the same actionSequence
	private int speed = 12;							//make only divisible by 2
	private int direction = 0;
	private int attackRange;					//Test value, will need tuning
	private int attackDamage;
	protected int collisionCounter;
	
	private boolean isAttacking = false;
	
	// D_x = v_i * t + (1/2 * (a_x) * (t^2))
	// D_y = v_i * t + (1/2 * (a_y - g) * (t^2))
	
	private int mass = 10;
	
	
	protected int maxHealth = 100;
	protected int maxMana = 100;
	private int health = 100;
	private int mana = 100;
	
	private int animationCounter = 0;
	
	protected boolean ability1Ready = true;
	protected boolean ability2Ready = true;
	protected boolean ability3Ready = true;
	protected boolean ability4Ready = true;

	
	private int ability1Cooldown = 10;
	private int ability2Cooldown = 10;
	private int ability3Cooldown = 10;
	private int ability4Cooldown = 10;

	private int ability1CooldownCounter = 0;
	private int ability2CooldownCounter = 0;
	private int ability3CooldownCounter = 0;
	private int ability4CooldownCounter = 0;
	
	protected Sprite markedForDeath = Game.getHero();

	
	public Sprite(int direction, int spawnX, int spawnY) {
		
		if(direction < 0){
			direction = 360 + direction;
		}
		setDirection(direction % 360);
		setXPos(spawnX);
		setYPos(spawnY);
		switch(direction) {									//draws the sprite in the direction it was created in
		case 0:
			setActionSequence(8);
			break;
		case 90:
			setActionSequence(11);
			break;
		case 180:
			setActionSequence(10);
			break;
		case 270:
			setActionSequence(9);
			break;
		}
	}
	
	public boolean getMoveCasting() {
		return moveCasting;
	}
	
	public boolean getIsMoving() {
		return isMoving;
	}
	
	public boolean getIsAttacking(){
		return isAttacking;
	}
	
	public boolean getcompletingSequence() {
		return completingSequence;
	}
	
	public int getInvokedAbility() {
		return invokedAbility;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public boolean getStaticMovement(){ 
		return this.staticMovement;
	}
	
	public int getDirection() {
		return direction;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getMana() {
		return mana;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public int getMaxMana() {
		return maxMana;
	}
	
	public int getAttackRange(){
		return attackRange;
	}

	public int getAttackDamage(){
		return attackDamage;
	}
	
	public int getCollisionCounter(){
		return collisionCounter;
	}
	
	public void incrementCollisionCounter(){
		++this.collisionCounter;
	}
	
	public void decrementCollisionCounter(){
		if(this.collisionCounter > 0){
			--this.collisionCounter;
		}
	}
	
	public void setMoveCasting(boolean moveCasting) {
		this.moveCasting = moveCasting;
		this.isMoving = moveCasting;					//this line is intentional
	}
	
	public void setIsMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}
	
	public void setIsAttacking(boolean isAttacking){
		this.isAttacking = isAttacking;
	}
	
	public void setcompletingSequence(boolean completingSequence) {
		this.completingSequence = completingSequence;
	}
	
	public void setInvokedAbility(int invokedAbility) {
		this.invokedAbility = invokedAbility;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void setStaticMovement(boolean staticMovement){
		this.staticMovement = staticMovement;
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public void setHealth(int health) {
		if(this.health < 0){
			this.health = 0;
		}
		else{
			this.health = health;
			if(this.health > maxHealth){
				this.health = maxHealth;
			}
		}
	
	}
	
	public void setMana(int mana) {
		if(this.mana < 0){
			this.mana = 0;
		}
		else{
			this.mana = mana;
			if(this.mana > 100){
				this.mana = 100;
			}
		}
	}
	
	public void setAttackRange(int attackRange){
		this.attackRange = attackRange;
	}
	
	public void setAttackDamage(int attackDamage){
		this.attackDamage = attackDamage;
	}
		
	public void setMarkedForDeath(Sprite markedForDeath){
		this.markedForDeath = markedForDeath;
	}
	
	protected void setAbility1Cooldown(int cooldown){
		this.ability1Cooldown = cooldown;
	}

	protected void setAbility2Cooldown(int cooldown){
		this.ability2Cooldown = cooldown;
	}
	
	protected void setAbility3Cooldown(int cooldown){
		this.ability3Cooldown = cooldown;
	}
	
	protected void setAbility4Cooldown(int cooldown){
		this.ability4Cooldown = cooldown;
	}
	
	public void ability1Setup() {		
	}
	
	public void ability2Setup() {		
	}

	public void ability3Setup() {		
	}

	public void ability4Setup() {
	}

	public void ability1Execute(int direction) {
		
	}

	public void ability2Execute(int direction) {
		
	}

	public void ability3Execute(int direction) {
		
	}

	public void ability4Execute(int direction) {
		
	}
		
	public void executeAbility(int direction){
		switch(getInvokedAbility()){
			case 1: 
				ability1Execute(direction);
				break;
			case 2:
				ability2Execute(direction);
				break;
			case 3:
				ability3Execute(direction);
				break;
			case 4:
				ability4Execute(direction);
				break;
			}
	}
	
	public void continueSequence() {
		if((this instanceof Arbiter) & (getActionSequence() > 21)){						//goes to the next step of the animation for oversized attacks
			if((animationCounter % 2) == 0){
				if(getActionStep() == 0){
					setActionStep(1);
				}
				if(getActionStep() == 10){
					executeAbility(getEffectiveDirection());
				}
				if(getActionStep() == 16){
					setActionStep(0);
					attack();
					setSequenceWalking();	
					animationCounter = 0;
					return;
				}
				else{
					setActionStep(getActionStep() + 3);
				}
			}
			animationCounter += 1;
		}
		else{
			setActionStep(getActionStep()+1);												//goes to the next step of the animation
		}
		
		if(getActionSequence() >= 0 & getActionSequence() <= 3) {						//spell-casting
			if(getActionStep()>6) {
				setSequenceWalking();
			}
			if(getActionStep() == 5) {
				executeAbility(getEffectiveDirection());
			}
		}
		
		else if(getActionSequence() >= 4 & getActionSequence() <= 7) {					//thrusting 
			if(getActionStep()>7) {
				attack();
				setSequenceWalking();
			}
			if(getActionStep() == 6) {
				executeAbility(getEffectiveDirection());
			}
		}
		
		else if(getActionSequence() >= 12 & getActionSequence() <= 15) {				//slash
			if(getActionStep() > 5) {
				attack();
				setSequenceWalking();	
			}
			if(getActionStep() == 5) {
				executeAbility(getEffectiveDirection());
			}
		}
		
		else if(getActionSequence() >= 16 & getActionSequence() <= 19) {				//currently shooting
			if(getActionStep()>12) {
				setSequenceWalking();
			}
			if(getActionStep() == 9) {													//frame 9 matches arrow release
				executeAbility(getEffectiveDirection());
			}
		}
		
		else if(getActionSequence() == 20) {											//death
			if(getActionStep()>5) {
				//game ends
				if(this == Game.getHero()){
					setActionStep(0);
				}
				else{
					Game.removeDrawable(this);
				}
			}
		}
	}
	
	public int getEffectiveDirection() {			//gets the direction the hero is facing
		if(this instanceof Arbiter){
			switch(getActionSequence()%4) {
			case 2:
				return 0;
			case 1:
				return 270;
			case 0:
				return 180;
			case 3:
				return 90;
			default:
				return 0;
			}
		}
		switch(getActionSequence()%4) {
			case 0:
				return 0;
			case 1:
				return 270;
			case 2:
				return 180;
			case 3:
				return 90;
			default:
				return 0;
			}
	}
	
	public void setSequenceWalking() {				//changes the actionSequence to walking based on current effectiveDirection
		setActionStep(0);
		setcompletingSequence(false);
		if(this instanceof Arbiter){
			switch(getActionSequence()%4) {
			case 2:
				setActionSequence(8);
				break;
			case 1:
				setActionSequence(9);
				break;
			case 0:
				setActionSequence(10);
				break;
			case 3:
				setActionSequence(11);
				break;
			default:
				setActionSequence(8);
				break;
			}
			return;
		}
		switch(getActionSequence()%4) {
			case 0:
				setActionSequence(8);
				break;
			case 1:
				setActionSequence(9);
				break;
			case 2:
				setActionSequence(10);
				break;
			case 3:
				setActionSequence(11);
				break;
			default:
				setActionSequence(8);
				break;
			}
	}
	
	public void doMovementLogic90(int actionSequence, int direction) {			//contains the logic for moving at directions 0, 90, 180, 270
		if(getcompletingSequence() == false) {
			setDirection(direction);
			if(getStaticMovement() == false) {
				if(getActionSequence()==actionSequence) {
					if(getActionStep()<8)
						setActionStep(getActionStep()+1);
					else
						setActionStep(0);
				}
				else {
					setActionSequence(actionSequence);
					setActionStep(0);
				}
			}
			else {
				if(getActionStep()<=0)
					setActionStep(8);
				else
					setActionStep(getActionStep()-1);
			}
		}
		if(getMoveCasting() == true)
			setDirection(direction);
	}
	
	public void doMovementLogic45(int seq1, int seq2, int direction) {			//contains the logic for moving at directions 45, 135, 225, 315
		if(getcompletingSequence() == false) {
			setDirection(direction);
			if(getStaticMovement() == false) {
				if(getActionSequence()!=seq1 & getActionSequence()!=seq2)												//if both keys are pressed at the same time, choose one of the directions
					setActionSequence(seq2);
				if(getActionStep()<8)
					setActionStep(getActionStep()+1);
				else
					setActionStep(0);
			}
			else {
				if(getActionStep()<=0)
					setActionStep(8);
				else
					setActionStep(getActionStep()-1);
			}
		}
		if(getMoveCasting() == true)
			setDirection(direction);
	}
		
	public void move() {
		if(isMoving == true) {
			setXPos(getXPos() + (int)(Math.round(Math.sin(Math.toRadians(direction)) * speed)));
			setYPos(getYPos() + (int)(Math.round(Math.cos(Math.toRadians(direction)) * speed * (-1))));
		}
		if(ability1Ready == false){
			if(ability1CooldownCounter > ability1Cooldown){
				ability1Ready = true;
				ability1CooldownCounter = 0;
			}
			else{
				++ability1CooldownCounter;
			}
		}
		if(ability2Ready == false){
			if(ability2CooldownCounter > ability2Cooldown){
				ability2Ready = true;
				ability2CooldownCounter = 0;
			}
			else{
				++ability2CooldownCounter;
			}
		}
		if(ability3Ready == false){
			if(ability3CooldownCounter > ability3Cooldown){
				ability3Ready = true;
				ability3CooldownCounter = 0;
			}
			else{
				++ability3CooldownCounter;
			}
		}
		if(ability4Ready == false){
			if(ability4CooldownCounter > ability4Cooldown){
				ability4Ready = true;
				ability4CooldownCounter = 0;
			}
			else{
				++ability4CooldownCounter;
			}
		}
	}
	
	public void abilitySetupHelper(int actionSelection) {
		switch(getActionSequence()){	
			case 8:
				setActionSequence(4*actionSelection);
				if(this instanceof Arbiter){
					setActionSequence(22);
				}
				break;
			case 9:
				setActionSequence(4*actionSelection+1);
				if(this instanceof Arbiter){
					setActionSequence(25);
				}	
				break;
			case 10:
				setActionSequence(4*actionSelection+2);
				if(this instanceof Arbiter){
					setActionSequence(28);
				}
				break;
			case 11:
				setActionSequence(4*actionSelection+3);
				if(this instanceof Arbiter){
					setActionSequence(31);
				}
				break;
			default:
				setActionSequence(4*actionSelection);
				if(this instanceof Arbiter){
					setActionSequence(4*actionSelection);
				}
				break;
		}
		if(actionSelection == 5) {
			setActionSequence(20);
		}
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
		for(Drawable d : Game.getDrawables()){
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
			}
		}
	}
	
	public BufferedImage rotate(BufferedImage image, float degreeOffset) 
	{
		float angle = getDirection()+degreeOffset;
	    float radianAngle = (float) Math.toRadians(angle) ; 

	    float sin = (float) Math.abs(Math.sin(radianAngle));
	    float cos = (float) Math.abs(Math.cos(radianAngle));

	    int w = image.getWidth() ;
	    int h = image.getHeight();

	    int neww = (int) Math.round(w * cos + h * sin);
	    int newh = (int) Math.round(h * cos + w * sin);

	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice gd = ge.getDefaultScreenDevice();
	    GraphicsConfiguration gc = gd.getDefaultConfiguration();

	    BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
	    Graphics2D g = result.createGraphics();

	    //-----------------------MODIFIED--------------------------------------
	    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON) ;
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC) ;
	    g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY) ;

	    AffineTransform at = AffineTransform.getTranslateInstance((neww-w)/2, (newh-h)/2);
	    at.rotate(radianAngle, w/2, h/2);
	    //---------------------------------------------------------------------

	    g.drawRenderedImage(image, at);
	    g.dispose();

	    return result;
	}
	
	//For Player
		public void doLogic(Set keySet) {
			if(getHealth() <= 0){
				if(getActionSequence() != 20){
					setActionSequence(20);
					setActionStep(0);
					setIsMoving(false);
				}
				continueSequence();
				return;
			}
			
			if(keySet.contains(KeyEvent.VK_SPACE)){ 
				setStaticMovement(true);
			}
			else{
				setStaticMovement(false);
			}
			
			
			if(keySet.contains(KeyEvent.VK_Q) & getcompletingSequence() != true) {
				if(ability1Ready){
					setInvokedAbility(1);							//records that the current ability being used is Q
					ability1Setup();
					ability1Ready = false;
				}
			}
			if(keySet.contains(KeyEvent.VK_W) & getcompletingSequence() != true) {
				if(ability2Ready){
					setInvokedAbility(2);							//records that the current ability being used is W
					ability2Setup();
					ability2Ready = false;
				}

			}
			if(keySet.contains(KeyEvent.VK_E) & getcompletingSequence() != true) {
				if(ability3Ready){
					setInvokedAbility(3);							//records that the current ability being used is E
					ability3Setup();
					ability3Ready = false;
				}
			}
			if(keySet.contains(KeyEvent.VK_R) & getcompletingSequence() != true) {
				if(ability4Ready){
					setInvokedAbility(4);							//records that the current ability being used is R
					ability4Setup();
					ability4Ready = false;
				}
			}
			
			
			if(getcompletingSequence() == true) {				//prevents other actions while performing ability
				continueSequence();								//Moved to Sprite to generalize movement
			}
			else
				setIsMoving(true);
			
			if(getMoveCasting() == true)
				setIsMoving(true);
			
			
			if ((keySet.contains(KeyEvent.VK_UP)) && (!keySet.contains(KeyEvent.VK_DOWN))) {
				if ((keySet.contains(KeyEvent.VK_LEFT)) && (!keySet.contains(KeyEvent.VK_RIGHT))) {
					doMovementLogic45(9, 8, 315);
				}
				else if ((!keySet.contains(KeyEvent.VK_LEFT)) && (keySet.contains(KeyEvent.VK_RIGHT))) {
					doMovementLogic45(11, 8, 45);
				}
				else {
					doMovementLogic90(8, 0);
				}
			}
			else if ((!keySet.contains(KeyEvent.VK_UP)) && (keySet.contains(KeyEvent.VK_DOWN))) {
				if ((keySet.contains(KeyEvent.VK_LEFT)) && (!keySet.contains(KeyEvent.VK_RIGHT))) {
					doMovementLogic45(10, 9, 225);
				}
				else if ((!keySet.contains(KeyEvent.VK_LEFT)) && (keySet.contains(KeyEvent.VK_RIGHT))) {
					doMovementLogic45(11, 10, 135);
					
				}
				else {
					doMovementLogic90(10, 180);
				}
			}
			else if ((keySet.contains(KeyEvent.VK_LEFT)) && (!keySet.contains(KeyEvent.VK_RIGHT))) {
				doMovementLogic90(9, 270);
			}
			else if ((!keySet.contains(KeyEvent.VK_LEFT)) && (keySet.contains(KeyEvent.VK_RIGHT))) {
				doMovementLogic90(11, 90);
			}
			else {
				setIsMoving(false);
				if(getcompletingSequence() == false) {
					if(getActionStep()>0)
						setActionStep(0);
				}
			}
		}
	
	//For AI
	public void doLogic() {
		//Check to see if dead
		if(getHealth() <= 0){
			if(getActionSequence() != 20){
				setActionSequence(20);
				setActionStep(0);
				setIsMoving(false);
			}
			continueSequence();
			return;
		}
		
		int xDistFromHero;
		int yDistFromHero;
		if(this instanceof Arbiter){
			int tempNum = (int) (Math.random() * 100);
		
			if(Game.getIsTitleScreen()){
				if(markedForDeath == Game.getHero()){
					Drawable temp = Game.getDrawables().get((int)(Math.random() * Game.getDrawables().size()));
					while(!(temp instanceof Sprite)){
						temp = Game.getDrawables().get((int)(Math.random() * Game.getDrawables().size()));
					}
					markedForDeath = (Sprite)temp;
					markedForDeath.setMarkedForDeath(this);
				}
				else if(!Game.getDrawables().contains(markedForDeath)){
					Drawable temp = Game.getDrawables().get((int)(Math.random() * Game.getDrawables().size()));
					while(!(temp instanceof Sprite)){
						temp = Game.getDrawables().get((int)(Math.random() * Game.getDrawables().size()));
					}
					markedForDeath = (Sprite)temp;
				}
			}
			else{
				if(!Game.getDrawables().contains(markedForDeath)){
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
				}
			}
		}		
		else{		
			if(Game.getIsTitleScreen()){
				if(markedForDeath == Game.getHero()){
					Drawable temp = Game.getDrawables().get((int)(Math.random() * Game.getDrawables().size()));
					while(!(temp instanceof Sprite) & !(temp instanceof Modifier)){
						temp = Game.getDrawables().get((int)(Math.random() * Game.getDrawables().size()));
					}
					markedForDeath = (Sprite)temp;
					markedForDeath.setMarkedForDeath(this);
				}
				else if(!Game.getDrawables().contains(markedForDeath)){
					Drawable temp = Game.getDrawables().get((int)(Math.random() * Game.getDrawables().size()));
					while(!(temp instanceof Sprite) & !(temp instanceof Modifier)){
						temp = Game.getDrawables().get((int)(Math.random() * Game.getDrawables().size()));
					}
					markedForDeath = (Sprite)temp;
				}
			}
		}
		xDistFromHero = getXPos() - markedForDeath.getXPos();
		yDistFromHero = getYPos() - markedForDeath.getYPos();
	
		 if(this instanceof OrcWizard){
				
				switch(getEffectiveDirection()){
					case 0:
						if(((xDistFromHero > -50) & (xDistFromHero < 50)) & ((yDistFromHero > 0) & (yDistFromHero < 2000))){
							setIsAttacking(true);
						}
						break;
					case 90:
						if(((xDistFromHero > -2000) & (xDistFromHero < 0)) & ((yDistFromHero > -50) & (yDistFromHero < 50))){
							setIsAttacking(true);
						}
						break;
					case 180:
						if(((xDistFromHero > -50) & (xDistFromHero < 50)) & ((yDistFromHero > -2000) & (yDistFromHero < 0))){
							setIsAttacking(true);
						}
						break;
					case 270:
						if(((xDistFromHero > 0) & (xDistFromHero < 2000)) & ((yDistFromHero > -50) & (yDistFromHero < 50))){
							setIsAttacking(true);
						}
						break;
				}
			}
		 else{
			 switch(getEffectiveDirection()){
				case 0:
					if(((xDistFromHero > -50) & (xDistFromHero < 50)) & ((yDistFromHero > 0) & (yDistFromHero < (50 + getAttackRange())))){
						setIsAttacking(true);
					}
					else if((xDistFromHero > 0) & ((yDistFromHero > 0) & (yDistFromHero < getAttackRange()))){
						setDirection(270);
					}
					else if((xDistFromHero < 0) & ((yDistFromHero > 0) & (yDistFromHero < getAttackRange()))){
						setDirection(90);
					}
					else if(((xDistFromHero > -50) & (xDistFromHero < 50)) & ((yDistFromHero < 0))){
						setDirection(180);
					}
					break;
				case 90:
					if(((xDistFromHero > -(50 + getAttackRange())) & (xDistFromHero < 0)) & ((yDistFromHero > -50) & (yDistFromHero < 50))){
						setIsAttacking(true);
					}
					else if((yDistFromHero > 0) & ((xDistFromHero > 0) & (xDistFromHero < getAttackRange()))){
						setDirection(0);
					}
					else if((yDistFromHero < 0) & ((xDistFromHero > 0) & (xDistFromHero < getAttackRange()))){
						setDirection(180);
					}
					else if(((yDistFromHero > -50) & (yDistFromHero < 50)) & ((xDistFromHero > 0))){
						setDirection(270);
					}
					break;
				case 180:
					if(((xDistFromHero > -50) & (xDistFromHero < 50)) & ((yDistFromHero > -(50 + getAttackRange())) & (yDistFromHero < 0))){
						setIsAttacking(true);
					}
					else if((xDistFromHero > 0) & ((yDistFromHero > 0) & (yDistFromHero < getAttackRange()))){
						setDirection(270);
					}
					else if((xDistFromHero < 0) & ((yDistFromHero > 0) & (yDistFromHero < getAttackRange()))){
						setDirection(90);
					}
					else if(((xDistFromHero > -50) & (xDistFromHero < 50)) & ((yDistFromHero > 0))){
						setDirection(0);
					}
					break;
				case 270:
					if(((xDistFromHero > 0) & (xDistFromHero < (50 + getAttackRange()))) & ((yDistFromHero > -50) & (yDistFromHero < 50))){
						setIsAttacking(true);
					}
					else if((yDistFromHero > 0) & ((xDistFromHero > 0) & (xDistFromHero < getAttackRange()))){
						setDirection(0);
					}
					else if((yDistFromHero < 0) & ((xDistFromHero > 0) & (xDistFromHero < getAttackRange()))){
						setDirection(180);
					}
					else if(((yDistFromHero > -50) & (yDistFromHero < 50)) & ((xDistFromHero < 0))){
						setDirection(90);
					}
					break;
			}
		 }
		
		//randomize direction 
		if(!(Game.getHero() == this)){
			if((getIsMoving() == false) & (getIsAttacking() == false) & (!(this instanceof Orc))){
				setDirection((int) (45 * (Math.floor(((Math.random() * 360) / 45)))));
			}
		}
		
		if(getIsAttacking() & getcompletingSequence() != true) {
			//int attackSel = (int) (Math.random() * 100);
			//if(this instanceof Projectile){
			//	attackSel = 0;
			//}
			//if(attackSel%4 == 0){
				if(ability1Ready){
					setInvokedAbility(1);			
					ability1Setup();
					setIsAttacking(false);
					ability1Ready = false;
				}
			//}
			/* Below doesnt work, it freezes the game...
			else if(attackSel%4 == 1){
				if(ability2Ready){
					setInvokedAbility(2);				
					ability2Setup();
					setIsAttacking(false);
					ability2Ready = false;
				}
			}
			else if(attackSel%4 == 2){
				if(ability3Ready){
					setInvokedAbility(3);							
					ability3Setup();
					setIsAttacking(false);
					ability3Ready = false;
				}
			}
			else if(attackSel%4 == 3){
				if(ability4Ready){
					setInvokedAbility(4);					
					ability4Setup();
					setIsAttacking(false);
					ability4Ready = false;
				}
			}
			*/
		}
		
		if(getcompletingSequence() == true) {				//prevents other actions while performing ability
			continueSequence();								//Moved to Sprite to generalize movement
		}
		else{
			setIsMoving(true);
		}
		
		if(getMoveCasting() == true)
			setIsMoving(true);
		
		if (getDirection() == 315) {
			doMovementLogic45(9, 8, 315);
		}
		else if (getDirection() == 45) {
			doMovementLogic45(11, 8, 45);
		}
		else if (getDirection() == 0) {
			doMovementLogic90(8, 0);
		}
		else if (getDirection() == 225) {
			doMovementLogic45(10, 9, 225);
		}
		else if (getDirection() == 135) {
			doMovementLogic45(11, 10, 135);
		}
		else if (getDirection() == 180){
			doMovementLogic90(10, 180);
		}
		else if (getDirection() == 270) {
			doMovementLogic90(9, 270);
		}
		else if (getDirection() == 90) {
			doMovementLogic90(11, 90);
		}
		else {
			setIsMoving(false);
			if(getcompletingSequence() == false) {
				if(getActionStep()>0)
					setActionStep(0);
			}
		}
		
		if(collisionCounter > 5){
			setDirection((int) (45 * (Math.floor(((Math.random() * 360) / 45)))));
		}
		if(getIsAttacking() == true){
			setIsMoving(false);
		}
	}
	
	public void paint(Graphics imageGraphics) {
		imageGraphics.drawImage(getGraphic(), getXPos(), getYPos(), getXPos()+getTileWidth(), getYPos()+getTileHeight(), getActionStep()*getTileWidth(), getActionSequence()*getTileHeight(), getActionStep()*getTileWidth()+getTileWidth(), getActionSequence()*getTileHeight()+getTileHeight(), null);
		if(this != Game.getHero()){
			int percentHealth = (60*this.getHealth())/this.getMaxHealth();			//155 is the px width of the health bar
			imageGraphics.setColor(Color.red);
			imageGraphics.fillRect(this.getXPos(), this.getYPos(), 60, 8);
			imageGraphics.setColor(Color.green);
			imageGraphics.fillRect(this.getXPos(), this.getYPos(), percentHealth, 8);

		}
	}
}
