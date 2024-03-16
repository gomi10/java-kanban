package ru.yandex.Practicum.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.Practicum.manager.Managers;
import ru.yandex.Practicum.manager.TaskManager;

import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {

    Epic epic;
    TaskManager taskManager;

    @BeforeEach
    void create() {
        taskManager = Managers.getDefault();
        epic = new Epic("Name", "Description");
        taskManager.createEpic(epic);
    }

    @Test
    void twoSubtasksShouldBeEqualsIfTheirIdsAreEquals() {
        Subtask subtask = new Subtask("Name", "Description", epic.getId());
        taskManager.createSubtask(subtask);
        int subtaskId = subtask.getId();
        Subtask superSubtask = taskManager.getSubtaskById(subtaskId);

        assertNotNull(superSubtask);
        assertEquals(subtask, superSubtask);
    }

    @Test
    void shouldChangeStatus() {
        Subtask subtask = new Subtask("Name", "Description", epic.getId());
        taskManager.createSubtask(subtask);
        Status status = subtask.getStatus();
        int id = subtask.getId();

        Subtask subtask1 = new Subtask("Name", "Description", subtask.getId(), Status.DONE, epic.getId());
        taskManager.updateSubtask(subtask1);
        assertEquals(Status.NEW, status);
        assertEquals(Status.DONE, taskManager.getSubtaskById(id).getStatus());
    }
}