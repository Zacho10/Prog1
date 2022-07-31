public class FlattenBuffer {
    /**
     * flattening of a buffer of buffers to a buffer
     *
     * @param b a buffer of buffers of class AbstractRingBuffer
     * @return a buffer of class AbstractRingBuffer that is the concatenation of the buffers in b
     */
    static AbstractRingBuffer flatten(AbstractRingBuffer b){

        // use a growing buffer
        GrowingBuffer tmp = new GrowingBuffer(1);

        // iterate over all buffers
        for(AbstractRingBuffer i = (AbstractRingBuffer) b.remove(); i!= null ; i = (AbstractRingBuffer) b.remove()){
            // iterate over all elements
            while (!i.isEmpty()){
                // add the oldest element of the current buffer
                tmp.put(i.remove());
            }
        }

        // create the resulting buffer
        AbstractRingBuffer res = new ObjectRingBuffer(tmp.getSize());

        // transfer the elements
        while(!tmp.isEmpty()){
            res.put(tmp.remove());
        }

        return res;
    }
}
