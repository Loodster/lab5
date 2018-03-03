package Simulator;

public abstract class Event {
	protected Event() {
	}
	public abstract void runEvent(EventQueue queue);
	public abstract double getStartTime();
	
	
}
