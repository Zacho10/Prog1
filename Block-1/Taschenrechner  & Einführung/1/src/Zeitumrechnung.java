import Prog1Tools.IOTools;

public class Zeitumrechnung {
    public static void main(String[] args) {
        long sec = IOTools
                .readLong("Geben Sie einen Zeitpunkt in Sekunden an, " +
                        "nach dem dem 1. Januar 1970: ");
        long y = sec / (60 * 60 * 24 * 365);
        long yearsSec = sec % (60 * 60 * 24 * 365);
        long d = yearsSec / (60 * 60 * 24);

        System.out.println("Das ist/sind: " + y + " Jahr(e) und " + d
                + " Tag(e) nach dem 1. Januar 1970.");

        /*
        // btw
        long epoch = System.currentTimeMillis()/1000; // Returns epoch in seconds.
        System.out.println("Jetzt ist übrigens die Unixzeit folgende: " + epoch);
         */

        String weekday = "";
        d = sec / (60 * 60 * 24); // wichtig ! 365 Tage sind nur ungefähr
        byte day = (byte) ((3L + d) % 7);

        switch (day) {
            case 0:
                weekday = "Montag";
                break;
            case 1:
                weekday = "Dienstag";
                break;
            case 2:
                weekday = "Mittwoch";
                break;
            case 3:
                weekday = "Donnerstag";
                break;
            case 4:
                weekday = "Freitag";
                break;
            case 5:
                weekday = "Samstag";
                break;
            case 6:
                weekday = "Sonntag";
                break;
        }

        System.out.println("Der Wochentag dieser Sekunde ist " + weekday + ".");

        /* Start the moon cycle, 0%, at **full** moon */
        /* 50% is new moon */

        double moonCycleProgress = (100 - 47.24) / 2; // in % of the moon cycle

        moonCycleProgress = (moonCycleProgress + ((d / 29.53) / 2)) % 100;

        String phase = "";
        if (moonCycleProgress <= 50) {
            phase = "abnehmend";
        } else {
            phase = "zunehmnend";
        }
        System.out.println("Der Mond ist " + phase + ".");
    }
}
