package simulator;

import java.util.Observable;


public abstract class State extends Observable {
	private boolean stop = false;
	
	public void handbreak(){
		stop = true;
		setChanged();
		notifyObservers();
	}
	

		
	}
	
	

