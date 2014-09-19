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
            //System.out.println(startTime);
            long endTime = startTime + timeQuantum;
            //System.out.println(endTime);
            while ((endTime - System.currentTimeMillis()) > 0) {;
                
                //System.out.println(endTime - System.currentTimeMillis());
            }
            setChanged();
            System.out.println("Timer time :" + System.currentTimeMillis());
            notifyObservers();
        }
    }
    
    
}
