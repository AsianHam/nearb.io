package com.example.asian.nearbio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

public class EventCollection {
    //composition
    private ArrayList<Event> mEventsList = new ArrayList<Event>();
    private ListIterator<Event> mListIterator = mEventsList.listIterator();

    public EventCollection() {
        super();
    }

    public Event add(Event ent) {
        mListIterator.add( ent );
        return ent;
    }

    public int getCount() { return mEventsList.size(); }

    public boolean hasNext() { return mListIterator.hasNext(); }

    public Event getNext() {
        if ( hasNext() ) {
            return mListIterator.next();
        } else {
            return null;
        }
    }

    public void remove() { mListIterator.remove(); }

    //public void reset() { entriesListIterator = entries.listIterator(); }

    public Event getEntryAt(int position) {
        if (position < 0 || position >= getCount()) {
            return null;
        } else {
            return mEventsList.get(position);
        }
    }

    public void sort() {
        Collections.sort(mEventsList);
    }
}
