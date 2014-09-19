package processscheduling;

import java.util.ArrayList;
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
    
    public Cpu getCpu(){
        return cpu;
    }
    
    public Dispatcher getDispatcher(){
        return dispatcher;
    }

    private ArrayList<Process> processes;

    public ProcessScheduling(long timeQuantum, long startTime) {
        this.timeQuantum = timeQuantum;
        this.cpu = new Cpu(timeQuantum, startTime);
        this.dispatcher = new Dispatcher(cpu, timeQuantum);

        processes = new ArrayList<>();

    }

    public void simulate() {
        currentProcess = dispatcher.dispatch();

    }

    public Process getCurrentProcess() {
        return currentProcess;
    }

    public boolean createTenProcesses() {
        for (int i = 0; i < 10; i++) {
            Process newProcess = new Process((i + 1), "Ready", (i + 1) * 1000, 0, 0, (i + 1) * 1000, -1, -1);
            System.out.println("qqqqqqqqqqqq : "+newProcess.getStartTime());
            dispatcher.addNewProcess(newProcess);
            processes.add(newProcess);
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
    
    public void interrupt(long time){
        dispatcher.interrupt(time);
    }


}
