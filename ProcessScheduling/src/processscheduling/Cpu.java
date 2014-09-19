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
    private long currentTime;

    public Cpu(long timeQuantum, long currentTime) {
        this.timeQunatum = timeQuantum;
        this.currentTime = currentTime;
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
                
                long currentBurst = current.getBurstTime();
                currentTime += 1000;
                if (currentBurst != 0) {
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
                        current.setState("Finished");
                        current.setFinishedTime(getCurrentTime());
                        System.out.println(current.getPid() + " finished execution and current burst is " + current.getBurstTime());
                        setChanged();
                        notifyObservers(current);
                        setChanged();
                        notifyObservers();

                    }
                }else{
                    this.cancel();
                }
                if (seconds != 0 && seconds % 3 == 0) {
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
            seconds = 0;
        }
    }

    /**
     * @return the currentTime
     */
    public long getCurrentTime() {
        return currentTime;
    }

    /**
     * @param currentTime the currentTime to set
     */
    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

}
