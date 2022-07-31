import java.lang.Math;

import static java.lang.Thread.sleep;

public class ListInserter implements Runnable{
    private List<Integer> list;
    Thread t;

    ListInserter(List<Integer> l, Thread thread){
        list = l;
        t = thread;
    }

    @Override
    public void run() {
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            // hack (avoid t==null check)
            // System.out.println(this+ "had nothing to wait for.");
        }
        Integer randomInt = (int)(100 * Math.random());
        ListEl<Integer> el = new ListEl<>(randomInt, null);
        // System.out.println(this+ "wants to instert" + randomInt);
        synchronized (list){
            ListEl<Integer> elm = list.getHead();
            if (elm != null) {
                while (elm.next != null) {
                    elm = elm.next;
                };
                // synchronized (prev)
                {
                    elm.next = el;
                }
            } else {
                list.setHead(el);
            }
        }
        System.out.println(this+" insterted "+ randomInt+".");
    }
}
