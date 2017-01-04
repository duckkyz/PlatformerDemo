package platformerBase.Screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import platformerBase.Game;
import platformerBase.FloorTiles.TikiGolem;
import platformerBase.ImplementedEntities.*;

public class HeroSelectScreen extends GameState{

	private int heroXSpawn = (int)(Math.floor((Math.random() * 3584)) + 64);
	private int heroYSpawn = (int)(Math.floor((Math.random() * 1094)) + 64);
	
	public HeroSelectScreen() {
		try {
			setBackgroundImage(ImageIO.read(new File("Background_Images/HeroSelect.png")));
		} catch(IOException ex) {}
		
		setBlankImage(new BufferedImage(getBackgroundImage().getWidth(this), getBackgroundImage().getHeight(this), BufferedImage.TYPE_INT_ARGB));		
		
		Orc test = new Orc(180, heroXSpawn, heroYSpawn);
		while((Game.checkCanSpawn(test) == false)){
			heroXSpawn = (int)(Math.floor((Math.random() * 3584)) + 64);
			heroYSpawn = (int)(Math.floor((Math.random() * 1094)) + 64);
			test = new Orc(180, heroXSpawn, heroYSpawn);
		}
	}

	public void keyPressed(KeyEvent keyEvent) {
		if (keyEvent.getKeyCode()==KeyEvent.VK_S) {
			Game.setHero(new Wizard(180, heroXSpawn, heroYSpawn));
		}
		else if (keyEvent.getKeyCode()==KeyEvent.VK_A) {
			Game.setHero(new Archer(180, heroXSpawn, heroYSpawn));
		}
		else if (keyEvent.getKeyCode()==KeyEvent.VK_D) {
			Game.setHero(new Druid(180, heroXSpawn, heroYSpawn));
		}
		else if (keyEvent.getKeyCode()==KeyEvent.VK_F) {
			Game.setHero(new Knight(180, heroXSpawn, heroYSpawn));
		}
		else if (keyEvent.getKeyCode()==KeyEvent.VK_Z) {
			Game.setHero(new Thief(180, heroXSpawn, heroYSpawn));
		}
		else if (keyEvent.getKeyCode()==KeyEvent.VK_X) {
			Game.setHero(new Warlock(180, heroXSpawn, heroYSpawn));
		}
		else if (keyEvent.getKeyCode()==KeyEvent.VK_C) {
			Game.setHero(new Bard(180, heroXSpawn, heroYSpawn));
		}
		else if (keyEvent.getKeyCode()==KeyEvent.VK_V) {
			Game.setHero(new DungeonMaster(180, heroXSpawn, heroYSpawn));
		}
		else if(keyEvent.getKeyCode()==KeyEvent.VK_ESCAPE) {
				TitleScreen titleScreen = new TitleScreen();
				getRootContainer().remove(this);
				getRootContainer().add(titleScreen);
				getRootContainer().doLayout();
				getRootContainer().repaint();
				return;
		}
		else{
			return;
		}
		
		PlayScreen playScreen = new PlayScreen();
		getRootContainer().remove(this);
		getRootContainer().add(playScreen);
		getRootContainer().doLayout();
		getRootContainer().repaint();
		
	}
	public void keyReleased(KeyEvent keyEvent) {
		getKeySet().remove(keyEvent.getKeyCode());
		
	}
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void paint(Graphics graphics) {
		requestFocus();
		super.paint(graphics);
		Graphics imageGraphics = getBlankImage().getGraphics();		//use imageGraphics to draw on the image
															
		//paint() should only be edited between these comments
		imageGraphics.drawImage(this.getBackgroundImage(), 0, 0, this);
		//paint() should only be edited between these comments
		
		if(getFullScreen()==true) {
			graphics.setColor(Color.BLACK);
			graphics.fillRect(0, 0, getMonitorWidth(), getMonitorHeight());
			graphics.drawImage(getBlankImage(), (getMonitorWidth()-getImageWidth())/2, (getMonitorHeight()-getImageHeight())/2, getImageWidth(), getImageHeight(), this);	//resize this to fit current resolution
		}
		else
			graphics.drawImage(getBlankImage(), 0, 0, getFrameWidth(), getFrameHeight(), this);	//resize this to fit current resolution
		imageGraphics.dispose();
		
		
	}

}
