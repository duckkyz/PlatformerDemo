package platformerBase.Interfaces;

public interface willAttack {

	public abstract void ability1Setup();
	
	public abstract void ability2Setup();
	
	public abstract void ability3Setup();
	
	public abstract void ability4Setup();
	
	public abstract void ability1Execute(int direction);

	public abstract void ability2Execute(int direction);

	public abstract void ability3Execute(int direction);
	
	public abstract void ability4Execute(int direction);
}
