import Prog1Tools.IOTools;

public class TaschenrechnerSwitchCase {
    public static void main(String[] args) {
        System.out.println("Dies ist ein primitiver Taschenrechner.");
        int operand1 = IOTools.readInt("Bitte eine Zahl eingeben:");
        int operand2 = IOTools.readInt("Bitte eine weitere Zahl eingeben:");
        int ergebnis = 0;
        char operator = IOTools
                .readChar("Bitte eien Operator in der Menge {+,-,*,/, %} eingeben");

        switch (operator) {
            case '+':
                ergebnis = operand1 + operand2;
                System.out.println("Et voilà:\n" + operand1 + operator + operand2 + "="
                        + ergebnis);
                break;
            case '-':
                ergebnis = operand1 - operand2;
                System.out.println("Et voilà:\n" + operand1 + operator + operand2 + "="
                        + ergebnis);
                break;
            case '*':
                ergebnis = operand1 * operand2;
                System.out.println("Et voilà:\n" + operand1 + operator + operand2 + "="
                        + ergebnis);
                break;
            case '/':
                ergebnis = operand1 / operand2;
                System.out.println("Et voilà:\n" + operand1 + operator + operand2 + "="
                        + ergebnis);
                break;
            case '%':
                ergebnis = operand1 % operand2;
                System.out.println("Et voilà:\n" + operand1 + operator + operand2 + "="
                        + ergebnis);
                break;
            default:
                System.out.println("Ungültiger Operator: " + operator);
                break;
        }

    }

}
