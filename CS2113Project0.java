import java.util.Scanner;

public class GroceryListManager {
    static final int MAX_SIZE = 50;
    static String[] itemArray = new String[MAX_SIZE];
    static boolean[] checkOffArray = new boolean[MAX_SIZE];
    static int numberOfItemsInList = 0;

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to Grocery List Management!");

        while (running) {
            displayMenu();
            int choice = scnr.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1: addItem(scnr); break;
                case 2: removeItem(scnr); break;
                case 3: checkOffItem(scnr); break;
                case 4: printListExampleStyle(); break;
                case 5:
                    printListExampleStyle();
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        scnr.close();
    }

    // This method is used to display menu
    public static void displayMenu() {
        System.out.println("\n1. Add Item to your Grocery List");
        System.out.println("2. Remove Item from your Grocery List");
        System.out.println("3. \"Check Off\" an Item from your Grocery List");
        System.out.println("4. Display your Grocery List");
        System.out.println("5. Exit");
        System.out.print("Please enter the number of an option above: ");
    }

    // This method is used to add an item
    public static void addItem(Scanner scnr) {
        System.out.print("Enter item to add: ");
        String newItem = scnr.nextLine().trim();

        if (findItem(newItem) != -1) {
            System.out.println("That item already exists!");
            return;
        }

        itemArray[numberOfItemsInList] = newItem;
        checkOffArray[numberOfItemsInList] = false;
        numberOfItemsInList++;
        System.out.print(newItem);
        System.out.println();
        printArrayExample();
    }

    // This method is used to remove an item
    public static void removeItem(Scanner scnr) {
        System.out.print("Enter item name or number to remove: ");
        String input = scnr.nextLine().trim();
        int index = -1;

        if (input.matches("\\d++")) {
            int num = Integer.parseInt(input) -1;
            if (num >= 0 && num < numberOfItemsInList) index = num;
        } else {
            index = findItem(input);
        }

        if (index == -1) {
            System.out.println("Item not found.");
            return;
        }

        for (int i = index; i < numberOfItemsInList - 1; i++) {
            itemArray[i] = itemArray[i + 1];
            checkOffArray[i] = checkOffArray[i + 1];
        }

        itemArray[numberOfItemsInList - 1] = null;
        checkOffArray[numberOfItemsInList - 1] = false;
        numberOfItemsInList--;
        System.out.println("Item removed.");
        printArrayExample();
    }

    // This method is to checkOff an item
    public static void checkOffItem(Scanner scnr) {
        System.out.print("Enter item name or number to check off: ");
        String input = scnr.nextLine().trim();
        int index = -1;

        if (input.matches("\\d++")) {
            int num = Integer.parseInt(input) - 1;
            if (num >= 0 && num < numberOfItemsInList) index = num;
        } else {
            index = findItem(input);
        }

        if (index == -1) {
            System.out.println("Item not found.");
            return;
        }

        checkOffArray[index] = true;
        System.out.println(itemArray[index] + " checked off.");
        printArrayExample();
    }

    // This method is used to print
    public static void printArrayExample() {
        System.out.println("\nnumberOfItemsInList: " + numberOfItemsInList);

        System.out.print("ItemArray:\n");
        for (int i = 0; i < numberOfItemsInList; i++) {
            System.out.print(itemArray[i] + " ");
        }
        System.out.println("... ...");

        System.out.print("CheckOffArray:\n");
        for (int i = 0; i < numberOfItemsInList; i++) {
            System.out.print(checkOffArray[i] + " ");
        }
        System.out.println("... ...");

        printListExampleStyle();
    }

    // This method is to print an array
    public static void printListExampleStyle() {
        System.out.println("Print List:");
        for (int i = 0; i < numberOfItemsInList; i++) {
            String status = checkOffArray[i] ? "x" : "-";
            System.out.println((i + 1) + ". " + status + " " + itemArray[i]);
        }
    }

    // This method is to find an item
    public static int findItem(String name) {
        for (int i = 0; i < numberOfItemsInList; i++) {
            if (itemArray[i].equalsIgnoreCase(name)) return i;
        }
        return -1;
    }
}

