package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealInMemoryRepository {
    public static Map<Integer, Meal> meals;
    private static List<Meal> mealList;
    private static AtomicInteger counter = new AtomicInteger(1);

    static {
        mealList = Arrays.asList(
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );
        meals = new ConcurrentHashMap<>();
        mealList.forEach(MealInMemoryRepository::save);
    }

    public static void save(Meal meal) {
        if (meal.getId() == 0) {
            int id = counter.getAndIncrement();
            meal.setId(id);
        }
        meals.put(meal.getId(), meal);
    }

    public static Meal findById(Integer id) {
        return meals.get(id);
    }

    public static List<Meal> findAll() {
        return new ArrayList<>(meals.values());
    }

    public static void delete(Integer id) {
        meals.remove(id);
    }
}
