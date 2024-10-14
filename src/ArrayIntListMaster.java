import java.util.Scanner;

public class ArrayIntListMaster {
    public static void main(String[] args) {
        boolean completed = false;
        ArrayIntList intList = new ArrayIntList();
        Scanner userSelector = new Scanner(System.in);
        String userSelection;

        while (!completed) {
            System.out.println("Would you like to \n" +
                    "'A' to add to the end.\n" +
                    "'I' to insert in the middle.\n" +
                    "'D' to delete a value.\n" +
                    "'P' to print all of the values.\n" +
                    "'Q' to quit.\n");

            userSelection = userSelector.next();
            userSelector.nextLine();

            switch (userSelection) {
                case "A":
                    addToArrayIntList(intList, userSelector);
                    break;
                case "I":
                    insertIntoArrayIntList(intList, userSelector);
                    break;
                case "D":
                    deleteFromArrayIntList(intList, userSelector);
                    break;
                case "P":
                    intList.printList();
                    break;
                case "Q":
                    completed = true;
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
            }
        }
        userSelector.close();
        System.exit(0);
    }

    private static void addToArrayIntList(ArrayIntList intList, Scanner userSelector) {
        System.out.println("What integer value would you like to add?");
        int newValue = userSelector.nextInt();
        userSelector.nextLine();
        intList.add(newValue);
    }

    private static void deleteFromArrayIntList(ArrayIntList intList, Scanner userSelector) {
        System.out.println("Which index would you like to delete from?");
        int index = userSelector.nextInt();
        userSelector.nextLine();
        intList.delete(index);
    }

    private static void insertIntoArrayIntList(ArrayIntList intList, Scanner userSelector) {
        System.out.println("At which index would you like to insert a value?");
        int index = userSelector.nextInt();
        userSelector.nextLine();
        System.out.println("What integer value would you like to insert?");
        int value = userSelector.nextInt();
        userSelector.nextLine();
        intList.insert(index, value);
    }

    private static class ArrayIntList {
        private int[] array;
        private int size;

        public ArrayIntList() {
            array = new int[10]; 
            size = 0;
        }

        public void add(int value) {
            ensureCapacity();
            array[size++] = value;
        }

        public void insert(int index, int value) {
            if (index < 0 || index > size) {
                System.out.println("Invalid index received; no change");
                return;
            }
            ensureCapacity();
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = value;
            size++;
        }

        public void delete(int index) {
            if (index < 0 || index >= size) {
                System.out.println("Invalid index received; no change");
                return;
            }
            System.arraycopy(array, index + 1, array, index, size - index - 1);
            size--;
        }

        public void printList() {
            if (size == 0) {
                System.out.println("The list is empty.");
            } else {
                System.out.print("List values: ");
                for (int i = 0; i < size; i++) {
                    System.out.print(array[i] + " ");
                }
                System.out.println();
            }
        }

        private void ensureCapacity() {
            if (size >= array.length) {
                int[] newArray = new int[array.length * 2];
                System.arraycopy(array, 0, newArray, 0, size);
                array = newArray;
            }
        }
    }
}
