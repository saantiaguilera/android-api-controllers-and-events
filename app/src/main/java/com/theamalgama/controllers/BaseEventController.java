package com.theamalgama.controllers;

import android.content.Context;
import android.view.View;

import com.theamalgama.event.Event;
import com.theamalgama.event.EventManager;
import com.theamalgama.event.listener.EventListener;

/**
 * Base controller which implements the sending/receiving of events.
 * You can broadcast a particular event to the EventManager
 * For receiving and handling events in a controller you will need override the getEventNotifierListener() and implmement an instance of EventNotifierListener
 *
 * Created by santiaguilera@theamalgama.com on 08/01/16.
 */
public abstract class BaseEventController<T extends View> extends BaseController<T> {

    //Event variables.
    private EventListener eventListener;

    public BaseEventController(Context context) {
        super(context);
    }

    public BaseEventController(Context context, T t) {
        super(context, t);
    }

    //-------------------SETTERS------------------//

    /**
     * Set an EventListener which will broadcast events to the eventmanager.
     *
     * @param eventListener used for sending events to the EventManager
     */
    public void setEventListener(EventListener eventListener){
        this.eventListener = eventListener;
    }

    /**
     * Short version of everything you need for the events. With this you will be able to:
     *  - Broadcast events to the eventmanager and the listening classes.
     *  - Receive and handle other events.
     *
     *  FOR OTHER VERSION WITH EventManager NOT IMPLEMENTING EventNotifierListener use:
     *  public <E extends EventManager & EventNotifierListener> void setEventHandlerListener(E e) {
     *      sameStuffHere;
     *  }
     *
     * @param eventManager the EventManager in particular. Must implement EventListener (obviously)
     */
    public void setEventHandlerListener(EventManager eventManager) {
        setEventListener(eventManager);

        if(eventManager!=null) {
            //avoid duplicates
            eventManager.removeListener(this);

            eventManager.addListener(this);
        }
    }

    //--------------------Handling of events---------------//

    /**
     * Send an event to the EventManager and handle it there
     *
     * @param event
     */
    protected void broadcastEvent(Event event) {
        if(eventListener==null)
            throw new NullPointerException("eventManager in BaseEventController can't be null");

        eventListener.broadcastEvent(event);
    }

}
