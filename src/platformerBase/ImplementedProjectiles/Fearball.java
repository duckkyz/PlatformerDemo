package platformerBase.ImplementedProjectiles;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import platformerBase.Game;
import platformerBase.DrawableClasses.Projectile;
import platformerBase.DrawableClasses.Sprite;
import platformerBase.ImplementedModifiers.*;
	
public class Fearball extends Projectile{			//Using the "CUSTOM" label to show what parameters should be changed when creating a similar projectile.
	
	private int xRef = 0;			//Location of first fireball in png. Is set to one of the 4 corners of the png
	private int yRef = 0;			
	private int xSlope = 1;			//As the actionSequence increments, is the next sequence in the positive or negative direction relative to the previous sequence
	private int ySlope = 1;
	private int xStart = 0;			//The top left coordinate in the portion of the png being drawn
	private int xStop = 0;			//The top right coordinate in the portion of the png being drawn
	private int yStart = 0;			//The bottom left coordinate in the portion of the png being drawn	
	private int yStop = 0;			//The bottem right coordinate in the portion of the png being drawn
	
	private int numOfFrames = 8;				//CUSTOM: Number of frames in the actionSequence (ex. 8 fireballs)
	private int limitingDimension = 0;			//The lesser of the width/height from the original png.
	private int nonlimitingDimension = 0;
	
	public Fearball(int direction, int spawnX, int spawnY, boolean heroProjectile) {
		super(direction, spawnX, spawnY, heroProjectile);
		
		try{
			setGraphic(ImageIO.read(new File("Drawable_Images/Fearball.png")));		//CUSTOM
			int originalWidth = getGraphic().getWidth();
			int originalHeight = getGraphic().getHeight();
			if(originalWidth <= originalHeight) {
				limitingDimension = originalWidth;
				nonlimitingDimension = originalHeight/numOfFrames-limitingDimension;
			}
			else {
				limitingDimension = originalHeight;
				nonlimitingDimension = originalWidth/numOfFrames-limitingDimension;
			}
			setGraphic(rotate(getGraphic(), 90));	//CUSTOM: Make sure to use the second parameter in the method to rotate the image to the 0th degree as a default

		}
		catch(IOException ex){
			
		}
		
		setActionSequence(0);
		setActionStep(0);
		setTileWidth((int)(limitingDimension+nonlimitingDimension*Math.abs(Math.sin(Math.toRadians(direction)))));
		setTileHeight((int)(limitingDimension+nonlimitingDimension*Math.abs(Math.cos(Math.toRadians(direction)))));
		setIsMoving(true);
		setXPos(getXPos()-getTileWidth()/2);
		setYPos(getYPos()-getTileHeight()/2);
		
		setTopHitBox(4 + ((((-32 * (int)(Math.cos(Math.toRadians(direction)))) % 34) + 34 ) % 34));			//0: 6  90:4  180:36  270:4
		setBotHitBox(4 + ((((32 * (int)(Math.cos(Math.toRadians(direction)))) % 34) + 34 ) % 34));			//0: 36 90:4  180:6   270:4
		setLeftHitBox(4 + ((((32 * (int)(Math.sin(Math.toRadians(direction)))) % 34) + 34 ) % 34));			//0: 4  90:36 180:4   270:6
		setRightHitBox(4 + ((((-32 * (int)(Math.sin(Math.toRadians(direction)))) % 34) + 34 ) % 34));		//0: 4  90:6  180:4   270:36
		
		if(direction >= 0 & direction <= 90) {
			xRef = getGraphic().getWidth();
			yRef = 0;
			xSlope = -1;
			ySlope = 1;
		}
		else if(direction >= 90 & direction <= 180) {
			xRef = getGraphic().getWidth();
			yRef = getGraphic().getHeight();
			xSlope = -1;
			ySlope = -1;
		}
		else if(direction >= 180 & direction <= 270) {
			xRef = 0;
			yRef = getGraphic().getHeight();
			xSlope = 1;
			ySlope = -1;
		}
		else if(direction >= 270 & direction <= 360) {
			xRef = 0;
			yRef = 0;
			xSlope = 1;
			ySlope = 1;
		}
		
		setDamage(5);											//CUSTOM
		setSpeed(30);											//CUSTOM
	}

