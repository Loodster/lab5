package Walmart;


import simulator.Event;
import simulator.EventQueue;

public class CheckoutEvent extends Event {
	private final EventType type = EventType.CHECKOUT;
	private double startTime;
	private StoreState state;
	private int customerID;
	
	public CheckoutEvent(StoreState state,double startTime, int customerID) {
		this.state = state;
		this.startTime = startTime;
		this.customerID = customerID;
	}
	
	@Override
	public void runEvent(EventQueue queue) {
		int idInQueue = (state.checkout(customerID, startTime,type));
		if(idInQueue != -1){
			queue.addEvent(new CheckoutEvent(state,startTime+state.getRandomPayTime(),idInQueue));
		}

	}

	@Override
	public double getStartTime() {
		// TODO Auto-generated method stub
		return startTime;
	}

}
