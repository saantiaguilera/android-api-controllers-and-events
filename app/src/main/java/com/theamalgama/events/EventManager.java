package com.theamalgama.events;

import android.app.Activity;

import com.theamalgama.events.interfaces.Event;
import com.theamalgama.events.interfaces.EventBroadcastListener;
import com.theamalgama.events.interfaces.EventListener;
import com.theamalgama.events.interfaces.EventNotifierListener;

import java.util.ArrayList;
import java.util.List;

public class EventManager implements EventListener, EventBroadcastListener {

    private Activity activity = null;

    private List<EventNotifierListener> eventNotifierListenerList = new ArrayList<>();

    public EventManager(Activity activity){

        if(activity == null)
            throw new NullPointerException("activity cannot be null in EventManager");

        this.activity = activity;

    }

    public Activity getActivity() {
        return activity;
    }

    public void addEventNotifierListener(EventNotifierListener listener){

        if(listener==null)
            return;

        eventNotifierListenerList.add(listener);

    }

    public boolean removeEventNotifierListener(EventNotifierListener listener){

        if(listener==null)
            return false;

        return eventNotifierListenerList.remove(listener);

    }

    @Override
    public boolean onEvent(Event event) {
        return event == null;
    }

    @Override
    public void broadcastEvent(Event event) {

        List<EventNotifierListener> eventNotifierListenerListCopy = new ArrayList<>(eventNotifierListenerList);

        for(EventNotifierListener listener: eventNotifierListenerListCopy){
            listener.onNewEvent(event);
        }

    }

}
