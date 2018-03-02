package Walmart;

import java.util.ArrayList;
import java.util.Observable;

import simulator.Event;
import simulator.State;


public abstract class WalmartState extends State {
	
	/**
	 * enum representing the state the store is in and 
	 * if that state means the store is closed
	 * 
	 * @author Michael 
	 * 
	 */
	public static enum StoreState{
		CLOSED(true),OPEN(false),FULL(false);
		private boolean closed;
		StoreState(boolean closed) {
			this.closed = closed;
		}
		
	}
	
	private String currentEvent;
	private int inStore = 0;
	private int customerCount  = 0;
	private int max = 250;
	private double Kmin = 0.5;
	private int missedCustomer = 0;
	private int registersInUse = 0;
	private int currentCustomer;
	private int cashRegisters = 4;
	private ArrayList<Integer> registerQueue = new ArrayList<Integer>();
	private double currentTime = 0;
	private StoreState state = StoreState.OPEN;
	private double freeTime = 0;
	private double queueTime = 0;
	/**
	 * A customer is trying to enter enter the store and 
	 * returns the state the store is in when 
	 * 
	 * @param customerID id of the customer 
	 * @param newTime the time the current event is taking place
	 * @return the state of the store when the customer tried to enter the store
	 */
	public StoreState arrival(int customerID, double newTime){
			
			calculateTimeWasting(newTime);
			
			currentTime = newTime;
			currentEvent = "arrival";
			currentCustomer = customerID;
			
			setChanged();
			notifyObservers();
			
			switch(state){
				case OPEN:
					customerCount++;
					inStore++;
					if(inStore == max){
						state = StoreState.FULL;
						return StoreState.OPEN;
					
					}else{
						return state;
					}
				case FULL:
					missedCustomer++;
					customerCount++;
					return state;
					
				case CLOSED:
					return state;
				
			}throw new NullPointerException();
			
			
			
		}
	/**
	 * Method representing a customer picking up wares and the going to the
	 * register to paying.
	 * @param customerID id of the customer 
	 * @param newTime the time the current event is taking place
	 */
	public void pickup(int customerID, double newTime){
			
			calculateTimeWasting(newTime);
			
			currentTime = newTime;
			currentEvent = "Pickup";
			currentCustomer = customerID;
			
			setChanged();
			notifyObservers();
			
			if(cashRegisters == registersInUse){
				registerQueue.add(customerID);
			}else{
				registersInUse++;
			}
		}
	/**
	 * A customer tries to pay for wares
	 * @param customerID id of the customer
	 * @param newTime the time the event is taking place
	 * @return boolean saying if the person succeeded in paying.
	 */
	public boolean checkout(int customerID, double newTime){
		if(!registerQueue.contains(customerID)){
			
			calculateTimeWasting(newTime);
			
			currentTime = newTime;
			currentEvent = "Checkout";
			currentCustomer = customerID;
			
			setChanged();
			notifyObservers();
			
			if(registerQueue.size()> 0){
				registerQueue.remove(0);
			}else{
				registersInUse--;
			}
			
		}
		return false;
	}

	
	private void calculateTimeWasting(double newTime){
		double timeMod = newTime - currentTime;
		if(registerQueue.size() > 0){
			queueTime += timeMod * registerQueue.size();
			
		}else{
			freeTime += timeMod * (cashRegisters - registersInUse); 
		}
	}
	/**
	 * Returns if given customer is in the register queue
	 * @param customerID id of a given customer
	 * @return if the customer is in the queue
	 */
	public boolean isInQueue(int customerID) {
		return registerQueue.contains(customerID);
	}
	/**
	 * Closes the store
	 */
	public void closeStore(){
	}
	// Getters below
	
	/**
	 * returns the number of customers in store currently
	 * @return inStore number of customers in the store
	 */
	public int customersInStore(){
		return inStore;
	}
	/**
	 * Returns how many customers have visited the store in total
	 * @return customerCount the total number of store visits
	 */
	public int totalCustomers(){
		return customerCount;
	}
	/**
	 * returns if the store is closed 
	 * @return closed boolean representing if the store is closed or not
	 */
	public boolean isClosed(){
		return state.closed;
	}
	/**
	 * Returns if the store is full
	 * @return logical answer to the question is the store full
	 */
	public boolean isFull(){
		return state == StoreState.FULL;
	}
	
	
}
