package RealTests.BasicMethodsTest;

import jsf.PriorityQueue;
import jsf.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PriorityQueueTest {
    PriorityQueue<Student> priorityQueue;
    Student veryLowPriority, lowPriority,
            mediumPriority, highPriority, veryHighPriority;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        priorityQueue = new PriorityQueue<Student>(
                ((Student s1, Student s2) ->
                (s1.getPriority() > s2.getPriority()) ? -1 : 1));
        veryLowPriority = new Student("veryLow",
                123, "veryLowEmail", 0, 0.0);
        lowPriority = new Student("Low",
                123, "LowEmail", 1, 1.0);
        mediumPriority = new Student("Med",
                123, "MedEmail", 2, 2.0);
        highPriority  = new Student("High",
                123, "HighEmail", 3, 3.0);
        veryHighPriority = new Student("veryHigh",
                123, "veryHighEmail", 4, 4.0);
    }

    @org.junit.jupiter.api.Test
    void add() {
        assertTrue(priorityQueue.isEmpty());
        priorityQueue.add(lowPriority);
        assertFalse(priorityQueue.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
        assertTrue(priorityQueue.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void poll() {
        assertTrue(priorityQueue.isEmpty());
        priorityQueue.add(lowPriority);
        priorityQueue.add(highPriority);
        assertFalse(priorityQueue.isEmpty());
        Student first = priorityQueue.poll();
        assertEquals(first.name, highPriority.name);
        Student second = priorityQueue.poll();
        assertTrue(second.name.equals(lowPriority.name));
        assertTrue(priorityQueue.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void printQueue() {
        priorityQueue.add(veryLowPriority);
        priorityQueue.add(lowPriority);
        priorityQueue.add(veryHighPriority);
        priorityQueue.add(highPriority);
        priorityQueue.add(mediumPriority);
        assertFalse(priorityQueue.isEmpty());
    }
}