package Store;

import java.util.Observable;

import simulator.View;

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
		case STOP:
		case CLOSE:
		default:
			// göra mest
		}
	}
}
