package com.santiago.event;

import android.content.Context;
import android.support.annotation.NonNull;

import com.santiago.event.listener.EventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Class in charge of receiving events (from listeners), manage them and/or broadcast them to the other listeners.
 *
 * @note Thread-safe.
 *
 * Created by santiaguilera@theamalgama.com on 01/03/16.
 */
public class EventManager implements EventListener {

    private Context context = null;

    private EventDispatcher dispatcher; //Dude in charge of dispatching events

    private final List<Object> objectsListening = new ArrayList<>(); //List of all the objects willing to receive events

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
        synchronized (objectsListening) {
            objectsListening.add(object);
        }
    }

    /**
     * Removes an instance to the list of all the classes that will be notified in the income of an event
     * @param object
     * @return if it was successfully removed
     */
    public boolean removeListener(@NonNull Object object) {
        synchronized (objectsListening) {
            return objectsListening.remove(object);
        }
    }

    /**
     * Method called (by one sending an event) for the purpose of managing that event, and/or else broadcast it to the
     * list of classes listening
     *
     * @note Since we could be adding or rm listeners from other threads, we create a copy of the current listeners
     * and iterate over them.
     *
     * @param event
     */
    @Override
    public void broadcastEvent(@NonNull Event event) {
        //Dispatch the event to ourselves
        dispatcher.dispatchEvent(event, this);

        List<Object> listenersCopy;
        synchronized (objectsListening) {
            listenersCopy = new ArrayList<>(objectsListening);
        }

        //Iterate through all the objects listening and dispatch it too
        for(Object object : listenersCopy)
            if(object != null)
                dispatcher.dispatchEvent(event, object);
    }

}
