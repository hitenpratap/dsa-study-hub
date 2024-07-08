package com.hprog99.dsa.stack;

/**
 * LinkedListStack
 */
public class LinkedListStack {
    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node top;
    private int size;

    public LinkedListStack() {
        top = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(int data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public int pop() throws Exception {
        if (isEmpty()) {
            throw new Exception("Stack is empty");
        }
        int data = top.data;
        top = top.next;
        size--;
        return data;
    }

    public int top() throws Exception {
        if (isEmpty()) {
            throw new Exception("Stack is empty");
        }
        return top.data;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Stack is empty";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Stack: ");
        Node current = top;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            LinkedListStack stack = new LinkedListStack();

            System.out.println("Pushing elements onto the stack:");
            stack.push(10);
            stack.push(20);
            stack.push(30);
            System.out.println(stack); // Expected Output: Stack: 30 -> 20 -> 10

            System.out.println("\nTop element: " + stack.top()); // Expected Output: Top element: 30
            System.out.println("Stack size: " + stack.size()); // Expected Output: Stack size: 3

            System.out.println("\nPopping elements from the stack:");
            System.out.println("Popped element: " + stack.pop()); // Expected Output: Popped
                                                                  // element: 30
            System.out.println(stack); // Expected Output after pop: Stack: 20 -> 10
            System.out.println("Popped element: " + stack.pop()); // Expected Output: Popped
                                                                  // element: 20
            System.out.println(stack); // Expected Output after pop: Stack: 10

            System.out.println("\nPushing more elements onto the stack:");
            stack.push(40);
            stack.push(50);
            System.out.println(stack); // Expected Output: Stack: 50 -> 40 -> 10

            System.out.println("\nTop element: " + stack.top()); // Expected Output: Top element: 50
            System.out.println("Stack size: " + stack.size()); // Expected Output: Stack size: 3
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}

