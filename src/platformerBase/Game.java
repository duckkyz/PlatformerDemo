package platformerBase;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import platformerBase.DrawableClasses.*;
import platformerBase.FloorTiles.*;
import platformerBase.ImplementedEntities.*;
import platformerBase.ImplementedModifiers.*;
import platformerBase.ImplementedProjectiles.*;
import platformerBase.TitleScreenSpash.TitleText;

public class Game {
	private static ArrayList<Drawable> drawables = new ArrayList<Drawable>();
	private static Sprite hero;
	private static final int mapHeight = 2*1080;
	private static final int mapWidth = 2*1920;
	private static int currentWave = 0;
	private boolean waveDoneSpawning = true;
	private int spawnCounter = 0;
	private boolean debugText = false;
	private static boolean isTitleScreen = false;
	private int regainCounter = 0;
	private int powerUpCounter = 0;
	
	public Game() {
		setUpGame();
	}

	public static Sprite getHero() {
		return hero;
	}
	
	public static ArrayList<Drawable> getDrawables() {
		return drawables;
	}
	
	public static int getMapHeight(){
		return mapHeight;
	}
	
	public static int getMapWidth(){
		return mapWidth;
	}
	
	public static int getCurrentWave() {
		return currentWave;
	}
	
	public static boolean getIsTitleScreen() {
		return isTitleScreen;
	}
	
	public static void setIsTitleScreen(boolean b) {
		isTitleScreen = b;		
	}
	
	public static void setHero(Sprite newHero) {
		hero = newHero;
	}
	
	public static void addDrawable(Drawable drawable) {
		drawables.add(drawable);
	}
	
	public static void removeDrawable(Drawable drawable) {
		drawables.remove(drawable);
	}
		
	public void setUpGame(){
		//Removes all things from game so it starts fresh
		currentWave = 0;
		drawables.removeAll(drawables);

		//This makes the walls around the edge.
		for(int i=2;i<33;++i){
			drawables.add(new LeftWall(0,(i*64)));
			drawables.add(new RightWall((mapWidth)-64,(i*64)));
		}
		for(int i=1; i<59; ++i){
			drawables.add(new TopWall((i*64),0));
			drawables.add(new BotWall((i*64),(mapHeight)-64));
		}
		drawables.add(new TopLeftCornerWall(0,0));
		drawables.add(new TopRightCornerWall((59*64),0));
		drawables.add(new BotLeftCornerWall(0,(mapHeight)-64));
		drawables.add(new BotRightCornerWall((59*64),(mapHeight)-64));
		
		//Testing walls
		
		//test
		drawables.add(new LightSwitch(48, mapHeight - 128));
		drawables.add(new LargeLight(48, mapHeight - (64*3)));
		drawables.add(new LargeLight(64*2, mapHeight - (64*2)));
		
		Modifier temp = new Speedup(1, 256, 256, true);
		drawables.add(temp);
		
		if(isTitleScreen){
			//TODO: fix image so it doenst look like its breathing
			drawables.add(new TitleText((mapWidth/2) - ((64 * 44)/2),mapHeight/2));
		}
		
		TikiGolem spawnGolem = new TikiGolem(0,0);
		for(int j = 0; j < 10; ++j){
			for(int i = 0; i < 2; ++i){
				//Initial new wall
				spawnGolem = new TikiGolem(((int)(Math.floor((Math.random() * (mapWidth - 126))/64) * 64)),((int)(Math.floor((Math.random() * (mapHeight - 192))/64) * 64)));

				//Checks to see if its on top of something else
				while((checkCanSpawn(spawnGolem) == false) & (drawables.size() < mapWidth*mapHeight)){
					spawnGolem = new TikiGolem(((int)(Math.floor((Math.random() * (mapWidth - 126))/64) * 64)),((int)(Math.floor((Math.random() * mapHeight)/64) * 64)));
				}
				drawables.add(spawnGolem);
			}
		}
		
		Sprite spawnSprite = new Sprite(0,0,0);
		if(isTitleScreen){
			for(int i = 0; i < 20; ++i){
				int orcDirection = (int) (45 * (Math.floor(((Math.random() * 360) / 45))));
				int orcXPos = (int)(Math.floor((Math.random() * (mapWidth - 2*64))/64) * 64);
				int orcYPos = (int)(Math.floor((Math.random() * (mapHeight - 2*64))/64) * 64);
				
				while((checkCanSpawn(spawnSprite) == false) & (drawables.size() < mapWidth*mapHeight)){
					orcDirection = (int) (45 * (Math.floor(((Math.random() * 360) / 45))));
					orcXPos = (int)(Math.floor((Math.random() * (mapWidth - 2*64))/64) * 64);
					orcYPos = (int)(Math.floor((Math.random() * (mapHeight - 2*64))/64) * 64);
					spawnSprite = new Sprite(orcDirection, orcXPos, orcYPos);
				}
				
				if(i%15 == 0){
					drawables.add(new Arbiter(orcDirection, orcXPos, orcYPos));
				}
				else if(i%3 == 1){
					drawables.add(new OrcWizard(orcDirection, orcXPos, orcYPos));
				}
				else{
					drawables.add(new Orc(orcDirection, orcXPos, orcYPos));

				}
			}
		}
	}
	
