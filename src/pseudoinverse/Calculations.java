/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pseudoinverse;

public class Calculations {
    private double[][] matrixTranspose;
    private double[][] transposeMultMatrix;
    private int sumCounter = 0;
    private int multCounter = 0;

    public Calculations(double[][] matrix){
    }
    
    public int getSumCounter(){
        return sumCounter;
    }
    
    public int getMultCounter(){
        return multCounter;
    }
    
    public double[][] transpose(double[][] matrix){
        matrixTranspose = new double[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrixTranspose[j][i] = matrix[i][j];
            }
        }
        System.out.println("Transpose: ");
        for (int i = 0; i < matrixTranspose.length; i++) {
            for (int j = 0; j < matrixTranspose[i].length; j++) {
                System.out.print(matrixTranspose[i][j] + " ");
            }
            System.out.println("");
        }
        return matrixTranspose;
    }
    
    public double[][] multMatrix(double[][] matrix1, double[][] matrix2){
        double sum = 0.0;
        transposeMultMatrix = new double[matrix1.length][matrix2[0].length];
//        for (int i = 0; i < matrix1.length; i++) {
//            for (int j = 0; j < matrix1[i].length; j++) {
//                sum += matrix1[i][j] * matrix2[j][i];
//            }
//            transposeMultMatrix[rowCounter][columnCounter] = sum;
//        }

        //i<2
        //j<2
        //k<3
        
        //i<2
        //j<2
        //k<3
        //i --> 1.matris satır
        //j --> 2.matrisin sutun
        //k --> 2.matris satır
        
        // i=0,j=0,k=0 --> sum += 0,0 * 0,0  //  i=0,j=0,k=1 --> sum+= 0,1 * 1,0  //  i=0,j=0,k=2 --> sum+= 0,2 * 2,0  //  i=0,j=1,k=0 --> sum+= 0,0 * 0,1  //  i=0,j=1,k=1 --> sum+= 0,0 * 1,1
        // i=0,j=1,k=2 --> sum += 0,2 * 2,1  //  i=1,
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                for (int k = 0; k < matrix1[0].length; k++) {
                    sum += matrix1[i][k] * matrix2[k][j];
                    multCounter++;
                }
                transposeMultMatrix[i][j] = sum;
                sum = 0;
            }
        }
        System.out.println("Çarpım: ");
        for (int i = 0; i < transposeMultMatrix.length; i++) {
            for (int j = 0; j < transposeMultMatrix[i].length; j++) {
                System.out.print(transposeMultMatrix[i][j] + " ");
            }
            System.out.println("");
        }      
        return transposeMultMatrix;
    }
    public double determinant(double matrix[][], int dim){
        double s = 1, det = 0;
        double[][] detMatrix = new double[matrix.length][matrix.length];
        int m,n;
        if(dim == 1){
            return(matrix[0][0]);
        }else{
            det = 0;
            for (int i = 0; i < dim; i++) {
                m = 0;
                n = 0;
                for (int j = 0; j < dim; j++) {
                    for (int k = 0; k < dim; k++) {
                        detMatrix[j][k] = 0;
                        if(j != 0 && k != i){
                            detMatrix[m][n] = matrix[j][k];
                            if(n < (dim - 2)){
                                n++;
                                sumCounter++;
                            }else{
                                n = 0;
                                m++;
                                sumCounter++;
                            }
                        }
                    }
                }
                det += s * (matrix[0][i] * determinant(detMatrix,dim-1));
                s = -1 * s;
                multCounter++;
            }
        }
        return det;
    }
    
    public double[][] cofactor(double matrix[][], int dim){
        double[][] cofac = new double[matrix.length][matrix.length];
        double[][] fac = new double[matrix.length][matrix.length];
        int m,n;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                m = 0;
                n = 0;
                for (int k = 0; k < dim; k++) {
                    for (int l = 0; l < dim; l++) {
                        if(k != i && l != j){
                            cofac[m][n] = matrix[k][l];
                            if(n < (dim - 2)){
                                n++;
                                sumCounter++;
                            }
                            else{
                                n = 0;
                                m++;
                                sumCounter++;
                            }
                        }
                    }
                }
                fac[i][j] = Math.pow(-1, i + j) * determinant(cofac, dim - 1);
                multCounter++;
            }
        }
        return transpose2(matrix, fac, dim);
    }
    public double[][] transpose2(double matrix[][], double fac[][], int dim){
        double tempMatrix[][] = new double [matrix.length][matrix.length];
        double inverse[][] = new double [matrix.length][matrix.length];
        double det;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                tempMatrix[i][j] = fac[j][i];
            }
        }
        det = determinant(matrix, dim);
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                inverse[i][j] = tempMatrix[i][j] / det;
            }
        }
        System.out.println("\n\n\ninverse of matrix is: ");

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                System.out.print("\t " + inverse[i][j]);
            }
            System.out.println("");
        }
        return inverse;
    }
}
