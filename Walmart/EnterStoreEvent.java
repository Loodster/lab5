package Walmart;

import java.util.Random;

import exceptions.AlreadyInQueueException;
import Simulator.Event;
import Simulator.EventQueue;
import Walmart.PickUpGoodsEvent;
import Walmart.WalmartState;

public class EnterStoreEvent extends Event {
	
	private double startTime;
	private WalmartState state;
	private int customerID = 0; 
	private static Random timeFrame = new Random(); 
	
	
	public EnterStoreEvent(WalmartState state, long startTime) {
		this.startTime = startTime;
		this.state = state;
	}

	@Override
	public void runEvent(EventQueue queue){
		if (state.isClosed() == false) {
			if (state.isFull() == false) {    //denna kommer döpas om i state
				customerID++; 
				PickUpGoodsEvent nextEvent = new PickUpGoodsEvent(customerID, state, startTime + timeFrame.nextLong()); //använd håkans random annars blir han ledsen
				queue.addEvent(nextEvent); 
				
			}
			EnterStoreEvent thisEvent = new EnterStoreEvent(state, startTime + timeFrame.nextLong());
			queue.addEvent(thisEvent);			//skapar en ny ankomsthändelse som läggs till i listan
		}
		
	}

	@Override
	public double getStartTime() {
		return startTime;
		
	}

}
