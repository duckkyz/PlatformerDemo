package platformerBase;

import java.awt.BorderLayout;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import platformerBase.Listeners.CustomContainerListener;
import platformerBase.Screens.GameState;
import platformerBase.Screens.PlayScreen;
import platformerBase.Screens.TitleScreen;


public class ViewController {
	
	private JFrame frame;
	private static Game game;
	private JPanel rootContainer;
	
	private static Timer timer;
	private final int timerDelay = 1;
	
	
	public ViewController() {
		frame = new JFrame("Marwolaeth");
		game = new Game();

		gameTimer();

		rootContainer = new JPanel();
		rootContainer.setLayout(new BorderLayout());
		frame.getContentPane().add(rootContainer, BorderLayout.CENTER);
		frame.setResizable(false);										//Prevents user from manually resizing frame.

		
		rootContainer.addContainerListener(new CustomContainerListener(rootContainer, timer));
		GameState.setFrame(frame);
		GameState.setRootContainer(rootContainer);
		TitleScreen titleScreen = new TitleScreen();
		
		titleScreen.setupResolution(titleScreen.getResolutionSizes(), titleScreen.getNumResolutionSizes());
		rootContainer.add(titleScreen);
		rootContainer.doLayout();
		((TitleScreen) rootContainer.getComponent(0)).prepaint(Game.getHero(), Game.getDrawables());
		rootContainer.repaint();
		
		TitleScreen titleScreen_test = new TitleScreen();
		rootContainer.remove(rootContainer.getComponent(0));
		rootContainer.add(titleScreen_test);
		rootContainer.doLayout();
		rootContainer.repaint();

		//Next 3 lines: Turn cursor transparent
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
		frame.getContentPane().setCursor(blankCursor);
		
		frame.setVisible(true);				
	}
	
	public static void setGame(Game game){
		ViewController.game = game;
	}
	
	public void gameTimer() {
		timer = new Timer(timerDelay, new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(rootContainer.getComponent(0) instanceof PlayScreen){
					((PlayScreen) rootContainer.getComponent(0)).prepaint(game.getHero(), game.getDrawables());
					if(game.doGameLogic(((PlayScreen)rootContainer.getComponent(0)).getKeySet()) == true){			
						rootContainer.repaint();
					}
					else{
						TitleScreen titleScreen = new TitleScreen();
						rootContainer.remove(rootContainer.getComponent(0));
						rootContainer.add(titleScreen);
						rootContainer.doLayout();
						rootContainer.repaint();
					}
				}
				else if(rootContainer.getComponent(0) instanceof TitleScreen){
					((TitleScreen) rootContainer.getComponent(0)).prepaint(game.getHero(), game.getDrawables());
					if(game.doGameLogic(((TitleScreen)rootContainer.getComponent(0)).getKeySet()) == true){			
						rootContainer.repaint();
					}	
				}
			}
			
		});
		timer.stop();
	}
	
	public static Timer getTimer() {
		return timer;
	}

}
