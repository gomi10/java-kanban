package ru.yandex.Practicum.model;

import ru.yandex.Practicum.manager.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {

    TaskManager taskManager = Managers.getDefault();

    @Test
    void twoEpicsShouldBeEqualsIfTheirIdsAreEquals() {
        Epic epic = new Epic("Name", "Description");
        taskManager.createEpic(epic);
        int epicId = epic.getId();
        Epic superEpic = taskManager.getEpicById(epicId);

        assertNotNull(superEpic);
        assertEquals(epic, superEpic);
    }

    @Test
    void shouldChangeStatus() {
        Epic epic = new Epic("Name", "Description");
        taskManager.createEpic(epic);
        Subtask subtask1 = new Subtask("Name", "Description", epic.getId());
        Subtask subtask2 = new Subtask("Name", "Description", epic.getId());
        taskManager.createSubtask(subtask1);
        taskManager.createSubtask(subtask2);

        Status status = epic.getStatus();
        assertEquals(Status.NEW, status);

        Subtask subtask3 = new Subtask("Name", "Description", subtask1.getId(), Status.DONE, epic.getId());
        taskManager.updateSubtask(subtask3);

        status = epic.getStatus();
        assertEquals(Status.IN_PROGRESS, status);

        Subtask subtask4 = new Subtask("Name", "Description", subtask2.getId(), Status.DONE, epic.getId());
        taskManager.updateSubtask(subtask4);

        status = epic.getStatus();
        assertEquals(Status.DONE, status);
    }
}