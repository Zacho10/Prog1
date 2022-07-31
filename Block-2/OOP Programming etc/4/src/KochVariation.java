public class KochVariation {
    public static void main(String[] args) {
        System.out.println(computeIteration(3));
        drawTurtleString(computeIteration(3));
    }

    static String computeIteration(int n) {
        switch (n) {
            case 0:
                return "F";
            default:
                if (n < 0) {
                    return "";
                } else {
                    StringBuffer res = new StringBuffer(computeIteration(n - 1));
                    for (int i = res.length() - 1; i >= 0; i--) {
                        String expansion;
                        Character current = res.charAt(i);
                        switch (current) {
                            case 'F':
                                expansion = "F+F-F-F+F";
                                break;
                            default:
                                expansion = current.toString();
                        }
                        res.replace(i, i + 1, expansion);
                    }
                    return res.toString();
                }
        }
    }

    static class Box {
        boolean n = false;
        boolean s = false;
        boolean w = false;
        boolean e = false;
    }

    static void drawTurtleString(String s) {
        int dir = 0;
        int x = 0;
        int y = 0;
        int maxX = 0;
        int maxY = 0;
        int minX = 0;
        int minY = 0;
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            switch (current) {
                case 'F':
                    x += ZufallsPartikel.deltaX(dir);
                    y += ZufallsPartikel.deltaY(dir);
                    break;
                case '-':
                    dir = (dir + 270) % 360;
                    break;
                case '+':
                    dir = (dir + 90) % 360;
                    break;
                default:
                    System.out.println("Something went wrong for " + current);
            }
            maxX = Math.max(x, maxX);
            maxY = Math.max(y, maxY);
            minX = Math.min(x, minX);
            minY = Math.min(y, minY);
        }
        int height = 1 + maxY - minY;
        int width = 1 + maxX - minX;
        char[][] canvas = new char[height][width];
        Box[][] prep = new Box[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                prep[i][j] = new Box();
            }
        }
        // https://unicode-table.com/en/blocks/box-drawing/
        x = -minX;
        y = -minY;
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            switch (current) {
                case 'F':
                    switch (dir) {
                        case (0):
                            prep[height - 1 - y][x].e = true;
                            prep[height - 1 - y][x + 1].w = true;
                            break;
                        case (90):
                            prep[height - 1 - y][x].n = true;
                            prep[height - 1 - y - 1][x].s = true;
                            break;
                        case (180):
                            prep[height - 1 - y][x].w = true;
                            prep[height - 1 - y][x - 1].e = true;
                            break;
                        case (270):
                            prep[height - 1 - y][x].s = true;
                            prep[height - 1 - y + 1][x].n = true;
                            break;
                    }
                    x += ZufallsPartikel.deltaX(dir);
                    y += ZufallsPartikel.deltaY(dir);
                    break;
                case '-':
                    dir = (dir + 270) % 360;
                    break;
                case '+':
                    dir = (dir + 90) % 360;
                    break;
                default:
                    System.out.println("Something went wrong later.");
            }
            // System.out.println("Position x,y is " + x + "," + y);

        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (prep[i][j].n) {
                    // prep[i][j].n
                    if (prep[i][j].e) {
                        // prep[i][j].n & prep[i][j].e
                        if (prep[i][j].s) {
                            // prep[i][j].n & prep[i][j].e & prep[i][j].s
                            if (prep[i][j].w) {
                                // prep[i][j].n & prep[i][j].e & prep[i][j].s & prep[i][j].w
                                canvas[i][j] = '┼';
                            } else {
                                // prep[i][j].n & prep[i][j].e & prep[i][j].s & ¬prep[i][j].w
                                canvas[i][j] = '├';
                            }
                        } else {
                            // prep[i][j].n & prep[i][j].e & ¬prep[i][j].s
                            if (prep[i][j].w) {
                                // prep[i][j].n & prep[i][j].e & ¬prep[i][j].s & prep[i][j].w
                                canvas[i][j] = '┴';
                            } else {
                                // prep[i][j].n & prep[i][j].e & ¬prep[i][j].s ¬prep[i][j].w
                                canvas[i][j] = '└';
                            }
                        }
                    } else {
                        // prep[i][j].n & ¬prep[i][j].e
                        if (prep[i][j].s) {
                            // prep[i][j].n & ¬prep[i][j].e & prep[i][j].s
                            if (prep[i][j].w) {
                                // prep[i][j].n & ¬prep[i][j].e & prep[i][j].s & prep[i][j].w
                                canvas[i][j] = '┤';
                            } else {
                                // prep[i][j].n & ¬prep[i][j].e & prep[i][j].s & ¬prep[i][j].w
                                canvas[i][j] = '│';
                            }
                        } else {
                            // prep[i][j].n & ¬prep[i][j].e & ¬prep[i][j].s
                            if (prep[i][j].w) {
                                // prep[i][j].n & ¬prep[i][j].e & ¬prep[i][j].s & prep[i][j].w
                                canvas[i][j] = '┘';
                            } else {
                                // prep[i][j].n & ¬prep[i][j].e & ¬prep[i][j].s & ¬prep[i][j].w
                                canvas[i][j] = '╵';
                            }
                        }
                    }
                } else {
                    // ¬prep[i][j].n
                    if (prep[i][j].e) {
                        // ¬prep[i][j].n & prep[i][j].e
                        if (prep[i][j].s) {
                            // ¬prep[i][j].n & prep[i][j].e & prep[i][j].s
                            if (prep[i][j].w) {
                                // ¬prep[i][j].n & prep[i][j].e & prep[i][j].s & prep[i][j].w ┬
                                canvas[i][j] = '┬';
                            } else {
                                // ¬prep[i][j].n & prep[i][j].e & prep[i][j].s & ¬prep[i][j].w ┌
                                canvas[i][j] = '┌';
                            }
                        } else {
                            // ¬prep[i][j].n & prep[i][j].e & ¬prep[i][j].s
                            if (prep[i][j].w) {
                                // ¬prep[i][j].n & prep[i][j].e & ¬prep[i][j].s & prep[i][j].w ─
                                canvas[i][j] = '─';
                            } else {
                                // ¬prep[i][j].n & prep[i][j].e & ¬prep[i][j].s & ¬prep[i][j].w ╶
                                canvas[i][j] = '╶';
                            }
                        }
                    } else {
                        // ¬prep[i][j].n & ¬prep[i][j].e
                        if (prep[i][j].s) {
                            // ¬prep[i][j].n & ¬prep[i][j].e & prep[i][j].s
                            if (prep[i][j].w) {
                                // ¬prep[i][j].n & ¬prep[i][j].e & prep[i][j].s & prep[i][j].w ┐
                                canvas[i][j] = '┐';
                            } else {
                                // ¬prep[i][j].n & ¬prep[i][j].e & prep[i][j].s & ¬prep[i][j].w ╷
                                canvas[i][j] = '╷';
                            }
                        } else {
                            // ¬prep[i][j].n & ¬prep[i][j].e & ¬prep[i][j].s
                            if (prep[i][j].w) {
                                // ¬prep[i][j].n & ¬prep[i][j].e & ¬prep[i][j].s & prep[i][j].w ╴
                                canvas[i][j] = '╴';
                            } else {
                                // ¬prep[i][j].n & ¬prep[i][j].e & ¬prep[i][j].s & ¬prep[i][j].w ' '
                                canvas[i][j] = ' ';
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i< canvas.length; i++){
            for (int j = 0; j < canvas[0].length; j++){
                System.out.print(canvas[i][j]);
            }
            System.out.println("");

        }
    }
}


