package ru.javawebinar.topjava.repository;

import java.util.List;

public interface Repository<T> {

    void save(T entity);

    T findById(Integer id);

    List<T> findAll();

    void delete(Integer id);
}
