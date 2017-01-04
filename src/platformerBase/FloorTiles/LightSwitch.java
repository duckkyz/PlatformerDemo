package platformerBase.FloorTiles;

public class LightSwitch extends Wall {
	private int frameCounter = 0;
	private boolean forwardAnimation = true;
	public LightSwitch(int x, int y){
		super(x,y);
		
		setActionStep((int) (0 + Math.floor(Math.random() * 8)));		//column
		setActionSequence((int) (5 + Math.floor(Math.random() * 0)));	//row
		this.setTileHeight(64 * 1);
		this.setTileWidth(64 * 1);
		setTopHitBox(8);
		setBotHitBox(4);
		setLeftHitBox(0);
		setRightHitBox(8);
	}
	
	public void move(){
		if(frameCounter > 3){
			if(forwardAnimation){
				setActionStep(getActionStep() + 1);
				if(getActionStep() > 6){
					setActionStep(6);
					frameCounter = 0;
					forwardAnimation = false;
				}
			}
			else{
				setActionStep(getActionStep() - 1);
				if(getActionStep() < 0){
					setActionStep(0);
					frameCounter = 0;
					forwardAnimation = true;
				}
			}
		}
		else{
			frameCounter++;
		}
		
	}
}
