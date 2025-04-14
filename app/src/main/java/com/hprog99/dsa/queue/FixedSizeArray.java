package com.hprog99.dsa.queue;

import java.util.NoSuchElementException;

/**
 * Implements a Queue data structure using a fixed-size circular array specifically for int data
 * types.
 */
public class FixedSizeArray {

    private int[] queueArray; // Array to store queue int elements
    private int capacity; // Maximum capacity of the queue
    private int front; // Index of the front element
    private int rear; // Index where the next element will be inserted
    private int size; // Current number of elements in the queue

    /**
     * Constructs an empty queue with the specified capacity.
     *
     * @param capacity the maximum capacity of the queue
     * @throws IllegalArgumentException if capacity is not positive
     */
    public FixedSizeArray(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive.");
        }
        this.capacity = capacity;
        this.queueArray = new int[capacity]; // Directly create an int array
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks if the queue is full.
     *
     * @return true if the queue is full, false otherwise
     */
    public boolean isFull() {
        return size == capacity;
    }

    /**
     * Returns the number of elements currently in the queue.
     *
     * @return the number of elements in the queue
     */
    public int size() {
        return size;
    }

    /**
     * Returns the maximum capacity of the queue.
     *
     * @return the capacity of the queue
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Adds an integer element to the rear of the queue.
     *
     * @param item the integer element to add
     * @throws IllegalStateException if the queue is full
     */
    public void enqueue(int item) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full. Cannot enqueue.");
        }
        queueArray[rear] = item;
        rear = (rear + 1) % capacity; // Move rear circularly
        size++;
        System.out.println("Enqueued: " + item); // Optional: for demonstration
    }

    /**
     * Removes and returns the integer element from the front of the queue.
     *
     * @return the integer element removed from the front of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public int dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty. Cannot dequeue.");
        }
        int item = queueArray[front];
        front = (front + 1) % capacity; // Move front circularly
        size--;
        System.out.println("Dequeued: " + item); // Optional: for demonstration
        return item;
    }

    /**
     * Returns the integer element at the front of the queue without removing it.
     *
     * @return the integer element at the front of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty. Cannot peek.");
        }
        return queueArray[front];
    }

    /**
     * Returns a string representation of the queue contents.
     *
     * @return a string representation of the queue
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "Queue (size=0, capacity=" + capacity + "): []";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Queue (size=").append(size).append(", capacity=").append(capacity)
                .append("): [");
        int current = front;
        for (int i = 0; i < size; i++) {
            sb.append(queueArray[current]);
            if (i < size - 1) {
                sb.append(", ");
            }
            current = (current + 1) % capacity;
        }
        sb.append("] (front=").append(front).append(", rear=").append(rear).append(")");
        return sb.toString();
    }

    // --- Main method for basic testing ---
    public static void main(String[] args) {
        System.out.println("--- Testing ArrayQueueInt ---");
        // Create a queue specifically for integers
        FixedSizeArray queue = new FixedSizeArray(5); // Capacity of 5

        System.out.println("Initial state: " + queue);
        System.out.println("Is empty? " + queue.isEmpty()); // true
        System.out.println("Is full? " + queue.isFull()); // false

        try {
            queue.dequeue(); // Should throw NoSuchElementException
        } catch (NoSuchElementException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }

        System.out.println("\nEnqueuing elements:");
        queue.enqueue(10);
        System.out.println(queue);
        queue.enqueue(20);
        System.out.println(queue);
        queue.enqueue(30);
        System.out.println(queue);

        System.out.println("\nPeeking:");
        System.out.println("Front element: " + queue.peek()); // 10
        System.out.println(queue); // Queue remains unchanged

        System.out.println("\nDequeuing elements:");
        System.out.println("Element removed: " + queue.dequeue()); // 10
        System.out.println(queue);
        System.out.println("Front element after dequeue: " + queue.peek()); // 20

        System.out.println("\nFilling the queue:");
        queue.enqueue(40);
        System.out.println(queue);
        queue.enqueue(50);
        System.out.println(queue);
        queue.enqueue(60); // Now full (20, 30, 40, 50, 60)
        System.out.println(queue);
        System.out.println("Is empty? " + queue.isEmpty()); // false
        System.out.println("Is full? " + queue.isFull()); // true

        try {
            queue.enqueue(70); // Should throw IllegalStateException
        } catch (IllegalStateException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }

        System.out.println("\nDequeuing until empty:");
        while (!queue.isEmpty()) {
            queue.dequeue();
            System.out.println(queue);
        }

        System.out.println("Is empty? " + queue.isEmpty()); // true
        System.out.println("Is full? " + queue.isFull()); // false

        System.out.println("\nTesting wrap-around:");
        queue.enqueue(100);
        queue.enqueue(200);
        queue.enqueue(300);
        System.out.println(queue);
        queue.dequeue(); // Remove 100
        queue.dequeue(); // Remove 200
        System.out.println(queue); // front=2, rear=3, size=1 [_, _, 300, _, _]
        queue.enqueue(400); // rear=4, size=2 [_, _, 300, 400, _]
        queue.enqueue(500); // rear=0, size=3 [500, _, 300, 400, _]
        queue.enqueue(600); // rear=1, size=4 [500, 600, 300, 400, _]
        queue.enqueue(700); // rear=2, size=5 [500, 600, 300, 400, 700] -> Now full
        System.out.println(queue);

        System.out.println("\n--- Test Complete ---");
    }
}
