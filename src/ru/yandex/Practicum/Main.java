package ru.yandex.Practicum;

import ru.yandex.Practicum.manager.Managers;
import ru.yandex.Practicum.manager.TaskManager;
import ru.yandex.Practicum.model.Epic;
import ru.yandex.Practicum.model.Subtask;
import ru.yandex.Practicum.model.Task;

public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");

        TaskManager taskManager = Managers.getDefault();

        Task task1 = new Task("1", "1");
        Task task2 = new Task("2", "2");
        taskManager.createTask(task1);
        taskManager.createTask(task2);

        Epic epic1 = new Epic("3", "3");
        taskManager.createEpic(epic1);
        Subtask subtask1 = new Subtask("4", "4", epic1.getId());
        Subtask subtask2 = new Subtask("5", "5", epic1.getId());
        Subtask subtask3 = new Subtask("6", "6", epic1.getId());
        taskManager.createSubtask(subtask1);
        taskManager.createSubtask(subtask2);
        taskManager.createSubtask(subtask3);

        Epic epic2 = new Epic("7", "7");
        taskManager.createEpic(epic2);

        taskManager.getTaskById(task1.getId());
        taskManager.getSubtaskById(subtask1.getId());
        taskManager.getTaskById(task2.getId());
        taskManager.getEpicById(epic2.getId());
        taskManager.getTaskById(task2.getId());
        taskManager.getSubtaskById(subtask2.getId());
        taskManager.getTaskById(task1.getId());
        System.out.println(taskManager.getHistory());

        taskManager.removeById(epic2.getId());
        taskManager.removeById(task2.getId());
        System.out.println(taskManager.getHistory());
    }
}