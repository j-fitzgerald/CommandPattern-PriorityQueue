package jsf;

public class Student implements Comparable<Student>{
    public String name, email;
    public int redID, units;
    public double gpa, priority;
    public static final double GPA_RATE = 0.3;
    public static final double UNITS_RATE = 0.7;
    public static final int MAX_UNITS = 150;
    public static final int MIN_UNITS = 0;
    public static final double MAX_GPA = 4.0;
    public static final double MIN_GPA = 0.0;
    public static final String INVALID_UNITS_MESSAGE =
            String.format("Invalid Units: " +
            "Units must be between %d and %d",
            MIN_UNITS, MAX_UNITS);
    public static final String INVALID_GPA_MESSAGE =
            String.format("Invalid GPA: GPA " +
            "must be between %.1f and %.1f",
            MIN_GPA, MAX_GPA);

    public Student(String name, int redID,
               String email, int units, double gpa){
        if (units < MIN_UNITS || units > MAX_UNITS)
            throw new IllegalArgumentException
                    (INVALID_UNITS_MESSAGE);
        if (gpa < MIN_GPA || gpa > MAX_GPA)
            throw new IllegalArgumentException
                    (INVALID_GPA_MESSAGE);
        this.name=name;
        this.redID = redID;
        this.email = email;
        this.units = units;
        this.gpa = gpa;
    }

    public String toString(){
        return redID + "\t" + name + "\t" +
                getPriority() + "\t" + gpa + "\t" + units;
    }

    public double getPriority(){
        return gpa/MAX_GPA*GPA_RATE
                + units/MAX_UNITS*UNITS_RATE;
    }

    public int compareTo(Student s) {
        if (getPriority() - s.getPriority() > 0) return 1;
        if (getPriority() - s.getPriority() < 0) return -1;
        return 0;
    }
}
