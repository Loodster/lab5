package simulator;

import java.util.ArrayList;
import java.util.Iterator;

import exceptions.AlreadyInQueueException;

/**
 * A class representing a queue of events,
 * can only gain access to events when iterating over the queue
 * @author Michael MobilaDator
 *
 */
public class EventQueue implements Iterable<Event>{
	
	private Node header = new Node();
	private int size = 0;
	/**
	 * Consturctor of EventQueue
	 * @param startEvent the event that start the chain of events
	 * @param stopEvent the event that signals the end of the chain
	 */
	public EventQueue(StartEvent startEvent, StopEvent stopEvent) {
		Node start = new Node();
		start.e = startEvent;
		header.nextNode = start;
		Node stop = new Node();
		stop.e = stopEvent;
		start.nextNode = stop;
	}
	
	private class Node{
		private Event e;
		private Node nextNode = null;
	}
	/**
	 * Adds new event to queue
	 * @param event to add to queue
	 */
	public void addEvent(Event event) {
		Node node = new Node();
		node.e = event;
		addNode(node,header);
	}
	
	private void addNode(Node node, Node compare){
		if(compare.nextNode == null) {
			compare.nextNode = node;
		}
		else {
		if(compare.nextNode.e == node.e){
			throw new AlreadyInQueueException();
		}
		if(compare.nextNode.e.getStartTime()>node.e.getStartTime()){
			node.nextNode = compare.nextNode;
			compare.nextNode = node;
			
		}
		else{
			addNode(node,compare.nextNode);
		}
		}
	}
	/**
	 * Returns and removes the next event in queue will
	 * give NullPointerException if queue is empty;
	 * @return next event in queue
	 */
	private Event nextEvent() {
		Event e = header.nextNode.e;
		header.nextNode = header.nextNode.nextNode;
		size--;
		return e;
	}
	/**
	 * Returns size of the event queue
	 * @return size of queue
	 */
	public int getSize() {
		return size;
	}
	/**
	 * Returns a iterator of the event queue
	 * @return iterator of the event queue
	 */
	@Override
	public Iterator<Event> iterator() {
		return new Iterator<Event>() {
			/**
			 * Checks if there is a next event to call
			 * @return and checks if there a next event to call
			 */
			@Override
			public boolean hasNext() {
				return header.nextNode != null;
			}
			/**
			 * Returns next event in the queue;
			 * @return nextEvent() the next event in queue
			 */
			@Override
			public Event next() {
				return nextEvent();
			}
			
		};
	}
	
}
