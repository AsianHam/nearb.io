package com.example.asian.nearbio;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

import com.example.asian.nearbio.Event;
import com.example.asian.nearbio.EventCollection;

public class EventsHandler extends DefaultHandler {
    private String LOG_TAG = "EventsHandler";
    private StringBuffer buf = new StringBuffer();
    private EventCollection mCollection = null;
    private Event mCurrentEvent = null;

    public EventsHandler (EventCollection eventCollection) {
        super();
        mCollection = eventCollection;
    }

    public void startElement( String namespaceURI, String localname, String qname,
                              Attributes attributes ) {

        qname = qname.replaceFirst( ".*:", "" );

        if ( qname.equals( "events" ) ) {
            Log.i(LOG_TAG, "found events tag");
        }

        if ( qname.equals( "event" ) ) {
            Log.i(LOG_TAG, "found event tag");
            mCurrentEvent = new Event();
            mCollection.add( mCurrentEvent );
        }


        buf.setLength(0);

    }

    public void characters( char[] ch, int start, int length ) {
        buf.append( ch, start, length );
    }

    public void endElement( String uri, String localname, String qname ) {
        String value = buf.toString();

        // remove any namespace ids
        qname = qname.replaceFirst( ".*:", "" );
        if (mCurrentEvent != null) {
            if ( qname.equals( "description" ) ) {
                Log.i(LOG_TAG, " description:" + value);
                if (mCurrentEvent.getHtmlDescription() == null) {
                    mCurrentEvent.setHtmlDescription(value);
                }
            } else if ( qname.equals( "title" ) ) {
                Log.i(LOG_TAG, " event title:" + value);
                mCurrentEvent.setTitle(value);
            } else if ( qname.equals( "latitude" ) ) {
                try {
                    mCurrentEvent.setLatitude(Float.valueOf(value.trim()));
                }
                catch (NumberFormatException nfe)
                {
                    Log.e(LOG_TAG, "latitude value caused NumberFormatException: " + nfe.getMessage());
                }
            } else if ( qname.equals( "longitude" ) ) {
                try {
                    mCurrentEvent.setLongitude(Float.valueOf(value.trim()));
                }
                catch (NumberFormatException nfe)
                {
                    Log.e(LOG_TAG, "longitude value caused NumberFormatException: " + nfe.getMessage());
                }
            } else if ( qname.equals( "url" ) ) {
                mCurrentEvent.setUrl(value);
            } else if ( qname.equals( "start_date" ) ) {
                if (mCurrentEvent.getStartDate() == null) {
                    mCurrentEvent.setStartDate(value);
                }
            } else if ( qname.equals( "distance" ) ) {
                String timmedString = value.trim();
                try {
                    String distanceString = timmedString.substring(0, timmedString.length()-1);
                    Log.i(LOG_TAG, "event distance from user is:" + distanceString);
                    mCurrentEvent.setDistance(Float.valueOf(distanceString));
                } catch (NumberFormatException nfe)
                {
                    Log.e(LOG_TAG, "distance value caused NumberFormatException: " + nfe.getMessage());
                }
            }
        }
    }
}
