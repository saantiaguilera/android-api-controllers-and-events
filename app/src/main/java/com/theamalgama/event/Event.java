package com.theamalgama.event;

import com.theamalgama.event.anotation.EventMethod;

import java.lang.reflect.Method;

/**
 * Created by santiaguilera@theamalgama.com on 01/03/16.
 */
public class Event {

    public void dispatchEventTo( Object listener ) {
        for (Class eventClass = this.getClass(); eventClass != Event.class; eventClass = eventClass.getSuperclass())
            dispatchMethodNamed( listener, eventClass );
    }

    private void dispatchMethodNamed( Object listener, Class eventClass ) {
        for(Method method : listener.getClass().getDeclaredMethods()) {
            EventMethod anotation = method.getAnnotation(EventMethod.class);
            if (anotation!=null && anotation.value()==eventClass)
               invokeMethod(method, listener);
        }
    }

    private void invokeMethod( Method m, Object receiver ) {
        try {
            m.setAccessible(true);

            if (m.getParameterTypes().length == 0)
                m.invoke(receiver);
            else m.invoke(receiver, this);
        } catch ( Exception e ) {
            throw new RuntimeException( e );
        }
    }

}
