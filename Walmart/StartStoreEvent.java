package store;

import simulator.EventQueue;
import simulator.StartEvent;

public class StartStoreEvent extends StartEvent {
	private final EventType type = EventType.START;
	private StoreState state;
	public StartStoreEvent(StoreState state) {
		this.state = state;
	}
	@Override
	public void runEvent(EventQueue queue) {
		// TODO Auto-generated method stub
		queue.addEvent(new EnterStoreEvent(state,state.getRandomArrivalTime()));
		
	}

}
