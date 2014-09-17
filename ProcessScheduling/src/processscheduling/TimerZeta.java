package processscheduling;

import java.util.Observable;

public class TimerZeta extends Observable implements Runnable {

    long timeQuantum;
    long startTime;

    public TimerZeta(long timeQuantum) {
        this.timeQuantum = timeQuantum;
    }

    
    @Override
    public void run() {
        while (true) {
            startTime = System.currentTimeMillis();
            long endTime = startTime + timeQuantum;
            while ((endTime - System.currentTimeMillis()) > 0) {;

            }
            notifyObservers();
        }
    }
    
    
}
