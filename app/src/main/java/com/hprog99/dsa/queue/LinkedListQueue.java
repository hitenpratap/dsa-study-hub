package com.hprog99.dsa.queue;

import java.util.NoSuchElementException;

public class LinkedListQueue {
    // Inner node class to represent each element in the queue.
    private static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    private Node front; // Points to the front element of the queue.
    private Node rear; // Points to the last element in the queue.
    private int size; // Keeps track of the number of elements in the queue.

    // Constructor initializes an empty queue.
    public LinkedListQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    // Adds a new element to the end of the queue.
    public void enqueue(int value) {
        Node newNode = new Node(value);
        // If queue is empty, both front and rear point to the new node.
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    // Removes and returns the front element from the queue.
    public int dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        int result = front.value;
        front = front.next;
        size--;
        // If the queue becomes empty after dequeue, make sure rear is also null.
        if (isEmpty()) {
            rear = null;
        }
        return result;
    }

    // Returns the front element of the queue without removing it.
    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return front.value;
    }

    // Checks whether the queue is empty.
    public boolean isEmpty() {
        return size == 0;
    }

    // Returns the number of elements in the queue.
    public int size() {
        return size;
    }

    // Main method for demonstration purposes.
    public static void main(String[] args) {
        LinkedListQueue queue = new LinkedListQueue();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        System.out.println("Front element is: " + queue.peek()); // Should print 10
        System.out.println("Dequeued element: " + queue.dequeue()); // Should print 10
        System.out.println("Current queue size: " + queue.size()); // Should print 2

        // Continue to test further operations if needed.
        queue.enqueue(40);
        System.out.println("Front element is: " + queue.peek()); // Should print 20
    }
}

