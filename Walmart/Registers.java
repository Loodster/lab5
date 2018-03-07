package store;

import java.util.ArrayList;
import exceptions.AlreadyInQueueException;
import exceptions.AlreadyUsingRegisterException;
import exceptions.NotAtRegisterException;
import exceptions.NotInQueueException;

public class Registers{
	private final int total; // number of registers
	private int[] registers; //customers using a register
	private final int notInUse = -1; //value representing free register
	private ArrayList<Integer> queue = new ArrayList<Integer>();// Queue to registers
	private int stodInQueue = 0;
	
	public Registers(int total) {
		this.total = total;
		registers =  new int[total];
		for(int i = 0; i<total;i++) {
			registers[i] = notInUse;
		}
	}
	
	public boolean useOne(int customerID) {
		if(!isUsing(customerID)) {
			if(inUse() == total) {
				if(queue.contains(customerID)) {
					throw new AlreadyInQueueException();
				}
				queue.add(customerID);
				stodInQueue++;
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
			if(registers[i] == notInUse) {
				registers[i] = customerID;
				return;
			}
		}
	}
	
	private int inUse() {
		int using = 0;
		for(int i: registers) {
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
				registers[getIndex(customerID)] = idInQueue;
				queue.remove(0);
				return idInQueue;
			}else {
				registers[getIndex(customerID)] = notInUse;
				return -1;
			}
		}else {
			throw new NotAtRegisterException();
		}
	}
	
	private int getIndex(int customerID)  {
		for(int i = 0; i<total; i++) {
			if (registers[i]==customerID) {
				return i;
			}
		}
		throw new NotInQueueException();
	}
	
	public int freeRegisters() {
		int number = 0;
		for(int reg: registers) {
			if(reg == notInUse) {
				number++;			
			}
		}
		return number;
	}
	
	
	private boolean isUsing(int customerID) {
		for(int i: registers) {
			if(i == customerID) {
				return true;
			}
		}
		return false;
	}
	
	public int queueSize() {
		return queue.size();
	}
	
	public int registers() {
		return registers.length;
	}

	public int[] queue() {
		int[] ret = new int[queue.size()];
		for(int i = 0; i<queue.size();i++) {
			ret[i] = queue.get(0);
		}
		return ret;
	}
	
	public int stodInQueue() {
		return stodInQueue;
	}
	
}