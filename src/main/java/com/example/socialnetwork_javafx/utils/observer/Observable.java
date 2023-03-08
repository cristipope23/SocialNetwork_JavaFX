package com.example.socialnetwork_javafx.utils.observer;

import com.example.socialnetwork_javafx.utils.events.Event;

public interface Observable<E extends Event> {
    void addObserver(Observer<E> e);
    void removeObserver(Observer<E> e);
    void notifyObservers(E t);
}
