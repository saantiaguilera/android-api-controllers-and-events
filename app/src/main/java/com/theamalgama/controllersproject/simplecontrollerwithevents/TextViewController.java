package com.theamalgama.controllersproject.simplecontrollerwithevents;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.theamalgama.controllers.BaseEventController;
import com.theamalgama.event.anotation.EventMethod;

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
                broadcastEvent(new SimpleControllerWithEventsTextViewClickEvent());
            }
        });
    }

    @EventMethod( SimpleControllerWithEventsTextViewClickEvent.class )
    private void someMethodThatTriggersThatEvent() {
        Log.w("TextViewController", "Event of type SimpleControllerWithEventsTextViewClickEvent found, event has no params");
    }

}
