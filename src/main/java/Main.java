import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean keepGoing = true;
        int input = 0;


        while (keepGoing) {
            System.out.println("\n1. Add a task");
            System.out.println("2. Remove a task");
            System.out.println("3. Show all current tasks");
            System.out.println("4. Mark a task done");
            System.out.println("5. Edit an existing task\n");
            System.out.print("Please pick an action (enter a number 1 - 5): ");

            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("\nThat is an invalid input.");
                scanner.nextLine();
                continue;
            }


            switch (input) {
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
                default:
                    System.out.println("That is an invalid input.");
            }
        }
    }

}
