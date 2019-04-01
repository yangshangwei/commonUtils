package com.artisan.commonUtils.DataStructures.StacksAndQueues;

public interface Stack<E> {
    int getSize();
    boolean isEmpty();
    void push(E e);
    E pop();
    E peek();
}
