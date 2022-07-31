import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Math;

public class PartikelThread extends Thread{
    Position pos;
    char mark ;
    int direction;

    static char[][] arena;

    public static void main(String[] args) throws IOException {
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);
        System.out.print("Bitte geben Sie die Breite des Feldes ein: ");
        Integer width = Integer.parseInt(br.readLine());
        System.out.print("Bitte geben Sie die Höhe des Feldes ein: ");
        Integer height = Integer.parseInt(br.readLine());
        inititialize(width, height);

        System.out.print("Bitte geben Sie Anzahl der Zufallspartikel ein: ");
        Integer count = Integer.parseInt(br.readLine());
        for (int i = 0; i < count; i++){
            (new PartikelThread( new Position(width/2,height/2), (char)((int)'a'+i))).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printToConsole();
    }

    /**
     * The constructor for a particle.
     *
     * @param p specifies the initial position.
     * @param c
     */
    public PartikelThread(Position p, char c) {
        direction = 90;
        pos = p;
        mark = c;
    }

    /**
     * getter for the x-position
     *
     * @return the x-position of the particle
     */
    int getX() {
        return pos.x;
    }

    /**
     * getter for the y-position
     *
     * @return the y-position of the particle
     */
    int getY() {
        return pos.y;
    }

    /**
     * the move method changes the direction and the position
     * with equal probability (50%)
     *
     * @return the updated position of the particle (possibly identical to the old one)
     */
    Position move() {
        int rand = (int) (2 * Math.random());
        switch (rand) {
            case 0:
                direction = (direction + 270) % 360;
                break;
            case 1:
                pos.x += deltaX(direction);
                pos.y += deltaY(direction);
                break;
            default:
                System.out.println("Oh no, something went wrong");
                return null;
        }

        return new Position(pos.x, pos.y);
    }

    /**
     * initialize sets up the arena in which the particle will run around
     *
     * @param width  the width of the arena
     * @param height the height of the arena
     */
    static void inititialize(int width, int height) {
        arena = new char[height][width];
        inititalizeWithChar(arena, ' ');

    }



    /**
     * printToConsole outputs the arena to the console
     */
    static void printToConsole() {
        for (int j = 0; j < arena[0].length+2; j++)
            System.out.print("-");
        System.out.println("");

        for (int i = arena.length-1; i >= 0; i--) {
            System.out.print("|");
            System.out.print(arena[i]);
            System.out.println("|");
        }

        for (int j = 0; j < arena[0].length+2; j++)
            System.out.print("-");
        System.out.println("");
    }

    /**
     * deltaX computes the change of the x-coordinate obtained by going
     * one step in the given direction
     *
     * @param degree a direction in degree
     * @return the change of the x-coordinate that results from going one step into direction degree
     */
    static int deltaX(int degree) {
        if (degree == 0) {
            return 1;
        }
        if (degree == 180) {
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
    static int deltaY(int degree) {
        if (degree == 90) {
            return 1;
        }
        if (degree == 270) {
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
    static void inititalizeWithChar(char[][] a, char c) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = c;
            }
        }
    }

        /**
     * run() lets the ant run
     */
     public void run() {
        int tmpx;
        int tmpy;
        while (this.getX() != -1 && this.getY() != -1 && this.getX() != arena[0].length && this.getY() != arena.length) {
            tmpx = this.getX();
            tmpy = this.getY();
            arena[tmpy][tmpx] = mark;
            Position p = this.move();
            try {

                arena[p.y][p.x] = Character.toUpperCase(mark);
            } catch (ArrayIndexOutOfBoundsException e) {
                arena[tmpy][tmpx] = Character.toUpperCase(mark);
            }
        }
    }
}

class Position {
    int x;
    int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}