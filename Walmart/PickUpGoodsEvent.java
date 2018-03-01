package Walmart;

import Walmart.WalmartState;
import Simulator.Event;
import Simulator.EventQueue;


public class PickUpGoodsEvent extends Event {
	
	private int customerID;
	private WalmartState state;
	private long startTime;
	
	
	public PickUpGoodsEvent(int customerID, WalmartState state, long startTime) {
		this.customerID = customerID;
		this.state = state;
		this.startTime = startTime;
		
	}


	@Override
	public void runEvent(EventQueue queue) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public long getStartTime() {
		// TODO Auto-generated method stub
		return startTime;
	}

}
