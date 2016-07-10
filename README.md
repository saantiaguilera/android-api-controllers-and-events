# Deprecated. 
###Events were continued in https://github.com/saantiaguilera/android-api-events

Controllers (Interactors+Presenters) / EventBus

-------------------------------------------------------------------
Events
-------------------------------------------------------------------

Create somewhere an instance of an EventManager. 
With this you will be able to start suscribing objects to "receive events" and also dispatching events to them!
```Java
eventManager = new EventManager(aContextWrapper, aTag);
```

If you want a class to start listening to events just
```Java
eventManager.addObservable(anActivity);
//or...
eventManager.removeObservable(anActivity);
```

Now this activity is able to start receiving Events !! But where does he receives them?

Lets say we have OneEvent and TwoEvent. He can receives them like:
```Java
@EventMethod(OneEvent.class)
private void oneMethod() {
    //Do something
}

@EventMethod(TwoEvent.class)
private void anotherMethod(TwoEvent event) {
    //Do something
}
```
Note: Method can only have either 1 param of the particular Event type or none

Note: When I get more time I will try to support the repeatable anotation since its not available yet
(Kinda like the @RequiresPermission() does)

So... Now we know how to receive events and how to start listening. What about sending one ?
```Java
///Somewhere in a method...
eventManager.dispatchEvent(new OneEvent());
```
And this will alone call all the methods that have its anotation and are observing that eventManager instance.

Also if you want to execute that particular method in another thread just do
```Java
@EventAsync
@EventMethod(SomeEvent.class)
private void whenCallingThisFromADispatchOfAnEventItWillBeAsynchronous() {
  //Do something...
}
```
But if you dispatch another event inside there, be careful that the observables will still execute their code in the main thread!
(Unless they also specified @EventAsync)

Finally, what is SomeEvent ?? 
```Java
public class SomeEvent extends Event {
    int aParam;
    String anotherParam;
    
    public SomeEvent(int aParam, String anotherParam) {
      this.aParam = aParam;
      this.anotherParam = anotherParam;
    }
    
    //getters...
}
```
Its just a subclassification of Event. Although it can have its own logic, ideally it should only be able to carry data from some
place to another.




-------------------------------------------------------------------
Controllers
-------------------------------------------------------------------

Usually you tend to see people putting all the logic and models inside the View. This is hella awful, not only for OOP but its also
Views with 500+ code lines.
So, for separating this a bit more, this is just a Controller/Interactor class.
This isnt only for views, it can also be used with everything you want to abstract

```Java
public class SomeController extends BaseController<SomeClass> {

    public SomeController(Context context) {
        super(context);
    }

    @Override
    protected void onElementAttached(SomeClass someClass) {
      //Do stuff with the class...
    }
    
    //Do all the logic things here. Leave the view just with setters of view things.

}
```
You can attach the view from inside or outside the class, by calling
```Java
someController.attachElement(aView);
```

The controller also can:
```Java
protected Context getContext(); //Get the context
public T getElement(); //Get the element attached
protected void dispatchEvent(Event someEvent); //Dispatch an event
protected void setContext(Context context); //Set a context
public void setDispatcher(EventDispatcherListener eventDispatcherListener); //Only is able to dispatch events
public void setEventManager(EventManager em); //Can dispatch + listen to events
public void attachElement(T t); //Attach him an element
```

Theres also a BaseRecyclerAdapter which is a wrapper of a RecyclerAdapter but you just have to override
```Java
protected @NonNull T createView(@NonNull ViewGroup parent, @NonNull int viewType);
protected void bindView(@NonNull T t, @NonNull E e);
```
Where T is the View and E is the model. And that is your whole adapter :)
