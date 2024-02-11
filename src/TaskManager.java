import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    int id = 0;
    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, Subtask> subtasks = new HashMap<>();
    HashMap<Integer, Epic> epics = new HashMap<>();

    public TaskManager() {
    }

    public void createTask(Task newTask) {
        int taskId = ++id;
        newTask.setId(taskId);
        tasks.put(taskId, newTask);
    }

    public void createSubtask(Subtask newSubtask) {
        int subtaskId = ++id;
        newSubtask.setId(subtaskId);
        subtasks.put(subtaskId, newSubtask);
        Epic epic = epics.get(newSubtask.getEpicId());
        epic.getSubtaskIds().add(subtaskId);
        checkEpicStatus(newSubtask.getEpicId());
    }

    public void createEpic(Epic newEpic) {
        int epicId = ++id;
        newEpic.setId(epicId);
        epics.put(epicId, newEpic);
    }

    public ArrayList<Task> getListAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Subtask> getListAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public ArrayList<Epic> getListAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public ArrayList<Subtask> getListAllSubtasksOneEpic(Epic epic) {
        ArrayList<Integer> subtaskIds = epic.getSubtaskIds();
        ArrayList<Subtask> subtasksOneEpic = new ArrayList<>();
        for (int subtaskId : subtaskIds) {
            subtasksOneEpic.add(subtasks.get(subtaskId));
        }
        return subtasksOneEpic;
    }

    public void printAll() {
        System.out.println(getListAllTasks());
        System.out.println(getListAllSubtasks());
        System.out.println(getListAllEpics());
    }

    public void clearAllTasks() {
        tasks.clear();
    }

    public void clearAllSubtasks() {
        for (Epic epic : epics.values()) {
            epic.getSubtaskIds().clear();
        }
        subtasks.clear();
        for (int id : epics.keySet()) {
            checkEpicStatus(id);
        }
    }

    public void clearAllEpics() {
        subtasks.clear();
        epics.clear();
    }

    public void removeById(Integer id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
        } else if (subtasks.containsKey(id)) {
            Subtask subtask = subtasks.get(id);
            int epicId = subtask.getEpicId();
            epics.get(epicId).getSubtaskIds().remove(id);
            subtasks.remove(id);
            checkEpicStatus(epicId);
        } else if (epics.containsKey(id)) {
            ArrayList<Integer> subtaskIds = epics.get(id).getSubtaskIds();
            for (int subtaskId : subtaskIds) {
                subtasks.remove(subtaskId);
            }
            epics.remove(id);
        }
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public Subtask getSubtaskById(int id) {
        return subtasks.get(id);
    }

    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    public void updateTask(Task newTask) {
        tasks.put(newTask.getId(), newTask);
    }

    public void updateSubtask(Subtask newSubtask) {
        subtasks.put(newSubtask.getId(), newSubtask);
        checkEpicStatus(newSubtask.getEpicId());
    }

    public void updateEpic(Epic newEpic) {
        epics.put(newEpic.getId(), newEpic);
        checkEpicStatus(newEpic.getId());
    }

    public void changeStatus(Task task, Status newStatus) {
        task.setStatus(newStatus);
    }

    public void changeStatus(Subtask subtask, Status newStatus) {
        subtask.setStatus(newStatus);
        checkEpicStatus(subtask.getEpicId());
    }

    public void checkEpicStatus(int epicId) {
        int countStatusNew = 0;
        int countStatusDone = 0;
        Epic epic = epics.get(epicId);
        ArrayList<Integer> subtaskIds = epic.getSubtaskIds();

        if (subtaskIds == null) {
            epic.setStatus(Status.NEW);
            return;
        }

        for (int subtaskId : subtaskIds) {
            Status status = subtasks.get(subtaskId).getStatus();
            if (status == Status.NEW) {
                countStatusNew++;
            } else if (status == Status.DONE) {
                countStatusDone++;
            }
        }

        if (subtaskIds.size() == countStatusNew) {
            epics.get(epicId).setStatus(Status.NEW);
        } else if (subtaskIds.size() == countStatusDone) {
            epics.get(epicId).setStatus(Status.DONE);
        } else {
            epics.get(epicId).setStatus(Status.IN_PROGRESS);
        }
    }
}