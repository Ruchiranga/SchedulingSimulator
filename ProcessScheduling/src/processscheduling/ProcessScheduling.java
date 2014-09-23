package processscheduling;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import view.DemostrationInterface;

/**
 *
 * @author Nands
 */
public class ProcessScheduling {

    /**
     * @param args the command line arguments
     */
    Dispatcher dispatcher;
    Cpu cpu;
    long timeQuantum;
    Process currentProcess;
    private ArrayList<Process> processes;

    
    public Cpu getCpu(){
        return cpu;
    }
    
    public Dispatcher getDispatcher(){
        return dispatcher;
    }

    
    public ProcessScheduling(long startTime) {
        this.cpu = new Cpu(startTime);
        this.dispatcher = new Dispatcher(cpu);
        processes = new ArrayList<>();

    }

    public void simulate() {
        currentProcess = dispatcher.dispatch();

    }

    public Process getCurrentProcess() {
        return currentProcess;
    }

    public boolean createTenProcesses() {
        Random r = new Random();
        
        for (int i = 0; i < 10; i++) {
            int random = Math.abs(r.nextInt())%10 +1 ;
            
            Process newProcess = new Process((i + 1), "Ready", random * 1000, 0, 0, random * 1000, -1, -1);
            
            dispatcher.addNewProcess(newProcess);
            processes.add(newProcess);
            newProcess.addObserver(dispatcher);            
            
        }
        return true;
    }

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
            DemostrationInterface demo = new DemostrationInterface();
            demo.setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProcessScheduling.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ProcessScheduling.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ProcessScheduling.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ProcessScheduling.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * @return the Processes
     */
    public ArrayList<Process> getProcesses() {
        return processes;
    }
    

    public long createTimeQuantum(){
        long sum=0,intSum=0, dividendSum =0;
        long dividend[] = new long[10];
        for(int i=0;i<10;i++){
            sum+= Math.pow(processes.get(i).getExecutionTime(), 2);
            for(int j=0;j<10 && j!=i;j++){
                intSum += processes.get(i).getExecutionTime();
            }  
            dividend[i] = intSum/9;    
            dividendSum+=dividend[i];
        } 
        
        int x = (int) (sum/dividendSum)/1000;
        return x*1000;
         
    }

    public void interrupt(long time){
        dispatcher.interrupt(time);
    }

    public void setTimeQuantum(long t) {
        this.timeQuantum = t;
        cpu.setTimeQuantum(t);
        dispatcher.setTimeQuantum(t);
    }

}
