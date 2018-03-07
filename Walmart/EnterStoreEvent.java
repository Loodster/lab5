package store;

import exceptions.AlreadyInQueueException;
import simulator.Event;
import simulator.EventQueue;

public class EnterStoreEvent extends Event {
	private final EventType type = EventType.ARRIVAL;
	
	private double startTime;
	private StoreState state;
	private static int nextID = 0; 
	private int cusomterID;
	private Simulator sim;
	
	
	public EnterStoreEvent(Simulator sim,StoreState state, double startTime) {
		this.startTime = startTime;
		this.state = state;
		this.sim = sim;
		cusomterID = nextID;
		nextID++;
	}

	@Override
	public void runEvent(EventQueue queue) {
		switch(state.arrival(cusomterID, startTime,type)) {
		case OPEN:
			PickUpGoodsEvent nextEvent = new PickUpGoodsEvent(sim,state, startTime+sim.getRandomPickTime(),cusomterID);
			queue.addEvent(nextEvent); //Ingen break ty ska göra samma sak som om den vore full.
		case FULL:
			EnterStoreEvent nextCustomer = new EnterStoreEvent(sim,state, startTime+sim.getRandomArrivalTime());
			queue.addEvent(nextCustomer);
			break;
		case CLOSED:
			break;
		}
	}

	@Override
	public double getStartTime() {
		return startTime;
	}
}

