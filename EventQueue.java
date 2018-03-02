package Simulator;

import java.util.ArrayList;

import exceptions.AlreadyInQueueException;

public class EventQueue{private Node header = new Node();
	private int size = 0;

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
	public void addEvent(Event event) throws AlreadyInQueueException{
		Node node = new Node();
		node.e = event;
		addNode(node,header);
	}
	
	private void addNode(Node node, Node compare) throws AlreadyInQueueException{
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
	public Event nextEvent() {
		Event e = header.nextNode.e;
		header.nextNode = header.nextNode.nextNode;
		return e;
	}
	
}
