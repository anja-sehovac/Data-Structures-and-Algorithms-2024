package Week1;

import java.util.Iterator;

class DoubleNode<Data> {
    Data data;
    DoubleNode<Data> next;
    DoubleNode<Data> prev;
}

class DoublyLinkedList<Data> implements Iterable<Data> {
    private DoubleNode<Data> head;
    private DoubleNode<Data> tail;
    private int size = 0;

    /* Add a new node to the front of the doubly linked list */
    public void addToFront(Data data) {
        DoubleNode<Data> newNode = new DoubleNode<>();
        newNode.data = data;
        newNode.next = head;
        newNode.prev = null;

        if (head != null) {
            head.prev = newNode;
        }

        head = newNode;

        if (tail == null) {
            tail = newNode;
        }

        size++;
    }

    /* Remove a node from the front of the doubly linked list */
    public void removeFromFront() {
        if (head == null) {
            throw new IndexOutOfBoundsException("The doubly linked list is empty.");
        }

        head = head.next;

        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }

        size--;
    }

    /* Add a new node to the end of the doubly linked list */
    public void addToRear(Data data) {
        DoubleNode<Data> newNode = new DoubleNode<>();
        newNode.data = data;
        newNode.next = null;
        newNode.prev = tail;

        if (tail != null) {
            tail.next = newNode;
        }

        tail = newNode;

        if (head == null) {
            head = newNode;
        }

        size++;
    }

    /* Remove a node at the end of the doubly linked list */
    public void removeFromRear() {
        if (tail == null) {
            throw new IndexOutOfBoundsException("The doubly linked list is empty.");
        }

        tail = tail.prev;

        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
        }

        size--;
    }

    /* Get a linked list node by index (0-indexed) */
    public Data get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid doubly linked list node.");
        }

        DoubleNode<Data> current = head;
        int i = 0;
        while (i < index) {
            current = current.next;
            i++;
        }

        return current.data;
    }

    /* Add an element to a doubly linked list by index (0-index) */
    public void add(int index, Data data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index for insertion.");
        }

        if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToRear(data);
        } else {
            DoubleNode<Data> newNode = new DoubleNode<>();
            newNode.data = data;

            DoubleNode<Data> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }

            newNode.next = current.next;
            newNode.prev = current;
            current.next.prev = newNode;
            current.next = newNode;

            size++;
        }
    }

    /* Delete an element from a doubly linked list by index (0-index) */
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index for removal.");
        }

        if (index == 0) {
            removeFromFront();
        } else if (index == size - 1) {
            removeFromRear();
        } else {
            DoubleNode<Data> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }

            current.next = current.next.next;
            current.next.prev = current;

            size--;
        }
    }

    /* Return the current size of the doubly linked list */
    public int count() {
        return size;
    }

    /* Return an Iterator Object */
    @Override
    public Iterator<Data> iterator() {
        return new DoublyLinkedListIterator();
    }

    /* Define the Iterator class, and hasNext() and next() methods */
    private class DoublyLinkedListIterator implements Iterator<Data> {
        private DoubleNode<Data> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Data next() {
            if (!hasNext()) {
                throw new IllegalStateException("No more elements in the doubly linked list.");
            }

            Data data = current.data;
            current = current.next;
            return data;
        }
    }

    /* Get head node (for test purposes) */
    public DoubleNode<Data> getHead() {
        return head;
    }

    /* Get tail node (for test purposes) */
    public DoubleNode<Data> getTail() {
        return tail;
    }
}

