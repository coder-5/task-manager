import java.util.Scanner;

void main() {

    Scanner scanner = new Scanner(System.in);
    int choice;

    scanner.useDelimiter("\n");

    System.out.println("Welcome to the task manager!");

    // Infinite loop for the main menu, only breaks on case 0
    main_loop:
    while (true) {
        System.out.println("\n0. exit");
        System.out.println("1. Add a task");
        System.out.println("2. Remove a task");
        System.out.println("3. Show tasks(sub menu)");
        System.out.println("4. Mark a task done");
        System.out.println("5. Edit an existing task");
        System.out.println("6. Change the priority of an existing task");
        System.out.println("7. Mark a task incomplete");
        System.out.println("8. Mass Delete(sub menu)");
        System.out.print("\nPlease pick an action (enter a number 0 - 8): ");

        // Read input as a line to allow advanced parsing
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
        } else {
            System.out.println("\nThat is an invalid input.");
            scanner.nextLine(); // Clear buffer
            continue;
        }


        // Delegate to TaskManager based on the calculated choice
        switch (choice) {
            case 0:
                System.out.println("Thank You for using the task manager, see you soon");
                break main_loop; // Exit program
            case 1:
                TaskManager.addTask(scanner);
                break;
            case 2:
                TaskManager.removeTask(scanner);
                break;
            case 3:
                // Sub-menu for viewing tasks in different ways
                System.out.println("0. return to main menu");
                System.out.println("1. Show all tasks");
                System.out.println("2. Show incomplete tasks");
                System.out.println("3. Show complete tasks");
                System.out.println("4. Show high priority tasks");
                System.out.println("5. Show medium priority tasks");
                System.out.println("6. Show low priority tasks");
                System.out.print("\nPlease pick an action (enter a number 0 - 6): ");
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                } else {
                    System.out.println("That is invalid.");
                    continue;
                }
                scanner.nextLine();
                switch (choice) {
                    case 0:
                        continue;
                    case 1:
                        TaskManager.showTasks(scanner);
                        continue;
                    case 2:
                        TaskManager.showOnlyCompleteOrIncomplete(scanner, false); // false = incomplete
                        continue;
                    case 3:
                        TaskManager.showOnlyCompleteOrIncomplete(scanner, true); // true = complete
                        continue;
                    case 4:
                        TaskManager.showCertainPriorityTasks(scanner, 'h');
                        continue;
                    case 5:
                        TaskManager.showCertainPriorityTasks(scanner, 'm');
                        continue;
                    case 6:
                        TaskManager.showCertainPriorityTasks(scanner, 'l');
                        continue;
                    default:
                        System.out.println("That is an invalid input.");
                        continue;
                }
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
                // Sub-menu for mass deletion
                System.out.println("0. return to main menu");
                System.out.println("1. Delete all complete tasks");
                System.out.println("2. Delete all incomplete tasks");
                System.out.println("3. Delete all tasks");
                System.out.print("\nPlease pick an action (enter a number 0 - 3): ");
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                } else {
                    System.out.println("That is invalid.");
                    continue;
                }
                scanner.nextLine();
                switch (choice) {
                    case 0:
                        continue;
                    case 1:
                        TaskManager.deleteAllCompletedOrIncompleteTasks(scanner, true);
                        continue;
                    case 2:
                        TaskManager.deleteAllCompletedOrIncompleteTasks(scanner, false);
                        continue;
                    case 3:
                        TaskManager.deleteAllTasks(scanner);
                        continue;
                    default:
                        System.out.println("That is an invalid input.");
                        continue;
                }
            default:
                System.out.println("That is an invalid input.");
                break;
        }
    }
}