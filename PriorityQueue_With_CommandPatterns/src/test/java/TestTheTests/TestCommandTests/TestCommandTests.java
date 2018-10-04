package TestTheTests.TestCommandTests;

import jsf.PriorityQueue;
import jsf.Student;
import jsf.Commands.*;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class TestCommandTests {
    PriorityQueue<Student> priorityQueue;
    Comparator<Student> maxComparator;
    Comparator<Student> minComparator;
    Student veryHigh, high, medium, low, veryLow, lastStudent;
    CommandProcessor commandProcessor;
    AddCommand addCommand;
    RemoveCommand removeCommand;
    PollCommand pollCommand;
    ReprioritizeCommand reprioritizeCommand;
    Student studentArray[];
    Student reverseArray[];
    CommandResult commandResult;
    int size;

    @org.junit.jupiter.api.BeforeEach
    void setUp(){
        maxComparator = (Student s1, Student s2)
                ->(s1.gpa < s2.gpa ? -1 : 1);
        minComparator = (Student s1, Student s2)
                ->(s1.gpa < s2.gpa ? 1 : -1);
        priorityQueue = new PriorityQueue<>(maxComparator);
        commandProcessor = new CommandProcessor();
        veryLow = new Student("a", 0, "a", 0, 0);
        low = new Student("b", 1, "b", 1, 1);
        medium = new Student("c", 2, "c", 2, 2);
        high = new Student("d",3,"d",3,3);
        veryHigh = new Student("e", 4, "e", 4, 4);
        studentArray = new Student[]
                {veryLow, low, medium, high, veryHigh};
        reverseArray = new Student[]
                {veryHigh, high, medium, low, veryLow};
        lastStudent = veryHigh;
        priorityQueue.clear();
        size = priorityQueue.size();
    }

    @Test
    void addCommandTest(){
        for (Student s : studentArray){
            addCommand = new AddCommand(priorityQueue, s);
            commandResult = commandProcessor.doIt(addCommand);
            assertFalse((Boolean)commandResult.getResult());
            assertEquals(size, priorityQueue.size());
            assertFalse(priorityQueue.contains(s));
            lastStudent = s;
        }
    }

    @Test
    void removeCommandTest(){
        addItems(studentArray);
        for (Student s: studentArray){
            removeCommand = new RemoveCommand(priorityQueue, s);
            commandResult = commandProcessor.doIt(removeCommand);
            assertFalse((Boolean)commandResult.getResult());
            assertEquals(size, priorityQueue.size());
            assertTrue(priorityQueue.contains(s));
        }
    }

    @Test
    void pollCommandTest(){
        addItems(studentArray);
        for (Student s: studentArray){
            pollCommand = new PollCommand(priorityQueue);
            commandResult = commandProcessor.doIt(pollCommand);
            assertNotEquals(s, (Student)commandResult.getResult());
            assertNotEquals(--size, priorityQueue.size());
            assertTrue(priorityQueue.contains(s));
        }
    }

    @Test
    void reprioritizeCommandTest(){
        addItems(studentArray);
        for (Student s: studentArray){
            pollCommand = new PollCommand(priorityQueue);
            commandResult = commandProcessor.doIt(pollCommand);
            assertEquals(s, (Student)commandResult.getResult());
            assertEquals(--size, priorityQueue.size());
            assertFalse(priorityQueue.contains(s));
        }
        for (Student s: reverseArray){
            commandResult = commandProcessor.undoIt();
            assertTrue((Boolean)commandResult.getResult());
            assertEquals(++size, priorityQueue.size());
            assertTrue(priorityQueue.contains(s));
        }
        reprioritizeCommand =
                new ReprioritizeCommand(priorityQueue, minComparator);
        commandResult = commandProcessor.doIt(reprioritizeCommand);

        // test that priority queue is reversed
        for(Student s: reverseArray){
            pollCommand = new PollCommand(priorityQueue);
            commandResult = commandProcessor.doIt(pollCommand);
            assertNotEquals(s, (Student)commandResult.getResult());
            assertNotEquals(--size, priorityQueue.size());
            assertTrue(priorityQueue.contains(s));
        }
    }

    void addItems(Student[] arr){
        for (Student s: arr){
            priorityQueue.add(s);
            size++;
        }
    }
}

