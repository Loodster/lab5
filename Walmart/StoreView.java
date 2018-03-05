package Walmart;

import java.util.Observable;

import Simulator.View;

public class StoreView extends View{
	
	public StoreView(StoreState state) {
		state.addObserver(this);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		StoreState state = (StoreState)arg0;
		String print = "";
		switch(state.getEvent()) {
		case START:
			print = print + "PARAMETRAR" + 
		    "\n" + "==========" + 
			"\n" + "Antal kassor, " + "N: " + state.getcashRegisters() +
			"\n" + "Max som ryms, M: " + state.getMax() +
			"\n" + "Ankomsthastighet, lambda: " + "1.0" + //ändrar till parameterar
			"\n" + "Plocktider, [P_min..Pmax]: " + "[0.5..1.0]" +//
			"\n" + "Betaltider, [K_min..Kmax]: " + "[2.0..3.0]" + //
			"\n" + "Frö, f: " + "1234" +//
			"\n" +
			"\n" + "FÖRLOPP" +
			"\n" + "==========" +
			"\n"+ "\t" + "Tid " + " Kund " + " ? " + " led " + "\t" + " ledT "+ "\t" +
			" I " +" \t" + " $" + "\t" + ":-(" + "\t" + "köat" + "\t" + "köT" +
			"\t" + "köar" + "\n" + "[Kassakö..]";
			
			
			print = print + state.getCurrentTime();
		case STOP:
		case CLOSE:
		default:
			// göra mest
		}
		System.out.println(print);
	}
}
