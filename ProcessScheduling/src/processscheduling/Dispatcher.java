/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processscheduling;

import java.util.Observable;
import java.util.Observer;

public class Dispatcher implements Observer {

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

    void addNewProcess(Process p) {
        readyQueue.enqueue(p);
    }

    public void interrupt() {

    }

    public Process dispatch() {

        cpu.pauseExecution();
        if (cpu.getCurrent() != null) {
            Process p = cpu.getCurrent();
            p.setState("Ready");
            readyQueue.enqueue(p);
        }
        Process next = readyQueue.dequeue();
        cpu.setCurrent(next);
        next.setState("Running");
        cpu.execute();
        return next;
    }

    @Override
    public void update(Observable o, Object arg) {
        //System.out.println("sdjfdskjfnddddddddddddddddddddddddddddddd");
        if (o.getClass().toString().contains("Timer")) {
            dispatch();
            //System.out.println("dispatched");
        } else if(o.getClass().toString().contains("Timer")){
            
        }else {
            interrupt();
        }
    }
}
