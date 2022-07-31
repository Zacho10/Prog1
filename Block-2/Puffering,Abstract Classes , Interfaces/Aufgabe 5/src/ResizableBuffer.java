public interface ResizableBuffer {

    void put(Object o);
    Object remove();
    boolean isEmpty();
    boolean isFull();
    void resize(int size);

}
