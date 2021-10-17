package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public class MealRepository implements Repository<Meal> {
    @Override
    public void save(Meal meal) {
        MealInMemoryRepository.save(meal);
    }

    @Override
    public Meal findById(Integer id) {
        return MealInMemoryRepository.findById(id);
    }

    @Override
    public List<Meal> findAll() {
        return MealInMemoryRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        MealInMemoryRepository.delete(id);
    }
}
