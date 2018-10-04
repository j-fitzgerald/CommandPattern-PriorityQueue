package RealTests.PriorityQueueTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmptyQueueTests extends SetupTests {

    // Does isEmpty() work?
    @Test
    @DisplayName("Empty Queue: isEmpty()")
    void emptyQueueIsEmpty(){
        assertTrue(priorityQueue.isEmpty());
    }

    // Does poll() fail?
    @Test
    @DisplayName("EmptyQueue: poll()")
    void emptyQueuepoll(){
        assertTrue(priorityQueue.poll() == (null));
    }

    // does add() work?
    @Test
    @DisplayName("EmptyQueue: enqueue")
    void emptyQueueadd(){
        assertTrue(priorityQueue.isEmpty());
        priorityQueue.add(lowPriority[0]);
        assertFalse(priorityQueue.isEmpty());
        assertTrue(priorityQueue.poll().equals(lowPriority[0]));
    }

    // Enqueue a Zero Priority
    @Test
    @DisplayName("EmptyQueue: enqueue Zero Priority")
    void emptyQueueEnqueueZeroPriority(){
        assertTrue(priorityQueue.isEmpty());
        priorityQueue.add(zeroPriority[0]);
        assertFalse(priorityQueue.isEmpty());
        assertTrue(priorityQueue.poll().equals(zeroPriority[0]));
    }
}
