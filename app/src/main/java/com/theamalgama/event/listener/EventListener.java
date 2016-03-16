package com.theamalgama.event.listener;

import com.theamalgama.event.Event;

/**
 * Created by santiaguilera@theamalgama.com on 01/03/16.
 */
public interface EventListener {

    /**
     * Broadcast the event to all the ones listening
     * @param event
     */
    void broadcastEvent(Event event);

}
