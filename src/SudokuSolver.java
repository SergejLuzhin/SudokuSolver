public class SudokuSolver {
    static int[][] initial_field;

    static final int NINEFACTORIAL = 362880;

    public int[][] sudoku(int[][] field) {
        initial_field = field;
        solve(field);
        return field;
    }

    public boolean isSolved(int[][] field){
        return true;
    }

   /* public boolean isSolved(int[][] field) {
        int[] column_factorials = {1, 1, 1, 1, 1, 1, 1, 1, 1};
        int[][] three_square_factorial = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        for (int i = 0; i < 9; i++) {
            int line_factorial = 1;
            for (int j = 0; j < 9; j++) {
                line_factorial *= field[i][j];
                column_factorials[j] *= field[i][j];
                if (field[i][j] == 0) {
                    System.out.println("массив не заполнен до конца");
                    return false; //массив не заполнен до конца
                } else if (initial_field[i][j] != 0 && field[i][j] != initial_field[i][j]) {
                    System.out.println("изначальные числа изменены");
                    return false; // изначальные числа изменены
                }
                if (i < 3 && j < 3) {
                    three_square_factorial[0][0] *= field[i][j];
                } else if (i < 3 && j >= 3 && j < 6) {
                    three_square_factorial[0][1] *= field[i][j];
                } else if (i < 3 && j >= 6) {
                    three_square_factorial[0][2] *= field[i][j];
                } else if (i >= 3 && i < 6 && j < 3) {
                    three_square_factorial[1][0] *= field[i][j];
                } else if (i >= 3 && i < 6 && j >= 3 && j < 6) {
                    three_square_factorial[1][1] *= field[i][j];
                } else if (i >= 3 && i < 6 && j >= 6) {
                    three_square_factorial[1][2] *= field[i][j];
                } else if (i >= 6 && j < 3) {
                    three_square_factorial[2][0] *= field[i][j];
                } else if (i >= 6 && j >= 3 && j < 6) {
                    three_square_factorial[2][1] *= field[i][j];
                } else if (i >= 6 && j >= 6) {
                    three_square_factorial[2][2] *= field[i][j];
                }
            }
            if (line_factorial != NINEFACTORIAL) {
                System.out.println("факториал линни не равен факториалу 9");
                return false; //факториал линни не равен факториалу 9 (в линии не все числа от 1 до 9)
            }
        }
        /* for (int factorial : column_factorials) {
            if (factorial != NINEFACTORIAL) {
                System.out.println("факториал столбца не равен факториалу 9 Полученное значение: " + factorial);
                return false; //факториал столбца не равен факториалу 9 (в столбце не все числа от 1 до 9)
            }
        }*/
        /*for (int[] factorials : three_square_factorial){
            for (int factorial : factorials){
                if (factorial != NINEFACTORIAL){
                    System.out.println("факторил квадрата не равен факториалу 9 " + factorial);
                    return false; //проверяем числа в квадратах 3 на 3
                }
            }
        }
        return true;
    } */

    private boolean solve(int[][] field) {
        // Проходим по всем ячейкам поля
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                // Если ячейка пуста (0), пробуем заполнить ее
                if (field[row][col] == 0) {
                    // Перебираем числа от 1 до 9
                    for (int num = 1; num <= 9; num++) {
                        // Проверяем, допустимо ли поставить num в ячейку (row, col)
                        if (isSafe(field, row, col, num)) {
                            field[row][col] = num;

                            // Рекурсивный вызов для заполнения следующей ячейки
                            if (solve(field)) {
                                return true; // Если поле решено, возвращаем true
                            }

                            // Если решение не найдено, очищаем ячейку и пробуем следующее значение
                            field[row][col] = 0;
                        }
                    }
                    return false; // Если ни одно число не подходит, возвращаемся назад
                }
            }
        }
        // Если поле заполнено корректно, возвращаем true
        return true;
    }

    // Проверка, допустимо ли поставить число num в ячейку (row, col)
    private boolean isSafe(int[][] field, int row, int col, int num) {
        // Проверка строки и столбца
        for (int i = 0; i < 9; i++) {
            if (field[row][i] == num || field[i][col] == num) {
                return false;
            }
        }

        // Проверка 3x3 подматрицы
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }
}
