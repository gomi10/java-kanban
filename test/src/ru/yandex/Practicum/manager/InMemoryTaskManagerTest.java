package ru.yandex.Practicum.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.Practicum.model.*;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {

    TaskManager taskManager;
    Task task1;
    Task task2;
    Epic epic;
    Subtask subtask1;
    Epic epic1;
    Epic epic2;

    @BeforeEach
    void create() {
        taskManager = Managers.getDefault();

        task1 = new Task("1", "1");
        task2 = new Task("2", "2");
        taskManager.createTask(task1);
        taskManager.createTask(task2);

        epic = new Epic("3", "3");
        taskManager.createEpic(epic);
        subtask1 = new Subtask("4", "4", epic.getId());
        taskManager.createSubtask(subtask1);

        epic1 = new Epic("5", "5");
        epic2 = new Epic("6", "6");
        taskManager.createEpic(epic1);
        taskManager.createEpic(epic2);
    }

    @Test
    void shouldAddTwoTasksInTaskManager() {
        assertNotNull(task1);
        assertNotNull(task2);
        assertEquals(2, taskManager.getListAllTasks().size());
    }

    @Test
    void shouldAddOneSubtaskInTaskManager() {
        assertNotNull(subtask1);
        assertEquals(1, taskManager.getListAllSubtasks().size());
    }

    @Test
    void shouldAddTwoEpicsInTaskManager() {
        assertNotNull(epic);
        assertNotNull(epic1);
        assertNotNull(epic2);
        assertEquals(3, taskManager.getListAllEpics().size());
    }

    @Test
    void shouldReturnObjectById() {
        int id = task1.getId();
        assertEquals(task1, taskManager.getTaskById(id));

        id = epic1.getId();
        assertEquals(epic1, taskManager.getEpicById(id));
    }

    @Test
    void shouldBePositiveIfTaskIsSameAfterAddInTaskManager() {
        assertEquals(task1, taskManager.getTaskById(1));
    }

    @Test
    void shouldRemoveSubtasksIdInEpic() {
        assertEquals(1, epic.getSubtaskIds().size());
        taskManager.removeById(subtask1.getId());
        assertEquals(0, epic.getSubtaskIds().size());
    }
}