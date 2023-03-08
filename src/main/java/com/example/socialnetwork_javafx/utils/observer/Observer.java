package com.example.socialnetwork_javafx.utils.observer;

import com.example.socialnetwork_javafx.utils.events.Event;

public interface Observer<E extends Event> {
    void update(E e);
}