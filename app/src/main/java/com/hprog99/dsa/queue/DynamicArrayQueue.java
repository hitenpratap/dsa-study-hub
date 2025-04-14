package com.hprog99.dsa.queue;

import java.util.NoSuchElementException;

public class DynamicArrayQueue {
    private int[] data; // Underlying array to store queue elements.
    private int front; // Index of the current front element.
    private int rear; // Index where the next element will be inserted.
    private int size; // Current number of elements in the queue.
    private int capacity; // Current capacity of the array.

    // Default constructor with an initial capacity.
    public DynamicArrayQueue() {
        capacity = 10; // You can choose any default capacity.
        data = new int[capacity];
        front = 0;
        rear = 0;
        size = 0;
    }

    // Adds an element to the back of the queue.
    public void enqueue(int value) {
        // Check if the underlying array is full. If so, resize (double the capacity).
        if (size == capacity) {
            resize(capacity * 2);
        }
        data[rear] = value;
        // Update the 'rear' index in a circular fashion.
        rear = (rear + 1) % capacity;
        size++;
    }

    // Removes and returns the front element of the queue.
    public int dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        int result = data[front];
        front = (front + 1) % capacity;
        size--;
        // Optionally, shrink the array if the number of elements is one-quarter of capacity.
        if (size > 0 && size == capacity / 4) {
            resize(capacity / 2);
        }
        return result;
    }

    // Returns (without removing) the front element of the queue.
    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return data[front];
    }

    // Returns true if the queue has no elements.
    public boolean isEmpty() {
        return size == 0;
    }

    // Returns the number of elements in the queue.
    public int size() {
        return size;
    }

    // Private helper method to resize the underlying array.
    private void resize(int newCapacity) {
        int[] newData = new int[newCapacity];
        // Copy current queue elements to the new array in order.
        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i) % capacity];
        }
        data = newData;
        // Reset front and rear pointers.
        front = 0;
        rear = size;
        capacity = newCapacity;
    }

    // Main method for demonstration purposes.
    public static void main(String[] args) {
        DynamicArrayQueue queue = new DynamicArrayQueue();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        System.out.println("Dequeued: " + queue.dequeue()); // Should print 10
        System.out.println("Front element: " + queue.peek()); // Should print 20
        queue.enqueue(40);
        System.out.println("Current queue size: " + queue.size()); // Should print 3

        // Continue to test further operations if needed.
    }
}

