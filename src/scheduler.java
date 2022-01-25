import java.util.*;

public class scheduler {
    public static void main(String[] args) {
        Queue<Task> pq = new PriorityQueue<>(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.getPriority().getP() - o2.getPriority().getP();
            }
        });
        Scanner scanner = new Scanner(System.in);
        String algorithm = scanner.next();
        int task_count = scanner.nextInt();
        for(int i = 0 ; i< task_count ;i++){
            String taskName = scanner.next();
            String taskType = scanner.next();
            int taskDuration = scanner.nextInt();
            Task task = new Task(taskName, taskDuration , Priority.valueOf(taskType));
            pq.add(task);
        }

        switch (algorithm){
            case "sjf": sjf(pq);
                break;
            case "fcfs" : fcfs(pq);
                break;
            case "rr" : rr();
                break;
            case "hhrn" : hhrn();
                break;
            default:
                System.out.println("algorithm is invalid");
        }
    }

    public static void sjf(Queue<Task> queue){
        PriorityQueue<Task> tasks = new PriorityQueue<>(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                int priority = o1.getPriority().getP() - o2.getPriority().getP();
                if (priority == 0){
                    return o1.getCpu_burst() - o2.getCpu_burst();
                }
                return priority;
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

    public static void rr(){

    }
    public static void hhrn(){

    }
}
