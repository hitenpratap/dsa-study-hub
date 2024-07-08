package com.hprog99.dsa.stack;

/**
 * DynamicArrayStack
 */
public class DynamicArrayStack {
    private int capacity;
    private int[] stack;
    private int top = -1;
    private static final int INITIAL_CAPACITY = 10;
    private static final double SHRINK_THRESHOLD = 0.25;

    public DynamicArrayStack() {
        this.capacity = INITIAL_CAPACITY;
        this.stack = new int[capacity];
    }

    public int size() {
        return top + 1;
    }

    public boolean isEmpty() {
        return top < 0;
    }

    public void push(int data) {
        if (size() == capacity) {
            resize(capacity * 2);
        }
        stack[++top] = data;
    }

    public int pop() throws Exception {
        if (isEmpty()) {
            throw new Exception("Stack is empty");
        }
        int data = stack[top];
        stack[top--] = 0;
        if (size() > INITIAL_CAPACITY && size() < capacity * SHRINK_THRESHOLD) {
            resize(capacity / 2);
        }
        return data;
    }

    public int top() throws Exception {
        if (isEmpty()) {
            throw new Exception("Stack is empty");
        }
        return stack[top];
    }

    private void resize(int newCapacity) {
        capacity = newCapacity;
        int[] newStack = new int[capacity];
        System.arraycopy(stack, 0, newStack, 0, size());
        stack = newStack;
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
            DynamicArrayStack stack = new DynamicArrayStack();

            System.out.println("Pushing elements onto the stack:");
            stack.push(10);
            stack.push(20);
            stack.push(30);
            System.out.println(stack); // Expected Output: Stack: 10, 20, 30

            System.out.println("\nTop element: " + stack.top()); // Expected Output: Top element: 30
            System.out.println("Stack size: " + stack.size()); // Expected Output: Stack size: 3

            System.out.println("\nPopping elements from the stack:");
            System.out.println("Popped element: " + stack.pop()); // Expected Output: Popped
                                                                  // element: 30
            System.out.println(stack); // Expected Output after pop: Stack: 10, 20
            System.out.println("Popped element: " + stack.pop()); // Expected Output: Popped
                                                                  // element: 20
            System.out.println(stack); // Expected Output after pop: Stack: 10

            System.out.println("\nPushing more elements onto the stack:");
            stack.push(40);
            stack.push(50);
            stack.push(60);
            stack.push(70);
            stack.push(80);
            stack.push(90);
            stack.push(100);
            stack.push(110);
            stack.push(120);
            stack.push(130); // This should trigger resizing
            System.out.println(stack); // Expected Output: Stack: 10, 40, 50, 60, 70, 80, 90, 100,
                                       // 110, 120, 130

            System.out.println("\nStack after resizing:");
            System.out.println("Stack size: " + stack.size()); // Expected Output: Stack size: 11
            System.out.println("Top element: " + stack.top()); // Expected Output: Top element: 130

            System.out.println("\nPopping elements to trigger shrinking:");
            while (!stack.isEmpty()) {
                System.out.println("Popped element: " + stack.pop());
                System.out.println(stack);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}

