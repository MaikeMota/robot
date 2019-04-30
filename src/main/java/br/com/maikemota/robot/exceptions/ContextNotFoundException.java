package br.com.maikemota.robot.exceptions;

public class ContextNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 39403270381998282L;

    public ContextNotFoundException(final String message) {
        super(message);
    }
}