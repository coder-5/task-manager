import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskManager {
    static ArrayList<Task> tasks = new ArrayList<>();

    public static void addTask(Scanner scanner) {
        boolean keepGoing = true;
        String taskName = "";
        boolean done = false;
        boolean taskMade = false;
        char priority = 0;
        boolean taskConfirmed = false;
        Main_loop:
        while (keepGoing){
            while (!taskMade) {
                System.out.print("Please enter the task or enter \"CANCEL\" to cancel: ");
                taskName = scanner.nextLine();
                taskMade = true;
            }

            if (taskName.equals("CANCEL")) {
                return;
            }

            while (!taskConfirmed) {
                if (taskName.isEmpty() || taskName.equals("\n")) {
                    System.out.println("The was no input, please try again.");
                    taskMade = false;
                    continue Main_loop;
                } else {
                    System.out.print("Is the task \"" + taskName + "\" (true or false): ");
                    if (scanner.hasNextBoolean()) {
                        if (scanner.nextBoolean()) {
                            scanner.nextLine();
                            taskConfirmed = true;
                        } else {
                            scanner.nextLine();
                            taskMade = false;
                            continue Main_loop;
                        }
                    } else {
                        scanner.nextLine();
                        System.out.println("That was invalid, please try again");
                    }
                }
            }

            if (priority == 0) {
                System.out.print("Please enter the priority(h for high, m for medium, l for low, we only look at the first character): ");
                String priorityString = scanner.nextLine();
                if (!priorityString.isEmpty()) {
                    priorityString = priorityString.toLowerCase();
                    priority = priorityString.charAt(0);
                    if (priority != 'h' && priority != 'm' && priority != 'l') {
                        System.out.println("The input's first character needs to be h, or m, or l");
                        priority = 0;
                        continue;
                    }
                } else {
                    System.out.println("you did not enter anything.");
                    continue;
                }
            }

            System.out.print("Please enter if it is done or not(true or false): ");
            if (scanner.hasNextBoolean()) {
                done = scanner.nextBoolean();
                keepGoing = false;
            } else {
                scanner.nextLine();
                System.out.println("Please enter true or false");
            }
        }

        Task task = new Task(taskName, done, priority);

        tasks.add(task);
    }

    public static void showTasks(Scanner scanner) {

        if (!tasks.isEmpty()) {
            System.out.println();
            int count = 1;
            for (Task i : tasks) {
                System.out.println(count + ". " + i);
                count++;
            }
            System.out.println();
        } else {
            System.out.println("\n there are no available tasks.\n");
        }

        System.out.print("Enter anything to continue: ");

        scanner.nextLine();

    }
    public static void showIncompleteTasks(Scanner scanner) {
        boolean checkIfAnyNotDoneTasksExist;
        int bigCount = 0;
        for (Task i : tasks) {
            if (!i.done) {
                bigCount++;
            }
        }
        checkIfAnyNotDoneTasksExist = bigCount == 0;


        if (!tasks.isEmpty() && !checkIfAnyNotDoneTasksExist) {
            System.out.println();
            int count = 1;
            for (Task i : tasks) {
                if (!i.done) {
                    System.out.println(count + ". " + i);
                    count++;
                }
            }
            System.out.println();
        } else {
            System.out.println("\n there are no available tasks.\n");
        }

        System.out.print("Enter anything to continue: ");

        scanner.nextLine();
    }
    public static void showCompleteTasks(Scanner scanner) {
        boolean checkIfAnyDoneTasksExist;
        int bigCount = 0;
        for (Task i : tasks) {
            if (i.done) {
                bigCount++;
            }
        }
        checkIfAnyDoneTasksExist = bigCount == 0;


        if (!tasks.isEmpty() && !checkIfAnyDoneTasksExist) {
            System.out.println();
            int count = 1;
            for (Task i : tasks) {
                if (i.done) {
                    System.out.println(count + ". " + i);
                    count++;
                }
            }
            System.out.println();
        } else {
            System.out.print("\n there are no available tasks.\n");
        }

        System.out.print("Enter anything to continue: ");

        scanner.nextLine();
    }

    public static void removeTask(Scanner scanner) {
        boolean notDeleted = true;

        Main_loop:
        while (notDeleted) {
            showTasks(scanner);


            if ( tasks.isEmpty()) {
                break;
            }
            boolean notConfirmed = true;

            System.out.print("Please enter the the task number you would like to delete or 0 to cancel: ");
            int taskToRemove;
            if (scanner.hasNextInt()) {
                taskToRemove = scanner.nextInt() - 1;
            } else {
                scanner.nextLine();
                System.out.println("The task number must me an integer.");
                continue;
            }

            while (notConfirmed) {

                if (taskToRemove == -1) {
                    break Main_loop;
                }


                try {
                    System.out.print("is the task you would like to delete \"" + tasks.get(taskToRemove) + "\" (true or false): ");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("That task number is invalid.");
                    continue Main_loop;
                }

                try {
                    if (scanner.nextBoolean()) {
                        tasks.remove(taskToRemove);
                        notDeleted = false;
                        notConfirmed = false;
                        System.out.println("The task has been successfully deleted");
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

    public static void markDone(Scanner scanner) {
        boolean notConfirmed = true;
        boolean notMarkedDone = true;

        if ( tasks.isEmpty()) {
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
                        tasks.get(taskToMarkDone).done = true;
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

    public static void editTask(Scanner scanner) {
        boolean notConfirmed = true;
        boolean notEditied = true;

        if ( tasks.isEmpty()) {
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
                        tasks.get(taskToEdit).task = scanner.nextLine();
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

    public static void changePriority(Scanner scanner) {
        boolean notConfirmed = true;
        boolean taskPriorityNotChanged = true;

        if ( tasks.isEmpty()) {
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
                        while (newPriority == 0) {
                            System.out.print("Please enter the new priority(h for high, m for medium, l for low, we only look at the first character): ");
                            newPriority = scanner.nextLine().toLowerCase().charAt(0);

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

    public static void markTaskIncomplete(Scanner scanner) {
        boolean notConfirmed = true;
        boolean notMarkedIncomplete = true;

        if ( tasks.isEmpty()) {
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
                        tasks.get(taskToMarkIncomplete).done = false;
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

    public static void deleteAllCompletedTasks(Scanner scanner) {
        boolean done  = false;
        System.out.print("Are you sure(true or false): ");

        while (!done) {
            if (scanner.hasNextBoolean()) {
                if (scanner.nextBoolean()) {
                    scanner.nextLine();
                    tasks.removeIf(i -> i.done);
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
}
