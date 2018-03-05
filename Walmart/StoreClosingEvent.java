package Walmart;

import Simulator.Event;
import Simulator.EventQueue;

public class StoreClosingEvent extends Event{
	
	private int customerID;
	private WalmartState state;
	private double startTime;
	
	public StoreClosingEvent(int customerID, WalmartState state, double startTime) {
		this.customerID = customerID;
		this.state = state;
		this.startTime = startTime;
	}


	@Override
	public void runEvent(EventQueue queue) {
		state.closeStore();
		
	}

	@Override
	public double getStartTime() {
		// TODO Auto-generated method stub
		return startTime;
	}

}	
