package org.example;

import java.util.NoSuchElementException;
import java.util.Random;

public class CircularLinkedList<T> {
    private static final class Node<E> {
        E data;
        Node<E> next;
        Node(E data) { this.data = data; }
    }

    private Node<T> head;
    private Node<T> tail;
    private Node<T> current;
    private int size = 0;
    private final Random rng;

    public CircularLinkedList() {
        this(new Random());
    }

    public CircularLinkedList(Random rng) {
        this.rng = rng;
    }

    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }

    public void append(T item) {
        Node<T> node = new Node<>(item);
        if (isEmpty()) {
            node.next = node;
            head = tail = current = node;
        } else {
            node.next = head; 
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public T getCurrent() {
        if (isEmpty()) throw new NoSuchElementException("CircularLinkedList is empty");
        return current.data;
    }

    public void step() {
        if (!isEmpty()) current = current.next;
    }

    public void move(int k) {
        if (isEmpty() || k == 0) return;
        int steps = ((k%size) + size)%size;
        for (int i = 0; i < steps; i++) current = current.next;
    }

    public int roll2d6AndMove() {
        int roll = (rng.nextInt(6) + 1) + (rng.nextInt(6) + 1);
        move(roll);
        return roll;
    }

    public void resetToHead() { current = head; }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        Node<T> n = head;
        for (int i = 0; i < size; i++) {
            sb.append(n.data);
            if (i < size - 1) sb.append(" -> ");
            n = n.next;
        }
        sb.append("] (current=").append(current.data).append(")");
        return sb.toString();
    }
}
