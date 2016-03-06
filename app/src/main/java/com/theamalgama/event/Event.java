package com.theamalgama.event;

import com.theamalgama.event.listeners.EventNotifierListener;

/**
 * Created by santiaguilera@theamalgama.com on 01/03/16.
 */
public interface Event {

    /**
     * Notify the listener about this event
     * @param listener
     */
    void notify(EventNotifierListener listener);

}
