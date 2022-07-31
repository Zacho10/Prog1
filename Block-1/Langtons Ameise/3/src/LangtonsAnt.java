public class LangtonsAnt {

    public static void main(String[] args) {
        // the current direction of the ant
        int direction = 0;
        // the "ground" on which the ant walks
        char[][] arena = new char[100][100];

        inititalizeWithChar(arena, ' ');
        initializeRandomCoreSquare(arena,10);

        InhomogeneousArrays.printCompactly(arena);

        int x=arena.length/2, y=arena[0].length/2;
        int round = 0;
        while ( x !=-1 && y !=-1 && x!=arena.length &&  y!=arena[arena.length-1].length){
            switch (arena[x][y]){
                case ' ':
                    arena[x][y]='X';
                    direction = (direction + 90) % 360 ;
                    break;
                case 'X':
                    arena[x][y]=' ';
                    direction = (direction + 270) % 360 ;
                    break;
                default:
                    System.out.println("Oh no, something went wrong");
                    return;
            }
            x = x + deltaX(direction);
            y = y + deltaY(direction);
            round++;
            if(round%100 == 0){
                InhomogeneousArrays.printCompactly(arena);
            }
        }
        System.out.println("-----");
        InhomogeneousArrays.printCompactly(arena);
        System.out.println("-----");

    }

    /**
     * deltaX computes the change of the x-coordinate obtained by going
     * one step in the given direction
     *
     * @param degree a direction in degree
     * @return the change of the x-coordinate that results from going one step into direction degree
     */
    static int deltaX(int degree){
        if (degree == 0){
            return 1;
        }
        if (degree == 180){
            return -1;
        }
        return 0;
    }


    /**
     * deltaY computes the change of the y-coordinate obtained by going
     * one step in the given direction
     *
     * @param degree a direction in degree
     * @return the change of the y-coordinate that results from going one step into direction degree
     */
    static int deltaY(int degree){
        if (degree == 90){
            return 1;
        }
        if (degree == 270){
            return -1;
        }
        return 0;
    }

    /**
     * intitializeWithChar writes the given char in all array cells
     *
     * @param a the char array to initialize with
     * @param c a character
     */
    static void inititalizeWithChar(char[][] a, char c){
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[i].length;j++){
                a[i][j] = c;
            }
        }
    }

    /**
     * initializeRandomCoreSquare initializes a square in the middle of
     * a given rectangular array whose sides are given by the second parameter
     * the two options for the character being ' ' or 'X'
     *
     * @param a a rectangular array of char values
     * @param width the width of the square to be initialized randomly
     */
    static void initializeRandomCoreSquare(char[][] a, int width){
        int upperRow = a.length/2 - width/2;
        int leftColumn = a[upperRow].length/2 - width/2;
        for(int i=0;i<width;i++){
            for(int j=0;j<width;j++){
                if (Math.random() <0.5){
                    a[i+upperRow][j+leftColumn] = ' ';
                } else {
                    a[i+upperRow][j+leftColumn] = 'X';
                }
            }
        }

    }
}
