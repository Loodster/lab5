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
	public enum State{
		CLOSED(true),OPEN(false),FULL(false);
		private boolean closed;
		State(boolean closed) {
			this.closed = closed;
		}
	}

	//Current state of the store
	private State state = State.OPEN;
	
	//Event info
	private EventType currentEvent;
	private int currentCustomer;
	
	//Customers
	private int inStore = 0;//number of customers in store
	private int customerCount  = 0;//Total number of visits
	private int max = 5;//Maximum number of customer in store
	private int missedCustomer = 0;// Number of missed customers
	
	//Registers and queue
	private Registers registers;
	private final double closingTime;
	private TimeCalculation timeCalc;
	
	public StoreState() {
		this(10,2);
	}
	
	public StoreState(double closingTime, int cashRegisters) {
		this.closingTime = closingTime;
		registers = new Registers(cashRegisters);
		timeCalc = new TimeCalculation();
		
	}
	/**
	 * A customer is trying to enter enter the store and 
	 * returns the state the store is in when 
	 * 
	 * @param customerID id of the customer 
	 * @param newTime the time the current event is taking place
	 * @return the state of the store when the customer tried to enter the store
	 */
	public State arrival(int customerID, double newTime, EventType event){
		updateListener(event, customerID, newTime);
		switch(state){
			case OPEN:
				customerCount++;
				inStore++;
				if(inStore == max){
					state = State.FULL;
					return State.OPEN;
				}
				return state;
			case FULL:
				missedCustomer++;
				customerCount++;
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
	public boolean pickup(int customerID, double newTime, EventType event){
			updateListener(event, customerID, newTime);
			return registers.useOne(customerID);
		}
	/**
	 * A customer tries to pay for wares
	 * @param customerID id of the customer
	 * @param newTime the time the event is taking place
	 * @return boolean saying if the person succeeded in paying.
	 */
	public int checkout(int customerID, double newTime, EventType event){
		updateListener(event, customerID, newTime);
		int id = registers.doneUsing(customerID);
		inStore--;
		if(state == State.FULL) {
			state = State.OPEN;
		}
		return id;
	}

	private void updateListener(EventType event, int customerID, double newTime) {
		timeCalc.calculateFreeTime(getFreeRegisters(), newTime);
		timeCalc.calculateQueueTime(getQueueSize(), newTime);
		timeCalc.setCurrentTime(newTime);
		currentEvent = event;
		currentCustomer = customerID;
		setChanged();
		notifyObservers();
	}
	
	private void updateListener(EventType event, double newTime) {
		timeCalc.calculateFreeTime(getFreeRegisters(), newTime);
		timeCalc.calculateQueueTime(getQueueSize(), newTime);
		timeCalc.setCurrentTime(newTime);
		currentEvent = event;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Closes the store
	 */
	public void closeStore(EventType event,double newTime){
		updateListener(event, newTime);
		state = State.CLOSED;
	}
	
	// Getters below
	
	/**
	 * returns the number of customers in store currently
	 * @return inStore number of customers in the store
	 */
	public int getInStore(){
		return inStore;
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
		return state == State.FULL;
	}

	public EventType getEvent() {
		return currentEvent;
	}
	
	public double getCurrentTime() {
		return timeCalc.getCurrentTime();
	}
	
	public int getCurrentCustomer() {
		return currentCustomer;
	}
	
	public int getMissedCustomer() {
		return missedCustomer;
	}
	
	public int getFreeRegisters() {
		return registers.freeRegisters();
	}
	
	public int getQueueSize() {
		return registers.queueSize();
	}
	
	public int getCashRegisters() {
		return registers.registers();
	}
	
	public int getMax() {
		return max;
	}

	public int getCustomerCount() {
		return customerCount;
	}
	
	public double getFreeTime() {
		return timeCalc.getFreeRegisterTime();
	}

	public double getQueueTime() {
		return timeCalc.getQueueTime();
	}

	public double getClosingTime() {
		return closingTime;
	}
	
	public int[] getQueue(){
		return registers.queue();
	}
	
	public int getStodInQueue() {
		return registers.stodInQueue();
	}
}
