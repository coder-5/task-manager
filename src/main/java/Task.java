public class Task implements Comparable<Task> {
    String task;    // The description/name of the task
    boolean done;   // Status: true if completed, false otherwise
    char priority;  // Priority level: 'h' (high), 'm' (medium), 'l' (low)

    // Constructor to initialize a new task
    public Task(String task, boolean done, char priority) {
        this.task = task;
        this.done = done;
        this.priority = priority;
    }

    // Helper method to convert priority characters into a numerical rank for sorting.
    // Lower number = Higher priority.
    private static int priorityRank(char p) {
        return switch (Character.toLowerCase(p)) {
            case 'h' -> 0; // High priority is rank 0 (top)
            case 'm' -> 1; // Medium
            case 'l' -> 2; // Low
            default -> 3;  // Unknown/Lowest
        };
    }

    // This method determines the sorting order of tasks in the list.
    // It is called automatically when Collections.sort() or List.sort() is used.
    @Override
    public int compareTo(Task other) {
        // 1) Sort by Status: Unfinished tasks come before finished tasks.
        // false (0) < true (1), so unfinished comes first if we compare directly.
        int thisDone = this.done ? 1 : 0;
        int otherDone = other.done ? 1 : 0;
        int cmp = Integer.compare(thisDone, otherDone);
        if (cmp != 0) return cmp;

        // 2) Sort by Priority: High > Medium > Low.
        // We compare the integer ranks derived from the helper method.
        cmp = Integer.compare(priorityRank(this.priority), priorityRank(other.priority));
        if (cmp != 0) return cmp;

        // 3) Sort by Name: Alphabetical order (A-Z), case-insensitive.
        String a = this.task == null ? "" : this.task;
        String b = other.task == null ? "" : other.task;
        return a.compareToIgnoreCase(b);
    }

    // Controls how the task looks when printed to the console.
    @Override
    public String toString() {
        String doneString;
        String priorityString;

        // Label completed tasks
        if (done) {
            doneString = " [DONE]";
        } else {
            doneString = "";
        }

        // Expand char priority to full word
        if (priority == 'h') {
            priorityString = "High";
        } else if (priority == 'm') {
            priorityString = "medium";
        } else {
            priorityString = "low";
        }

        // Format: "Buy Milk [DONE], This task is High priority"
        return String.format("%s%s, This task is %s priority", task, doneString, priorityString);
    }
}