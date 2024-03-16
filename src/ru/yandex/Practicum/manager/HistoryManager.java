package ru.yandex.Practicum.manager;

import ru.yandex.Practicum.model.Task;

import java.util.ArrayList;

public interface HistoryManager {

    void add(Task task);

    ArrayList<Task> getHistory();

}