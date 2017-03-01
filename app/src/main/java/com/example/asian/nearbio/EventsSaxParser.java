package com.example.asian.nearbio;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import android.util.Log;

import com.example.asian.nearbio.EventCollection;

public class EventsSaxParser implements IEventsXMLParser{
    private String LOG_TAG = "EventsSaxParser";

    public EventCollection parserFromURL(URL url) throws IOException,
            EventsParseException {


        EventCollection events = new EventCollection();
        try {
			/* original working version */
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            Log.i(LOG_TAG, "EdisSaxParser.parseFromUrl :" + url);
            SAXParser parser = parserFactory.newSAXParser();

			/* trying...
			String parserClass = "org.apache.xerces.parsers.SAXParser";
			Parser parser = ParserFactory.makeParser(parserClass);
			*/


            EventsHandler handler = new EventsHandler(events);

            InputStream in = url.openStream();
            Date startDate = new Date();
            Log.i(LOG_TAG, "parser start timestamp: " + startDate);
            parser.parse(in, handler);
            Date finishDate = new Date();
            long interval = (finishDate.getTime() - startDate.getTime()) / 1000;
            Log.i(LOG_TAG, "EDIS XMLParser TOOK THIS MUCH TIME (sec) TO FINISH : " + interval);
        } catch (UnknownHostException ue) {
            throw new IOException(ue.toString());
        } catch (IOException io) {
            throw new IOException(io.toString());
        } catch (SAXParseException saxe) {
            throw new EventsParseException(saxe.toString());
        } catch (ParserConfigurationException pce) {
            throw new EventsParseException(pce.toString());
        } catch (SAXException sae) {
            throw new EventsParseException(sae.toString());
        }
        return events;
    }
}
