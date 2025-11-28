public class Task implements Comparable<Task> {
    String task;
    boolean done;
    char priority;

    public Task(String task, boolean done, char priority) {
        this.task = task;
        this.done = done;
        this.priority = priority;
    }

    private static int priorityRank(char p) {
        return switch (Character.toLowerCase(p)) {
            case 'h' -> 0;
            case 'm' -> 1;
            case 'l' -> 2;
            default -> 3;
        };
    }

    @Override
    public int compareTo(Task other) {
        // 1) Unfinished before finished
        int thisDone = this.done ? 1 : 0;
        int otherDone = other.done ? 1 : 0;
        int cmp = Integer.compare(thisDone, otherDone);
        if (cmp != 0) return cmp;

        // 2) Priority: High > Medium > Low
        cmp = Integer.compare(priorityRank(this.priority), priorityRank(other.priority));
        if (cmp != 0) return cmp;

        // 3) Task name A→Z (case-insensitive)
        String a = this.task == null ? "" : this.task;
        String b = other.task == null ? "" : other.task;
        return a.compareToIgnoreCase(b);
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
