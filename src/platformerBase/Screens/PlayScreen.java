package platformerBase.Screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import platformerBase.Game;
import platformerBase.ViewController;
import platformerBase.DrawableClasses.*;

public class PlayScreen extends GameState{

	//only do reocurring math operations once
	private int heroX;
	private int heroY;
	private int percentHealth;				//needed for drawing UI
	private int percentMana;
	private int heroResourcesXLocation = 0;		//for easy change if we want to move the resource location
	private int heroResourcesYLocation = 0;
	private Image heroResources;			//health/mana bar
	
	private boolean isPaused = false;
	
	public PlayScreen() {
		try {
			setBackgroundImage(ImageIO.read(new File("Background_Images/Map.png")));
			heroResources = ImageIO.read(new File("UI_Images/HeroResources.png"));
		} 
		catch(IOException ex) {
			
		}
		//TODO Maybe move this
		makeMap();
		setBlankImage(new BufferedImage(getBackgroundImage().getWidth(this), getBackgroundImage().getHeight(this), BufferedImage.TYPE_INT_ARGB));
		scaling = (double) getResolutionSizes()[0][0]/ (double) getImageWidth();
		
		scaling = scaling*1.25;																			//Added for Ben's testing. Reverted other values
		
		scaledBackgroundImageWidth = (int) (getBackgroundImage().getWidth(this) / (scaling));		
		scaledBackgroundImageHeight = (int) (getBackgroundImage().getHeight(this) / (scaling));	
		bottomBarYLocation = (getMonitorHeight()-getImageHeight()) / 2 + getImageHeight();
		rightBarXLocation = (getMonitorWidth()-getImageWidth()) / 2 + getImageWidth();
		halfDiffDisImgHeight = (getMonitorHeight()-getImageHeight()) / 2;
		halfDiffDisImgWidth = (getMonitorWidth()-getImageWidth()) / 2;
		Game.setIsTitleScreen(false);
		
	}

	public void keyPressed(KeyEvent keyEvent) {
		getKeySet().add(keyEvent.getKeyCode());
		
		if(keyEvent.getKeyCode()==KeyEvent.VK_ESCAPE) {
			TitleScreen titleScreen = new TitleScreen();
			getRootContainer().remove(this);
			getRootContainer().add(titleScreen);
			getRootContainer().doLayout();
			getRootContainer().repaint();				
		}
		else if(keyEvent.getKeyCode()==KeyEvent.VK_P) {
			if(isPaused == false) {
				ViewController.getTimer().stop();
				isPaused = true;
			}
			else {
				ViewController.getTimer().start();
				isPaused = false;
			}
		}

	}

	
	public void keyReleased(KeyEvent keyEvent) {
		getKeySet().remove(keyEvent.getKeyCode());
		
	}

	
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub.
		
	}

	
	public void actionPerformed(ActionEvent arg0) {
		
	}
	
	public void prepaint(Sprite hero, ArrayList<Drawable> drawables) {
		setImageGraphics(getBlankImage().getGraphics());		//use imageGraphics to draw on the image
		getImageGraphics().drawImage(this.getBackgroundImage(), 0, 0, this);

		
		for(int x = 0;x < drawables.size();x++) {
			drawables.get(x).paint(getImageGraphics());
		}
		
		hero.paint(getImageGraphics());
		heroX = hero.getXPos()+32;
		heroY = hero.getYPos()+32;
		percentHealth = (155*hero.getHealth())/hero.getMaxHealth();			//155 is the px width of the health bar
		percentMana = (141*hero.getMana())/hero.getMaxMana();
	}
	
	public void paint(Graphics graphics) {
		requestFocus();
		super.paint(graphics);
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, getFrameWidth(), getFrameHeight());		//makes whatever is outside the image black
		if(getFullScreen()==true) {
			graphics.drawImage(getBlankImage(), (int) ((-1)*(heroX)/(scaling)+getImageWidth()/2+halfDiffDisImgWidth), (int) ((-1)*(heroY)/(scaling)+getImageHeight()/2+halfDiffDisImgHeight), scaledBackgroundImageWidth, scaledBackgroundImageHeight, this);
			graphics.fillRect(0, 0, getMonitorWidth(), halfDiffDisImgHeight);																			//top
			graphics.fillRect(0, bottomBarYLocation, getMonitorWidth(), halfDiffDisImgHeight);														//bottom
			graphics.fillRect(0, 0, halfDiffDisImgWidth, getMonitorHeight());																			//left
			graphics.fillRect(rightBarXLocation, 0, halfDiffDisImgWidth, getMonitorHeight());															//right
		}
		else
			graphics.drawImage(getBlankImage(), (int) ((-1)*(heroX)/(scaling)+getImageWidth()/2), (int) ((-1)*(heroY)/(scaling)+getImageHeight()/2), scaledBackgroundImageWidth, scaledBackgroundImageHeight, this);
		
		//draw UI
		int scaledHeroResourcesImageWidth = (int) (heroResources.getWidth(this) / (scaling));		
		int scaledHeroResourcesImageHeight = (int) (198 / (scaling));	
		graphics.drawImage(heroResources, 0, 0, scaledHeroResourcesImageWidth, scaledHeroResourcesImageHeight, 0, 0, heroResources.getWidth(this), 198, this);
		graphics.drawImage(heroResources, (int)(122/scaling), (int)(42/scaling), (int)((122 + percentHealth)/scaling), (int)(75/scaling), 0, 200, percentHealth, 232, this);
		graphics.drawImage(heroResources, (int)(102/scaling), (int)(86/scaling), (int)((102 + percentMana)/scaling), (int)(112/scaling), 0, 233, percentMana, 259, this);
		
		//Wave Counter
		graphics.setFont(new Font("TimesRoman", Font.PLAIN, (int)(25/scaling))); 
		graphics.setColor(Color.WHITE);
		if(Game.getCurrentWave() < 10)
			graphics.drawString(Integer.toString(Game.getCurrentWave()), (int)(100/scaling), (int)(160/scaling));
		else
			graphics.drawString(Integer.toString(Game.getCurrentWave()), (int)(94/scaling), (int)(160/scaling));
	}
}
