package store;

import simulator.Event;
import simulator.EventQueue;

public class CheckoutEvent extends Event {
	private final EventType type = EventType.CHECKOUT;
	private double startTime;
	private StoreState state;
	private int customerID;
	private Simulator sim;
	
	public CheckoutEvent( Simulator sim,StoreState state, double startTime, int customerID) {
		this.state = state;
		this.startTime = startTime;
		this.customerID = customerID;
		this.sim = sim;
	}
	
	@Override
	public void runEvent(EventQueue queue) {
		int idInQueue = (state.checkout(customerID, startTime,type));
		if(idInQueue != -1){
			queue.addEvent(new CheckoutEvent(sim,state, startTime+sim.getRandomPayTime(),idInQueue));
		}

	}

	@Override
	public double getStartTime() {
		// TODO Auto-generated method stub
		return startTime;
	}

}
