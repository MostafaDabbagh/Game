package com.example.game.model;


import com.example.game.enums.GameResult;
import com.example.game.enums.Value;
import com.example.game.exceptions.IndexAlreadyTakenException;

public class TicTacToe {
    private Value[][] table = new Value[3][3];
    private Value turn = Value.X;

    public TicTacToe() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                table[i][j] = Value.E;
            }
        }
    }

    public Value getTurn() {
        return turn;
    }

    public void printTable() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                if (table[i][j].equals(Value.E))
                    System.out.print("-  ");
                else
                    System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void makeAction(int i, int j) throws Exception {
        if (table[i][j].equals(Value.X) || table[i][j].equals(Value.O))
            throw new IndexAlreadyTakenException();
        if (turn.equals(Value.X))
            table[i][j] = Value.X;
        else if (turn.equals(Value.O))
            table[i][j] = Value.O;
        swithTurn();
    }

    private void swithTurn() {
        if (turn.equals(Value.X))
            turn = Value.O;
        else if (turn.equals(Value.O))
            turn = Value.X;
    }

    public boolean isGameFinished() {
        boolean draw = true;
        boolean somebodyWon = false;
        for (int i = 0; i < 3; i++) {
            if (!(getNumberOfOinRow(i) > 0 && getNumberOfXinRow(i) > 0)
                    || !(getNumberOfOinColumn(i) > 0 && getNumberOfXinColumn(i) > 0)
            )
                draw = false;
            if (getNumberOfOinRow(i) == 3
                    || getNumberOfOinColumn(i) == 3
                    || getNumberOfXinRow(i) == 3
                    || getNumberOfXinColumn(i) == 3)
                somebodyWon = true;
        }
        return (somebodyWon || draw);
    }

    private int getNumberOfOinRow(int row) {
        int numberOfO = 0;
        for (int i = 0; i < table[row].length; i++) {
            if (table[row][i].equals(Value.O))
                numberOfO++;
        }
        return numberOfO;
    }

    private int getNumberOfOinColumn(int column) {
        int numberOfO = 0;
        for (int i = 0; i < table.length; i++) {
            if (table[i][column].equals(Value.O))
                numberOfO++;
        }
        return numberOfO;
    }

    private int getNumberOfXinRow(int row) {
        int numberXfO = 0;
        for (int i = 0; i < table[row].length; i++) {
            if (table[row][i].equals(Value.X))
                numberXfO++;
        }
        return numberXfO;
    }

    private int getNumberOfXinColumn(int column) {
        int numberOfX = 0;
        for (int i = 0; i < table.length; i++) {
            if (table[i][column].equals(Value.X))
                numberOfX++;
        }
        return numberOfX;
    }

    public GameResult getGameResult() {
        boolean Owon = false;
        boolean Xwon = false;
        for (int i = 0; i < 3; i++) {
            if (getNumberOfOinRow(i) == 3 || getNumberOfOinColumn(i) == 3)
                Owon = true;
            if (getNumberOfXinRow(i) == 3 || getNumberOfXinColumn(i) == 3)
                Xwon = true;
        }
        if (Owon)
            return GameResult.O_WINS;
        else if (Xwon)
            return GameResult.X_WINS;
        return GameResult.DRAW;
    }


}
