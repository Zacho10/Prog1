

public class SinglyLinkedList {

    /** head of the list */
    private ListEntry head;

    /**
     * flattens the list into a list of Objects,
     * assuming that values that are List Entries are "heads"
     * (of the next lower list level)
     */
    public void flatten(){
        if (head != null) {
            if (head.value == null) {
                this.deleteValue("null");
                this.flatten();
            } else {
                // head.value != null and ...
                // we are going to build a new list in two different ways
                SinglyLinkedList l;
                if (head.value.getClass() == SinglyLinkedList.class){
                    l = (SinglyLinkedList) head.value;
                    this.deleteValue(l);
                    l.flatten();
                    this.flatten();
                    l.appendList(this);
                    this.head = l.head;
                } else {
                    Object tmp = head.value;
                    this.deleteValue(tmp);
                    this.flatten();
                    this.insertAtHead(tmp);
                }
            }
        } else {
            // nothing to do
        }
    }


    public void appendList(SinglyLinkedList l){
        ListEntry tmp = new ListEntry();
        tmp.next = head;
        while(tmp.next != null){
            tmp=tmp.next;
        }
        tmp.next=l.head;
    }



//	private ListEntry lastEntry;

    /**
     * inserts a values at the end of the list
     *
     * @param value
     *            value which shall be added
     */
    public void append(Object value) {
        if (head == null) {
            // insert at head
            head = new ListEntry();
            head.value = value;
            return;
        }
        // find end
        ListEntry current = head, last = head;
        while (current != null) {
            last = current;
            current = current.next;
        }
        current = new ListEntry();
        current.value = value;
        last.next = current;
        // Why do you need this last statement?
    }

    public void appendNeu(Object value){
        ListEntry neu = new ListEntry();
        neu.value = value;
        if(head==null){
            head = neu;
            return;
        }
        ListEntry temp = head;
        while(temp.next!= null) {
            temp = temp.next;
        }
        temp.next = neu;
    }

    /**
     * Get value at a specific position
     *
     * @param position
     * @return the value at position "position" or null if the list has less
     *         entries
     */
    public Object getValue(int position) {
        int counter = 0;
        ListEntry current = head;
        while (current != null && position != counter++)
            current = current.next;
        if (current == null)
            return null;
        else
            return current.value;
    }

    /**
     * deletes the specified value if contained in this list. Note: this method
     * uses reference comparison, not equals()!
     *
     * @param value
     */
    public void deleteValue(Object value) {
        if (head == null)
            return; // empty list

        if (head.value == value) {
            // it's the head element
            head = head.next;
            return;
        }

        ListEntry current = head, entryB4DelMe = head;
        while (current.next != null && current.value != value) {
            entryB4DelMe = current;
            current = current.next;
        }
        if (current.value != value) // (current.next == null)
            return; // not in this list
        entryB4DelMe.next = entryB4DelMe.next.next;

    }

    /**
     * inserts a value at the head of the list
     *
     * @param value
     *            value which shall be added
     */
    public void insertAtHead(Object value) {
        if (head == null) {
            head = new ListEntry();
            head.value = value;
            return;
        }
        ListEntry newHead = new ListEntry();
        newHead.value = value;
        newHead.next = head;
        head = newHead;
    }

    /**
     * inserts a value after the entry with the value "after" or at the end of
     * the list if the value is not contained
     *
     * @param value
     *            value which shall be added
     * @param after
     *            desired predecessor for value
     */
    public void insertAfter(Object value, Object after) {
        if (head == null) {
//			head = new ListEntry();
//			head.value = value;
            System.out.println("Empty list, cannot insert after " + after);
            return;
        }
        ListEntry newone = new ListEntry();
        newone.value = value;
        ListEntry current = head;
        while (current.next != null && current.value != after)
            current = current.next;
        if (current.value != after) {
            System.out.println("Cannot insert after " + after + ", element not found");
            return;
        }
        ListEntry temp = current.next;
        current.next = newone;
        newone.next = temp;
    }

