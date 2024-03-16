package ru.yandex.Practicum.manager;

import org.junit.jupiter.api.Test;
import ru.yandex.Practicum.model.Task;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {

    HistoryManager historyManager = Managers.getDefaultHistory();

    @Test
    void shouldSaveHistory() {
        Task task = new Task("1", "1");
        historyManager.add(task);
        ArrayList<Task> historyList = historyManager.getHistory();
        assertNotNull(historyList);
        assertEquals(1, historyList.size());
    }
}