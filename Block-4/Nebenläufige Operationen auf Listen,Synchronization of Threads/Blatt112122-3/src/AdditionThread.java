public class AdditionThread extends Thread{
    static int x = 0;
    static boolean blocked = false;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new AdditionThread().start();
        }
        System.out.println(x);
    }

    @Override
    public void run() {
        boolean done = false;
        while (!done) {
            if (blocked) {
                Thread.yield();
            }
            if (!blocked) {
                blocked = true;
                x++;
                blocked = false;
                done = true;
            }
        }
    }
}