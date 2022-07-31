import java.util.LinkedList;
import java.util.List;

public class FlattenSolution{

    public static List listFlatten(List t){
        List res = new LinkedList();

        try {
            for (Object x : t) {
                System.out.println(x);
                try {
                    res.addAll(listFlatten((List) x));
                } catch (ClassCastException e) {
                    res.add(x);
                }
            }
        } catch (NullPointerException e){
            res.add(t);
        }
        return res;
    }

    public static double max(Integer[] a, int i, int j){
        int max = 0;
        for(int k= i; k<=j;k++){
            try {
                max = Math.max(max, a[k]);
            } catch (ArrayIndexOutOfBoundsException e) {
                //
            } catch (NullPointerException e){

            }
        }
        return max;
    }

    public static void main(String[] args) {
        Integer[] ints = {1,2,3};
        List l = new LinkedList();

        for(Integer i : ints){
            l.add(i);
        }
        l.add(null);
        l.add(((LinkedList<?>) l).clone());
        l.add(((LinkedList<?>) l).clone());
        l.add(((LinkedList<?>) l).clone());

        l = listFlatten(l);

        System.out.println(l);

        System.out.println(max(ints,2,4));
        System.out.println(max(ints,2,2));
        ints[2] = null;
        System.out.println(max(ints,0,2));
    }
}


