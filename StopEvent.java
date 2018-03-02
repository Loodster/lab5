package simulator;

public abstract class StopEvent extends Event{
	private final long stop = Long.MAX_VALUE;

	@Override
	public long getStartTime() {
		return stop;
	}

}
