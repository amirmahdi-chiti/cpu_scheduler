public class Task {
    private String name;
    private int cpu_burst;
//    private  Priority priority;

    public Task(String name, int cpu_burst) {
        this.name = name;
        this.cpu_burst = cpu_burst;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCpu_burst() {
        return cpu_burst;
    }

    public void setCpu_burst(int cpu_burst) {
        this.cpu_burst = cpu_burst;
    }

//    public Priority getPriority() {
//        return priority;
//    }
//
//    public void setPriority(Priority priority) {
//        this.priority = priority;
//    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                '}';
    }
}
//enum Priority{
//        X(3),
//        Y(2),
//        Z(1);
//        private final int p;
//
//    public int getP() {
//        return p;
//    }
//
//    Priority(int p) {
//        this.p = p;
//    }
//}