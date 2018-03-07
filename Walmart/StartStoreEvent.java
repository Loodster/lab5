package store;

import simulator.EventQueue;
import simulator.StartEvent;

public class StartStoreEvent extends StartEvent {
	private final EventType type = EventType.START;
	private StoreState state;
	private Simulator sim;
	public StartStoreEvent(StoreState state, Simulator sim) {
		this.state = state;
		this.sim = sim;
	}
	@Override
	public void runEvent(EventQueue queue) {
		// TODO Auto-generated method stub
		queue.addEvent(new EnterStoreEvent(sim,state,sim.getRandomArrivalTime()));
		
	}

}
