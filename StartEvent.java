package Simulator;

public abstract class StartEvent extends Event {
	private final double startTime = 0;
	protected StartEvent(){
		super();
	}
	public abstract void runEvent(EventQueue queue);
	public double getStartTime() {
		return startTime;
	}
}
