package csc402;

public class Utility {

    public static int convertTo1D(int row, int col, int width) {

        int cols = width;

        int conversionFormula1D = col + (row * cols);
        return conversionFormula1D;
    }
    public static class Pair {

        private int int1;
        private int int2;

        public Pair(int int1, int int2) {
            this.int1 = int1;
            this.int2 = int2;
        }


    }
    public static Pair convertTo2D(int loc, int width) {

        int cols = width;

        //int conversionFormula1D = col + (row * cols);
        int row = loc/cols;
        int col = loc % cols;

        Pair pair2D = new Pair(row, col);
        return pair2D;

    }




}