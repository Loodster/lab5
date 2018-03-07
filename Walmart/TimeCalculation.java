package store;

public class TimeCalculation {
	
	private double currentTime;
	private double freeRegisterTime;
	private double queueTime;
	/**
	 * Calculate free register time
	 * @param registers
	 * @param newTime
	 */
	public void calculateFreeTime(int registers, double newTime) {
		freeRegisterTime += registers*(newTime-currentTime);
	}
	
	/**
	 * calculate time spent in queue
	 * @param queueSize
	 * @param newTime
	 */
	public void calculateQueueTime(int queueSize, double newTime) {
		queueTime += queueSize*(newTime-currentTime);
	}
	
	public void setCurrentTime(double newTime) {
		currentTime = newTime;
	}
	/**
	 * @return the currentTime
	 */
	public double getCurrentTime() {
		return currentTime;
	}
	/**
	 * @return the freeRegisterTime
	 */
	public double getFreeRegisterTime() {
		return freeRegisterTime;
	}
	/**
	 * @return the queueTime
	 */
	public double getQueueTime() {
		return queueTime;
	}
	

}
