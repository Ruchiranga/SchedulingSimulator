package processscheduling;

/**
 *
 * @author Nands
 */
public class ProcessScheduling {

    /**
     * @param args the command line arguments
     */
    TimerZeta timer;
    Dispatcher dispatcher;
    Cpu cpu;
    long timeQuantum;

    public ProcessScheduling(long timeQuantum) {
        this.timeQuantum = timeQuantum;
        this.timer = new TimerZeta(1000);
        this.cpu = new Cpu(timeQuantum);
        this.dispatcher = new Dispatcher(cpu, timeQuantum);

        timer.addObserver(dispatcher);
    }

    public void simulate() {
        for (int i = 0; i < 10; i++) {
            dispatcher.addNewProcess(new Process(i, "Ready", (i + 1) * 1000, 0, 0, (i + 1) * 1000));
        }
        
        Thread slicer= new Thread(timer);
        slicer.start();
        
        //dispatcher.dispatch();
    }

    public static void main(String[] args) {
        ProcessScheduling scheduler = new ProcessScheduling(3000);
        scheduler.simulate();

    }

}
