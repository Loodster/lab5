package Walmart;

import java.util.Observable;

import Simulator.View;

public class StoreView extends View{
	
	private String print = "";
	private StoreSimulator sim;
	
	public StoreView(StoreSimulator sim, WalmartState state) {
		state.addObserver(this);
		this.sim = sim;
		
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		WalmartState state = (WalmartState)arg0;
		
		switch(state.getEvent()) {
		case START:
			    print = print + "PARAMETRAR" + 
				"\n" + "==========" + 
				"\n" + "Antal kassor, " + "N: " + state.getCashRegisters() +
				"\n" + "Max som ryms, M: " + sim.getMax() +
				"\n" + "Ankomsthastighet, lambda: " + sim.getLambda() + 
				"\n" + "Plocktider, [P_min..Pmax]: " +"[" + sim.getPickMin() + ".." + sim.getPickMax() + "]" +
				"\n" + "Betaltider, [K_min..Kmax]: "   +"[" + sim.getPayMin() + ".." + sim.getPayMax() + "]" +  			"\n" + "Frö, f: " + state.getSeed() +//
				"\n" +
				"\n" + "FÖRLOPP" +
				"\n" + "==========" +
				"\n" + "\t" + "Tid " + " Kund " + " ? " + " led " + "\t" + " ledT "+ "\t" +
				" I "+ " \t" + " $" + "\t" + ":-(" + "\t" + "köat" + "\t" + "köT" +
				"\t" + "köar" + "\t" + "[Kassakö..]";
		
				print = print + "\t" + state.getCurrentTime() + " Start";
			
		case CLOSE:
			print = print + "\t" + state.getCurrentTime() + "Stänger" +
			"\t" + "---" +
			"\t" + open + 
			"\t" + state.getFreeRegisters() +
			"\t" + state.getFreeTime() + 
			"\t" + state.getInStore() +
			"\t" + (state.getCustomerCount() - state.getMissedCustomer()) +
			"\t" + state.getMissedCustomer() +
			"\t" + //state.getAllQueued
			"\t" + state.getQueueTime() +
			"\t" + state.getQueueSize() +
			"\t"; //+ state.getQueue		
			break;
			
		case STOP:
			print = print + state.getCurrentTime()+
			"Stop" + 
			"\n" + "\n" +
			"RESULTAT" + "\n" +
			"============" + "\n" +
			"\n" + "1)" + " Av "
			+ state.getCustomerCount() + " kunder handlade "
			+ (state.getCustomerCount() - state.getMissedCustomer()) + " medan "
			+ state.getMissedCustomer() + " missades." + "\n" + "\n"
			+ "2)" + " Total tid " 
			+ state.getCashRegisters() + " kassor varit lediga: "
			+ state.getFreeTime() + " te."
			+ "Genomsnitlig ledig kassatid: "
			+ (state.getFreeTime()/state.getCashRegisters()) + " te."
			+ "3)" + "Total tid "+ /*state.getAllQueued+ */ " kunder tvingats köa: "
			+ state.getQueueTime() + " te." + "\n" 
			+ "Genomsnitlig kötid: " + (state.getQueueTime()/state.getAllQueued())
			+ " te.";
			break;
		default:
			char open;
			if(state.isClosed()) {
				open = 's';
			}else {
				open = 'ö';
			}
			print = print + "\n" +
			"\t" + state.getCurrentTime() + " " + state.getEvent() + 
			"\t" + state.getCurrentCustomer() + 
			"\t" + open + 
			"\t" + state.getFreeRegisters() +
			"\t" + state.getFreeTime() + 
			"\t" + state.getInStore() +
			"\t" + (state.getCustomerCount() - state.getMissedCustomer()) +
			"\t" + state.getMissedCustomer() +
			"\t" + //state.getAllQueued
			"\t" + state.getQueueTime() +
			"\t" + state.getQueueSize() +
			"\t"; //+ state.getQueue
			
		
		}
		System.out.println(print);
	}
}
