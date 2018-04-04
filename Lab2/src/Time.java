import java.util.Scanner;

public class Time implements Comparable<Time>{
    private int Hr;
    private int Min;

    public Time(int hr, int min) {
        Hr = hr;
        Min = min;
    }

    public Time() {


    }

    public void read(String statement){

        System.out.println(statement);
        System.out.println("please Enter in 24 hour format");

        Scanner reader=new Scanner(System.in);
        System.out.print("Hour : ");
        int hour=Integer.parseInt(reader.nextLine());

        System.out.print("Min : ");
        int min=Integer.parseInt(reader.nextLine());


        if(hour>=24){
            this.Hr=hour%24;
        }
        else{
            this.Hr=hour;
        }

        if(min>=60){
            this.Hr=min/60;
            this.Min=min%60;

        }
        else{
            this.Min=min;
        }


    }


    public void read(int hour, int min){
        if(hour>=24){
            this.Hr=hour%24;
        }
        else{
            this.Hr=hour;
        }

        if(min>=60){
            this.Hr++;
            this.Hr=hour%60;

        }
        else{
            this.Hr=hour;
        }


    }


    public boolean lessThan(Time secondTime){

        if(this.Hr<secondTime.Hr){
            return true;

        }
        else if(this.Hr==secondTime.Hr && this.Min<secondTime.Min){
            return true;
        }
        else return false;

    }

    public Time Subtract(Time time){
        String back="";
        int min=0;
        int hour=0;
        if(this.Min>=time.Min){
            min=this.Min-time.Min;



            hour=this.Hr-time.Hr;
            back="hour : "+hour+"Min :"+min;
        }

        else if(this.Min<time.Min){
            min=this.Min+60-time.Min;
            this.Hr--;
            hour=this.Hr-time.Hr;
            back="hour : "+hour+" Min :"+min;
        }


        Time duration=new Time();
        duration.Min=min;
        duration.Hr=hour;
        return duration;
    }

    public int compareTo(Time o){
        if(o.lessThan(this)){return 1;}
        else if(this.lessThan(o)){return -1;}
        else {return 0;}


    }
    @Override
    public String toString() {
        return this.Hr+":"+this.Min;
    }
}
