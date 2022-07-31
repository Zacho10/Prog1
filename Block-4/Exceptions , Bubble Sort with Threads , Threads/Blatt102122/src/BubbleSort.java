import java.util.LinkedList;
import java.util.List;

public class BubbleSort {
    static void bubbleSort(List<Integer> l) {
        int n = l.size();
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                synchronized (l) {
                    if (l.get(j) > l.get(j + 1)) {
                        // swap elements
                        l.add(j, l.remove(j + 1));
                    }
                }
    }

    static Boolean sorted(List<Integer> l) {
        Boolean res = true;
        for (int i = 0; i < l.size() - 1; res = res & (l.get(i) <= l.get(++i))) ;
        return res;
    }

    public static void main(String[] args) {
        List<Integer> x = new LinkedList<>();
        final int SIZE = 100;
        Thread[] threads = new Thread[SIZE / 10];
        do {
            for (int i = 0; i < SIZE; i++)
            x.add((int) Math.round(SIZE  * java.lang.Math.random()));

            for (int i = 0; i < threads.length; i++) {
                threads[i] = new Bubbler(x);
            }
            for (int i = 0; i < threads.length; i++) {
                threads[i].start();
            }
        } while (sorted(x));

        System.out.println("\\o/");


    }
}

class Bubbler extends Thread {

    List<Integer> x;

    public Bubbler(List<Integer> x){
        this.x = x;
    }

    public void run(){
        BubbleSort.bubbleSort(x);
        System.out.println(this+" has run.");
    }
}

