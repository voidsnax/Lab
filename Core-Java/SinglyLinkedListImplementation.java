import java.util.Scanner;

public class SinglyLinkedListImplementation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SinglyLinkedList list = new SinglyLinkedList();
        int choice;

        do {
            System.out.println("\n1. Insert at Beginning");
            System.out.println("2. Insert at End");
            System.out.println("3. Delete by Value");
            System.out.println("4. Search");
            System.out.println("5. Traverse");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value: ");
                    list.insertBeginning(sc.nextInt());
                    break;

                case 2:
                    System.out.print("Enter value: ");
                    list.insertEnd(sc.nextInt());
                    break;

                case 3:
                    System.out.print("Enter value to delete: ");
                    list.deleteByValue(sc.nextInt());
                    break;

                case 4:
                    System.out.print("Enter value to search: ");
                    int value = sc.nextInt();

                    if (list.search(value))
                        System.out.println("Value found.");
                    else
                        System.out.println("Value not found.");
                    break;

                case 5:
                    list.traverse();
                    break;

                case 6:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 6);

        sc.close();
    }
}

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class SinglyLinkedList {
    Node head;

    void insertBeginning(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;

        System.out.println("Inserted at beginning.");
    }

    void insertEnd(int value) {
        Node newNode = new Node(value);

        if (head == null) {
            head = newNode;
        } else {
            Node current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = newNode;
        }

        System.out.println("Inserted at end.");
    }

    void deleteByValue(int value) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        if (head.data == value) {
            head = head.next;
            System.out.println("Value deleted.");
            return;
        }

        Node current = head;

        while (current.next != null
                && current.next.data != value) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("Value not found.");
        } else {
            current.next = current.next.next;
            System.out.println("Value deleted.");
        }
    }

    boolean search(int value) {
        Node current = head;

        while (current != null) {
            if (current.data == value)
                return true;

            current = current.next;
        }

        return false;
    }

    void traverse() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        Node current = head;

        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }

        System.out.println("NULL");
    }
}