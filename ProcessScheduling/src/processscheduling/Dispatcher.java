/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processscheduling;

import java.util.Observable;
import java.util.Observer;

public class Dispatcher implements Observer {

    private Queue readyQueue;
    private Queue blockedQueue;
    private Cpu cpu;
    private long timeQuantum;

    Dispatcher(Cpu cpu, long timeQuantum) {
        readyQueue = new Queue(10);
        blockedQueue = new Queue(10);
        this.timeQuantum = timeQuantum;
        this.cpu = cpu;
    }

    void addNewProcess(Process p) {
        getReadyQueue().enqueue(p);
    }

    public void interrupt() {

    }

    public void dispatch() {

        getCpu().pauseExecution();
        if (getCpu().getCurrent() != null) {
            getReadyQueue().enqueue(getCpu().getCurrent());
        }
        Process next = getReadyQueue().dequeue();
        getCpu().setCurrent(next);
        getCpu().execute();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o.getClass().toString().contains("Timer")) {
            dispatch();
        } else {
            interrupt();
        }
    }

    /**
     * @return the readyQueue
     */
    public Queue getReadyQueue() {
        return readyQueue;
    }

    /**
     * @param readyQueue the readyQueue to set
     */
    public void setReadyQueue(Queue readyQueue) {
        this.readyQueue = readyQueue;
    }

    /**
     * @return the blockedQueue
     */
    public Queue getBlockedQueue() {
        return blockedQueue;
    }

    /**
     * @param blockedQueue the blockedQueue to set
     */
    public void setBlockedQueue(Queue blockedQueue) {
        this.blockedQueue = blockedQueue;
    }

    /**
     * @return the cpu
     */
    public Cpu getCpu() {
        return cpu;
    }

    /**
     * @param cpu the cpu to set
     */
    public void setCpu(Cpu cpu) {
        this.cpu = cpu;
    }

    /**
     * @return the timeQuantum
     */
    public long getTimeQuantum() {
        return timeQuantum;
    }

    /**
     * @param timeQuantum the timeQuantum to set
     */
    public void setTimeQuantum(long timeQuantum) {
        this.timeQuantum = timeQuantum;
    }
    
    
}
