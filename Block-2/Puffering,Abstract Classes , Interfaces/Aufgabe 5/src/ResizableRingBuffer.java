public class ResizableRingBuffer implements ResizableBuffer {
    private int bufferSize;

    ObjectRingBuffer buffer;
    public ResizableRingBuffer(int g) {
        buffer = new ObjectRingBuffer(g);
        bufferSize = g;
    }

    @Override
    public void put(Object o) {
        buffer.put(o);
    }

    @Override
    public Object remove() {
        return buffer.remove();
    }

    @Override
    public boolean isEmpty() {
        return buffer.isEmpty();
    }

    @Override
    public boolean isFull() {
        return buffer.isFull();
    }

    /**
     * a simple resize operation without many checks
     *
     * @param increment the additional space to be allocated
     */
    @Override
    public void resize(int increment) {
        ObjectRingBuffer bigger = new ObjectRingBuffer( bufferSize + increment);

        while (!this.buffer.isEmpty()){
            Object o = buffer.remove();
            if (o == null){
                System.out.println("Uh, oooh,...");
                return;
            } else {
                bigger.put(o);
            }
        }
        this.buffer = bigger;
        bufferSize = bufferSize + increment;
    }

    public int getSize(){
        return bufferSize;
    }
}
