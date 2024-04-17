package ru.yandex.Practicum.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.Practicum.model.Task;
import ru.yandex.Practicum.util.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {
    Task task1;
    Task task2;
    Task task3;
    MyLinkedList myLinkedList;
    HistoryManager historyManager;

    @BeforeEach
    void createTasks() {
        task1 = new Task("1", "1", 1);
        task2 = new Task("2", "2", 2);
        task3 = new Task("3", "3", 3);
        myLinkedList = new MyLinkedList();
        historyManager = Managers.getDefaultHistory();
    }

    @Test
    void shouldSaveHistory() {
        historyManager.add(task1);
        historyManager.add(task2);
        assertNotNull(historyManager.getHistory());
        assertEquals(2, historyManager.getHistory().size());
    }

    @Test
    void shouldReturnPrevAndNextItems() {
        myLinkedList.linkLast(task1);
        myLinkedList.linkLast(task2);
        myLinkedList.linkLast(task3);
        Node<Task> testTask1 = myLinkedList.getHashMap().get(task1.getId());
        Node<Task> testTask2 = myLinkedList.getHashMap().get(task3.getId());
        assertEquals(task2, testTask1.getNext().getE());
        assertEquals(task2, testTask2.getPrev().getE());
    }

    @Test
    void checkOfDuplicatesAndHistory() {
        historyManager.add(task1);
        historyManager.add(task2);
        historyManager.add(task1);
        List<Task> testList = List.of(task2, task1);
        assertEquals(testList, historyManager.getHistory());
    }

    @Test
    void checkUnlinkMethod() {
        myLinkedList.linkLast(task1);
        myLinkedList.linkLast(task2);
        myLinkedList.linkLast(task3);
        myLinkedList.unlink(2);
        assertEquals(task1, myLinkedList.getNode(3).getPrev().getE());
        assertEquals(task3, myLinkedList.getNode(1).getNext().getE());
    }

    @Test
    void shouldRemoveTask() {
        historyManager.add(task1);
        historyManager.add(task2);
        historyManager.add(task3);
        assertEquals(3, historyManager.getHistory().size());
        historyManager.remove(task2.getId());
        assertEquals(2, historyManager.getHistory().size());
    }
}