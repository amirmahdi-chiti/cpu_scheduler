import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Scheduler {
    public static void main(String[] args) {
        Queue<Task> pq = new LinkedList<>();
        String algorithm = "";
        int quantum = 0;
        try {
            File myObj = new File("./task.txt");
            Scanner scanner = new Scanner(myObj);
            algorithm = scanner.next();
            if(algorithm.equals("rr")) {quantum = scanner.nextInt();}
            int task_count = scanner.nextInt();
            for(int i = 0 ; i< task_count ;i++){
                String taskName = scanner.next();
                int taskDuration = scanner.nextInt();
                Task task = new Task(taskName, taskDuration);
                pq.add(task);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        switch (algorithm){
            case "sjf": sjf(pq);
                break;
            case "fcfs" : fcfs(pq);
                break;
            case "rr" : rr(pq,quantum);
                break;
            case "hhrn" : hhrn(pq);
                break;
            default:
                System.out.println("algorithm is invalid");
        }
    }

    public static void sjf(Queue<Task> queue){
        PriorityQueue<Task> tasks = new PriorityQueue<>(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                    return o1.getCpu_burst() - o2.getCpu_burst();
            }
        });
        while (!queue.isEmpty()){
            tasks.add(queue.remove());
        }

        int time = 0;
        while (!tasks.isEmpty()){
            Task task = tasks.remove();
            for (int i = 0;i<task.getCpu_burst();i++){
                System.out.println("time: " + time);
                System.out.print("running task : ");
                System.out.println(task);
                System.out.print("ready queue : ");
                System.out.println(tasks);
                System.out.println("------------------");
                time++;
            }

        }

    }

    public static void fcfs(Queue<Task> queue){
        int time = 0;
        while (!queue.isEmpty()){
            Task task = queue.remove();
            for (int i = 0;i<task.getCpu_burst();i++){
                System.out.println("time: " + time);
                System.out.print("running task : ");
                System.out.println(task);
                System.out.print("ready queue : ");
                System.out.println(queue);
                System.out.println("------------------");
                time++;
            }

        }
    }

    public static void rr(Queue<Task> queue, int q){
        int time = 0;
        while (!queue.isEmpty()){
            Task task = queue.remove();
            int bur = task.getCpu_burst();
            for (int i = 0;i<Math.min(bur,q);i++){
                System.out.println("time: " + time);
                System.out.print("running task : ");
                System.out.println(task);
                System.out.print("ready queue : ");
                System.out.println(queue);
                System.out.println("------------------");
                time++;
                task.setCpu_burst(task.getCpu_burst()-1);
            }
            if (task.getCpu_burst() != 0){
                queue.add(task);
            }
        }

    }
    public static void hhrn(Queue<Task> queue){
        int time = 0;
        LinkedList<Task> tasks = (LinkedList<Task>) queue;
        while (!tasks.isEmpty()){
            int finalTime = time;
            tasks.sort(new Comparator<Task>() {
                @Override
                public int compare(Task o1, Task o2) {
                    return (o2.getCpu_burst() + finalTime) / o2.getCpu_burst() - (o1.getCpu_burst() + finalTime) / o1.getCpu_burst();
                }
            });
            Task task = tasks.remove(0);
            for (int i = 0;i<task.getCpu_burst();i++){
                System.out.println("time: " + time);
                System.out.print("running task : ");
                System.out.println(task);
                System.out.print("ready queue : ");
                System.out.println(task);
                System.out.println("------------------");
                time++;
            }

        }
    }
}
