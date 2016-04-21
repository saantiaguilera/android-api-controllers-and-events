package com.santiago.controllersproject.simplecontrollerwithevents;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.santiago.controllers.BaseController;
import com.santiago.event.anotation.EventAsync;
import com.santiago.event.anotation.EventMethod;

/**
 * Created by santi on 08/01/16.
 */
public class TextViewViewController extends BaseController<TextView> {

    public TextViewViewController(Context context) {
        super(context);
    }

    @Override
    protected void onElementAttached(TextView textView) {
        textView.setText("TextViewViewController Test");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                broadcastEvent(new SimpleControllerWithEventsTextViewClickEvent());
            }
        });
    }

    @EventAsync
    @EventMethod(SimpleControllerWithEventsTextViewClickEvent.class)
    private void someMethodThatTriggersThatEvent() {
        Log.w("TextViewViewController", "Event of type: " + SimpleControllerWithEventsTextViewClickEvent.class.getName() + " dispatched from thread: " + Thread.currentThread().getName());
    }

}
