package RealTests.PriorityQueueTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LargeQueueTests extends SetupTests {

    // "Large" Queue - 6 items
    // Setup (high, high, med, med, low, low priority)
    void sixItemSetup(){
        priorityQueue.add(lowPriority[0]);
        priorityQueue.add(mediumPriority[0]);
        priorityQueue.add(highPriority[0]);
        priorityQueue.add(highPriority[1]);
        priorityQueue.add(mediumPriority[1]);
        priorityQueue.add(lowPriority[1]);

    }

    // Does add() with very high priority work (head)?
    @Test
    @DisplayName("large queue - enqueue head")
    void sixItemQueueEnqueueHead(){
        sixItemSetup();
        priorityQueue.add(veryHighPriority[0]);
        assertTrue(priorityQueue.poll().equals(veryHighPriority[0]));
        assertTrue(priorityQueue.poll().equals(highPriority[0]));
        assertTrue(priorityQueue.poll().equals(highPriority[1]));
        assertTrue(priorityQueue.poll().equals(mediumPriority[0]));
        assertTrue(priorityQueue.poll().equals(mediumPriority[1]));
        assertTrue(priorityQueue.poll().equals(lowPriority[0]));
        assertTrue(priorityQueue.poll().equals(lowPriority[1]));
        assertTrue(priorityQueue.isEmpty());
    }

    // does add() to the tail work (tail)?
    @Test
    @DisplayName("six item - enqueue tail")
    void sixItemEnqueueTail(){
        sixItemSetup();
        priorityQueue.add(veryLowPriority[0]);
        assertTrue(priorityQueue.poll().equals(highPriority[0]));
        assertTrue(priorityQueue.poll().equals(highPriority[1]));
        assertTrue(priorityQueue.poll().equals(mediumPriority[0]));
        assertTrue(priorityQueue.poll().equals(mediumPriority[1]));
        assertTrue(priorityQueue.poll().equals(lowPriority[0]));
        assertTrue(priorityQueue.poll().equals(lowPriority[1]));
        assertTrue(priorityQueue.poll().equals(veryLowPriority[0]));
        assertTrue(priorityQueue.isEmpty());
    }
}
