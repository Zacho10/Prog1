public class List<T> {
    private volatile ListEl<T> head;
    public ListEl<T> getHead() {
        return head;
    }
    public void setHead(ListEl<T> head) {
        this.head = head;
    }

    public List(){
        head = null;
    }

    public String toString(){
        if (head == null){
            return "[]";
        } else {
            return "[" + head.toString();
        }
    }


    public static void main(String[] args) {
        int count = 20;
        List<Integer> l = new List();
        // l.setHead(new ListEl<Integer>(1,null));
        Thread[] inserters = new Thread[count];
        Thread[] nibblers = new Thread[count];
        Thread thread = null;
        for (int i = 0; i < count; i++) {
            inserters[i] = new Thread(new ListInserter(l,null));
            // thread = inserters[i];
        }
        for (int i = 0; i < count; i++) {
            // inserters[i] = new Thread(new ListInserter(l,thread));
            //   inserters[i] = new Thread(new ListInserter(l,null));
            nibblers[i] = new Thread(new ListNibbler(l, inserters[i]));
        }
        for (int i = 0; i < count; i++) {
            inserters[i].start();
            nibblers[i].start();
        }

            try {
                inserters[count-1].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        synchronized (l) {
            System.out.println(l);
        }

        for (int i = 0; i < count; i++) {


        try {
            nibblers[i].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        }

        synchronized (l) {
            System.out.println(l);
        }
    }
}

class ListEl<T>{
    T val;
    ListEl<T> next;

    ListEl(T v, ListEl<T> n){
        val = v;
        next = n;
    }

    @Override
    public String toString(){
        String res = "" + val;
        if (next==null)
            res = res +"]";
        else
            res = res + ", " + next;
        return res;
    }
}
