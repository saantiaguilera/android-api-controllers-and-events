package com.theamalgama.controllersproject.simplecontrollerwithevents;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.theamalgama.controllers.BaseEventController;
import com.theamalgama.events.interfaces.Event;

/**
 * Created by santi on 08/01/16.
 */
public class TextViewController extends BaseEventController<TextView> {

    protected TextViewController(Context context) {
        super(context);
    }

    protected TextViewController(Context context, TextView textView) {
        super(context, textView);
    }

    @Override
    protected void onViewAttached(TextView textView) {
        textView.setText("TextViewController Test");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEvent(new SimpleControllerWithEventsTextViewClickEvent());
            }
        });
    }

    @Override
    public void onNewEvent(Event event) {
        super.onNewEvent(event);

        if(event instanceof TextViewController.SimpleControllerWithEventsTextViewClickEvent)
            Log.w("TextViewController", "Event of type SimpleControllerWithEventsTextViewClickEvent found");
    }

    public class SimpleControllerWithEventsTextViewClickEvent implements Event {};

}
