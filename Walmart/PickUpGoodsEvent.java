package Walmart;

import Walmart.WalmartState;
import Simulator.Event;
import Simulator.EventQueue;
//import next event


public class PickUpGoodsEvent extends Event {
	
	private int customerID;
	private WalmartState state;
	private double startTime;
	
	
	public PickUpGoodsEvent(int customerID, WalmartState state, double startTime) {
		this.customerID = customerID;
		this.state = state;
		this.startTime = startTime;
		
	}


	@Override
	public void runEvent(EventQueue queue) {
		state.pickup(customerID, startTime);
		//skapa objekt och kalla på nästa event, tror man ska kunna gå in utan att handla(?)
		
	}


	@Override
	public double getStartTime() {
		// TODO Auto-generated method stub
		return startTime;
	}

}