    public void insertAfterPos(int pos, Object value) {
        if (head == null) {
            System.out.println("Empty list, cannot insert after position " + pos);
            return;
        }
        ListEntry current = head;
        ListEntry newOne = new ListEntry();
        newOne.value = value;
        if (pos == 0) {
            newOne.next = head;
            head = newOne;
            return;
        }
        for (int i = 0; i < pos; i++) {
            if (current.next == null) {
                System.out.println("Cannot insert after position " + pos + ", element not found");
                return;
            }
            current = current.next;
        }
        newOne.next = current.next;
        current.next = newOne;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        ListEntry current = head;
        if (current == null)
            return "[]";
        String res = "[";
        while (current != null && current.next != null) {
            res += current.value + ",";
            current = current.next;
        }
        res += current.value + "]";
        return res;
    }


    /**
     * selection sort the list, assuming that all Objects are Integers
     */
    public void sort(){
        // the unsorted part of the list is left implicit, we only need the first ListEntry of the unsorted list
        ListEntry firstUnsorted;
        // initialize with head the first element of the unsorted list (if any)
        for (firstUnsorted = head; firstUnsorted != null; firstUnsorted = firstUnsorted.next){
            // first find the first Element with minimum value
            ListEntry min = min(firstUnsorted);
            // swap values
            Object minVal = min.value;
            min.value = firstUnsorted.value;
            firstUnsorted.value = minVal;

            // repeat if more elements exist (following the now last element of the sorted list)

        }

    }

    /**
     * find the first listEntry with minimal value (assuming entries to be Integer objects)
     * @param x the head of the list of which the minimal element is searched for
     * @return the first ListEntry with minimal value
     */
    public static ListEntry min(ListEntry x){
        if (x == null)
            return null;
        ListEntry min = x;
        ListEntry tmp = x.next;
        while(tmp != null){
            min = ((Integer) min.value <= (Integer) tmp.value) ? min : tmp;
            tmp = tmp.next;
        }
        return min;
    }

    public void randomIntegerGrow(){
        Integer newInt;
        newInt = (int) Math.round(10000.0 * Math.random());

        if (head == null){
            head = new ListEntry();
            head.value = newInt;
        } else {
            ListEntry tmp = head;
            while(true){
                int choice = (int) Math.floor(2*Math.random());
                if (choice == 0) {
                    if (tmp.value == null) {
                        tmp.value = newInt;
                        break;
                    } else {
                        if (tmp.value.getClass() == SinglyLinkedList.class) {
                            ((SinglyLinkedList) (tmp.value)).randomIntegerGrow();
                            break;
                        } else {
                            SinglyLinkedList z = new SinglyLinkedList();
                            z.insertAtHead(tmp.value);
                            tmp.value = z;
                            int anotherChoice = (int) Math.floor(2 * Math.random());
                            if (anotherChoice == 0) {
                                z.insertAtHead(newInt);
                            } else {
                                z.append(newInt);
                            }
                            break;
                        }
                    }
                } else {
                    if (tmp.next == null){
                        tmp.next = new ListEntry();
                        tmp.next.value = newInt;
                        break;
                    } else {
                        tmp = tmp.next;
                    }

                }
            }
        }



    }


    public static void main(String[] args) {
        SinglyLinkedList sll = new SinglyLinkedList();
        int[] someInts = {4,7,5,8,32,-10,0,1,1};
        for(int i : someInts){
            sll.append(i);
        }
        System.out.println("Die Eingabeliste ist diese: " + sll +".");
        sll.sort();
        System.out.println("Sortiert sieht das so aus: " + sll +".");


        int[][] test = {
                {5,7,8,30},
                {10,20},
                {19,22,50},
                {28,35,40,45}
        };
        SinglyLinkedList[] x = new SinglyLinkedList[test.length];
        for (int i = 0; i<test.length;i++){
            x[i] = new SinglyLinkedList();
            for (int k : test[i]){
                x[i].append(k);
            }
        }

        SinglyLinkedList y = new SinglyLinkedList();
        for (int i = 0; i < test.length; i++){
            y.append(x[i]);
        }


        for(int z = 0; z <30; z++){
            y.randomIntegerGrow();
        }
        System.out.println("List mit Listen von Listen"+y);
        y.flatten();
        System.out.println("Die Flache Version"+y);






        // Questions:
        // 1. What happens if you try to get an object that doesn't exist (analogy: ArrayIndexOutOfBounds)
        // 2. What about "InsertAfter" a non-existing element?
        // 3. What if you have the same value twice, which operations behave differently from what you might expect? How do they behave?
        // --> Think about the "corner cases" in programs

        // Exercise: implement a doubly-linked list and test it
    }

}
