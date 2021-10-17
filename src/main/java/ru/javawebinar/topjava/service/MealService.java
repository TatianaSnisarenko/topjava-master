package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.List;

public class MealService {
    private final MealRepository mealRepository;

    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public void save(Meal meal){
        mealRepository.save(meal);
    }

    public Meal findById(Integer id) {
        return mealRepository.findById(id);
    }

    public List<Meal> findAll() {
        return mealRepository.findAll();
    }

    public void delete(Integer id) {
        mealRepository.delete(id);
    }
}
