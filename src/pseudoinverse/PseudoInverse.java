/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pseudoinverse;

public class PseudoInverse {

    /**
     * @param args the command line arguments
     */
    static double[][] matrix;
    static Results res = new Results();

    public PseudoInverse(double[][] receivedMatrix) {
        matrix = receivedMatrix;
        pseudoCalculate();
    }

    public static Results getResult() {
        return res;
    }

    public void pseudoCalculate() {
        res.setVisible(true);
        Calculations calc = new Calculations(matrix);
//        System.out.println("det: " + calc.determinant(matrix,matrix.length));
//        if(calc.determinant(matrix, matrix.length));
//        calc.cofactor(matrix, matrix.length);

        double[][] resultMatrix = null;
        res.setTextArea("Given matrix: \n");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                res.setTextArea(String.format("%4.2f    ", matrix[i][j]));
                System.out.print(String.format("%4.2f    ", matrix[i][j]));
            }
            res.setTextArea("\n");
            System.out.println("");
        }

        double[][] transposeMatrix = calc.transpose(matrix);
        res.setTextArea("\nMatrix Transpose: \n");
        for (int i = 0; i < transposeMatrix.length; i++) {
            for (int j = 0; j < transposeMatrix[i].length; j++) {
                res.setTextArea(String.format("%4.2f    ", transposeMatrix[i][j]));
                System.out.print(String.format("%4.2f    ", transposeMatrix[i][j]));
            }
            res.setTextArea("\n");
            System.out.println("");
        }

        double[][] transposeMultMatrixLeft = calc.multMatrix(transposeMatrix, matrix);
        res.setTextArea("\nMatrix Transpose * Matrix: \n");
        for (int i = 0; i < transposeMultMatrixLeft.length; i++) {
            for (int j = 0; j < transposeMultMatrixLeft[i].length; j++) {
                res.setTextArea(String.format("%4.2f    ", transposeMultMatrixLeft[i][j]));
                System.out.print(String.format("%4.2f    ", transposeMultMatrixLeft[i][j]));
            }
            res.setTextArea("\n");
            System.out.println("");
        }

        double[][] transpoMultMatrixRight = calc.multMatrix(matrix, transposeMatrix);
        res.setTextArea("\nMatrix * Matrix Transpose: \n");
        for (int i = 0; i < transpoMultMatrixRight.length; i++) {
            for (int j = 0; j < transpoMultMatrixRight[i].length; j++) {
                res.setTextArea(String.format("%4.2f    ", transpoMultMatrixRight[i][j]));
                System.out.print(String.format("%4.2f    ", transpoMultMatrixRight[i][j]));
            }
            res.setTextArea("\n");
            System.out.println("");
        }

        if (calc.determinant(transposeMultMatrixLeft, transposeMultMatrixLeft.length) < -1e-3
                || calc.determinant(transposeMultMatrixLeft, transposeMultMatrixLeft.length) > 1e-3) {
            res.setTextArea("\nMatrix Transpose * Matrix Determinant: " + calc.determinant(transposeMultMatrixLeft, transposeMultMatrixLeft.length) + "\n");
            System.out.println("determinant: " + calc.determinant(transposeMultMatrixLeft, transposeMultMatrixLeft.length));
            double[][] inverse = null;
            if (transposeMultMatrixLeft.length == 1) {
//                for (int i = 0; i < matrix.length; i++) {
//                    for (int j = 0; j < matrix[0].length; j++) {
//                        resultMatrix[j][i] = matrix[i][j] / transposeMultMatrixLeft[0][0];
//                    }
//                }
                inverse[0][0] = 1 / transposeMultMatrixLeft[0][0];
            } else {
                inverse = calc.cofactor(transposeMultMatrixLeft, transposeMultMatrixLeft.length);
            }

            res.setTextArea("\n(Transpose Matrix * Matrix) Inverse: \n");
            for (int i = 0; i < inverse.length; i++) {
                for (int j = 0; j < inverse[i].length; j++) {
                    res.setTextArea(String.format("%4.2f    ", inverse[i][j]));
                    System.out.print(String.format("%4.2f    ", inverse[i][j]));
                }
                res.setTextArea("\n");
                System.out.println("");
            }

            resultMatrix = calc.multMatrix(inverse, transposeMatrix);
        } else if (calc.determinant(transpoMultMatrixRight, transpoMultMatrixRight.length) < -1e-3
                || calc.determinant(transpoMultMatrixRight, transpoMultMatrixRight.length) > 1e-3) {
            res.setTextArea("\nMatrix Transpose * Matrix Determinant: " + calc.determinant(transpoMultMatrixRight, transpoMultMatrixRight.length) + "\n");
            double[][] inverse = null;
            if (transpoMultMatrixRight.length == 1) {
//                resultMatrix = new double[matrix[0].length][matrix.length];
//                for (int i = 0; i < matrix.length; i++) {
//                    for (int j = 0; j < matrix[0].length; j++) {
//                        resultMatrix[j][i] = matrix[i][j] / transpoMultMatrixRight[0][0];
//                    }
//                }
                inverse = new double[1][1];
                inverse[0][0] = 1 / transpoMultMatrixRight[0][0];
            } else {
                inverse = calc.cofactor(transpoMultMatrixRight, transpoMultMatrixRight.length);
            }
            
            res.setTextArea("\n(Matrix * Transpose Matrix) Inverse: \n");
            for (int i = 0; i < inverse.length; i++) {
                for (int j = 0; j < inverse[i].length; j++) {
                    res.setTextArea(String.format("%4.2f    ", inverse[i][j]));
                    System.out.print(String.format("%4.2f    ", inverse[i][j]));
                }
                res.setTextArea("\n");
                System.out.println("");
            }
            
            resultMatrix = calc.multMatrix(transposeMatrix, inverse);
        }
        res.setTextArea("\nResults: \n");
        for (int i = 0; i < resultMatrix.length; i++) {
            for (int j = 0; j < resultMatrix[i].length; j++) {
                res.setTextArea(String.format("%4.2f    ", resultMatrix[i][j]));
                System.out.print(String.format("%4.2f    ", resultMatrix[i][j]));
            }
            res.setTextArea("\n");
            System.out.println("");
        }
        
        res.setTextArea("\nSum Of Sum Operations: " + calc.getSumCounter());
        res.setTextArea("\nSum Of Multiple Operations: " + calc.getMultCounter());
    }

    /*
    public static void main(String[] args) {
        // TODO code application logic here
        //new SelectMatrixType().setVisible(true);
        
        System.out.println("burası");
        Calculations calc = new Calculations(matrix);
//        System.out.println("det: " + calc.determinant(matrix,matrix.length));
//        if(calc.determinant(matrix, matrix.length));
//        calc.cofactor(matrix, matrix.length);
        double[][] resultMatrix = null;
        double[][] transposeMatrix = calc.transpose(matrix);
        double[][] transposeMultMatrixLeft = calc.multMatrix(transposeMatrix, matrix);
        double[][] transpoMultMatrixRight = calc.multMatrix(matrix, transposeMatrix);
        if(calc.determinant(transposeMultMatrixLeft, transposeMultMatrixLeft.length) != 0){
            double[][] inverse = calc.cofactor(transposeMultMatrixLeft, transposeMultMatrixLeft.length);
            resultMatrix = calc.multMatrix(inverse, transposeMatrix);
        }else if(calc.determinant(transpoMultMatrixRight, transpoMultMatrixRight.length) != 0){
            double[][] inverse = calc.cofactor(transpoMultMatrixRight, transpoMultMatrixRight.length);
            resultMatrix = calc.multMatrix(transposeMatrix, inverse);
        }
        System.out.println("Sonuç:");
        for (int i = 0; i < resultMatrix.length; i++) {
            for (int j = 0; j < resultMatrix[i].length; j++) {
                System.out.print(String.format("%4.2f    ", resultMatrix[i][j]));
            }
            System.out.println("");
        }   
    }
     */
}
