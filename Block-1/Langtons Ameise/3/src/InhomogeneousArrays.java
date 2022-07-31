public class InhomogeneousArrays {
    public static void main(String[] args) {
        char[][] theMessages =
                {
                        {'t', 'o', 'b', 'i', 'a', 's'},
                        {'h', 'e', 'i', 'n', 'd', 'e', 'l'},
                        {'1', '2', '1', '6', '3'}
                };
        System.out.println(makeIntoOneLine(theMessages));

        // printVerbosely(theMessages);
        mirror(theMessages);
        // printVerbosely(theMessages);
        System.out.println(makeIntoOneLine(theMessages));
        swapRows(theMessages,0,1);
        System.out.println(makeIntoOneLine(theMessages));
        upsideDown(theMessages);
        // printVerbosely(theMessages);
        System.out.println(makeIntoOneLine(theMessages));
    }

    /**
     * swapRows exchanges two rows of an array, given by two indices
     *
     * @param msgs a char[]-array
     * @param i    the index of a row
     * @param j    the index of the second row
     */
    public static void swapRows(char[][] msgs, int i, int j) {
        char[] tmp;
        tmp = msgs[i];
        msgs[i] = msgs[j];
        msgs[j] = tmp;
    }

    /**
     * upsideDown reverses the order of the rows in the char[][]
     *
     * @param msgs the char[]-array whose order will be reversed
     */
    public static void upsideDown(char[][] msgs) {

        for (int i = 0; i < msgs.length / 2; i++) {
            swapRows(msgs, i, msgs.length - 1 - i);
        }
    }

    /**
     * printCompactly takes a char[]-Array and writes each row in a line
     * as if it was a string of characters
     *
     * @param msgs an array of char[]s to be printed
     */
    public static void printCompactly(char[][] msgs) {
        for (int i = 0; i < msgs.length; i++) {
            System.out.println(msgs[i]);
        }
    }

    /**
     * printVerbosely takes a char[]-Array and writes the corresponding constant
     *
     * @param msgs an array of char[]s to be printed
     */
    public static void printVerbosely(char[][] msgs) {
        System.out.println("{");
        for (int i = 0; i < msgs.length; i++) {
            System.out.print("  {");
            for (int j = 0; j < msgs[i].length; j++) {
                System.out.print("'" + msgs[i][j] + "'");
                if (j + 1 < msgs[i].length) System.out.print(",");
            }
            System.out.print("}");
            if (i + 1 < msgs.length) System.out.print(",");
            System.out.println();
        }
        System.out.println("}");
    }

    /**
     * makeIntoOneLine provides a "flattening" of a char[][] into a String,
     * separating the rows by spaces
     *
     * @param msgs an array of char arrays
     * @return the concatenation of all char arrays
     */
    public static String makeIntoOneLine(char[][] msgs) {
        int size = 0;
        for (int i = 0; i < msgs.length && msgs != null; i++) {
            size += msgs.length;
        }
        String res = "";
        int index = 0;
        for (int i = 0; i < msgs.length && msgs != null; i++) {
            for (int j = 0; j < msgs[i].length; j++) {
                res = res + msgs[i][j];
            }
            if (i + 1 < msgs.length) res = res + " ";
        }
        return res;
    }

    public static void mirror(char[][] msgs) {
        for (int i = 0; i < msgs.length; i++) {
            for (int j = 0; j < msgs[i].length/2; j++) {
                char tmp = msgs[i][j];
                msgs[i][j] = msgs[i][msgs[i].length-1-j];
                msgs[i][msgs[i].length-1-j] = tmp;
            }
        }
    }
}
