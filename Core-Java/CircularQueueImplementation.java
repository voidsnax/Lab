import java.util.Scanner;

class CircularQueue {
    int[] queue = new int[5];
    int front = -1, rear = -1;

    void enqueue(int value) {
        if ((rear + 1) % queue.length == front) {
            System.out.println("Queue Overflow!");
            return;
        }

        if (front == -1)
            front = 0;

        rear = (rear + 1) % queue.length;
        queue[rear] = value;

        System.out.println(value + " inserted.");
    }

    void dequeue() {
        if (front == -1) {
            System.out.println("Queue Underflow!");
            return;
        }

        System.out.println("Deleted: " + queue[front]);

        if (front == rear) {
            front = rear = -1;
        } else {
            front = (front + 1) % queue.length;
        }
    }

    void display() {
        if (front == -1) {
            System.out.println("Queue is empty.");
            return;
        }

        System.out.println("Queue elements:");
        int i = front;

        while (true) {
            System.out.println(queue[i]);

            if (i == rear)
                break;

            i = (i + 1) % queue.length;
        }
    }
}

public class CircularQueueImplementation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CircularQueue q = new CircularQueue();
        int choice;

        do {
            System.out.println("\n1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Display");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value: ");
                    q.enqueue(sc.nextInt());
                    break;
                case 2:
                    q.dequeue();
                    break;
                case 3:
                    q.display();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 4);

        sc.close();
    }
}