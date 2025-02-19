package com.epam.rd.qa.basicio;


public class Matrix {
    private final int rows;
    private final int columns;
    private final double[][] data;
    
    public Matrix(int rows, int columns) {
        if (rows <= 0 || columns <= 0) {
            throw new MatrixException("Invalid matrix dimensions");
        }
        this.rows = rows;
        this.columns = columns;
        this.data = new double[rows][columns];
    }
    
    public Matrix(double[][] data) {
        if (data == null || data.length == 0) {
            throw new MatrixException("Input array is null or empty");
        }
        this.rows = data.length;
        this.columns = data[0].length;
        for (double[] row : data) {
            if (row == null || row.length != columns) {
                throw new MatrixException("Input array is jagged");
            }
        }
        this.data = data;
    }
    
    public int getRows() {
        return rows;
    }
    
    public int getColumns() {
        return columns;
    }
    
    public double[][] toArray() {
        return data;
    }
    
    public double get(int row, int column) {
        checkIndices(row, column);
        return data[row][column];
    }
    
    public void set(int row, int column, double value) {
        checkIndices(row, column);
        data[row][column] = value;
    }
    
    private void checkIndices(int row, int column) {
        if (row < 0 || row >= rows || column < 0 || column >= columns) {
            throw new MatrixException("Invalid row or column index");
        }
    }
    
    public Matrix add(Matrix other) {
        if (other == null || this.rows != other.rows || this.columns != other.columns) {
            throw new MatrixException("Matrices are not compatible for addition");
        }
        Matrix result = new Matrix(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.data[i][j] = this.data[i][j] + other.data[i][j];
            }
        }
        return result;
    }
    
    public Matrix subtract(Matrix other) {
        if (other == null || this.rows != other.rows || this.columns != other.columns) {
            throw new MatrixException("Matrices are not compatible for subtraction");
        }
        Matrix result = new Matrix(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.data[i][j] = this.data[i][j] - other.data[i][j];
            }
        }
        return result;
    }
    
    public Matrix multiply(Matrix other) {
        if (other == null || this.columns != other.rows) {
            throw new MatrixException("Matrices are not compatible for multiplication");
        }
        Matrix result = new Matrix(this.rows, other.columns);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.columns; j++) {
                double sum = 0.0;
                for (int k = 0; k < this.columns; k++) {
                    sum += this.data[i][k] * other.data[k][j];
                }
                result.data[i][j] = sum;
            }
        }
        return result;
    }
}

class MatrixException extends RuntimeException {
    public MatrixException(String message) {
        super(message);
    }
}