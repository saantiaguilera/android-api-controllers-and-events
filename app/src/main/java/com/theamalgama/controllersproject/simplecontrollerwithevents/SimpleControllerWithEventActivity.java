package com.theamalgama.controllersproject.simplecontrollerwithevents;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.theamalgama.controllersproject.R;
import com.theamalgama.events.ControllersProjectEventManager;
import com.theamalgama.events.interfaces.Event;
import com.theamalgama.events.interfaces.EventNotifierListener;

public class SimpleControllerWithEventActivity extends Activity implements EventNotifierListener {

    private TextViewController textViewController;

    private ControllersProjectEventManager eventManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //Create instance of controller
        textViewController = new TextViewController(this, (TextView) findViewById(R.id.activity_main_textview));

        //Create an eventManager and set it
        eventManager = new ControllersProjectEventManager(this);
        textViewController.setEventHandlerListener(eventManager);

        eventManager.addEventNotifierListener(this);
    }

    @Override
    public void onNewEvent(Event event) {
        if(event instanceof TextViewController.SimpleControllerWithEventsTextViewClickEvent)
            Log.w("SimpleControllerWithEventActivity", "Event of type SimpleControllerWithEventsTextViewClickEvent found");
    }
}
