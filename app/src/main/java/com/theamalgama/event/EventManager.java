package com.theamalgama.event;

import android.content.Context;

import com.theamalgama.event.listeners.EventListener;
import com.theamalgama.event.listeners.EventNotifierListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Class in charge of receiving events (from listeners or holders) and manage them or else broadcast them to the other listeners
 * if no action will be done
 *
 * Created by santiaguilera@theamalgama.com on 01/03/16.
 */
public class EventManager implements EventListener {

    private Context context = null;

    private List<EventNotifierListener> eventNotifierListenerList = new ArrayList<>(); //List of all the instances willing to receive events

    public EventManager(Context context){

        if(context == null)
            throw new NullPointerException("context cannot be null in EventManager");

        this.context = context;

    }

    public Context getContext() {
        return context;
    }

    /**
     * Adds an instance (that must implement EventNotifierListener) to the list of all the
     * classes that will be notified in the income of an event
     * @param listener
     */
    public void addEventNotifierListener(EventNotifierListener listener){

        if(listener==null)
            return;

        eventNotifierListenerList.add(listener);

    }

    /**
     * Removes an instance to the list of all the classes that will be notified in the income of an event
     * @param listener
     * @return if it was successfully removed
     */
    public boolean removeEventNotifierListener(EventNotifierListener listener){

        if(listener==null)
            return false;

        return eventNotifierListenerList.remove(listener);

    }

    /**
     * Method called (by one sending an event) for the purpose of managing that event, or else broadcast it to the
     * list of classes listening
     * @param event
     * @return if the event was handled or not by the EventManager
     */
    @Override
    public boolean broadcastEvent(Event event) {
        for(EventNotifierListener listener: new ArrayList<>(eventNotifierListenerList)){
            if(listener!=null)
                event.notify(listener);
        }

        return event == null;
    }

}
