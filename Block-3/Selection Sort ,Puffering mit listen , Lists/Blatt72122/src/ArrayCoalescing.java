public class ArrayCoalescing {

    private static Boolean lt(Integer i, Integer j){
        if (i == null || j == null ){
            return i!=null;
        } else {
            return i < j;
        }

    }

    /**
     * assumption
     * @param x
     * @return
     */
    public static SinglyLinkedList merge(SinglyLinkedList[] x){
        SinglyLinkedList res = new SinglyLinkedList();


        while(true) {
            Integer min = null;
            Integer minIndex = null;
            for (int i = 0; i < x.length; i++) {
                Integer theInt = (Integer) x[i].getValue(0);
                if (lt(theInt, min)) {
                    minIndex = i;
                    min = theInt;
                }
            }
            if (min != null) {
                res.append(min);
                x[minIndex].deleteValue(min);
            } else {
                break;
            }
        }

        return res;
    }


    private static int computeTotalLength(SinglyLinkedList l){
        int length = 0;

        for (int i = 0; l.getValue(i) != null; i++) {
            length += ((int[]) (l.getValue(i))).length;
        }

        return length;

    }
    private static int computeLength(SinglyLinkedList l) {
        int length = 0;
        for (int i = 0; l.getValue(i) != null; i++) {
            length ++;
        }
        return length;
    }


    // totally minimal barely acceptable solution for the "stupid" exercise

    public static int[] totalMerge(SinglyLinkedList l) {
        int[] res = null;
        // the length of the new array
        res = new int[computeTotalLength(l)];

        int theIndex = 0;
        for (int i = 0; l.getValue(i) != null; i++) {
            int[] x = (int[]) (l.getValue(i));
            for (int j = 0; j < x.length; j++) {
                res[theIndex++] = x[j];
            }
        }

        selectionSort(res);
        return res;
    }

    /**
     * see https://www.javatpoint.com/selection-sort-in-java
     * @param arr
     */
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[index]) {
                    index = j;//searching for lowest index
                }
            }
            int smallerNumber = arr[index];
            arr[index] = arr[i];
            arr[i] = smallerNumber;
        }
    }

    public static void main(String[] args) {
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
        System.out.println("merged" + merge(x));
    }
}
