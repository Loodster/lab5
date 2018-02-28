package Simulator;

public abstract class StartEvent extends Event {
	private final long startTime = 0;
	protected StartEvent(){
		super();
	}
	public abstract void runEvent(EventQueue queue);
	public long getStartTime() {
		return startTime;
	}
}
