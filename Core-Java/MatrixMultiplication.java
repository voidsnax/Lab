import java.util.Scanner;

public class MatrixMultiplication {

    static int[][] inputMatrix(Scanner sc, int rows, int cols) {
        int[][] matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        return matrix;
    }

    static int[][] multiplyMatrices(int[][] a, int[][] b) {
        int[][] result = new int[a.length][b[0].length];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < b.length; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return result;
    }

    static void displayMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter rows and columns of first matrix:");
        int r1 = sc.nextInt();
        int c1 = sc.nextInt();

        System.out.println("Enter rows and columns of second matrix:");
        int r2 = sc.nextInt();
        int c2 = sc.nextInt();

        if (c1 != r2) {
            System.out.println("Matrix multiplication is not possible.");
        } else {
            System.out.println("Enter elements of first matrix:");
            int[][] a = inputMatrix(sc, r1, c1);

            System.out.println("Enter elements of second matrix:");
            int[][] b = inputMatrix(sc, r2, c2);

            int[][] product = multiplyMatrices(a, b);

            System.out.println("Product matrix:");
            displayMatrix(product);
        }

        sc.close();
    }
}