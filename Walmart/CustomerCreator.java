package store;

public class CustomerCreator {
	private int nextCustomerID = 0;
	public int getNewCustomerId() {
		int newID = nextCustomerID;
		nextCustomerID++;
		return newID;
	}
}
