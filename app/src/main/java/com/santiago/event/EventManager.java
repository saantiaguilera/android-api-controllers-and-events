package com.santiago.event;

import android.content.Context;
import android.support.annotation.NonNull;

import com.santiago.event.listener.EventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO I think it cant have race conditions on the broadcast/add/rm. But pay atention in case it happens
 *
 * Class in charge of receiving events (from listeners), manage them and/or broadcast them to the other listeners.
 *
 * Created by santiaguilera@theamalgama.com on 01/03/16.
 */
public class EventManager implements EventListener {

    private Context context = null;

    private EventDispatcher dispatcher; //Dude in charge of dispatching events

    private List<Object> objectsListening = new ArrayList<>(); //List of all the objects willing to receive events

    public EventManager(@NonNull Context context){
        this.context = context;

        dispatcher = new EventDispatcher();
    }

    public @NonNull Context getContext() {
        return context;
    }

    /**
     * Adds an instance to the list of all the
     * classes that will be notified in the income of an event
     * @param object
     */
    public void addListener(@NonNull Object object){
        objectsListening.add(object);
    }

    /**
     * Removes an instance to the list of all the classes that will be notified in the income of an event
     * @param object
     * @return if it was successfully removed
     */
    public boolean removeListener(@NonNull Object object) {
        return objectsListening.remove(object);
    }

    /**
     * Method called (by one sending an event) for the purpose of managing that event, and/or else broadcast it to the
     * list of classes listening
     * @param event
     */
    @Override
    public void broadcastEvent(@NonNull Event event) {
        //Dispatch the event to ourselves
        dispatcher.dispatchEvent(event, this);

        //Iterate through all the objects listening and dispatch it too
        for(Object object : new ArrayList<>(objectsListening))
            if(object != null)
                dispatcher.dispatchEvent(event, object);
    }

}
