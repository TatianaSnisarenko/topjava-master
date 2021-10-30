package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final int NOT_FOUND = 10;
    public static final int userIdForMeal = 100002;

    public static final Meal meal = new Meal(100002, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Breakfast", 500);

    public static final List<Meal> allMealsForUser = Stream.of(
            new Meal(100002, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Breakfast", 500),
            new Meal(100003, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Lunch", 1000),
            new Meal(100004, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Diner", 500),
            new Meal(100005, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Border meal", 100),
            new Meal(100006, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Breakfast", 1000),
            new Meal(100007, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Lunch", 500),
            new Meal(100008, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Diner", 410)
    ).sorted(Comparator.comparing(Meal::getDateTime).reversed()).collect(Collectors.toList());

    public static final List<Meal> mealsBetweenInclusive = allMealsForUser.stream()
            .filter(meal -> meal.getDate().equals(LocalDate.of(2020, Month.JANUARY, 30)))
            .collect(Collectors.toList());

    public static Meal getNew() {
        return new Meal(LocalDateTime.of(2020, Month.MAY, 30, 10, 0), "New meal", 510);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(meal);
        updated.setDateTime(LocalDateTime.of(2020, Month.MAY, 30, 11, 0));
        updated.setDescription("NewMeal");
        updated.setCalories(380);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparatorIgnoringFields().isEqualTo(expected);
    }
}
