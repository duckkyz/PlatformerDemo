package platformerBase.Listeners;

import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import platformerBase.Game;
import platformerBase.ViewController;
import platformerBase.Screens.*;

public class CustomContainerListener implements ContainerListener {
    
	private JPanel rootContainer;
	private Timer timer;
	
	public CustomContainerListener(JPanel rootContainer, Timer timer) {
		this.rootContainer = rootContainer;
		this.timer = timer;
	}
	
	
	public void componentAdded(ContainerEvent e) {
		try {
				if(rootContainer.getComponent(0) instanceof PlayScreen) {
					ViewController.setGame(new Game());
					timer.start();
				}
				else if(rootContainer.getComponent(0) instanceof TitleScreen) {
					ViewController.setGame(new Game());
					timer.start();
				}
				else{
					timer.stop();
				}
		} 
  	  	catch (ArrayIndexOutOfBoundsException ev) {
  	  	} 
	}

    public void componentRemoved(ContainerEvent e) {
       
    }
}
