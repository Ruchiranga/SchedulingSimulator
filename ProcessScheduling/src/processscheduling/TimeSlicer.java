/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processscheduling;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author ruchiranga
 */
public class TimeSlicer extends Observable {

    Timer timer;
    long quantum;

    public TimeSlicer(long quantum) {
        this.quantum = quantum;
        timer = new Timer();

    }

    public void startTimer() {
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                setChanged();
                notifyObservers();
            }
        };
        timer.schedule(task, 0, quantum);
    }

}
