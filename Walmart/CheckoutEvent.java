package Walmart;

import exceptions.AlreadyInQueueException;
import simulator.Event;
import simulator.EventQueue;

public class CheckoutEvent extends Event {
	
	private double startTime;
	private StoreState storeState;
	private int customerID;
	
	public CheckoutEvent(StoreState storeState,double startTime, int customerID) {
		this.storeState = storeState;
		this.startTime = startTime;
		this.customerID = customerID;
	}
	
	@Override
	public void runEvent(EventQueue queue) {
		if(storeState.checkout(customerID, startTime)) {
			//This customer has is now done? maybe change what does what?
		}else {
			//change start time here.
			queue.addEvent(this);
		}

	}

	@Override
	public double getStartTime() {
		// TODO Auto-generated method stub
		return startTime;
	}

}
