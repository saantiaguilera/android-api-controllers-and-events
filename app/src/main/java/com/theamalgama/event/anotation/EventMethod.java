package com.theamalgama.event.anotation;

import com.theamalgama.event.Event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by santi on 16/03/16.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventMethod {

    Class<? extends Event> value() default Event.class;

}
