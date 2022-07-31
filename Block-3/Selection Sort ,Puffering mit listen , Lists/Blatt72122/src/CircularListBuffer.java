public class CircularListBuffer {

    private ListEntry first; // first *free* (or equal to last if full)
    private ListEntry last; // last *full*

    public CircularListBuffer(int capacity){
        if (capacity > 0){
            ListEntry lastCreated = new ListEntry();
            first = lastCreated;
            for(int i = 1; i <capacity; i++){
                lastCreated.next = new ListEntry();
                lastCreated = lastCreated.next;
            }
            lastCreated.next = first;
            last = null; // buffer is empty
        } else {
            throw new IllegalArgumentException("Capacity should be positive.");
        }
    }

    public boolean isEmpty(){
        return (last == null);
    }

    public boolean isFull(){
        return (first == last);
    }

    public Object remove(){
        Object res = null;
        if (last != null){
            res = last.value;
            if (last.next == first){
                last = null;
            } else {
                last = last.next;
            }
        }
        return res;
    }

    public boolean put(Object o){
        if (isFull()){
            return false;
        } else {
            if(isEmpty()){
                last = first;
            }
            // System.out.println("putting "+o);
            first.value = o;
            first = first.next;
            if (first == null){
                throw new RuntimeException("yikes");
            }
            return true;
        }
    }

    public String toString(){
        String res = "";
        if (isEmpty()){
            res = "[]";
        } else {
            res = "[";
            ListEntry tmp = last;

            while(tmp.next!= first){
                // System.out.println("now strining"+tmp.value);
                res = res + tmp.value.toString()+ ", ";
                tmp = tmp.next;
            }
            res = res + tmp.value.toString()+"]";

        }
        return res;
    }

    public static void main(String[] args) {
        CircularListBuffer cb = new CircularListBuffer(15);
        int[] someInts = {4, 7, 5, 8, 32, -10, 0, 1, 1};
        for (int i : someInts) {
            cb.put(i);
        }
        System.out.println("Der Pufferinhalt ist: " + cb + ".");

    }


}
