public class LinkedListDeque<T> {
    private class Node {
        public Node prev;
        public Node next;
        public T item;

        public Node(Node p, Node n, T i){
            prev = p;
            next = n;
            item = i;
        }
    }

    /** The first element is sentinel.next, the last element is sentinel.prev */
    private Node sentinel;
    private int size;

    /** Construct an empty LinkedListDeque */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /** Add item to the front of Deque */
    public void addFirst(T item){
        if (size == 0){
            size += 1;
            sentinel.next = new Node(sentinel, sentinel, item);
            sentinel.prev = sentinel.next;
        } else {
            size += 1;
            Node newNode = new Node(sentinel, sentinel.next, item);
            sentinel.next.prev = newNode;
            sentinel.next = newNode;
        }

    }

    /** Add item to the end of Deque */
    public void addLast(T item){
        if (size == 0){
            size += 1;
            sentinel.prev = new Node(sentinel, sentinel, item);
            sentinel.next = sentinel.prev;
        } else {
            size += 1;
            Node newNode = new Node(sentinel.prev, sentinel, item);
            sentinel.prev.next = newNode;
            sentinel.prev = newNode;
        }

    }

    /** Check if LinkedListDeque is empty */
    public boolean isEmpty(){
        return size == 0;
    }

    /** Returns the size of Deque */
    public int size(){
        return size;
    }

    /** Prints LinkedListDeque from first to last */
    public void printDeque(){
        if (size == 0){
            System.out.println("Empty!");
        } else {
            Node n = sentinel.next;
            while(n != sentinel){
                System.out.print(n.item.toString() + " ");
                n = n.next;
            }
            System.out.println();
        }
    }

    /** Remove the first item in Deque and return it */
    public T removeFirst(){
        if (size == 0){
            return null;
        }
        Node f = sentinel.next;
        T item = f.item;
        sentinel.next = f.next;
        f.next.prev = sentinel;
        f.prev = null;
        f.next = null;
        f.item = null;  // need or not
        size -= 1;
        return item;
    }

    /** Remove the last item in Deque and return it */
    public T removeLast(){
        if (size == 0){
            return null;
        }
        Node l = sentinel.prev;
        T item = l.item;
        sentinel.prev = l.prev;
        l.prev.next = sentinel;
        size -= 1;
        return item;
    }

    /** Return item at the position of index in Deque iteratively */
    public T get(int index){
        if (index > size - 1 || index < 0){
            return null;
        }
        Node n = sentinel;
        while (index > -1){
            n = n.next;
            index -= 1;
        }
        return n.item;
    }

    /** Return item at the position of index in Deque Recursively */
    public T getRecursive(int index){
        return getRecursiveHelper(index).item;
    }

    private Node getRecursiveHelper(int index){
        if (index > size - 1 || index < 0){
            return null;
        }
        if (index == 0){
            return sentinel.next;
        } else {
            Node n = getRecursiveHelper(index - 1);
            n = n.next;
            return n;
        }
    }

    public static void main(String[] args){
        LinkedListDeque<Integer> li = new LinkedListDeque<>();
        li.addFirst(0);
        li.addFirst(1);
        li.addFirst(2);
        li.addFirst(3);
        li.addFirst(4);
        li.addLast(-1);
        li.removeFirst();
        li.removeLast();
        li.removeLast();
    }
}
