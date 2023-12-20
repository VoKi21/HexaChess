package chessgame.util;

import java.util.AbstractList;

public class BrittleList<T> extends AbstractList<T> {

    private Node<T> head;
    private int size;

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    @Override
    public boolean add(T element) {
        Node<T> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> last = getLastNode();
            last.next = newNode;
        }
        size++;
        return true;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);

        if (index == 0) {
            T removedData = head.data;
            head = null;
            size = 0;
            return removedData;
        }

        Node<T> previous = getNode(index - 1);
        T removedData = previous.next.data;
        previous.next = null;
        size = index;
        return removedData;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        Node<T> node = getNode(index);
        return node.data;
    }

    @Override
    public int size() {
        return size;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private Node<T> getNode(int index) {
        checkIndex(index);
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    private Node<T> getLastNode() {
        Node<T> current = head;
        while (current.next != null) {
            current = current.next;
        }
        return current;
    }
}

