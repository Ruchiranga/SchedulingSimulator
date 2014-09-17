/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processscheduling;

/**
 *
 * @author Nands
 */
public class Queue {

    private int maxSize;
    private Process[] queArray;
    private int front;
    private int rear;
    private int nItems;

    public Queue(int s) // constructor
    {
        maxSize = s;
        queArray = new Process[maxSize];
        front = 0;
        rear = -1;
        nItems = 0;
    }

    public void enqueue(Process process) // put item at rear of queue
    {
        if (rear == maxSize - 1) // deal with wraparound
        {
            rear = -1;
        }
        queArray[++rear] = process;         // increment rear and insert
        nItems++;                     // one more item
    }

    public Process dequeue() // take item from front of queue
    {
        Process process = queArray[front++]; // get value and incr front
        if (front == maxSize) // deal with wraparound
        {
            front = 0;
        }
        nItems--;                      // one less item
        return process;
    }

    public Process peekFront() // peek at front of queue
    {
        return queArray[front];
    }

    public boolean isEmpty() // true if queue is empty
    {
        return (nItems == 0);
    }

    public boolean isFull() // true if queue is full
    {
        return (nItems == maxSize);
    }

    public int size() // number of items in queue
    {
        return nItems;
    }
}
