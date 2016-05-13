package com.santiago.controllersproject.simplecontrollerwithevents;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;

import com.santiago.controllersproject.R;
import com.santiago.event.EventManager;
import com.santiago.event.anotation.EventMethod;

public class SimpleControllerWithEventActivity extends Activity {

    private TextViewViewController textViewController;

    private EventManager eventManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());

        setContentView(R.layout.activity_main);

        //Create instance of controller
        textViewController = new TextViewViewController(this);
        textViewController.attachElement((TextView) findViewById(R.id.activity_main_textview));

        //Create an eventManager and set it
        eventManager = new EventManager(this, this);
        textViewController.setEventHandlerListener(eventManager);

        eventManager.addObservable(this);
    }

    @EventMethod(SimpleControllerWithEventsTextViewClickEvent.class)
    private void someMethoasdThatTriggersThatEvent(SimpleControllerWithEventsTextViewClickEvent event) {
        Log.w("SimpleContActivity", "Method from type: " + SimpleControllerWithEventsTextViewClickEvent.class.getName() + " dispatched from thread: " + Thread.currentThread().getName());
    }

}
