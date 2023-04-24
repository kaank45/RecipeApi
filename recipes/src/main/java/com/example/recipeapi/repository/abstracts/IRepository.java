package com.example.recipeapi.repository.abstracts;

import java.util.List;

public interface IRepository<T> {
    List<T> getAll();
    T add(T item);
}
