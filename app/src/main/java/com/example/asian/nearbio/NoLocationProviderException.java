package com.example.asian.nearbio;

public class NoLocationProviderException extends Exception{
    private static final long serialVersionUID = 134233245342L;

    public NoLocationProviderException (String msg) {
        super (msg);
    }

    public NoLocationProviderException () {
        super ();
    }

    public NoLocationProviderException (String msg, Throwable cause) {
        super (msg, cause);
    }

    public NoLocationProviderException (Throwable cause) {
        super (cause);
    }
}
