package store;

import random.ExponentialRandomStream;
import random.UniformRandomStream;
import simulator.Event;
import simulator.EventQueue;

public class Simulator {
	// Simulation varibles
	private double lambda = 1.0;
	private final double pickMin = 0.5;
	private final double pickMax = 1.0;
	private final double payMin = 2.0;
	private final double payMax = 3.0;
	private final long seed = 1234;
	
	private ExponentialRandomStream arrivalGen = new ExponentialRandomStream(lambda,seed); 
	private UniformRandomStream pickGen = new UniformRandomStream(pickMin,pickMax,seed);
	private UniformRandomStream payGen = new UniformRandomStream(payMin,payMax,seed);
	
	public Simulator() {
		StoreState state = new StoreState();
		EventQueue test = new EventQueue(new StartStoreEvent(state, this), new StopStoreEvent(state));
		test.addEvent(new StoreClosingEvent(state,state.getClosingTime()));
		for(Event e: test) {
			e.runEvent(test);
		}
	}
	
	public double getLambda() {
		return lambda;
	}

	public double getPickMin() {
		return pickMin;
	}

	public double getPickMax() {
		return pickMax;
	}

	public double getPayMin() {
		return payMin;
	}

	public double getPayMax() {
		return payMax;
	}

	public long getSeed() {
		return seed;
	}

	public double getRandomArrivalTime() {
		return arrivalGen.next();
	}
	
	public double getRandomPickTime() {
		return pickGen.next();
	}
	
	public double getRandomPayTime() {
		return payGen.next();
	}
}
