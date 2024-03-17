package ru.yandex.Practicum.manager;

import ru.yandex.Practicum.model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private final List<Task> historyList = new ArrayList<>();
    private static final int MAX_LIST_SIZE = 10;

    @Override
    public void add(Task task) {
        if (historyList.size() == MAX_LIST_SIZE) {
            historyList.removeFirst();
        }
        historyList.add(task);
    }

    @Override
    public List<Task> getHistory() {
        return historyList;
    }
}