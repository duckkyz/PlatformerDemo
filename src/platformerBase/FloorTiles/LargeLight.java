package platformerBase.FloorTiles;

public class LargeLight extends Wall {
	
	private int frameCounter = 0;
	private boolean forwardAnimation = true;
	public LargeLight(int x, int y){
		super(x,y);
		
		setActionStep((int) (8 + Math.floor(Math.random() * 3)));		//column
		setActionSequence((int) (6 + Math.floor(Math.random() * 0)));	//row
		this.setTileHeight(64 * 1);
		this.setTileWidth(64 * 1);
		setTopHitBox(8);
		setBotHitBox(4);
		setLeftHitBox(0);
		setRightHitBox(8);
	}
	
	public void move(){
		if(frameCounter % 6 == 0){
			if(frameCounter != 0){
				if(forwardAnimation){
					setActionStep(getActionStep() + 1);
					if(getActionStep() > 10){
						setActionStep(10);
						frameCounter = 0;
						forwardAnimation = false;
					}
				}
				else{
					setActionStep(getActionStep() - 1);
					if(getActionStep() < 8){
						setActionStep(8);
						frameCounter = 0;
						forwardAnimation = true;
					}
				}
			}
		}
		frameCounter++;
		
	}
	
}
