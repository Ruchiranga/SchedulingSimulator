
package processscheduling;

import java.util.ArrayList;

public class Process {
    private int pid;
    private String state;
    private long burstTime;
    private long waitingTime;
    private long turnAroundTime;
    private long executionTime; //service time= executionTime-burstTime

    public Process(int pid, String state, long burstTime, long waitingTime, long turnAroundTime, long executionTime) {
        this.pid = pid;
        this.state = state;
        this.burstTime = burstTime;
        this.waitingTime = waitingTime;
        this.turnAroundTime = turnAroundTime;
        this.executionTime = executionTime;           
        
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
    
}
