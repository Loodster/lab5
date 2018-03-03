package simulator;

public abstract class StopEvent extends Event{
	private final double stop = Double.MAX_VALUE;

	@Override
	public double getStartTime() {
		return stop;
	}

}
