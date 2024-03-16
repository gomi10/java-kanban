package ru.yandex.Practicum.model;

import org.junit.jupiter.api.Test;
import ru.yandex.Practicum.manager.*;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    TaskManager taskManager = Managers.getDefault();

    @Test
    void twoTasksShouldBeEqualsIfTheirIdsAreEquals() {
        Task task = new Task("Name", "Description");
        taskManager.createTask(task);
        int taskId = task.getId();
        Task superTask = taskManager.getTaskById(taskId);

        assertNotNull(superTask);
        assertEquals(task, superTask);
    }

    @Test
    void shouldChangeStatus() {
        Task task = new Task("Name", "Description");
        taskManager.createTask(task);
        Status status = task.getStatus();
        int id = task.getId();

        Task task1 = new Task("Name", "Description", task.getId(), Status.DONE);
        taskManager.updateTask(task1);
        assertEquals(Status.NEW, status);
        assertEquals(Status.DONE, taskManager.getTaskById(id).getStatus());
    }
}