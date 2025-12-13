import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskManager {
    // The main list that holds all Task objects
    static ArrayList<Task> tasks = new ArrayList<>();

    // Logic to create and add a new task
    public static void addTask(Scanner scanner) {
        boolean keepGoing = true;
        String taskName = "";
        boolean done = false;
        boolean taskMade = false;
        char priority = 0;
        boolean taskConfirmed = false;

        // Main_loop label allows breaking/continuing the outer loop from inside nested structures
        Main_loop:
        while (keepGoing){

            // Loop until a valid task name is entered
            while (!taskMade) {
                System.out.print("Please enter the task or enter \"CANCEL\" to cancel: ");
                taskName = scanner.nextLine();
                taskMade = true;
            }

            // Allow user to abort creation
            if (taskName.equals("CANCEL")) {
                return;
            }

            // Confirm the input is correct
            while (!taskConfirmed) {
                if (taskName.isEmpty() || taskName.equals("\n")) {
                    System.out.println("The was no input, please try again.");
                    taskMade = false;
                    continue Main_loop; // Restart outer loop to get name again
                } else {
                    System.out.print("Is the task \"" + taskName + "\" (true or false): ");
                    if (scanner.hasNextBoolean()) {
                        if (scanner.nextBoolean()) {
                            scanner.nextLine(); // Consume newline
                            taskConfirmed = true;
                        } else {
                            scanner.nextLine(); // Consume newline
                            taskMade = false;
                            continue Main_loop; // Restart if user says false
                        }
                    } else {
                        scanner.nextLine(); // Clear invalid input
                        System.out.println("That was invalid, please try again");
                    }
                }
            }

            // Get Priority (High, Medium, Low)
            if (priority == 0) {
                System.out.print("Please enter the priority(h for high, m for medium, l for low, we only look at the first character): ");
                String priorityString = scanner.nextLine();
                if (!priorityString.isEmpty()) {
                    priorityString = priorityString.toLowerCase();
                    priority = priorityString.charAt(0);
                    // Validate specific characters
                    if (priority != 'h' && priority != 'm' && priority != 'l') {
                        System.out.println("The input's first character needs to be h, or m, or l");
                        priority = 0; // Reset to loop again
                        continue;
                    }
                } else {
                    System.out.println("you did not enter anything.");
                    continue;
                }
            }

            // Get Completion status
            System.out.print("Please enter if it is done or not(true or false): ");
            if (scanner.hasNextBoolean()) {
                done = scanner.nextBoolean();
                scanner.nextLine(); // Consume newline
                keepGoing = false; // Exit main loop, we have all data
            } else {
                scanner.nextLine(); // Clear invalid input
                System.out.println("Please enter true or false");
            }
        }

        // Create the object and add to list
        Task task = new Task(taskName, done, priority);
        tasks.add(task);

        // Sort immediately to keep the list organized based on Task.compareTo rules
        tasks.sort(null);
    }

    // Displays all tasks
    public static void showTasks(Scanner scanner) {
        tasks.sort(null); // Ensure list is sorted before showing

        if (!tasks.isEmpty()) {
            System.out.println();
            int count = 1;
            // Iterate and print with numbering (1. Task...)
            for (Task i : tasks) {
                System.out.println(count + ". " + i);
                count++;
            }
            System.out.println();
        } else {
            System.out.println("\n there are no available tasks.\n");
        }

        System.out.print("Enter anything to continue: ");
        scanner.nextLine(); // Pause for user
    }

    // Filtered display: Show only completed OR incomplete tasks
    public static void showOnlyCompleteOrIncomplete(Scanner scanner, boolean status)  {
        tasks.sort(null);

        // Check if any tasks match the filter before printing headers
        boolean checkIfAnyNotDoneTasksExist;
        int bigCount = 0;
        for (Task i : tasks) {
            if (i.done == status) {
                bigCount++;
            }
        }
        checkIfAnyNotDoneTasksExist = bigCount == 0;


        if (!tasks.isEmpty() && !checkIfAnyNotDoneTasksExist) {
            System.out.println();
            int count = 1;
            for (Task i : tasks) {
                if (i.done == status) {
                    System.out.println(count + ". " + i);
                    count++;
                }
            }
            System.out.println();
        } else {
            System.out.println("\n there are no tasks matching the given filter tasks.\n");
        }

        System.out.print("Enter anything to continue: ");
        scanner.nextLine();
    }

    // Filtered display: Show only tasks of a specific priority
    public static void showCertainPriorityTasks(Scanner scanner, char letter) {
        tasks.sort(null);

        boolean checkIfAnyHighPriorityTasksExist;
        int bigCount = 0;
        for (Task i : tasks) {
            if (i.priority == letter) {
                bigCount++;
            }
        }
        checkIfAnyHighPriorityTasksExist = bigCount == 0;

        if (!tasks.isEmpty() && !checkIfAnyHighPriorityTasksExist) {
            System.out.println();
            int count = 1;
            for (Task i : tasks) {
                if (i.priority == letter) {
                    System.out.println(count + ". " + i);
                    count++;
                }
            }
            System.out.println();
        } else {
            System.out.print("\n there are no tasks matching the given filter tasks..\n");
        }

        System.out.print("Enter anything to continue: ");
        scanner.nextLine();
    }

    // Deletes a specific task by index
    public static void removeTask(Scanner scanner) {
        boolean notDeleted = true;

        Main_loop:
        while (notDeleted) {
            showTasks(scanner); // Show list so user knows indices

            if (tasks.isEmpty()) {
                break;
            }
            boolean notConfirmed = true;

            System.out.print("Please enter the the task number you would like to delete or 0 to cancel: ");
            int taskToRemove;
            if (scanner.hasNextInt()) {
                taskToRemove = scanner.nextInt() - 1; // Convert 1-based user input to 0-based index
            } else {
                scanner.nextLine();
                System.out.println("The task number must me an integer.");
                continue;
            }

            while (notConfirmed) {
                if (taskToRemove == -1) { // User entered 0
                    break Main_loop;
                }

                try {
                    // Confirm with user before deleting
                    System.out.print("is the task you would like to delete \"" + tasks.get(taskToRemove) + "\" (true or false): ");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("That task number is invalid.");
                    continue Main_loop;
                }

                try {
                    if (scanner.nextBoolean()) {
                        tasks.remove(taskToRemove);
                        notDeleted = false; // Exit loops
                        notConfirmed = false;
                        System.out.println("The task has been successfully deleted");
                    } else {
                        continue Main_loop; // User said false, restart
                    }
                } catch (InputMismatchException  e) {
                    scanner.nextLine();
                    System.out.println("That is invalid please try again");
                }
            }
        }
    }

    // Marks a specific task as Done (true)
    public static void markDone(Scanner scanner) {
        boolean notConfirmed = true;
        boolean notMarkedDone = true;

        if (tasks.isEmpty()) {
            System.out.println("There are no valid tasks to mark done.");
            return;
        }

        Main_loop:
        while (notMarkedDone) {
            showTasks(scanner);
            System.out.print("Please enter the the task number you would like to mark done or 0 to cancel: ");
            int taskToMarkDone;
            if (scanner.hasNextInt()) {
                taskToMarkDone = scanner.nextInt() - 1;
            } else {
                scanner.nextLine();
                System.out.println("The task number must me an integer.");
                continue;
            }

            while (notConfirmed) {
                if (taskToMarkDone == -1) {
                    return;
                }

                try {
                    System.out.print("is the task you would like to mark done \"" + tasks.get(taskToMarkDone) + "\" (true or false): ");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("That task number is invalid.");
                    continue Main_loop;
                }

                try {
                    if (scanner.nextBoolean()) {
                        tasks.get(taskToMarkDone).done = true; // Update status
                        notMarkedDone = false;
                        notConfirmed = false;
                        System.out.println("The task has been successfully marked done");
                    } else {
                        continue Main_loop;
                    }
                } catch (InputMismatchException  e) {
                    scanner.nextLine();
                    System.out.println("That is invalid please try again");
                }
            }
        }
    }

    // Updates the text name of an existing task
    public static void editTask(Scanner scanner) {
        boolean notConfirmed = true;
        boolean notEditied = true;

        if (tasks.isEmpty()) {
            System.out.println("There are no valid tasks to edit.");
            return;
        }

        Main_loop:
        while (notEditied) {
            showTasks(scanner);
            System.out.print("Please enter the the task number you would like to edit or 0 to cancel: ");
            int taskToEdit;
            if (scanner.hasNextInt()) {
                taskToEdit = scanner.nextInt() - 1;
            } else {
                scanner.nextLine();
                System.out.println("The task number must me an integer.");
                continue;
            }

            while (notConfirmed) {
                if (taskToEdit == -1) {
                    return;
                }

                try {
                    System.out.print("is the task you would like to edit \"" + tasks.get(taskToEdit) + "\" (true or false): ");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("That task number is invalid.");
                    continue Main_loop;
                }


                try {
                    if (scanner.nextBoolean()) {
                        scanner.nextLine();
                        System.out.print("Please enter the new text for the task: ");
                        tasks.get(taskToEdit).task = scanner.nextLine(); // Update Name
                        notEditied = false;
                        notConfirmed = false;
                        System.out.println("The task has been successfully edited");
                    } else {
                        continue Main_loop;
                    }
                } catch (InputMismatchException  e) {
                    scanner.nextLine();
                    System.out.println("That is invalid please try again");
                }
            }
        }
    }

    // Updates the priority char of an existing task
    public static void changePriority(Scanner scanner) {
        boolean notConfirmed = true;
        boolean taskPriorityNotChanged = true;

        if (tasks.isEmpty()) {
            System.out.println("There are no valid tasks to change the priority of.");
            return;
        }

        Main_loop:
        while (taskPriorityNotChanged) {
            showTasks(scanner);
            System.out.print("Please enter the the task number you would like to change the priority of or 0 to cancel: ");
            int taskToChangePriorityOf;
            if (scanner.hasNextInt()) {
                taskToChangePriorityOf = scanner.nextInt() - 1;
            } else {
                scanner.nextLine();
                System.out.println("The task number must me an integer.");
                continue;
            }

            while (notConfirmed) {
                if (taskToChangePriorityOf == -1) {
                    return;
                }

                try {
                    System.out.print("is the task you would like to change the priority of \"" + tasks.get(taskToChangePriorityOf) + "\" (true or false): ");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("That task number is invalid.");
                    continue Main_loop;
                }


                try {
                    if (scanner.nextBoolean()) {
                        scanner.nextLine();
                        char newPriority = 0;
                        // Loop to ensure new priority is valid (h/m/l)
                        while (newPriority == 0) {
                            System.out.print("Please enter the new priority(h for high, m for medium, l for low, we only look at the first character): ");
                            String newPriorityS = scanner.nextLine();

                            if (newPriorityS.isEmpty()) {
                                System.out.println("That was and invalid priority");
                                continue;
                            }

                            newPriority = newPriorityS.toLowerCase().charAt(0);

                            if (newPriority != 'h' && newPriority != 'm' && newPriority != 'l') {
                                System.out.println("That was and invalid priority");
                                newPriority = 0;
                            }
                        }
                        tasks.get(taskToChangePriorityOf).priority = newPriority;
                        taskPriorityNotChanged = false;
                        notConfirmed = false;
                        System.out.println("The task has been successfully changed priority");
                    } else {
                        continue Main_loop;
                    }
                } catch (InputMismatchException  e) {
                    scanner.nextLine();
                    System.out.println("That is invalid please try again");
                }
            }
        }
    }

    // Marks a task as Incomplete (false)
    public static void markTaskIncomplete(Scanner scanner) {
        boolean notConfirmed = true;
        boolean notMarkedIncomplete = true;

        if (tasks.isEmpty()) {
            System.out.println("There are no valid tasks to mark incomplete.");
            return;
        }

        Main_loop:
        while (notMarkedIncomplete) {
            showTasks(scanner);
            System.out.print("Please enter the the task number you would like to mark incomplete or 0 to cancel: ");
            int taskToMarkIncomplete;
            if (scanner.hasNextInt()) {
                taskToMarkIncomplete = scanner.nextInt() - 1;
            } else {
                scanner.nextLine();
                System.out.println("The task number must me an integer.");
                continue;
            }

            while (notConfirmed) {
                if (taskToMarkIncomplete == -1) {
                    return;
                }

                try {
                    System.out.print("is the task you would like to mark incomplete \"" + tasks.get(taskToMarkIncomplete) + "\" (true or false): ");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("That task number is invalid.");
                    continue Main_loop;
                }

                try {
                    if (scanner.nextBoolean()) {
                        tasks.get(taskToMarkIncomplete).done = false; // Set done to false
                        notMarkedIncomplete = false;
                        notConfirmed = false;
                        System.out.println("The task has been successfully marked incomplete");
                    } else {
                        continue Main_loop;
                    }
                } catch (InputMismatchException  e) {
                    scanner.nextLine();
                    System.out.println("That is invalid please try again");
                }
            }
        }
    }

    // Bulk delete based on completion status
    public static void deleteAllCompletedOrIncompleteTasks(Scanner scanner, boolean choice) {
        boolean done  = false;
        System.out.print("Are you sure(true or false): ");

        while (!done) {
            if (scanner.hasNextBoolean()) {
                if (scanner.nextBoolean()) {
                    scanner.nextLine();
                    // Lambda function: remove if i.done matches the choice (true or false)
                    tasks.removeIf(i -> i.done == choice);
                    done = true;
                    System.out.println("All Completed tasks have been deleted.");
                }
                else {
                    return;
                }
            } else {
                scanner.nextLine();
                System.out.println("Please enter true or false: ");
            }
        }
    }

    // Clears the entire list
    public static void deleteAllTasks(Scanner scanner) {
        boolean done  = false;
        System.out.print("Are you sure(true or false): ");

        while (!done) {
            if (scanner.hasNextBoolean()) {
                if (scanner.nextBoolean()) {
                    scanner.nextLine();
                    tasks.clear(); // Wipe list
                    done = true;
                    System.out.println("All tasks have been deleted.");
                }
                else {
                    return;
                }
            } else {
                scanner.nextLine();
                System.out.println("Please enter true or false: ");
            }
        }
    }
}