import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> subtaskIds = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description);
    }

    public Epic(String name, String description, int id) {
        super(name, description,id);
    }

    public Epic(String name, String description, int id, ArrayList<Integer> subtaskIds) {
        super(name, description,id);
        this.subtaskIds = subtaskIds;
    }

    public Epic(String name, String description, ArrayList<Integer> subtaskIds) {
        super(name, description);
        this.subtaskIds = subtaskIds;
    }

    public ArrayList<Integer> getSubtaskIds() {
        return this.subtaskIds;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "name='" + super.getName() + '\'' +
                ", description='" + super.getDescription() + '\'' +
                ", id=" + super.getId() +
                ", AmountOfSubtasks=" + subtaskIds.size() +
                ", status=" + super.getStatus() +
                '}';
    }
}