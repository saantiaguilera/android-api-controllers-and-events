package com.santiago.controllers;

import android.content.Context;
import android.content.ContextWrapper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.santiago.event.Event;
import com.santiago.event.EventManager;
import com.santiago.event.listener.EventDispatcherListener;

import java.lang.ref.WeakReference;

/**
 * Base controller which implements the sending/receiving of events.
 * You can broadcast a particular event to the EventManager
 * For receiving and handling events in a controller you will need override the getEventNotifierListener() and implmement an instance of EventNotifierListener
 *
 * Created by santiaguilera@theamalgama.com on 08/01/16.
 */
public abstract class BaseController<T> {

    private WeakReference<ContextWrapper> context;

    private T t;

    private EventDispatcherListener eventDispatcherListener;

    public BaseController(@NonNull ContextWrapper context) {
        setContext(context);
    }

    //------------------CONTEXT---------------------//

    protected @NonNull Context getContext() {
        if(context == null || context.get() == null)
            throw new NullPointerException("No context found");

        return context.get();
    }

    protected void setContext(@NonNull ContextWrapper context){
        this.context = new WeakReference<>(context);
    }

    //-------------------ELEMENT--------------------//

    /**
     * Attach an element to the controller
     * @param t
     */
    public void attachElement(@Nullable T t) {
        this.t = t;

        if (t != null)
            onElementAttached(t);
    }

    /**
     * Called after attaching an element, subclass
     * should implement it
     * @param t
     */
    protected abstract void onElementAttached(@NonNull T t);

    /**
     * Get the element attached to the view
     * @return
     */
    public @Nullable T getElement() {
        return t;
    }

    //-------------------SETTERS------------------//

    /**
     * Set an EventDispatcherListener which will broadcast events.
     *
     * @param eventDispatcherListener used for sending events to the EventManager and the listening classes
     */
    public void setDispatcher(@NonNull EventDispatcherListener eventDispatcherListener){
        this.eventDispatcherListener = eventDispatcherListener;
    }

    /**
     * @return eventDispatcherListener instance
     */
    public @NonNull
    EventDispatcherListener getDispatcher() {
        return eventDispatcherListener;
    }

    /**
     * Short version of everything you need for the events. With this you will be able to:
     *  - Broadcast events to the eventmanager and the listening classes.
     *  - Receive and handle other events.
     *
     * @param eventManager the EventManager in particular. Must implement EventDispatcherListener (obviously)
     */
    public void setEventManager(@NonNull EventManager eventManager) {
        setDispatcher(eventManager);

        //avoid duplicates
        eventManager.removeObservable(this);
        eventManager.addObservable(this);
    }

    //--------------------Handling of events---------------//

    /**
     * Send an event, process it (optional) in the eventmanager, and
     * dispatch it to all the listeners
     *
     * @param event
     */
    protected void dispatchEvent(@NonNull Event event) {
        if(eventDispatcherListener == null)
            throw new NullPointerException(EventDispatcherListener.class.getSimpleName() + " instance in " + this.getClass().getSimpleName() + " can't be null");

        eventDispatcherListener.dispatchEvent(event);
    }

}
