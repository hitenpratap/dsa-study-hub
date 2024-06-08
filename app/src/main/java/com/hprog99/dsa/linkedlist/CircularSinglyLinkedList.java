package com.hprog99.dsa.linkedlist;

/**
 * CircularSinglyLinkedList
 */
public class CircularSinglyLinkedList {

    private static class Node {

        private Node next;
        private int data;

        public Node(int data) {
            this.next = null;
            this.data = data;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public CircularSinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean contains(int data) {
        Node current = head;
        while (current != null) {
            if (current.data == data) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must be less than the size of the list");
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public void addAtHead(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        tail.next = head;
        size++;
    }

    public void addAtTail(int data) {
        if (head == null) {
            addAtHead(data);
        } else {
            Node newNode = new Node(data);
            tail.next = newNode;
            tail = newNode;
            tail.next = head;
            size++;
        }
    }

    public void addAtIndex(int data, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index must be less than the size of the list");
        }
        if (index == 0) {
            addAtHead(data);
        } else if (index == size) {
            addAtTail(data);
        } else {
            Node newNode = new Node(data);
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
            size++;
        }
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must be less than the size of the list");
        }
        if (index == 0) {
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                tail.next = head;
            }
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
            if (index == size - 1) {
                tail = current;
            }
        }
        size--;
    }

    public void print() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        Node current = head;
        do {
            System.out.print(current.data + " -> ");
            current = current.next;
        } while (current != head);
        System.out.println("(head)");
    }

    public static void main(String[] args) {
        CircularSinglyLinkedList csll = new CircularSinglyLinkedList();

        // Test addAtHead
        csll.addAtHead(2); // List: 2
        csll.addAtHead(1); // List: 1 -> 2
        csll.print(); // Expected output: 1 -> 2 -> (head)

        // Test addAtTail
        csll.addAtTail(3); // List: 1 -> 2 -> 3
        csll.addAtTail(4); // List: 1 -> 2 -> 3 -> 4
        csll.print(); // Expected output: 1 -> 2 -> 3 -> 4 -> (head)

        // Test addAtIndex
        csll.addAtIndex(5, 4); // List: 1 -> 2 -> 3 -> 4 -> 5
        csll.print(); // Expected output: 1 -> 2 -> 3 -> 4 -> 5 -> (head)
        csll.addAtIndex(0, 0); // List: 0 -> 1 -> 2 -> 3 -> 4 -> 5
        csll.print(); // Expected output: 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> (head)
        csll.addAtIndex(10, 3); // List: 0 -> 1 -> 2 -> 10 -> 3 -> 4 -> 5
        csll.print(); // Expected output: 0 -> 1 -> 2 -> 10 -> 3 -> 4 -> 5 -> (head)

        // Test remove
        csll.remove(0); // List: 1 -> 2 -> 10 -> 3 -> 4 -> 5
        csll.print(); // Expected output: 1 -> 2 -> 10 -> 3 -> 4 -> 5 -> (head)
        csll.remove(2); // List: 1 -> 2 -> 3 -> 4 -> 5
        csll.print(); // Expected output: 1 -> 2 -> 3 -> 4 -> 5 -> (head)
        csll.remove(csll.getSize() - 1); // List: 1 -> 2 -> 3 -> 4
        csll.print(); // Expected output: 1 -> 2 -> 3 -> 4 -> (head)

        // Test get
        System.out.println("Element at index 0: " + csll.get(0)); // Expected: 1
        System.out.println("Element at index 2: " + csll.get(2)); // Expected: 3
        try {
            System.out.println("Element at index 10: " + csll.get(10)); // Expected:
                                                                        // IndexOutOfBoundsException
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Caught expected IndexOutOfBoundsException for get(10)");
        }

        // Test contains
        System.out.println("List contains 3: " + csll.contains(3)); // Expected: true
        System.out.println("List contains 6: " + csll.contains(6)); // Expected: false

        // Test getSize
        System.out.println("Size of the list: " + csll.getSize()); // Expected: 4
    }
}
