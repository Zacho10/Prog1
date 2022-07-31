abstract public class AbstractRingBuffer {

    private int frei;  // the "last" *free* cell (typically just before the "oldest" element)
    private int neueste; // most recently *used* cell (unless empty)
    private int groesse; // size of the ring
    private boolean full; // true if, and only if, all cells are used

    /**
     * initialize the common variables
     * according to the implementation on
     * the level of the abstract class
     * @param groesse the size of the buffer >= 1
     */
    protected AbstractRingBuffer(int groesse){
        this.groesse = groesse;
        frei = 0;
        neueste = 0;
        full = false;
    }

    /**
     * check for emptiness
     * @return the truth value of "this buffer is empty"
     */
    public boolean isEmpty(){
        return frei == neueste && !full;
    }

    /**
     * check for fullness
     */
    public boolean isFull(){ return full; }


    /**
     * helper method for freeing the oldest cell
     */
    private void freeOldest(){
        frei++;
        frei = frei % groesse;
        full = false;
    }

    /**
     * helper method for advancing the index of the newest element
     */
    private void updateNeueste(){
        neueste++;
        neueste = neueste % groesse;
        if (neueste==frei) full=true;
    }

    /**
     * write an object at the cell of the index
     * @param o the object
     * @param index the index
     */
    abstract protected void writeToIndex(Object o, int index);

    /**
     * read an object at the cell of the index (if any)
     * @param index the index
     */
    abstract protected Object readFromIndex(int index);

    /**
     * put a new object into the buffer (if capacity admits)
     * @param o the new object to be put
     */
    public void put(Object o){
        if (!full){
            updateNeueste();
            writeToIndex(o, neueste);
        } else {
            System.out.println("Leider kein Platz mehr!");
        }
    }

    /**
     * remove the "oldest" object from the buffer
     * @return the "oldest" object (if any) or null (if none)
     */
    public Object remove(){
        if (isEmpty()) {
            System.out.println("Leider keine Elemente vorhanden!");
            return null;
        } else {
            // the following two lines should be atomic in a concurrent setting
            freeOldest();
            return readFromIndex(frei);
        }
    }

    public int getSize(){
        return this.groesse;
    }
}
