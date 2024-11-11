public class Main {

    static int[][] initial_field = {
            {4, 0, 3, 0, 0, 2, 0, 0, 0},
            {5, 0, 0, 0, 6, 0, 1, 2, 0},
            {9, 0, 0, 0, 0, 0, 0, 0, 4},
            {0, 0, 8, 0, 7, 0, 0, 0, 0},
            {0, 0, 0, 2, 0, 3, 0, 0, 8},
            {0, 3, 6, 0, 0, 0, 7, 0, 0},
            {0, 7, 0, 9, 2, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 5, 0, 9, 6},
            {0, 0, 0, 8, 0, 4, 5, 0, 0}};

    public static void main(String[] args) {
        SudokuSolver sudokuSolver = new SudokuSolver();
        int[][] field_sudokued = sudokuSolver.sudoku(initial_field);
        for (int i = 0; i < 9; i++) {
            System.out.print("[");
            for (int j = 0; j < 9; j++) {
                if (j != 8) {
                    System.out.print(field_sudokued[i][j] + ", ");
                } else {
                    System.out.print(field_sudokued[i][j]);
                }
            }
            System.out.print("]\n");
        }
    }
}