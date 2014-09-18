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
        dispatcher.dispatch();
    }

    public static void main(String[] args) {
        ProcessScheduling scheduler = new ProcessScheduling(3000);
        scheduler.simulate();

    }
    
    public long createTimeQuantum(){
        long sum=0,dividend=0,intSum=0;
        for(int i=0;i<10;i++){
            sum+= Math.pow(dispatcher.getReadyQueue().getProcess(i).getExecutionTime(), 2);
            for(int j=0;j<10 && j!=i;j++){
                intSum += dispatcher.getReadyQueue().getProcess(i).getExecutionTime();
            }  
            dividend += intSum/9;             
        } 
        
        return Math.round(Math.sqrt(sum)/dividend);        
    }

}
