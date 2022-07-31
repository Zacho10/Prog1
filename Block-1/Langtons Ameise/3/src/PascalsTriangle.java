import Prog1Tools.IOTools;

public class PascalsTriangle {
    public static void main(String[] args) {

        byte num = IOTools.readByte("Bitte eine natürliche Zahl größer 0 eingeben:");
        int[][] triangle = new int[num][];
        printPascalTriangleUpToRowAndIndex(triangle,num-1,num-1);
        /*
        char[][] convertedTriangle = new char[triangle.length][];

        for(int i=0;i < triangle.length; i++){
            for(int j =0; j < triangle[i].length; j++){
                if (j==0) convertedTriangle[i] = new char[triangle[i].length];
                convertedTriangle[i][j] = (char) (48+triangle[i][j]);
            }
        }
        InhomogeneousArrays.printCompactly(convertedTriangle);
        */
    }

    /**
     * printPascalTriangleUpToRowAndIndex prints Pascal's triangle up to row and index
     *
     * @param partialTriangle a triangular array of the shape of Pascal's triangle, initialized in rows
     * @param row the row to fill
     * @param index the index in the row until which to fill
     */
    public static void printPascalTriangleUpToRowAndIndex(int[][] partialTriangle, int row, int index) {

        if (row == 0){
            partialTriangle[0] = new int[1];
            partialTriangle[0][0] = 1;
            System.out.println(1);
            return;
        } else {
            assert(row > 0);
            if (index > 0) {

                printPascalTriangleUpToRowAndIndex(partialTriangle,row,index-1);
                if (index == partialTriangle[row].length-1){
                    partialTriangle[row][index] = 1;
                    System.out.println(1);
                    return;
                } else{
                    assert(index>0 && index < partialTriangle[row].length-1);
                    partialTriangle[row][index] = partialTriangle[row-1][index]+ partialTriangle[row-1][index-1];
                    System.out.print(partialTriangle[row][index]+" ");
                    return;
                }
            } else {
                assert(index == 0);
                printPascalTriangleUpToRowAndIndex(partialTriangle,row-1,row-1);
                partialTriangle[row] = new int[partialTriangle[row-1].length+1];
                partialTriangle[row][index] =1;
                System.out.print(partialTriangle[row][index]+" ");
                return;
            }
        }
    }
}
