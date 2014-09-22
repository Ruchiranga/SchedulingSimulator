/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processscheduling;

import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author Nands
 */
public class Queue extends Observable {

    private ArrayList<Process> queArray;

    public Queue(int s) // constructor
    {
        queArray = new ArrayList<>();
    }

    public ArrayList<Process> getProcesses() {
        return queArray;
    }

    public void enqueue(Process process) // put item at rear of queue
    {
        queArray.add(process);        // increment rear and insert
        setChanged();
        notifyObservers(process);
    }

    public Process dequeue() // take item from front of queue
    {
        if (!isEmpty()) {
            Process process = queArray.get(0); // get value and incr front
            process.setState("Running");
            queArray.remove(0);
            setChanged();
            notifyObservers(process);
            return process;
        }
        return null;

    }

    public boolean isEmpty() // true if queue is empty
    {
        return (queArray.size() == 0);
    }

    public int size() // number of items in queue
    {
        return queArray.size();
    }
   
    public Process getProcess(int number){
        return queArray.get(number);
    }
    
    public Process peek(){
        return queArray.get(0);
    }

}
