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

    Queue readyQueue;
    Queue blockedQueue;
    Cpu cpu;
    long timeQuantum;

    Dispatcher(Cpu cpu, long timeQuantum) {
        readyQueue = new Queue(10);
        blockedQueue = new Queue(10);
        this.timeQuantum = timeQuantum;
        this.cpu = cpu;
        cpu.addObserver(this);
    }

    public Queue getReadyQueue() {
        return readyQueue;
    }

    public Queue getBlockedQueue() {
        return blockedQueue;
    }

    void addNewProcess(Process p) {
        readyQueue.enqueue(p);
    }

    public void interrupt(long time) {
        cpu.pauseExecution();
        if (cpu.getCurrent() != null) {
            Process p = cpu.getCurrent();
            if (!p.isComplete()) {
                p.setState("Blocked");
                blockedQueue.enqueue(p);
            }
        }
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                Process blocked = blockedQueue.dequeue();
                if (blocked != null) {
                    readyQueue.enqueue(blocked);
                }
            }
        };

        timer.schedule(task, 0, time);  
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
            if(next.getStartTime() == (long)(-1)){
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
        }
    }
}
