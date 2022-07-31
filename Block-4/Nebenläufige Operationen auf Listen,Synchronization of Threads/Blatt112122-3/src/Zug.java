class Zug extends Thread {
    int abschnitt; // der Abschnitt in dem sich der Zug befindet

    public void run() {
        while (true) {
            int next = (abschnitt + 1) % RingBahn.freieAbschnitte.length;
            synchronized (RingBahn.freieAbschnitte[next]) {
                if (RingBahn.freieAbschnitte[next]) {
                    synchronized (RingBahn.freieAbschnitte[abschnitt]) {
                        RingBahn.freieAbschnitte[next] = false;
                        System.out.println(this + " fährt weiter nach Abschnitt " + next + ".");
                        RingBahn.freieAbschnitte[abschnitt] = true;
                        abschnitt = next;
                    }
                }
                try {
                    Thread.sleep((long) (Math.random() * 10));
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public Zug(int a, String name) {
        abschnitt = a;
        super.setName(name);
    }
}

class RingBahn {
    static private final int NUM = 30;
    private static Zug[] zuege = new Zug[NUM];
    static volatile Boolean[] freieAbschnitte = new Boolean[2*NUM];
    static {
        for (int i = 0; i < NUM; i++) {
            // private static final Zug x = new Zug(1, "Zug x");
            // private static final Zug y = new Zug(3, "Zug y");
            // private static final Zug z = new Zug(5, "Zug z");
            char letter = (char)(i+ (int) 'a');
            zuege[i] = new Zug(2 * i + 1, "Zug " + letter);

            // = {true, false, true, false, true, false};
            freieAbschnitte[2*i] = true;
            freieAbschnitte[1+2*i] = false;
        }
    }



    public static void main(String[] args) {

        for (int i = 0; i < NUM; i++){
            zuege[i].start();
        }
        // y.start();
        // x.start();
        // z.start();


    }
}