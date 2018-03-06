package Walmart;

import java.util.ArrayList;
import exceptions.AlreadyInQueueException;
import exceptions.AlreadyUsingRegisterException;
import exceptions.NotAtRegisterException;
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
	
	public boolean useOne(int customerID) {
		if(!isUsing(customerID)) {
			if(inUse() == total) {
				if(queue.contains(customerID)) {
					throw new AlreadyInQueueException();
				}
				queue.add(customerID);
				return false;
			}
			else {
				useRegister(customerID);
				return true;
			}
		}
		throw new AlreadyUsingRegisterException();

	}
	
	private void useRegister(int customerID) {
		for(int i = 0; i<total; i++) {
			if(inUse[i] == notInUse) {
				inUse[i] = customerID;
				return;
			}
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
	
	public int doneUsing(int customerID) {
		if(isUsing(customerID)) {
			if(!queue.isEmpty()) {
				int idInQueue = queue.get(0);
				inUse[getIndex(customerID)] = idInQueue;
				queue.remove(0);
				return idInQueue;
			}else {
				inUse[getIndex(customerID)] = notInUse;
				return -1;
			}
		}else {
			throw new NotAtRegisterException();
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
	
	public int queueSize() {
		return queue.size();
	}
	
	
}
