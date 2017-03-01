package com.example.asian.nearbio;

public interface IQueryEngineListener {
    public enum EngineError {
        LOCATION_ERR, URL_ERR, PARSING_ERR, IO_ERR, UNKNOWN_ERR
    }

    public void notifyProcessingSuccess (EventCollection collection) ;
    public void notifyProcessingFailure (IQueryEngineListener.EngineError error) ;
}