	public void doLogic() {
		if(getActionSequence() == (numOfFrames - 1)){
			setActionSequence(0);
		}
		else{
			setActionSequence(getActionSequence()+1);
		}
		//setActionSequence(5);
		
	}
	
	public void attack(Sprite beingAttacked){
		if(heroProjectile == true){
			Fear scare = new Fear(0,this.getXPos(), this.getYPos(), true);
			scare.activate(beingAttacked);
			Poison drain = new Poison(0, this.getXPos(), this.getYPos(), true);
			drain.activate(beingAttacked);
			Game.addDrawable(drain);
			Game.addDrawable(scare);
		}
		
		int newHealth = beingAttacked.getHealth() - getDamage();
		if(newHealth < 0){
			System.out.println(beingAttacked.getClass().getSimpleName() + " died to a fearball???");
		}
		beingAttacked.setHealth(newHealth);
		
	}
	
	public void paint(Graphics imageGraphics) {			
		if(getDirection() == 0 | getDirection() == 180) {
			xStart = 0;
			xStop = getTileWidth();
			yStart = getActionSequence()*getTileHeight();
			yStop = getActionSequence()*getTileHeight()+getTileHeight();
		}
		else if(getDirection() == 90 | getDirection() == 270) {
			xStart = getActionSequence()*getTileWidth();
			xStop = getActionSequence()*getTileWidth()+getTileWidth();
			yStart = 0;
			yStop = getTileHeight();
		}
		else if(getDirection() > 0 & getDirection() < 90) {
			int xSmoother = (int)(1+Math.round(Math.cos(Math.toRadians(getDirection()))));
			int ySmoother = (int)(-1-Math.round(Math.sin(Math.toRadians(getDirection()))));
			xStart = xSmoother*getActionSequence()  +xRef+xSlope*getTileWidth()+xSlope*getActionSequence()*(getGraphic().getWidth()-getTileWidth()*(int)Math.abs(Math.cos(Math.toRadians(getDirection()))))/numOfFrames;
			xStop = xSmoother*getActionSequence()   +xRef+xSlope*getTileWidth()+xSlope*getActionSequence()*(getGraphic().getWidth()-getTileWidth()*(int)Math.abs(Math.cos(Math.toRadians(getDirection()))))/numOfFrames+getTileWidth();
			yStart = ySmoother*getActionSequence()  +yRef+ySlope*getActionSequence()*(getGraphic().getHeight()-getTileHeight()*(int)Math.abs(Math.sin(Math.toRadians(getDirection()))))/numOfFrames;
			yStop = ySmoother*getActionSequence()   +yRef+ySlope*getActionSequence()*(getGraphic().getHeight()-getTileHeight()*(int)Math.abs(Math.sin(Math.toRadians(getDirection()))))/numOfFrames+getTileHeight();
		}
		else if(getDirection() > 90 & getDirection() < 180) {
			int xSmoother = (int)(1+Math.abs(Math.round(Math.cos(Math.toRadians(getDirection())))));
			int ySmoother = (int)(1+Math.abs(Math.round(Math.sin(Math.toRadians(getDirection())))));
			xStart = xSmoother*getActionSequence()  +xRef+xSlope*getTileWidth()+xSlope*getActionSequence()*(getGraphic().getWidth()-getTileWidth()*(int)Math.abs(Math.cos(Math.toRadians(getDirection()))))/numOfFrames;
			xStop = xSmoother*getActionSequence()   +xRef+xSlope*getTileWidth()+xSlope*getActionSequence()*(getGraphic().getWidth()-getTileWidth()*(int)Math.abs(Math.cos(Math.toRadians(getDirection()))))/numOfFrames+getTileWidth();
			yStart = ySmoother*getActionSequence()  +yRef+ySlope*getTileHeight()+ySlope*getActionSequence()*(getGraphic().getHeight()-getTileHeight()*(int)Math.abs(Math.sin(Math.toRadians(getDirection()))))/numOfFrames;
			yStop = ySmoother*getActionSequence()   +yRef+ySlope*getTileHeight()+ySlope*getActionSequence()*(getGraphic().getHeight()-getTileHeight()*(int)Math.abs(Math.sin(Math.toRadians(getDirection()))))/numOfFrames+getTileHeight();
		}
		else if(getDirection() > 180 & getDirection() < 270) {
			int xSmoother = (int)(-1-Math.abs(Math.round(Math.cos(Math.toRadians(getDirection())))));
			int ySmoother = (int)(1+Math.abs(Math.round(Math.sin(Math.toRadians(getDirection())))));
			xStart = xSmoother*getActionSequence()  +xRef+xSlope*getActionSequence()*(getGraphic().getWidth()-getTileWidth()*(int)Math.abs(Math.cos(Math.toRadians(getDirection()))))/numOfFrames;
			xStop = xSmoother*getActionSequence()   +xRef+xSlope*getActionSequence()*(getGraphic().getWidth()-getTileWidth()*(int)Math.abs(Math.cos(Math.toRadians(getDirection()))))/numOfFrames+getTileWidth();
			yStart = ySmoother*getActionSequence()  +yRef+ySlope*getTileHeight()+ySlope*getActionSequence()*(getGraphic().getHeight()-getTileHeight()*(int)Math.abs(Math.sin(Math.toRadians(getDirection()))))/numOfFrames;
			yStop = ySmoother*getActionSequence()   +yRef+ySlope*getTileHeight()+ySlope*getActionSequence()*(getGraphic().getHeight()-getTileHeight()*(int)Math.abs(Math.sin(Math.toRadians(getDirection()))))/numOfFrames+getTileHeight();
		}
		else if(getDirection() > 270 & getDirection() < 360) {
			int xSmoother = (int)(-1-Math.abs(Math.round(Math.cos(Math.toRadians(getDirection())))));
			int ySmoother = (int)(-1-Math.abs(Math.round(Math.sin(Math.toRadians(getDirection())))));
			xStart = xSmoother*getActionSequence()  +xRef+xSlope*getActionSequence()*(getGraphic().getWidth()-getTileWidth()*(int)Math.abs(Math.cos(Math.toRadians(getDirection()))))/numOfFrames;
			xStop = xSmoother*getActionSequence()   +xRef+xSlope*getActionSequence()*(getGraphic().getWidth()-getTileWidth()*(int)Math.abs(Math.cos(Math.toRadians(getDirection()))))/numOfFrames+getTileWidth();
			yStart = ySmoother*getActionSequence()  +yRef+ySlope*getActionSequence()*(getGraphic().getHeight()-getTileHeight()*(int)Math.abs(Math.sin(Math.toRadians(getDirection()))))/numOfFrames;
			yStop = ySmoother*getActionSequence()   +yRef+ySlope*getActionSequence()*(getGraphic().getHeight()-getTileHeight()*(int)Math.abs(Math.sin(Math.toRadians(getDirection()))))/numOfFrames+getTileHeight();
			//System.out.println("Direction: "+getDirection()+" xref: "+xRef+" As: "+getActionSequence()+" xStart: "+xStart+" xStop: "+xStop+" yStart: "+yStart+" yStop: "+yStop+" Graphic Width: "+getGraphic().getWidth()+" Graphic Height: "+ getGraphic().getHeight()+" Tile Width: "+getTileWidth()+" Title Hieght: "+getTileHeight());

		}
		imageGraphics.drawImage(getGraphic(), getXPos(), getYPos(), getXPos()+getTileWidth(), getYPos()+getTileHeight(), xStart, yStart, xStop, yStop, null);
	}
}
