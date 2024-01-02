import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Thread {
    int banyakThread;
    Queue<String> taskThread;

    public Thread(int banyakThread) {
        this.banyakThread = banyakThread;
        this.taskThread = new LinkedList<>();
    }

    public void tambahThread(String task) {
        taskThread.offer(task);
    }

    public void cpuJalankanTask() {
        int jalankanTask = Math.min(banyakThread, taskThread.size());
        if (jalankanTask > 0) {
            for (int i = 0; i < jalankanTask; i++) {
                String task = taskThread.poll();
                System.out.println("task " + task + " done");
            }
        }
        int sisaThread = banyakThread - jalankanTask;
        System.out.println(sisaThread + " idle thread");
    }

    public void cetakSisaTask() {
        for (String task : taskThread) {
            System.out.println("task " + task);
        }
    }
}

public class LK4_Queue {
    public static void main(String[] args) {
        /*
         * Enter your code here. Read input from STDIN. Print output to STDOUT. Your
         * class should be named Solution.
         */
        Scanner input = new Scanner(System.in);
        int taskThread = input.nextInt();
        input.nextLine();

        Thread thread = new Thread(taskThread);

        String command;
        while (true) {
            command = input.nextLine();

            switch (command) {
                case "START":
                    thread.cpuJalankanTask();
                    break;
                case "DONE":
                    System.out.println("task left:");
                    thread.cetakSisaTask();
                    return;
                default:
                    thread.tambahThread(command);
                    break;
            }
        }
    }
}