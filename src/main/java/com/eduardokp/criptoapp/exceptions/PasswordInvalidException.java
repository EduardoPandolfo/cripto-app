package com.eduardokp.criptoapp.exceptions;

public class PasswordInvalidException extends IllegalArgumentException {
    public final static String MSG = "Invalid password";

    public PasswordInvalidException() {
        super(MSG);
    }
}