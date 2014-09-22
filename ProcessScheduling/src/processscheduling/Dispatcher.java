/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processscheduling;

import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

public class Dispatcher extends Observable implements Observer {

    private Queue readyQueue;
    private Queue blockedQueue;
    private Cpu cpu;
    private long timeQuantum;

    Dispatcher(Cpu cpu) {
        readyQueue = new Queue(10);
        blockedQueue = new Queue(10);
        this.timeQuantum = timeQuantum;
        this.cpu = cpu;
        cpu.addObserver(this);
    }

    public void setTimeQuantum(long t) {
        this.timeQuantum = t;
    }

    public Queue getReadyQueue() {
        return readyQueue;
    }

    public Queue getBlockedQueue() {
        return blockedQueue;
    }

    void addNewProcess(Process p) {
        getReadyQueue().enqueue(p);
    }

    public void interrupt(long time) {
        cpu.pauseExecution();
        if (cpu.getCurrent() != null) {
            Process p = cpu.getCurrent();
            if (!p.isComplete()) {
                p.setState("Blocked");
                blockedQueue.enqueue(p);
                p.performIo();
            }
        }

        dispatch();

    }

    public Process dispatch() {

        cpu.pauseExecution();
        if (cpu.getCurrent() != null) {
            if (!cpu.getCurrent().getState().equalsIgnoreCase("Blocked")) {
                Process p = cpu.getCurrent();
                if (!p.isComplete()) {
                    p.setState("Ready");
                    readyQueue.enqueue(p);
                }
            }
        }

        Process next = readyQueue.dequeue();

        if (next != null) {
            if (next.getStartTime() == (long) (-1)) {
                next.setStartTime(cpu.getCurrentTime());
            }
            cpu.setCurrent(next);
            next.setState("Running");
            cpu.execute();
            return next;
        } else {
            cpu.setCurrent(null);
            return null;
        }

    }

    @Override
    public void update(Observable o, Object arg) {

        if (arg == null) {

            dispatch();
        } else if (o.getClass().toString().contains("Process")) {
            Process p = blockedQueue.dequeue();
            readyQueue.enqueue(p);
            p.setState("Ready");
            if (readyQueue.peek().getPid() == p.getPid()) {
                dispatch();
            }
        }

    }

    /**
     * @param readyQueue the readyQueue to set
     */
    public void setReadyQueue(Queue readyQueue) {
        this.readyQueue = readyQueue;
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
}
