package com.bptn.course.week2.fourInARowGame;

import java.util.Arrays;
import java.util.Scanner;

public class Board {
	Scanner scanner = new Scanner(System.in);
    // Add instance variables
    private String[][] board;

    public void boardSetUp() {
        
        System.out.println("------ Board Set up -------");
        System.out.println("Number of rows: ");
        int rows = scanner.nextInt();
        System.out.println("Number of cols: ");
        int cols = scanner.nextInt();

        // Initialize a row by column array
        this.board = new String[rows][cols];

        // Initialize empty board with dashes (-)
        for (String[] row : board) {
            Arrays.fill(row, "-");
        }
    }

    public int getColumns() {
        return board[0].length;
    }
       public void printBoard() {
        for (String[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }

    public boolean columnFull(int col) {
        // Check if the column is full by just checking the 0'th row's value
        return !board[0][col].equals("-");
    }

    public boolean boardFull() {
        for (int i = 0; i < this.board[0].length; i++) {
            if (!columnFull(i)) {
                return false;
            }
        }
        return true;
    }

    public boolean addToken(int colToAddToken, String token) {
        int rowToAddToken = board.length - 1;

        while (rowToAddToken >= 0) {
            if (board[rowToAddToken][colToAddToken].equals("-")) {
                // You now know the right row and column to place the token. Place it and then return true.
                board[rowToAddToken][colToAddToken] = token;
                return true;
            } else {
                rowToAddToken -= 1;
            }
        }

        return false;
    }

    public boolean checkIfPlayerIsTheWinner(String playerNumber) {
        if (checkHorizontal(playerNumber)) {
            return true;
        } else if (checkVertical(playerNumber)) {
            return true;
        } else if (checkLeftDiagonal(playerNumber)) {
            return true;
        } else if (checkRightDiagonal(playerNumber)) {
            return true;
        }
        return false;
    }

    public boolean checkVertical(String playerNumber) {
        for (int col = 0; col < board[0].length; col++) {
            for (int row = 0; row < board.length - 3; row++) {
                if (board[row][col].equals(playerNumber)
                        && board[row][col].equals(board[row + 1][col])
                        && board[row][col].equals(board[row + 2][col])
                        && board[row][col].equals(board[row + 3][col])) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkHorizontal(String playerNumber) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length - 3; col++) {
                if (board[row][col].equals(playerNumber)
                        && board[row][col].equals(board[row][col + 1])
                        && board[row][col].equals(board[row][col + 2])
                        && board[row][col].equals(board[row][col + 3])) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkLeftDiagonal(String playerNumber) {
        for (int row = 0; row < board.length - 3; row++) {
            for (int col = 0; col < board[0].length - 3; col++) {
                if (board[row][col].equals(playerNumber)
                        && board[row][col].equals(board[row + 1][col + 1])
                        && board[row][col].equals(board[row + 2][col + 2])
                        && board[row][col].equals(board[row + 3][col + 3])) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkRightDiagonal(String playerNumber) {
        for (int row = 0; row < board.length - 3; row++) {
            for (int col = 3; col < board[0].length; col++) {
                if (board[row][col].equals(playerNumber)
                        && board[row][col].equals(board[row + 1][col - 1])
                        && board[row][col].equals(board[row + 2][col - 2])
                        && board[row][col].equals(board[row + 3][col - 3])) {
                    return true;
                }
            }
        }
        
        return false;
    }

//    public static void main(String[] args) {
//        Board board1 = new Board();
//        board1.boardSetUp();
//        board1.printBoard();
//
//        board1.addToken(0, "x");
//        board1.addToken(0, "x");
//        board1.addToken(0, "x");
//        board1.addToken(1, "y");
//        board1.addToken(1, "z");
//        board1.addToken(1, "w");
//        board1.addToken(0, "x");
//
//        System.out.println("Board for testing checkVertical");
//        System.out.println("Board 1 check vertical with x returns -> " + board1.checkVertical("x"));
//        System.out.println("Board 1 check vertical with y returns -> " + board1.checkVertical("y"));
//
//        board1.printBoard();
//
//        Board board2 = new Board();
//        // Test with at least a 4-by-4 size board.
//        board2.boardSetUp();
//        board2.printBoard();
//
//        board2.addToken(0, "x");
//        board2.addToken(0, "x");
//        board2.addToken(0, "w");
//        board2.addToken(0, "w");
//        board2.addToken(1, "y");
//        board2.addToken(1, "x");
//        board2.addToken(1, "w");
//        board2.addToken(2, "y");
//        board2.addToken(2, "w");
//        board2.addToken(2, "x");
//        board2.addToken(3, "w");
//        board2.addToken(3, "w");
//        board2.addToken(3, "w");
//        board2.addToken(3, "x");
//
//        System.out.println("Board for testing diagonals");
//        System.out.println("Board 2 check right diagonal with x returns -> " + board2.checkRightDiagonal("x"));
//        System.out.println("Board 2 check right diagonal y returns -> " + board2.checkRightDiagonal("y"));
//        System.out.println("Board 2 check left diagonal w returns -> " + board2.checkLeftDiagonal("w"));
//
//        board2.printBoard();
//    }
}