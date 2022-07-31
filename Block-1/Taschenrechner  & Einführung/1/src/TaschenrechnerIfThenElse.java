import Prog1Tools.IOTools;

public class TaschenrechnerIfThenElse {
    public static void main(String[] args) {
        System.out.println("Dies ist ein primitiver Taschenrechner.");
        int operand1 = IOTools.readInt("Bitte eine Zahl eingeben:");
        int operand2 = IOTools.readInt("Bitte eine weitere Zahl eingeben:");
        int ergebnis = 0;
        char operator = IOTools
                .readChar("Bitte einen der Operatoren in der Menge {+,-,*,/, %} eingeben:");
        if (operator == '+') {
            ergebnis = operand1 + operand2;
            System.out.println("Et voilà:\n" + operand1 + operator + operand2 + "="
                    + ergebnis);
        } else if (operator == '-') {
            ergebnis = operand1 - operand2;
            System.out.println("Et voilà:\n" + operand1 + operator + operand2 + "="
                    + ergebnis);
        } else if (operator == '*') {
            ergebnis = operand1 * operand2;
            System.out.println("Et voilà:\n" + operand1 + operator + operand2 + "="
                    + ergebnis);
        } else if (operator == '/') {
            ergebnis = operand1 / operand2;
            System.out.println("Et voilà:\n" + operand1 + operator + operand2 + "="
                    + ergebnis);
        } else if (operator == '%') {
            ergebnis = operand1 % operand2;
            System.out.println("Et voilà:\n" + operand1 + operator + operand2 + "="
                    + ergebnis);
        } else {
            System.out.println("Ungültiger Operator: " + operator);
        }

    }

}
