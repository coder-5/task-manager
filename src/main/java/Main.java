import java.util.Scanner;

void main() {

    Scanner scanner = new Scanner(System.in);
    int input;

    main_loop:
    while (true) {
        System.out.println("\n0. exit");
        System.out.println("1. Add a task");
        System.out.println("2. Remove a task");
        System.out.println("3. Show all current tasks");
        System.out.println("4. Mark a task done");
        System.out.println("5. Edit an existing task");
        System.out.println("6. Change the priority of an existing task");
        System.out.println("7. Mark a task incomplete");
        System.out.println("8. Show only incomplete tasks");
        System.out.println("9. Show only complete tasks");
        System.out.print("\nPlease pick an action (enter a number 0 - 9): ");

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
            case 8:
                TaskManager.showIncompleteTasks();
                break;
            case 9:
                TaskManager.showCompleteTasks();
                break;
            default:
                System.out.println("That is an invalid input.");
        }
    }
}
