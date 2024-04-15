package ru.yandex.Practicum.util;

import ru.yandex.Practicum.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyLinkedList<E extends Task> {
    Node<E> first;
    Node<E> last;
    private final Map<Integer, Node<E>> history = new HashMap<>();

    public void linkLast(E e) {
        if (history.containsKey(e.getId())) {
            removeNode(e.getId());
        }

        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;

        history.put(e.getId(), newNode);
    }

    public void unlink(int id) {
        Node<E> x = history.get(id);
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }
        x.e = null;
        history.remove(id);
    }

    public void removeNode(int id) {
        unlink(id);
    }

    public List<E> getTasks() {
        List<E> historyList = new ArrayList<>();
        Node<E> item = first;
        while (item != null) {
            historyList.add(item.e);
            item = item.next;
        }
        return historyList;
    }

    public Map<Integer, Node<E>> getHashMap() {
        return history;
    }

    public Node<E> getNode(int id) {
        return history.get(id);
    }
}