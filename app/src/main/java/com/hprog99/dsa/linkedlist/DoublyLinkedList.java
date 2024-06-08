package com.hprog99.dsa.linkedlist;

/**
 * DoublyLinkedList
 */
public class DoublyLinkedList {

    private static class Node {
        private Node next;
        private Node prev;
        private int data;

        public Node(int data) {
            this.next = null;
            this.prev = null;
            this.data = data;
        }
    }

    private Node head;
    private int size;

    public DoublyLinkedList() {
        this.size = 0;
        this.head = null;
    }

    public int getSize() {
        return size;
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

    public void print() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public void addAtHead(int data) {
        Node newNode = new Node(data);
        Node current = head;
        if (current != null) {
            newNode.next = current;
            current.prev = newNode;
        }
        head = newNode;
        size++;
    }

    public void addAtTail(int data) {
        Node current = head;
        if (current == null) {
            addAtHead(data);
        } else {
            Node newNode = new Node(data);
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
            newNode.prev = current;
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
            newNode.prev = current;
            if (current.next != null) {
                current.next.prev = newNode;
            }
            current.next = newNode;
            size++;
        }
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must be less than the size of the list");
        }
        if (index == 0) {
            if (head.next != null) {
                head = head.next;
                head.prev = null;
            } else {
                head = null;
            }
        } else {
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            if (current.next != null) {
                current.next.prev = current.prev;
            }
            current.prev.next = current.next;
        }
        size--;
    }

    public static void main(String[] args) {
        DoublyLinkedList dll = new DoublyLinkedList();

        // Test addAtHead
        dll.addAtHead(2); // List: 2
        dll.addAtHead(1); // List: 1 -> 2
        dll.print(); // Expected output: 1 -> 2 -> null

        // Test addAtTail
        dll.addAtTail(3); // List: 1 -> 2 -> 3
        dll.addAtTail(4); // List: 1 -> 2 -> 3 -> 4
        dll.print(); // Expected output: 1 -> 2 -> 3 -> 4 -> null

        // Test addAtIndex
        dll.addAtIndex(5, 4); // List: 1 -> 2 -> 3 -> 4 -> 5
        dll.print(); // Expected output: 1 -> 2 -> 3 -> 4 -> 5 -> null
        dll.addAtIndex(0, 0); // List: 0 -> 1 -> 2 -> 3 -> 4 -> 5
        dll.print(); // Expected output: 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> null
        dll.addAtIndex(10, 3); // List: 0 -> 1 -> 2 -> 10 -> 3 -> 4 -> 5
        dll.print(); // Expected output: 0 -> 1 -> 2 -> 10 -> 3 -> 4 -> 5 -> null

        // Test remove
        dll.remove(0); // List: 1 -> 2 -> 10 -> 3 -> 4 -> 5
        dll.print(); // Expected output: 1 -> 2 -> 10 -> 3 -> 4 -> 5 -> null
        dll.remove(2); // List: 1 -> 2 -> 3 -> 4 -> 5
        dll.print(); // Expected output: 1 -> 2 -> 3 -> 4 -> 5 -> null
        dll.remove(dll.getSize() - 1); // List: 1 -> 2 -> 3 -> 4
        dll.print(); // Expected output: 1 -> 2 -> 3 -> 4 -> null

        // Test get
        System.out.println("Element at index 0: " + dll.get(0)); // Expected: 1
        System.out.println("Element at index 2: " + dll.get(2)); // Expected: 3
        try {
            System.out.println("Element at index 10: " + dll.get(10)); // Expected:
                                                                       // IndexOutOfBoundsException
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Caught expected IndexOutOfBoundsException for get(10)");
        }

        // Test contains
        System.out.println("List contains 3: " + dll.contains(3)); // Expected: true
        System.out.println("List contains 6: " + dll.contains(6)); // Expected: false

        // Test getSize
        System.out.println("Size of the list: " + dll.getSize()); // Expected: 4
    }
}
