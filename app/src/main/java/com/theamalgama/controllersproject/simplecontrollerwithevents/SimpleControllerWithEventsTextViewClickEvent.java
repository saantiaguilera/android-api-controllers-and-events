package com.theamalgama.controllersproject.simplecontrollerwithevents;

import com.theamalgama.event.Event;
import com.theamalgama.event.listeners.EventHandler;
import com.theamalgama.event.listeners.EventNotifierListener;

public class SimpleControllerWithEventsTextViewClickEvent implements Event {

    /**
     * In this particular example this method wont be called because we never
     * created a class that extends EventManager and overrides the getEventHandler() method
     * Thus none of the events will be handled prior broadcast.
     *
     * We can simply make it return false. But since we are trying to show how this work
     * We created a method on the handler and show how it should be done
     */
    @Override
    public boolean handle(EventHandler handler) {
        return handler.onTextClicked();
    }

    @Override
    public void notify(EventNotifierListener listener) {
        listener.onTextClicked();
    }

};