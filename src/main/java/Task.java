public class Task {
    String task;
    boolean done;
    char priority;

    public Task(String task, boolean done, char priority) {
        this.task = task;
        this.done = done;
        this.priority = priority;
    }

    @Override
    public String toString() {
        String doneString;
        String priorityString;


        if (done) {
            doneString = " [DONE]";
        } else {
            doneString = "";
        }

        if (priority == 'h') {
            priorityString = "High";
        } else if (priority == 'm') {
            priorityString = "medium";
        } else {
            priorityString = "low";
        }

        return String.format("%s%s, This task is %s priority", task, doneString, priorityString);
    }
}
