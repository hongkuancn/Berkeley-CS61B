public class ArrayDeque<T> implements Deque<T> {

    /**
     * The first element is sentinel.next, the last element is sentinel.prev.
     */
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    /**
     * Construct an empty LinkedListDeque.
     */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
        size = 0;
    }

    /**
     * Add item to the front of Deque.
     */
    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        size += 1;
        nextFirst = minusOne(nextFirst);
    }

    /**
     * Add item to the end of Deque.
     */
    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        size += 1;
        nextLast = plusOne(nextLast);
    }

    /**
     * Check if LinkedListDeque is empty.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the size of Deque.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Prints LinkedListDeque from first to last.
     */
    @Override
    public void printDeque() {
        if (size == 0) {
            System.out.println("Empty!");
        } else {
            int m = plusOne(nextFirst);
            while (m != nextLast) {
                System.out.print(items[m].toString() + " ");
                m = plusOne(m);
            }
            System.out.println();
        }
    }

    /**
     * Remove the first item in Deque and return it.
     */
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T item = items[plusOne(nextFirst)];
        items[plusOne(nextFirst)] = null;
        nextFirst = plusOne(nextFirst);
        size -= 1;
        if (items.length > 8 && size == items.length / 4) {
            resize(items.length / 2);
        }
        return item;
    }

    /**
     * Remove the last item in Deque and return it.
     */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T item = items[minusOne(nextLast)];
        items[minusOne(nextLast)] = null;
        nextLast = minusOne(nextLast);
        size -= 1;
        if (items.length > 8 && size == items.length / 4) {
            resize(items.length / 2);
        }

        return item;
    }

    /**
     * Return item at index in Deque in constant time.
     */
    @Override
    public T get(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        return items[(index + nextFirst + 1) % items.length];
    }

    /* Resize the deque */
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];

        if (nextLast == plusOne(nextFirst)) {
            /* Expand the deque */
            System.arraycopy(items, plusOne(nextFirst), a, 1, items.length - plusOne(nextFirst));
            System.arraycopy(items, 0, a, items.length - plusOne(nextFirst) + 1, nextLast);
            nextFirst = 0;
            nextLast = size + 1;
            items = a;
        } else {
            /* Shrink the deque */
            if (nextFirst > nextLast) {
                System.arraycopy(items, plusOne(nextFirst), a, 1, items.length - nextFirst - 1);
                System.arraycopy(items, 0, a, items.length - nextFirst, nextLast);
            } else {
                /* nextFirst < nextLast */
                System.arraycopy(items, nextFirst + 1, a, 1, size);
            }
            nextFirst = 0;
            nextLast = size + 1;
            items = a;
        }

    }

    /**
     * Helper function to compute index before a given index.
     */
    private int minusOne(int index) {
        if (index == 0) {
            return items.length - 1;
        } else {
            return index - 1;
        }
    }

    /**
     * Helper function to compute index after a given index.
     */
    private int plusOne(int index) {
        if (index == items.length - 1) {
            return 0;
        } else {
            return index + 1;
        }
    }
}
