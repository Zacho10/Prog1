import Prog1Tools.IOTools;

public class HalloWeltWerkzeuge {
    public static void main(String[] args) {
        String name = IOTools.readString("Bitte geben Sie Ihren Nachnamen ein: ");
        String vorname = IOTools.readString("Bitte geben Sie Ihren Vornamen ein: ");
        String anrede = IOTools.readString("Bitte geben Sie die von Ihnen gewünschte Anrede ein: ");
        System.out.println("\n\nHallo " + anrede + " "+ name + ", oder darf ich Du sagen " + vorname + "☺?");
    }
}
