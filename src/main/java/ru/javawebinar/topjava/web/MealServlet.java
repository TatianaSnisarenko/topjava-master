package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    private static final int CALORIES_PER_DAY = 2000;

    private MealService mealService;

    public MealServlet() {
        mealService = new MealService(new MealRepository());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getPathInfo();
        if (action == null) {
            action = request.getServletPath();
        }

        try {
            switch (action) {
                case "/new":
                    showNewMealForm(request, response);
                    break;
                case "/insert":
                    insertMeal(request, response);
                    break;
                case "/delete":
                    deleteMeal(request, response);
                    break;
                case "/edit":
                    showEditFormForMeal(request, response);
                    break;
                case "/update":
                    try {
                        updateMeal(request, response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    listMeals(request, response);
                    break;
            }
        } catch (IOException ex) {
            throw new ServletException(ex);
        }
    }

    private void updateMeal(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Meal meal = mealService.findById(id);
        setAllDataForMealFromJSP(request, meal);
        mealService.save(meal);
        response.sendRedirect("meals.jsp");
    }

    private void deleteMeal(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
           mealService.delete(id);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        response.sendRedirect("meals.jsp");
    }

    private void insertMeal(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Meal meal = new Meal();
        setAllDataForMealFromJSP(request, meal);
        mealService.save(meal);
        response.sendRedirect("meals.jsp");
    }

    private void setAllDataForMealFromJSP(HttpServletRequest request,Meal meal) {
        String description = request.getParameter("description");
        Integer calories = Integer.parseInt(request.getParameter("calories"));
        String dateTimeString = request.getParameter("dateTime");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ISO_DATE_TIME);
        meal.setDescription(description);
        meal.setCalories(calories);
        meal.setDateTime(dateTime);
    }

    private void showEditFormForMeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Meal meal = mealService.findById(id);
        request.setAttribute("meal", meal);
        request.getRequestDispatcher("/meal-form.jsp").forward(request, response);
    }

    private void showNewMealForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/meal-form.jsp").forward(request, response);
    }

    private void listMeals(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to meals");
        List<Meal> meals = mealService.findAll();
        List<MealTo> mealTos = MealsUtil.filteredByStreams(meals, LocalTime.MIN, LocalTime.MAX, CALORIES_PER_DAY);
        request.setAttribute("meals", mealTos);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
