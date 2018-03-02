package Walmart;

import java.util.ArrayList;
import java.util.Observable;

import simulator.Event;
import simulator.State;


public abstract class WalmartState extends State {
	
	public static enum StoreState{
		CLOSED,OPEN,FULL;
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
	private boolean closed = false;
	private StoreState state = StoreState.OPEN;
	private double freeTime = 0;
	private double queueTime = 0;
	
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
	
	public void closedStore(){
		
	}
	
	
	public int customersInStore(){
		return inStore;
	}
	public int totalCustomers(){
		return customerCount;
	}
	
	public boolean isClosed(){
		return closed;
	}
	public boolean isFull(){
		return state == StoreState.FULL;
	}
	
	

	
	
	
	
	

	
	private void calculateTimeWasting(double newTime){
		double timeMod = newTime - currentTime;
		if(registerQueue.size() > 0){
			queueTime += timeMod * registerQueue.size();
			
		}else{
			freeTime += timeMod * (cashRegisters - registersInUse); 
		}
	}
	
	
	
	
}
