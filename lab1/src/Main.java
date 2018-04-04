public class Main {
    public static void main(String[] args) {

        Time time1 = new Time(),
                time2 = new Time(),
                duration;
        time1.read("Enter time 1 ? ");
        time2.read("Enter time 2 ? ");
        if (time1.lessThan(time2)) {
            duration = time2.subtract(time1);
            System.out.print("Starting time was " +
                    time1.toString());
        } else {
                duration = time1.subtract(time2);
                System.out.print("Starting time was " +
                        time2.toString());
            }
            System.out.println("\nDuration was " + duration.toString());
        }
    }



