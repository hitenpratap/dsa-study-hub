package com.hprog99.dsa.stack;

/**
 * FixedSizeArrayStack
 */
public class FixedSizeArrayStack {

    protected int capacity;
    public static final int CAPACITY = 10;
    protected int[] stack;
    protected int top = -1;

    public FixedSizeArrayStack() {
        this(CAPACITY);
    }

    public FixedSizeArrayStack(int capacity) {
        this.capacity = capacity;
        this.stack = new int[capacity];
    }

    public int size() {
        return top + 1;
    }

    public boolean isEmpty() {
        return top < 0;
    }

    public void push(int data) throws Exception {
        if (size() == capacity) {
            throw new Exception("Stack is full");
        }
        stack[++top] = data;
    }

    public int pop() throws Exception {
        if (isEmpty()) {
            throw new Exception("Stack is empty");
        }
        int data = stack[top];
        stack[top--] = Integer.MIN_VALUE;
        return data;
    }

    public int top() throws Exception {
        if (isEmpty()) {
            throw new Exception("Stack is empty");
        }
        return stack[top];
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Stack is empty";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Stack: ");
        for (int i = 0; i <= top; i++) {
            sb.append(stack[i]);
            if (i < top) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            FixedSizeArrayStack stack = new FixedSizeArrayStack(5);

            System.out.println("Pushing elements onto the stack:");
            stack.push(10);
            stack.push(20);
            stack.push(30);
            // Expected Output: Stack: 10, 20, 30
            System.out.println(stack);

            System.out.println("\nTop element: " + stack.top());
            // Expected Output: Top element: 30
            System.out.println("Stack size: " + stack.size());
            // Expected Output: Stack size: 3

            System.out.println("\nPopping elements from the stack:");
            System.out.println("Popped element: " + stack.pop());
            // Expected Output: Popped element: 30
            // Expected Output after pop: Stack: 10, 20
            System.out.println(stack);
            System.out.println("Popped element: " + stack.pop());
            // Expected Output: Popped element: 20
            // Expected Output after pop: Stack: 10
            System.out.println(stack);

            System.out.println("\nPushing more elements onto the stack:");
            stack.push(40);
            stack.push(50);
            stack.push(60);
            // Expected Output: Stack: 10, 40, 50, 60
            System.out.println(stack);

            System.out.println("\nAttempting to push when stack is full:");
            stack.push(70); // This should throw an exception
        } catch (Exception e) {
            System.err.println(e.getMessage());
            // Expected Output: Stack is full
        }
    }
}

