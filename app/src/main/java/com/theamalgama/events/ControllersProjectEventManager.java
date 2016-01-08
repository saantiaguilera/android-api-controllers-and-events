package com.theamalgama.events;

import android.app.Activity;
import android.widget.Toast;

import com.theamalgama.controllersproject.simplecontrollerwithevents.TextViewController;
import com.theamalgama.events.interfaces.Event;

public class ControllersProjectEventManager extends EventManager {

    public ControllersProjectEventManager(Activity activity) {
        super(activity);
    }

    @Override
    public boolean onEvent(Event event) {

        if(super.onEvent(event))
            return true;

        if(event instanceof TextViewController.SimpleControllerWithEventsTextViewClickEvent) {
            Toast.makeText(getActivity(), "EventManager got an event of TextViewClick !", Toast.LENGTH_SHORT).show();
            broadcastEvent(event);
        }

        return false;
    }

}
