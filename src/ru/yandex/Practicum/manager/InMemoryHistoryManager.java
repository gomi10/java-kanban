package ru.yandex.Practicum.manager;

import ru.yandex.Practicum.model.Task;
import ru.yandex.Practicum.util.MyLinkedList;

import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    MyLinkedList myLinkedList = new MyLinkedList();

    @Override
    public void add(Task task) {
        myLinkedList.linkLast(task);
    }

    @Override
    public List<Task> getHistory() {
        return myLinkedList.getTasks();
    }

    @Override
    public void remove(int id) {
        myLinkedList.removeNode(id);
    }
}