package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Interface {
    final private Scanner sc;
    final private char[][] matrix;
    private boolean xxx;
    private boolean ooo;
    private int xs ;
    private int os;

    public Interface(){
        this.sc = new Scanner(System.in);
        this.matrix = new char[3][3]; // tic tac toe matrix
        this.xxx = false; // win condition for X
        this.ooo = false; // win condition for O
        this.xs = 0; // count for X
        this.os = 0; // count for O
    }

    public void start(){
        emptyPrint(matrix);

        for (int i = 0; i < 9; i++) {
            xAction(matrix);
            checkResults(matrix);

            // if there is an outcome, loop breaks, else game continues.
            if (!(checkResults(matrix).equals(""))) {
                System.out.println(checkResults(matrix));
                break;
            }
            oAction(matrix);
            checkResults(matrix);
            if (!(checkResults(matrix).equals(""))) {
                System.out.println(checkResults(matrix));
                break;
            }
        }

    }

    // initial print of empty matrix
    private void emptyPrint(char[][] matrix){
        for (int i = 0; i < 9; i++) {
            matrix[i / 3][i % 3] = ' ';
        }

        print(matrix);
    }

    // print method for tic tac toe
    private void print(char[][] matrix) {
        System.out.println("---------");

        // 3 characters in each row
        for (int i = 0; i < 3; i++) {
            System.out.println(String.format("| %c %c %c |", matrix[i][0], matrix[i][1], matrix[i][2]));
        }
        System.out.println("---------");

    }

    // method for player X's turn
    private void xAction(char[][] matrix) {
        while (true) {
            System.out.print("Enter coordinates: ");
            try {

                int coorX = sc.nextInt();
                int coorY = sc.nextInt();

                // only insert character Y if character at coordinate is space
                if (matrix[3 - coorY][coorX - 1] == ' ') {
                    matrix[3 - coorY][coorX - 1] = 'X';
                    xs++;
                    break;
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }

            } catch (InputMismatchException ex) {
                System.out.println("You should enter numbers!");
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("Coordinates should be from 1 to 3!");
            } finally {
                sc.nextLine();
            }

        }
        print(matrix);
    }

    // method for player O's turn
    private void oAction ( char[][] matrix){
        while (true) {
            System.out.print("Enter coordinates: ");
            try {

                int coorX = sc.nextInt();
                int coorY = sc.nextInt();

                // only insert character O if character at coordinate is space
                if (matrix[3 - coorY][coorX - 1] == ' ') {
                    matrix[3 - coorY][coorX - 1] = 'O';
                    os++;
                    break;
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }

            } catch (InputMismatchException ex) {
                System.out.println("You should enter numbers!");
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("Coordinates should be from 1 to 3!");
            } finally {
                sc.nextLine();
            }

        }
        print(matrix);
    }

    // method for checking results
    private String checkResults (char[][] matrix){
        for (int i = 0; i < 3; i++) {

            int row = 0;
            int col = 0;
            int leftDiag = 0;
            int rightDiag = 0;

            // win conditions
            for (int j = 0; j < 3; j++) {
                /*
                coordinates for row pattern:
                [(1,1), (1,2), (1,3)] OR
                [(2,1), (2,2), (2,3)] OR
                [(3,1), (3,2), (3,3)]
                 */
                row += matrix[i][j];

                /*
                coordinates for column pattern:
                [(1,1), (2,1), (3,1)] OR
                [(1,2), (2,2), (3,2)] OR
                [(1,3), (2,3), (3,3)]
                 */
                col += matrix[j][i];

                /*
                coordinates for diagonal pattern(top left):
                [(1,3), (2,2), (3,1)]
                 */
                leftDiag += matrix[j][j];

                /*
                coordinates for diagonal pattern(top right):
                 [(1,1), (2,2), (3,3)]
                 */
                rightDiag += matrix[j][2 - j];
            }

            // ASCII value of x^3 is 264
            // ASCII value of o^3 is 237
            xxx = xxx || row == 264 || col == 264 || leftDiag == 264 || rightDiag == 264;
            ooo = ooo || row == 237 || col == 237 || leftDiag == 237 || rightDiag == 237;
        }

        String result = "";

        // result outcome
        if (ooo) {
            result = "O wins";
        } else if (xxx) {
            result = "X wins";
        } else if (xs + os == 9) {
            result = "Draw";
        }

        return result;
    }


}

