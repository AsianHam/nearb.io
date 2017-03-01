package com.example.asian.nearbio;

import java.io.IOException;
import java.net.URL;

import com.example.asian.nearbio.EventCollection;

public interface IEventsXMLParser {
    public EventCollection parserFromURL (URL url) throws IOException, EventsParseException;
}
