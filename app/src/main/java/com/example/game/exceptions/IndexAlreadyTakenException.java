package com.example.game.exceptions;

public class IndexAlreadyTakenException extends Exception {
    public IndexAlreadyTakenException(){
        super("This index of the table has already taken.");
    }
}
