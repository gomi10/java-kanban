package ru.yandex.Practicum.util;

import ru.yandex.Practicum.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyLinkedList {
    private Node<Task> first;
    private Node<Task> last;
    private final Map<Integer, Node<Task>> history = new HashMap<>();

    public void linkLast(Task task) {
        if (history.containsKey(task.getId())) {
            removeNode(task.getId());
        }

        final Node<Task> l = last;
        final Node<Task> newNode = new Node<>(l, task, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.setNext(newNode);

        history.put(task.getId(), newNode);
    }

    public void unlink(int id) {
        Node<Task> x = history.get(id);
        final Node<Task> next = x.getNext();
        final Node<Task> prev = x.getPrev();

        if (prev == null) {
            first = next;
        } else {
            prev.setNext(next);
            x.setPrev(null);
        }

        if (next == null) {
            last = prev;
        } else {
            next.setPrev(prev);
            x.setNext(null);
        }
        x.setE(null);
        history.remove(id);
    }

    public void removeNode(int id) {
        unlink(id);
    }

    public List<Task> getTasks() {
        List<Task> historyList = new ArrayList<>();
        Node<Task> item = first;
        while (item != null) {
            historyList.add(item.getE());
            item = item.getNext();
        }
        return historyList;
    }

    public Map<Integer, Node<Task>> getHashMap() {
        return history;
    }

    public Node<Task> getNode(int id) {
        return history.get(id);
    }

    public Node<Task> getFirst() {
        return first;
    }

    public void setFirst(Node<Task> first) {
        this.first = first;
    }

    public Node<Task> getLast() {
        return last;
    }

    public void setLast(Node<Task> last) {
        this.last = last;
    }
}