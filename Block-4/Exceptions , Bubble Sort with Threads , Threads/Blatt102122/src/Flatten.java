import java.util.LinkedList;
import java.util.List;

public class Flatten {
    public static List listFlatten(List t) {
        List res = new LinkedList();

        for (Object x : t) {
            System.out.println(x);

            res.addAll(listFlatten((List) x));
        }

        return res;
    }

    public static double max(Integer[] a, int i, int j) {
        int max = 0;
        for (int k = i; k <= j; k++) {
            max = Math.max(max, a[k]);
        }
        return max;
    }

    public static void main(String[] args) {
        Integer[] ints = {1, 2, 3};
        List l = new LinkedList();

        for (Integer i : ints) {
            l.add(i);
        }
        l.add(null);
        l.add(((LinkedList<?>) l).clone());

        l = listFlatten(l);

        System.out.println(l);

        System.out.println(max(ints, 2, 4));
        System.out.println(max(ints, 2, 2));
        ints[2] = null;
        System.out.println(max(ints, 0, 2));
    }
}