	public static boolean checkCanSpawn(Drawable toSpawn){
		int newX = ((toSpawn.getXPos() + toSpawn.getLeftHitBox()));
		int newY = ((toSpawn.getYPos() + toSpawn.getTopHitBox()));
		int newMaxX = newX + (toSpawn.getTileWidth() - toSpawn.getLeftHitBox() - toSpawn.getRightHitBox());	//Right
		int newMaxY = newY + (toSpawn.getTileHeight() - toSpawn.getTopHitBox() - toSpawn.getBotHitBox());	//Bottom
		
		int dX;
		int dY;
		int dMaxX;
		int dMaxY;
		
		
		for(Drawable d : drawables){
			dX = ((d.getXPos() + d.getLeftHitBox()));
			dY = ((d.getYPos() + d.getTopHitBox()));
			dMaxX = dX + (d.getTileWidth() - d.getLeftHitBox() - d.getRightHitBox());	//Right
			dMaxY = dY + (d.getTileHeight() - d.getTopHitBox() - d.getBotHitBox());	//Bottom
			
			if((newX >= dX) & (newX <= dMaxX)){ 			//Collision from the left
				if((newY >= dY) & (newY <= dMaxY)){		//Collision from the bottom
					return false;
				}
				
				else if((newMaxY >= dY) & (newMaxY <= dMaxY)){	//Collision from top
					return false;
				}
			}
			else if ((newMaxX >= dX) & (newMaxX <= dMaxX)){	//Collision from the right
				if((newY >= dY) & (newY <= dMaxY)){		//Collision from the bottom
					return false;
				}
				
				else if((newMaxY >= dY) & (newMaxY <= dMaxY)){	//Collision from top
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean projectileHandling(Sprite movingS, Drawable d){
		if(movingS instanceof Projectile){
			if(d instanceof Wall){
				if(movingS instanceof GrappleArrow){
					GrappleArrow temp = (GrappleArrow) movingS;
					temp.attack(d);
				}
				drawables.remove(movingS);
				return true;
			}
			else if(d instanceof Projectile){
				return true;
			}
			else{
				if(d instanceof Sprite){
					Projectile p = (Projectile) movingS;
					Sprite dSprite = (Sprite) d;
					if(isTitleScreen == false){
						if(p.getIsFromHero() & (!(dSprite == hero))){
							if((dSprite instanceof Hero) | (dSprite instanceof Villain)){
								p.attack(dSprite);
							}
							drawables.remove(p);
						}
						else if(!(p.getIsFromHero()) & (dSprite == hero)){
							if((dSprite instanceof Hero) | (dSprite instanceof Villain)){
								p.attack(dSprite);
							}
							drawables.remove(p);
						}
						return true;
					}
					else{
						if((dSprite instanceof Hero) | (dSprite instanceof Villain)){
							p.attack(dSprite);
						}
						drawables.remove(p);
					}
				}
				return true;
			}
		}
		return false;
	}
	
	public boolean powerUpHandling(Sprite movingS, Drawable d){
		if(movingS instanceof Modifier){
			if(movingS instanceof BearTrap){
			}
			Modifier movingModifier = (Modifier) movingS;
			if(d instanceof Sprite){
				Sprite spriteD = (Sprite) d;
				if(movingModifier.getIsActivated() == false){
					if(((spriteD == Game.getHero() | (spriteD instanceof Arbiter))) & (movingModifier.getHeroOnly() == true)){
						movingModifier.activate(spriteD);
					}
					else if((spriteD instanceof Sprite) & (movingModifier.getHeroOnly() == false) & (spriteD != Game.getHero())){
						movingModifier.activate(spriteD);
					}
				}
			}
			return true;
		}
		else if(d instanceof Modifier){
			Modifier movingModifier = (Modifier) d;
			if(d instanceof Sprite){
				if(movingModifier.getIsActivated() == false){
					if(((movingS == Game.getHero() | (movingS instanceof Arbiter))) & (movingModifier.getHeroOnly() == true)){
						movingModifier.activate(movingS);
					}
					else if((movingS instanceof Sprite) & (movingModifier.getHeroOnly() == false) & (movingS != Game.getHero())){
						movingModifier.activate(movingS);
					}
				}
			}
			return true;
		}
		return false;
	}
	
	private void spawnNewWave(int currentWave) {
		if(waveDoneSpawning == false){
			Sprite spawnSprite = new Sprite(0,0,0);
			int orcDirection = (int) (45 * (Math.floor(((Math.random() * 360) / 45))));
			int orcXPos = (int)(Math.floor((Math.random() * (mapWidth - 2*64))/64) * 64);
			int orcYPos = (int)(Math.floor((Math.random() * (mapHeight - 2*64))/64) * 64);				
			while((checkCanSpawn(spawnSprite) == false) & (drawables.size() < mapWidth*mapHeight)){
				orcDirection = (int) (45 * (Math.floor(((Math.random() * 360) / 45))));
				orcXPos = (int)(Math.floor((Math.random() * (mapWidth - 2*64))/64) * 64);
				orcYPos = (int)(Math.floor((Math.random() * (mapHeight - 2*64))/64) * 64);
				spawnSprite = new Sprite(orcDirection, orcXPos, orcYPos);
			}
			
			if(spawnCounter%15 == 14){
				drawables.add(new Arbiter(orcDirection, orcXPos, orcYPos));
			}
			else if(spawnCounter%3 == 1){
				drawables.add(new OrcWizard(orcDirection, orcXPos, orcYPos));
			}
			else{
				drawables.add(new Orc(orcDirection, orcXPos, orcYPos));

			}
			
			++spawnCounter;
			if(spawnCounter > currentWave){
				waveDoneSpawning = true;
				++this.currentWave;
			}	
		}
	}
	
	private void spawnPowerUp(int powerUpCounter) {
		int willSpawn = (int)(Math.random() * 10000);
		if(powerUpCounter <= (Game.getCurrentWave()/4))
		if(willSpawn > 9998){
			Sprite spawnSprite = new Sprite(0,0,0);
			int orcXPos = (int)(Math.floor((Math.random() * (mapWidth - 2*64))/64) * 64);
			int orcYPos = (int)(Math.floor((Math.random() * (mapHeight - 2*64))/64) * 64);				
			while((checkCanSpawn(spawnSprite) == false) & (drawables.size() < mapWidth*mapHeight)){
				orcXPos = (int)(Math.floor((Math.random() * (mapWidth - 2*64))/64) * 64);
				orcYPos = (int)(Math.floor((Math.random() * (mapHeight - 2*64))/64) * 64);
				spawnSprite = new Sprite(0, orcXPos, orcYPos);
			}
			
			drawables.add(new Speedup(1, orcXPos, orcYPos, true));	
			++powerUpCounter;
		}
	}
	
	public boolean checkForOutsideMap(Drawable d){
		if(d.getXPos() > mapWidth){
			removeDrawable(d);
			return true;
		}
		else if(d.getXPos() < 0){
			removeDrawable(d);
			return true;
		}
		else if(d.getYPos() > mapHeight){
			removeDrawable(d);
			return true;
		}
		else if(d.getYPos() < 0) {
			removeDrawable(d);
			return true;
		}
		else{
			return false;
		}
	}
	
	public void setUpMovements(Set keySet){
		hero.doLogic(keySet);
		for(int x = 0; drawables.size() > x; x++){
			Drawable d = drawables.get(x);
			if(d instanceof Sprite){
				if(checkForOutsideMap(d) == false){
					d.doLogic();
				}
			}
			if(d instanceof Modifier) {
				d.doLogic();
			}
		}
	}
	
	public void checkForSpriteCollision(ArrayList<Drawable> drawables, Sprite hero){
		Collections.sort(drawables, Drawable.PosComparator);				//Sorts drawables so there is no need for spawn order collision casing
		Drawable movingD;													
		if(debugText){
		}
		for(int y = 0; (drawables.size() + 1) > y; y++){					//Iterates through all drawables and hero
			if(y == drawables.size()){										//Un-intrusively inserts hero into check
				if(isTitleScreen == false){
					movingD = hero;
				}
				else{
					continue;
				}
			}
			else{
				movingD = drawables.get(y);
			}
			
			if(!(movingD instanceof Sprite)){								//If this drawable is not a sprite, it cant move so no need to check if its colliding
				continue;
			}
			
			Sprite movingS = (Sprite) movingD;								//If its a sprite, lets cast it to sprite
			
			int newX = 0;
			int newY = 0;
			int newMaxX = 0;	//Right
			int newMaxY = 0;	//Bottom
			
			newX = ((movingS.getXPos() + movingS.getLeftHitBox()) + (int)(Math.round(Math.sin(Math.toRadians(movingS.getDirection())) * movingS.getSpeed())));
			newY = ((movingS.getYPos() + movingS.getTopHitBox()) + (int)(Math.round(Math.cos(Math.toRadians(movingS.getDirection())) * movingS.getSpeed() * (-1))));
			newMaxX = newX + (movingS.getTileWidth() - movingS.getLeftHitBox() - movingS.getRightHitBox());	//Right
			newMaxY = newY + (movingS.getTileHeight() - movingS.getTopHitBox() - movingS.getBotHitBox());	//Bottom

			//Initializes the movement booleans
			boolean canGoUp = true;
			boolean canGoDown = true;
			boolean canGoLeft = true;
			boolean canGoRight = true;
			
			//Used for single collision checking
			ArrayList<Integer> collisionX = new ArrayList<Integer>();
			ArrayList<Integer> collisionY = new ArrayList<Integer>();
			int collision1Count = 0;
			int collision2Count = 0;
			int collision3Count = 0;
			int collision4Count = 0;
			int dX;
			int dY;
			int dMaxX;
			int dMaxY;
			
			//To save need of creating new d every iteration
			Drawable d;
			
			//Iterates through the list of drawables again, this time checking if movingS is hitting this drawable
			for(int x = 0; (drawables.size() + 1) > x; x++){
				if(x == (drawables.size())){					//Insert hero
					if(isTitleScreen == false){
						d = hero;
					}
					else{
						continue;
					}
				}
				else{
					d = drawables.get(x);
				}
				if(d == movingS){								//Skip yourself because obvious
					continue;
				}
				
				if(d == null){
					continue;
				}
				
				dX = ((d.getXPos() + d.getLeftHitBox()));
				dY = ((d.getYPos() + d.getTopHitBox()));
				dMaxX = dX + (d.getTileWidth() - d.getLeftHitBox() - d.getRightHitBox());	//Right
				dMaxY = dY + (d.getTileHeight() - d.getTopHitBox() - d.getBotHitBox());	//Bottom

				if((newX >= dX) & (newX <= dMaxX)){ 			//Collision from the left
					if((newY >= dY) & (newY <= dMaxY)){		//Collision from the bottom
						//Projectile handling happens here, if it should continue it will
						//if(movingS == hero){
							if(debugText){
								System.out.println("collision 1 for " + d.getClass().getSimpleName() + ": " + d.getXPos() + ", " + d.getYPos() + ", " + movingS.getDirection());
							}
						//}
						if(projectileHandling(movingS, d)){
							continue;
						}
						
						if(powerUpHandling(movingS, d)){
							continue;
						}
						
						if(movingS.getDirection() == 0){
							canGoUp = false;
						}
						
						else if(movingS.getDirection() == 45){
							canGoUp = false;
						}
						
						else if(movingS.getDirection() == 225){
							canGoLeft = false;
							if(collision1Count > 0){
								//canGoDown = false;
							}
						}
						
						else if(movingS.getDirection() == 270){
							canGoLeft = false;
						}
						else if(movingS.getDirection() == 315){
							if(collision3Count > 0){
								canGoUp = false;
								if(!collisionY.contains(dMaxY)){
									if((collision1Count > 0)){
										canGoLeft = false;
									}
								}
							}
						}
						
						if(d instanceof BotLeftCornerWall){
							movingS.setDirection(-movingS.getDirection());
							canGoDown = false;
							canGoLeft = false;
						}
						
						++collision1Count;											//Used for single collision 
						collisionX.add(dMaxX);
						collisionY.add(dMaxY);
						
					}
					
					else if((newMaxY >= dY) & (newMaxY <= dMaxY)){	//Collision from top
						//Projectile handling happens here, if it should continue it will
						//if(movingS == hero){
							if(debugText){
								System.out.println("collision 2 for " + d.getClass().getSimpleName() + ": " + d.getXPos() + ", " + d.getYPos() + ", " + movingS.getDirection());
							}
						//}
						if(projectileHandling(movingS, d)){
							continue;
						}
					
						if(powerUpHandling(movingS, d)){
							continue;
						}
						
						if(movingS.getDirection() == 135){
							canGoDown = false;
						}
						
						else if(movingS.getDirection() == 270){
							canGoLeft = false;
						}
						
						else if(movingS.getDirection() == 315){
							canGoLeft = false;
							if((collision1Count > 0)){
								if(!collisionX.contains(dMaxX)){
									canGoUp = false;
								}
							}
						}	
						
						else if(movingS.getDirection() == 180){
							canGoDown = false;
						}
						
						else if(movingS.getDirection() == 225){
							if(collision4Count > 0){
								canGoDown = false;
							}
							if((collision1Count > 0)){
								
							}
							if(collision2Count >0){
								canGoDown = false;
							}
						}
						
						++collision2Count;													//Single Collision Detection
						collisionX.add(dMaxX);
						collisionY.add(dY);
						
					}
				}
				else if ((newMaxX >= dX) & (newMaxX <= dMaxX)){	//Collision from the right
					if((newY >= dY) & (newY <= dMaxY)){		//Collision from the bottom
						//if(movingS == hero){
							if(debugText){
								System.out.println("collision 3 for " + d.getClass().getSimpleName() + ": " + d.getXPos() + ", " + d.getYPos() + ", " + movingS.getDirection());
							}
						//}
						//Projectile handling happens here, if it should continue it will
						if(projectileHandling(movingS, d)){
							continue;
						}

						if(powerUpHandling(movingS, d)){
							continue;
						}
						
						++collision3Count;												//Single Collision detection
						collisionX.add(dX);
						collisionY.add(dMaxY);
						
						if(movingS.getDirection() == 0){
							canGoUp = false;
						}
						
						if((collision3Count > 1) & (movingS.getDirection() == 45)){
							canGoRight = false;
						}
						
						else if(movingS.getDirection() == 90){
							canGoRight = false;
						}
						
						else if(movingS.getDirection() == 135){
							canGoRight = false;
						}
						
						else if(movingS.getDirection() == 315){
							canGoUp = false;
						}
					}
					else if((newMaxY >= dY) & (newMaxY <= dMaxY)){	//Collision from top
						//if(movingS == hero){
							//if(debugText){
								System.out.println("collision 4 for " + d.getClass().getSimpleName() + ": " + d.getXPos() + ", " + d.getYPos() + ", " + movingS.getDirection());
							//}
						//}
						//Projectile handling happens here, if it should continue it will
						if(projectileHandling(movingS, d)){
							continue;
						}
						
						if(powerUpHandling(movingS, d)){
							continue;
						}
						
						++collision4Count;													//Single collision detection
						collisionX.add(dX);
						collisionY.add(dY);
						
						if(movingS.getDirection() == 45){
							canGoRight = false;
						}
						
						else if(movingS.getDirection() == 90){
							canGoRight = false;
						}
						
						else if(movingS.getDirection() == 180){
							canGoDown = false;
						}
						
						else if(movingS.getDirection() == 225){
							canGoDown = false;
						}
					}
				}
				if((canGoDown & canGoRight & canGoUp & canGoLeft) == false){
					if(d instanceof Wall){
						movingS.incrementCollisionCounter();
					}
				}
				else{
					movingS.decrementCollisionCounter();
				}
			}
			
			/*** This is the single collision detection section ***/
			if((collision1Count + collision2Count + collision3Count + collision4Count) == 1){
				if(collision1Count == 1){
					if((movingS.getDirection() == 0) | (movingS.getDirection() == 45)){
						canGoUp = false;
					}
					else if((movingS.getDirection() == 270) | (movingS.getDirection() == 225)){
						canGoLeft = false;
					}
					else if(movingS.getDirection() == 315){
						if((collisionX.get(0) - newX) < (collisionY.get(0) - newY)){
							canGoLeft = false;
						}
						else if((collisionX.get(0) - newX) > (collisionY.get(0) - newY)){
							canGoUp = false;
						}
						else{
							canGoUp = false;
							canGoLeft = false;
						}
					}
				}
				else if(collision2Count == 1){
					if((movingS.getDirection() == 135) | (movingS.getDirection() == 180)){
						canGoDown = false;
					}
					else if((movingS.getDirection() == 270) | (movingS.getDirection() == 315)){
						canGoLeft = false;
					}
					else if(movingS.getDirection() == 225){
						if((collisionX.get(0) - newX) < (newMaxY - collisionY.get(0))){
							canGoLeft = false;
						}
						else if((collisionX.get(0) - newX) > (newMaxY - collisionY.get(0))){
							canGoDown = false;
						}
						else{
							canGoDown = false;
							canGoLeft = false;
						}
					}
				}
				else if(collision3Count == 1){
					if((movingS.getDirection() == 0) | (movingS.getDirection() == 315)){
						canGoUp = false;
					}
					else if((movingS.getDirection() == 90) | (movingS.getDirection() == 135)){
						canGoRight = false;
					}
					else if(hero.getDirection() == 45){					
						if((newMaxX - collisionX.get(0)) < (collisionY.get(0) - newY)){
							canGoRight = false;
						}
						else if((newMaxX - collisionX.get(0)) > (collisionY.get(0) - newY)){
							canGoUp = false;					
						}
						else{
							canGoUp = false;
							canGoRight = false;
						}
					}
				}
				else if(collision4Count == 1){
					if((movingS.getDirection() == 180) | (movingS.getDirection() == 225)){
						canGoDown = false;
					}
					else if((movingS.getDirection() == 45) | (movingS.getDirection() == 90)){
						canGoRight = false;
					}
					else if(hero.getDirection() == 135){
						if((newMaxX - collisionX.get(0)) < (newMaxY - collisionY.get(0))){
							canGoRight = false;
						}
						else if((newMaxX - collisionX.get(0)) > (newMaxY - collisionY.get(0))){
							canGoDown = false;
						}
						else{
							canGoDown = false;
							canGoRight = false;
						}
					}
				}
			}
			
			/*** Actual collision handling cases ***/
			collisionHandling(movingS, canGoUp, canGoDown, canGoLeft, canGoRight);
		}
	}

	public void collisionHandling(Sprite movingS, boolean canGoUp, boolean canGoDown, boolean canGoLeft, boolean canGoRight){
		if(movingS.getDirection() == 0){
			if(canGoUp == false){
				movingS.setIsMoving(false);
			}
		}

		else if(movingS.getDirection() == 45){
			if((canGoUp == false) & (canGoRight == false)){
				movingS.setIsMoving(false);
			}
			else if(canGoUp == false){
				movingS.setDirection(90);
			}
			else if(canGoRight == false){
				movingS.setDirection(0);
			}
		}
		
		else if(movingS.getDirection() == 90){
			if(canGoRight == false){
				movingS.setIsMoving(false);
			}
		}
		
		else if(movingS.getDirection() == 135){
			if((canGoDown == false) & (canGoRight == false)){
				movingS.setIsMoving(false);
			}
			else if(canGoDown == false){
				movingS.setDirection(90);
			}
			else if(canGoRight == false){
				movingS.setDirection(180);
			}
		}
		
		else if(movingS.getDirection() == 180){
			if(canGoDown == false){
				movingS.setIsMoving(false);
			}
		}
		
		else if(movingS.getDirection() == 225){
			if((canGoDown == false) & (canGoLeft == false)){
				movingS.setIsMoving(false);
			}
			else if(canGoDown == false){
				movingS.setDirection(270);
			}
			else if(canGoLeft == false){
				movingS.setDirection(180);
			}
		}
		
		else if(movingS.getDirection() == 270){
			if(canGoLeft == false){
				movingS.setIsMoving(false);
			}
		}
		
		else if(movingS.getDirection() == 315){
			if((canGoUp == false) & (canGoLeft == false)){
				movingS.setIsMoving(false);
			}
			else if(canGoUp == false){
				movingS.setDirection(270);
			}
			else if(canGoLeft == false){
				movingS.setDirection(0);
			}
		}
	}
	
	public void moveDrawables(){
		if(isTitleScreen == false){
			hero.move();
		}
		for(int x = 0; drawables.size() > x; x++){
			Drawable d = drawables.get(x);
			//if((d instanceof Sprite) & !(d instanceof Projectile)){
			//	Sprite s = (Sprite)d;
			//	s.setHealth(s.getHealth() - 1);
			//}
			d.move();
		}
	}
		
	public boolean doGameLogic(Set keySet) {	
		
		if(isTitleScreen == false){
			//Checks if hero is dead if so returns false
			if(hero.getHealth() <= 0){
				if(hero.getActionStep() == 5) {
					return false;
				}
			}
		}
		
		//Hero heath/mana regain
		if(regainCounter > 1){
			if(isTitleScreen == false){
				if((hero.getHealth() < hero.getMaxHealth() & hero.getHealth() > 0)){
					hero.setHealth(hero.getHealth() + 1);
				}
				if((hero.getMana() < hero.getMaxMana())){
					hero.setMana(hero.getMana() + 1);
				}
			}
			for(Drawable d : drawables){
				if(d instanceof Sprite){
					Sprite s = (Sprite) d;
					if((s.getMana() < s.getMaxMana())){
						s.setMana(s.getMana() + 1);
					}
				}
			}
			regainCounter = 0;
		}
		else{
			++regainCounter;
		}
	
		//Debug stuff
		if(keySet.contains(KeyEvent.VK_B)){
			this.debugText = true;
			
			for(int i=0; i<(Game.getMapWidth()/64); ++i){
				drawables.add(new Arrow(180, i * 64, 128, true));
			}
			for(int i=0; i<(Game.getMapHeight()/64); ++i){
				drawables.add(new Fireball(90, 64, i * 64, true));
			}
			
		}
		else{
			this.debugText = false;
		}
		
		//Check if all non hero entities are dead, if so spawn a new wave
		int waveCounter = 0;
		for(int x = 0; drawables.size() > x; x++){
			Drawable d = drawables.get(x);
			if(d instanceof Sprite){
				if(d instanceof Projectile | d instanceof Modifier){
					continue;
				}
				++waveCounter;
				break;
			}
		}
		if(isTitleScreen == true){
			if(waveCounter < 1){
				spawnCounter = 0;
				this.waveDoneSpawning = false;
			}
		}
		else{
			if(waveCounter == 0){
				spawnCounter = 0;
				this.waveDoneSpawning = false;
			}
		}
		spawnNewWave(currentWave);
		spawnPowerUp(powerUpCounter);
		//Set up movements
		setUpMovements(keySet);
		
		//Check for collision
		checkForSpriteCollision(drawables, hero);
		
		//Move if can
		moveDrawables();
		
		return true;
	}
}
