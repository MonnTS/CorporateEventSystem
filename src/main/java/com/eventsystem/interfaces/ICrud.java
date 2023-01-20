package com.eventsystem.interfaces;

import java.util.List;
import java.util.Optional;

public interface ICrud<T> {
    Optional<T> getEntityById(Integer id);
    List<T> getEntities();
    void createEntity(T t);
    void updateEntity(T t);
    void deleteEntity(T t);
}
