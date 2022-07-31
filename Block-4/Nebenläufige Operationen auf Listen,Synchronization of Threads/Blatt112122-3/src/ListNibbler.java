import static java.lang.Thread.sleep;

public class ListNibbler implements Runnable {
    private List<Integer> list;
    private Thread inserter;

    ListNibbler(List<Integer> l, Thread t) {
        list = l;
        inserter = t;
    }

    @Override
    public void run() {
        try {
            inserter.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Boolean done = false;
        while (!done) {
            // System.out.println(this + " is looking to nibble.");
            int i = 0;
            synchronized (list) {
                // System.out.println(this + "has locked the list "+list);
                for (ListEl<Integer> el = list.getHead(); el != null ; el = el.next) {
                    // System.out.println(this + "is now at pos" + i++);
                    Integer randomInt = (int) (1.3 * Math.random());
                    switch (randomInt) {
                        case (0):
                            if (list.getHead().next == null){
                                list.setHead(null);
                                done = true;
                            }
                            break;
                        case (1):
                            //synchronized (el)
                            {
                                try {
                                    //synchronized (el.next)
                                    {
                                        System.out.println(this +" eating " + el.next.val);
                                        el.next = el.next.next;
                                        done = true;
                                    }
                                } catch (NullPointerException e) {
                                    System.out.println(this +" eating " +list.getHead().val);
                                    list.setHead(list.getHead().next);
                                    done = true;
                                }
                            }
                    }
                    if (randomInt == 1)
                        break;
                }
            }
        }
    }
}