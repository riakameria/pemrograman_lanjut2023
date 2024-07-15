
import java.util.ArrayList;
import java.util.Scanner;

public class Tugas_2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        System.out.println("Masukkan Angka matrix matriks 3x3:");
        for (int i = 0; i < 3; i++) {
            ArrayList<Integer> Matrix = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                System.out.print("angka [" + i + "][" + j + "]: ");
                Matrix.add(scanner.nextInt());
            }
            matrix.add(Matrix);
        }


        System.out.println("\nMatriks asli:");
        printMatrix(matrix);

        ArrayList<ArrayList<Integer>> transposeMatrix = transpose(matrix);

        System.out.println("\nMatriks transpose:");
        printMatrix(transposeMatrix);


        ArrayList<ArrayList<Integer>> sumMatrix = addMatrices(matrix, transposeMatrix);

        System.out.println("\nHasil penjumlahan matriks asli dan matriks transpose:");
        printMatrix(sumMatrix);

        scanner.close();
    }

    public static ArrayList<ArrayList<Integer>> transpose(ArrayList<ArrayList<Integer>> matrix) {
        ArrayList<ArrayList<Integer>> transpose = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ArrayList<Integer> Matrix = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                Matrix.add(matrix.get(j).get(i));
            }
            transpose.add(Matrix);
        }
        return transpose;
    }

    public static ArrayList<ArrayList<Integer>> addMatrices(ArrayList<ArrayList<Integer>> matrix1, ArrayList<ArrayList<Integer>> matrix2) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ArrayList<Integer> Matrix = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                Matrix.add(matrix1.get(i).get(j) + matrix2.get(i).get(j));
            }
            result.add(Matrix);
        }
        return result;
    }

    public static void printMatrix(ArrayList<ArrayList<Integer>> matrix) {
        for (ArrayList<Integer> Matrix : matrix) {
            for (int element : Matrix) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
}
