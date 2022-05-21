# Some explanation for Matrix.java
## Create matrix
There are four ways to create a Matrix class, you can pass the length and the width of the matrix to create an empty matrix, or just convert a two-dimensional double array to the matrix.

The *Zero* or *Eye* method support to create a zero matrix or identity matrix with the input of the length and width.

## Update or output matrix elements
You can use *set* or *get* method to update or obtain the value of the element according to the input index. *getRow* or *getColumn* return an array of the specified row or column. *getRows* or *getColumns* return a submatrix by the input row/column index.

*hstack* method merge a new matrix from the right side, and the *vstack* method merge the matrix from the lower side.

*Show* method will print the matrix in matrix view.

Use *clone* method to copy a matrix.

## Matrix operations

The *plus* and *subtract* method calculate the sum and difference of the matrix.

The *Multiply* method calculate the product of two or more matrices, the *Multiplyed* is a static method that create a new matrix of the product of the input matrices.

The *inverse* method returns the inversion of the original matrix.

The *Determinant* method return the determinant of the matrix, the calculation is based on recursion which calculate the cofactor of the matrix.

*Transpose* method returns the transpose of the matrix. *innerProduct* calculates the innerproduct between two vectors, $\vert b\vert = b^T b$

*QR_decomposition* method decompose the matrix into an orthogonal matrix Q and a triangular matrix R. Based on the decomposition, *eigenValue* method calculate the eigenvalue of the matrix by computing RQ iteratively, the iteration times is default 20, you can also set the iteration times.
