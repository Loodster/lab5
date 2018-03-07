package store;

import simulator.Event;
import simulator.EventQueue;

public class PickUpGoodsEvent extends Event {

	private final EventType event = EventType.PICKUP;
	private int customerID;
	private StoreState state;
	private double startTime;
	private Simulator sim;
	
	public PickUpGoodsEvent(Simulator sim, StoreState state, double startTime,int customerID) {
		this.customerID = customerID;
		this.state = state;
		this.startTime = startTime;
		this.sim = sim;
		
	}

	@Override
	public void runEvent(EventQueue queue) {
		if(state.pickup(customerID, startTime,event)) {
			queue.addEvent(new CheckoutEvent(sim,state,startTime+sim.getRandomPayTime(),customerID));
		}
		//skapa objekt och kalla på nästa event, tror man ska kunna gå in utan att handla(?)
		
	}


	@Override
	public double getStartTime() {
		// TODO Auto-generated method stub
		return startTime;
	}

}
