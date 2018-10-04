package RealTests.PriorityQueueTest;

import jsf.PriorityQueue;
import jsf.Student;

public class SetupTests {
    PriorityQueue<Student> priorityQueue;
    Student[] veryLowPriority, lowPriority, mediumPriority,
            highPriority, veryHighPriority,
            zeroPriority, maxPriority, duplicates;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        priorityQueue =
            new PriorityQueue<Student>(
                ((Student s1, Student s2) ->
                (s1.getPriority() > s2.getPriority()) ? -1 : 1));
        maxPriority = new Student[3];
        veryHighPriority = new Student[3];
        highPriority = new Student[3];
        mediumPriority = new Student[3];
        lowPriority = new Student[3];
        veryLowPriority = new Student[3];
        zeroPriority = new Student[3];
        duplicates = new Student[5];

        fill("max",maxPriority, 150, Student.MAX_GPA);
        fill("VeryHigh", veryHighPriority, 125, Student.MAX_GPA);
        fill("high", highPriority, 100,3.5);
        fill("medium",mediumPriority, 75,3.0);
        fill("low",lowPriority, 50,2.0);
        fill("veryLow",veryLowPriority, 25,1.0);
        fill("zero", zeroPriority, 0,0.0);
        fillDuplicates(duplicates);
    }

    public void fill(String namePrefix, Student[] s,
                    int units, double gpa){
        for (int i = 0; i < s.length; i++){
            String name = namePrefix + "_" + i + "_" + gpa;
            s[i] = new Student(name, i, name + ".com",
                    units, Math.max(gpa - 0.1*i,0));
        }
    }

    public void fillDuplicates(Student[] duplicates){
        for (int i = 0; i < duplicates.length; i++){
            duplicates[i] = new Student("duplicate_" + i,
                    123, "duplicate + i", 4, 4.0);
        }
    }
}
