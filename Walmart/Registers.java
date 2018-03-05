package Walmart;

import java.util.ArrayList;

import exceptions.AlreadyUsingRegisterException;
import exceptions.NotInQueueException;

public class Registers{
	private final int total; // number of registers
	private int[] inUse; //customers using a register
	private final int notInUse = -1; //value representing free register
	private ArrayList<Integer> queue = new ArrayList<Integer>();// Queue to registers
	
	public Registers(int total) {
		this.total = total;
		inUse =  new int[total];
		for(int i = 0; i<total;i++) {
			inUse[i] = notInUse;
		}
	}
	
	public void useOne(int customerID) {
		if(!isUsing(customerID)) {
			if(inUse() == total) {
				if(queue.contains(customerID)) {
					throw new AlreadyInQueueException();
				}
				queue.add(customerID);
			}
			else {
				for(int i = 0; i<total; i++) {
					if(inUse[i] == notInUse) {
						inUse[i] = customerID;
						break;
					}
				}
			}
		}else{
			throw new AlreadyUsingRegisterException();
		}
	}
	
	
	private int inUse() {
		int using = 0;
		for(int i: inUse) {
			if(i != notInUse) {
				using++;
			}
		}
		return using;
	}
	
	public boolean doneUsing(int customerID) {
		if(isUsing(customerID)) {
			if(!queue.isEmpty()) {
				inUse[getIndex(customerID)] = queue.get(0);
				queue.remove(0);
			}else {
				inUse[getIndex(customerID)] = notInUse;
			}
			return true;
		}else {
			if(!queue.contains(customerID)) {
				throw new NotInQueueException();
			}
			return false;
		}
	}
	
	private int getIndex(int customerID)  {
		for(int i = 0; i<total; i++) {
			if (inUse[i]==customerID) {
				return i;
			}
		}
		throw new NotInQueueException();
	}
	
	public int freeRegisters() {
		int number = 0;
		for(int reg: inUse) {
			if(reg == notInUse) {
				number++;			
			}
		}
		return number;
	}
	
	
	private boolean isUsing(int customerID) {
		for(int i: inUse) {
			if(i == customerID) {
				return true;
			}
		}
		return false;
	}
	
	public int getQueueSize() {
		return queue.size();
	}
	
	
}
