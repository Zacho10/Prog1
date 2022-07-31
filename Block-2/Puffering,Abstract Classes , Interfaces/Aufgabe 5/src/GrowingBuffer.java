public class GrowingBuffer extends ResizableRingBuffer {
    /**
     * constructor as per super
     * @param g the size
     */
    public GrowingBuffer(int g) {
        super(g);
    }

    /**
     * put but with doubling the size if the buffer is full
     *
     * @param o the object that will be put (unless we are out of memory)
     */
    @Override
    public void put(Object o){
        if (this.isFull()){
            this.resize(this.getSize());
        }
        this.put(o);
    }

    /**
     * append a buffer to the growing buffer
     * the buffer is "consumed" in the process
     *
     * @param b the buffer to be appended
     */
    public void append(AbstractRingBuffer b){

        while(!b.isEmpty()){
            this.put(b.remove());
        }
    }
}
