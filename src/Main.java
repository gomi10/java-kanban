public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");

        TaskManager taskManager = new TaskManager();

        Task task1 = new Task("1", "1");
        Task task2 = new Task("2", "2");
        taskManager.createTask(task1);
        taskManager.createTask(task2);

        Epic epic1 = new Epic("3", "3");
        taskManager.createEpic(epic1);
        Subtask subtask1 = new Subtask("4", "4", epic1.getId());
        taskManager.createSubtask(subtask1);

        Epic epic2 = new Epic("5", "5");
        taskManager.createEpic(epic2);
        Subtask subtask2 = new Subtask("6", "6", epic2.getId());
        Subtask subtask3 = new Subtask("7", "7", epic2.getId());
        taskManager.createSubtask(subtask2);
        taskManager.createSubtask(subtask3);

        System.out.println(taskManager.getListAllTasks());
        System.out.println(taskManager.getListAllSubtasks());
        System.out.println(taskManager.getListAllEpics());
        System.out.println();

        taskManager.changeStatus(task1, Status.IN_PROGRESS);
        taskManager.changeStatus(task2, Status.DONE);
        taskManager.changeStatus(subtask1, Status.DONE);
        taskManager.changeStatus(subtask2, Status.IN_PROGRESS);
        taskManager.changeStatus(subtask3, Status.DONE);

        taskManager.printAll();
        System.out.println();

        taskManager.removeById(task1.getId());
        taskManager.removeById(epic1.getId());
        taskManager.printAll();
    }
}