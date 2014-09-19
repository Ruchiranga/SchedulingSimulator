/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processscheduling;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class Cpu extends Observable {

    private Process current;
    TimerTask task;
    long timeQunatum;
    Timer timer;
    int seconds;
    

    public Cpu(long timeQuantum) {
        this.timeQunatum = timeQuantum;
    }

    /**
     * @return the current
     */
    public Process getCurrent() {
        return current;
    }

    /**
     * @param current the current to set
     */
    public void setCurrent(Process current) {
        this.current = current;
    }

    public void execute() { //This is the timer task that runs
        timer = new Timer();
        task = new TimerTask() {

            @Override
            public void run() {
                
                System.out.println("Cpu started at :" + System.currentTimeMillis());
                long currentBurst = current.getBurstTime();
                if (currentBurst - 1000 > 0) {
                    current.setBurstTime(currentBurst - 1000);
                    seconds++;
                    System.out.println(current.getPid() + " is executed and current burst is " + current.getBurstTime() + "( Execution time :" + current.getExecutionTime() + " )");
                    setChanged();
                    notifyObservers(current);
                    
                } else {
                    current.setBurstTime(0);
                    seconds = 0;
                    current.setIsComplete(true);
                    System.out.println(current.getPid() + " finished execution and current burst is " + currentBurst);
                    setChanged();
                    notifyObservers(current);
                    setChanged();
                    notifyObservers();
                    

                }
                
                if(seconds%3==0){
                    setChanged();
                    notifyObservers();
                }
            }
        };
        timer.schedule(task, 0, 1000);
    }

    void pauseExecution() {
        if (timer != null) {
            timer.cancel();
        }
    }

}
