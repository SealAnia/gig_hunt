package com.example.gig_hunt.exception;

public class NumberOfSymbolsDifferentFromRequiredException extends Exception {

    public static final String message = "The required number of chars is: ";

    public NumberOfSymbolsDifferentFromRequiredException(int i) {
        super(message + i);
    }

}
