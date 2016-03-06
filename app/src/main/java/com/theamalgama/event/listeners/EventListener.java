package com.theamalgama.event.listeners;

import com.theamalgama.event.Event;

/**
 * Created by santiaguilera@theamalgama.com on 01/03/16.
 */
public interface EventListener {

    /**
     * Call the event manager and manage the event sent.
     * @param event
     * @return wether the event manager took actions in the managing of the event or not
     */
    boolean broadcastEvent(Event event);

}
