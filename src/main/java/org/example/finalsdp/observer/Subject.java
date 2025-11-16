package org.example.finalsdp.observer;

import java.util.Map;

public interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers(String message, Map<String, Object> data);
}
