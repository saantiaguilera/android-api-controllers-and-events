package com.theamalgama.controllers;

import android.content.Context;
import android.view.View;

import com.theamalgama.events.EventManager;
import com.theamalgama.events.interfaces.Event;
import com.theamalgama.events.interfaces.EventBroadcastListener;
import com.theamalgama.events.interfaces.EventListener;
import com.theamalgama.events.interfaces.EventNotifierListener;

/**
 * Base controller which implements the sending/receiving of events.
 * You can either send an event to the eventManager and handle it there or bypass it and broadcast an event to all the classes listening
 * For receiving and handling events in a controller you will need to add the extending class to the notifierListenerList in the eventManager
 *
 * Created by santi on 08/01/16.
 */
public abstract class BaseEventController<T extends View> extends BaseController<T> implements EventNotifierListener {

    //Event variables.
    private EventListener eventListener;
    private EventBroadcastListener eventBroadcastListener;

    public BaseEventController(Context context) {
        super(context);
    }

    public BaseEventController(Context context, T t) {
        super(context, t);
    }

    //-------------------SETTERS------------------//

    /**
     * Set an EventListener which will send events to the eventmanager.
     *
     * On contrary to the broadcastListener, here events will be sent to the eventmanager
     * and handled there. On the eventmanager they might broadcast this/another event to the classes listening to respond to them
     *
     * @param eventListener used for sending events to the EventManager
     */
    public void setEventListener(EventListener eventListener){
        this.eventListener = eventListener;
    }

    /**
     * Set an EventNotifierListener which will broadcast events to all the
     * classes listening.
     *
     * On contrary to the eventManager, here events will be sent to all the classes listening the eventmanager,
     * bypassing the handling of the eventManager, and letting each class listening respond to the event you are sending.
     *
     * @param broadcastListener
     */
    public void setEventBroadcastListener(EventBroadcastListener broadcastListener) {
        this.eventBroadcastListener = broadcastListener;
    }

    /**
     * Short version of everything you need for the events. With this you will be able to:
     *  - Send events to the event manager.
     *  - Broadcast events to other classes.
     *  - Receive and handle other events.
     *
     *  FOR OTHER VERSION WITH EventManager NOT IMPLEMENTING EventNotifierListener use:
     *  public <E extends EventManager & EventNotifierListener> void setEventHandlerListener(E e) {
     *      sameStuffHere;
     *  }
     *
     * @param eventManager the EventManager in particular. Must implement EventNotifierListener and EventListener
     */
    public void setEventHandlerListener(EventManager eventManager) {
        setEventListener(eventManager);
        setEventBroadcastListener(eventManager);

        if(eventManager!=null) {
            //avoid duplicates
            eventManager.removeEventNotifierListener(this);

            eventManager.addEventNotifierListener(this);
        }
    }

    //--------------------Handling of events---------------//

    /**
     * Override this method in your extending class to hear the events and handle them
     *
     * @param event The new event to handle
     */
    @Override
    public void onNewEvent(Event event) {}

    /**
     * Send an event to the EventManager and handle it there
     *
     * @param event
     */
    protected void onEvent(Event event) {
        if(eventListener==null)
            throw new NullPointerException("eventManager in BaseEventController can't be null");

        eventListener.onEvent(event);
    }

    /**
     * Broadcast an event to all the classes listening
     *
     * @param event
     */
    protected void broadcastEvent(Event event) {
        if(eventBroadcastListener==null)
            throw new NullPointerException("eventBroadcastListener in BaseEventControll cant't be null");

        eventBroadcastListener.broadcastEvent(event);
    }

}
