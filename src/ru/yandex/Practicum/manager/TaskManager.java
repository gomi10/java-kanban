package ru.yandex.Practicum.manager;

import ru.yandex.Practicum.model.*;

import java.util.ArrayList;

public interface TaskManager {
    void createTask(Task newTask);

    void createSubtask(Subtask newSubtask);

    void createEpic(Epic newEpic);

    ArrayList<Task> getListAllTasks();

    ArrayList<Subtask> getListAllSubtasks();

    ArrayList<Epic> getListAllEpics();

    ArrayList<Subtask> getListAllSubtasksOneEpic(Epic epic);

    void printAll();

    void clearAllTasks();

    void clearAllSubtasks();

    void clearAllEpics();

    void removeById(Integer id);

    Task getTaskById(int id);

    Subtask getSubtaskById(int id);

    Epic getEpicById(int id);

    void updateTask(Task newTask);

    void updateSubtask(Subtask newSubtask);

    void updateEpic(Epic newEpic);

    void changeStatus(Task task, Status newStatus);

    void changeStatus(Subtask subtask, Status newStatus);

    void checkEpicStatus(int epicId);

    ArrayList<Task> getHistory();
}