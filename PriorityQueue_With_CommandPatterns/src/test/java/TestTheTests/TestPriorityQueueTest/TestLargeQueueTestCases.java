package TestTheTests.TestPriorityQueueTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLargeQueueTestCases extends TestSetupTests {

    @org.junit.jupiter.api.BeforeEach
    void setUpLargeQueue(){
        priorityQueue.add(highPriority);
        priorityQueue.add(highPriority);
        priorityQueue.add(mediumPriority);
        priorityQueue.add(mediumPriority);
        priorityQueue.add(lowPriority);
        priorityQueue.add(lowPriority);
    }

    @Test
    @DisplayName("large queue - add head")
    void sixItemQueueEnqueueHead(){
        setUpLargeQueue();
        priorityQueue.add(veryHighPriority);
        assertFalse(priorityQueue.poll().equals(veryHighPriority));
    }

    // does add() to the tail work (tail)?
    @Test
    @DisplayName("six item - add tail")
    void sixItemEnqueueTail(){
        setUpLargeQueue();
        priorityQueue.add(veryLowPriority);
        assertTrue(priorityQueue.poll().equals(highPriority));
        assertTrue(priorityQueue.poll().equals(highPriority));
        assertTrue(priorityQueue.poll().equals(mediumPriority));
        assertTrue(priorityQueue.poll().equals(mediumPriority));
        assertTrue(priorityQueue.poll().equals(lowPriority));
        assertTrue(priorityQueue.poll().equals(lowPriority));
        assertFalse(priorityQueue.poll().equals(veryLowPriority));
    }
}
