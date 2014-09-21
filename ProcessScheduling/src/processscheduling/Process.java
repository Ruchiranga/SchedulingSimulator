package processscheduling;

import java.util.ArrayList;

public class Process {

    private int pid;
    private String state;
    private long burstTime;
    private long waitingTime;
    private long turnAroundTime;
    private long executionTime; //service time= executionTime-burstTime
    private boolean isComplete;
    private long startTime;
    private long finishedTime;

    public Process(int pid, String state, long burstTime, long waitingTime, long turnAroundTime, long executionTime, long startTime, long finishedTime) {
        this.pid = pid;
        this.state = state;
        this.burstTime = burstTime;
        this.waitingTime = waitingTime;
        this.turnAroundTime = turnAroundTime;
        this.executionTime = executionTime;
        this.isComplete = false;
        this.startTime = startTime;
        this.finishedTime = finishedTime;
    }

    /**
     * @return the pid
     */
    public int getPid() {
        return pid;
    }

    /**
     * @param pid the pid to set
     */
    public void setPid(int pid) {
        this.pid = pid;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the burstTime
     */
    public long getBurstTime() {
        return burstTime;
    }

    /**
     * @param burstTime the burstTime to set
     */
    public void setBurstTime(long burstTime) {
        this.burstTime = burstTime;
    }

    /**
     * @return the waitingTime
     */
    public long getWaitingTime() {
        return waitingTime;
    }

    /**
     * @param waitingTime the waitingTime to set
     */
    public void setWaitingTime(long waitingTime) {
        this.waitingTime = waitingTime;
    }

    /**
     * @return the turnAroundTime
     */
    public long getTurnAroundTime() {
        return turnAroundTime;
    }

    /**
     * @param turnAroundTime the turnAroundTime to set
     */
    public void setTurnAroundTime(long turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }

    /**
     * @return the executionTime
     */
    public long getExecutionTime() {
        return executionTime;
    }

    /**
     * @param executionTime the executionTime to set
     */
    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    /**
     * @return the isComplete
     */
    public boolean isComplete() {
        return isComplete;
    }

    /**
     * @param isComplete the isComplete to set
     */
    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    /**
     * @return the startTime
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the finishedTime
     */
    public long getFinishedTime() {
        return finishedTime;
    }

    /**
     * @param finishedTime the finishedTime to set
     */
    public void setFinishedTime(long finishedTime) {
        this.finishedTime = finishedTime;
    }

}
