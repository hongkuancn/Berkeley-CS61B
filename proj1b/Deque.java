public interface Deque<T> {
    public void addFirst(T item);
    public void addLast(T item);
    public T removeFirst();
    public T removeLast();
    public int size();
    public T get(int indez);
    public boolean isEmpty();
    public void printDeque();
}
