package org.example.finalsdp.observer;

import java.util.Map;

public interface Observer {
    void update(String message, Map<String, Object> data);
}
