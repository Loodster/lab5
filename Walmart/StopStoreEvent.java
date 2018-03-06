package store;

import simulator.EventQueue;
import simulator.StopEvent;

public class StopStoreEvent extends StopEvent{

	private final EventType type = EventType.STOP;
	
	public StopStoreEvent(StoreState state) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void runEvent(EventQueue queue) {
		// TODO Auto-generated method stub
	}

}
