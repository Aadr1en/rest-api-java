import java.util.LinkedList;
import java.util.List;

public class TaskService {
    private List<Task> tasks = new LinkedList<>();
    private int nextId = 1;

    public Task createTask(String agence, String guichet) {
        Task task = new Task(nextId++, agence, guichet);
        tasks.add(task);
        return task;
    }

    public Task getTaskById(int id) {
        for (Task t : tasks) {
            if (t.getId() == id) return t;
        }
        return null;
    }

    public int getPendingCount() {
        return tasks.size();
    }
}
