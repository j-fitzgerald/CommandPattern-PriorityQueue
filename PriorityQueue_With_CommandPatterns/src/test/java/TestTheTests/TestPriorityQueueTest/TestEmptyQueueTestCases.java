package TestTheTests.TestPriorityQueueTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestEmptyQueueTestCases extends TestSetupTests {

    @Test
    @DisplayName("Empty Queue: isEmpty()")
    void emptyQueueIsEmpty(){
        assertFalse(priorityQueue.isEmpty());
    }

    @Test
    @DisplayName("EmptyQueue: poll()")
    void emptyQueuepoll(){
        assertFalse(priorityQueue.poll() == (null));
    }

    @Test
    @DisplayName("EmptyQueue: add")
    void emptyQueueAdd(){
        assertFalse(priorityQueue.add(lowPriority));
    }
}
