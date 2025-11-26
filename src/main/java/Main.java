import java.util.Scanner;

void main() {

    Scanner scanner = new Scanner(System.in);
    int input;

    main_loop:
    while (true) {
        IO.println("0. exit");
        IO.println("\n1. Add a task");
        IO.println("2. Remove a task");
        IO.println("3. Show all current tasks");
        IO.println("4. Mark a task done");
        IO.println("5. Edit an existing task");
        IO.println("6. Change the priority of an existing task");
        IO.println("7. Mark a task incomplete");
        IO.print("\nPlease pick an action (enter a number 0 - 7): ");

        if (scanner.hasNextInt()) {
            input = scanner.nextInt();
            scanner.nextLine();
        } else {
            IO.println("\nThat is an invalid input.");
            scanner.nextLine();
            continue;
        }


        switch (input) {
            case 0:
                break main_loop;
            case 1:
                TaskManager.addTask(scanner);
                break;
            case 2:
                TaskManager.removeTask(scanner);
                break;
            case 3:
                TaskManager.showTasks();
                break;
            case 4:
                TaskManager.markDone(scanner);
                break;
            case 5:
                TaskManager.editTask(scanner);
                break;
            case 6:
                TaskManager.changePriority(scanner);
                break;
            case 7:
                TaskManager.markTaskIncomplete(scanner);
                break;
            default:
                IO.println("That is an invalid input.");
        }
    }
}
