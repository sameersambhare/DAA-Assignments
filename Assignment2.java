
import java.util.*;

class Job {

    public int id;
    public int deadline;
    public int profit;

    public Job(int id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class Assignment2 {

    private static void JobScheduling(ArrayList<Job> job, int n) {
        int size = job.size();
        boolean result[] = new boolean[n];
        ArrayList<Job> schedule = new ArrayList<>();
        //get the job
        for (int i = 0; i < size; i++) {
            Job temp = job.get(i);
            //allocate the slot
            for (int j = Math.min((n - 1), (temp.deadline-1)); j >= 0; j--) {
                if (!result[j]) {
                    result[j] = true;
                    schedule.add(temp);
                    break;
                }
            }
        }
        System.out.println("Schedule jobs are given below: ");
        schedule.forEach(k -> {
            System.out.println("id: " + k.id + " deadline:  " + k.deadline + " profit:  " + k.profit);
        });

        //totalProfit
        int totalProfit = 0;
        for (Job i : schedule) {
            totalProfit += i.profit;
        }
        System.out.println("Total profit: " + totalProfit);
    }

    public static void main(String[] args) {
        int counter = 5;
        ArrayList<Job> job = new ArrayList<>();
        job.add(new Job(1, 3, 100));
        job.add(new Job(2, 2, 19));
        job.add(new Job(3, 1, 27));
        job.add(new Job(4, 1, 25));
        job.add(new Job(5, 2, 15));

        Collections.sort(job, (a, b) -> b.profit - a.profit);

        //maxdeadline
        int maxDeadline = job.get(0).deadline;
        for (int i = 1; i < counter; i++) {
            if (job.get(i).deadline > maxDeadline) {
                maxDeadline = job.get(i).deadline;
            }
        }
        JobScheduling(job, maxDeadline);
    }
}
