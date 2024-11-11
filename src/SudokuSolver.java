public class SudokuSolver {
    static int[][] initial_field;

    public int[][] sudoku(int[][] field) {
        initial_field = field;
        solve(field);
        return field;
    }

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
