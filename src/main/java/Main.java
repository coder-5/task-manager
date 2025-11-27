import java.util.Scanner;

void main() {

    Scanner scanner = new Scanner(System.in);
    int choice;

    System.out.println("Welcome to the task manager!");

    main_loop:
    while (true) {
        System.out.println("\n0. exit");
        System.out.println("1. Add a task");
        System.out.println("2. Remove a task");
        System.out.println("3. Show tasks");
        System.out.println("4. Mark a task done");
        System.out.println("5. Edit an existing task");
        System.out.println("6. Change the priority of an existing task");
        System.out.println("7. Mark a task incomplete");
        System.out.println("8. Delete all complete tasks");
        System.out.print("\nPlease pick an action (enter a number 0 - 8): ");

        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
            scanner.nextLine();
        } else {
            System.out.println("\nThat is an invalid input.");
            scanner.nextLine();
            continue;
        }


        switch (choice) {
            case 0:
                System.out.println("Thank You for using the task manager, see you soon");
                break main_loop;
            case 1:
                TaskManager.addTask(scanner);
                break;
            case 2:
                TaskManager.removeTask(scanner);
                break;
            case 3:
                System.out.println("0. return to main menu");
                System.out.println("1. Show all tasks");
                System.out.println("2. Show incomplete tasks");
                System.out.println("3. Show complete tasks");
                System.out.print("\nPlease pick an action (enter a number 0 - 3): ");
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 0:
                        continue;
                    case 1:
                        TaskManager.showTasks();
                        continue;
                    case 2:
                        TaskManager.showIncompleteTasks();
                        continue;
                    case 3:
                        TaskManager.showCompleteTasks();
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
                TaskManager.deleteAllCompletedTasks(scanner);
                break;
            default:
                System.out.println("That is an invalid input.");
                break;
        }
    }
}
