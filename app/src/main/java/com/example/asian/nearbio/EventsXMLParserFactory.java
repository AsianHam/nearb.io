package com.example.asian.nearbio;

public class EventsXMLParserFactory {
    private static EventsXMLParserFactory singleton = null;

    public static EventsXMLParserFactory getInstance () {
        if (singleton == null) {
            singleton = new EventsXMLParserFactory();
        }
        return singleton;
    }

    private EventsXMLParserFactory () {
        super();
    }

    public IEventsXMLParser getParser () {
        return new EventsSaxParser();
    }
}
