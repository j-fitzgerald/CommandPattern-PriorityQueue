package TestTheTests.TestPriorityQueueTest;

import jsf.PriorityQueue;
import jsf.Student;

public class TestSetupTests {
    PriorityQueue<Student> priorityQueue;
    Student veryLowPriority, lowPriority, mediumPriority,
            highPriority, veryHighPriority, zeroPriority, maxPriority;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        priorityQueue = new PriorityQueue<Student>
                (((Student s1, Student s2) ->
                (s1.getPriority() > s2.getPriority()) ? -1 : 1));
        zeroPriority = new Student("zero", 123, "zeroEmail", 0, 0.0);
        veryLowPriority =
                new Student("veryLow", 123, "veryLowEmail", 10, 0.5);
        lowPriority = new Student("Low", 123, "LowEmail", 25, 1.0);
        mediumPriority = new Student("Med", 123, "MedEmail", 50, 2.0);
        highPriority  =
                new Student("High", 123, "HighEmail", 75, 3.0);
        veryHighPriority =
                new Student("veryHigh", 123,
                "veryHighEmail", 100, 4.0);
        maxPriority = new Student("max", 123, "maxEmail", 150, 4.0);
    }
}
