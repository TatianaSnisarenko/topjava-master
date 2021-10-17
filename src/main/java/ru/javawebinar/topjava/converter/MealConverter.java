package ru.javawebinar.topjava.converter;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

public class MealConverter {
    public static MealTo toMealTo(Meal meal, boolean excess) {
        return new MealTo(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess);
    }

    public static Meal fromMealTo(MealTo mealTo) {
        return new Meal(mealTo.getId(), mealTo.getDateTime(), mealTo.getDescription(), mealTo.getCalories());
    }

}
