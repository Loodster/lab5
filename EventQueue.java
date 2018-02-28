package Simulator;

import java.util.ArrayList;

import exceptions.AlreadyInQueueException;

public class EventQueue{
	private ArrayList<Event> queue;
	public EventQueue(StartEvent startEvent) {
		queue = new ArrayList<Event>();
		queue.add(startEvent);
	}
	/**
	 * 
	 * @param event
	 */
	public void addEvent(Event event) throws AlreadyInQueueException {
		if(queue.contains(event)) {
			throw new AlreadyInQueueException();
		}
		queue.add(event);
	}
	public Event getNextEvent() {
		Event send = queue.get(0);
		long start = queue.get(0).getStartTime();
		for(Event e : queue) {
			if(e.getStartTime() < start) {
				start = e.getStartTime();
				send = e;
			}
		}
		return send;
				
	}
	
}
