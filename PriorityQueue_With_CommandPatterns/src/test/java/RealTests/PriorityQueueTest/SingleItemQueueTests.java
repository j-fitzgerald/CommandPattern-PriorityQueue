package RealTests.PriorityQueueTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SingleItemQueueTests extends SetupTests {
    // ONE ITEM ONLY
    void singleItemSetup(){
        priorityQueue.add(mediumPriority[0]);
    }

    // Does isEmpty() return false?
    @Test
    @DisplayName("SingleItem: isEmpty()")
    void singleItemIsEmpty(){
        singleItemSetup();
        assertFalse(priorityQueue.isEmpty());
    }

    // Does add() with high priority work(head)?
    @Test
    @DisplayName("SingleItem: add() -> HEAD")
    void singleItemEnqueueHead(){
        singleItemSetup();
        priorityQueue.add(highPriority[0]);
        assertTrue(priorityQueue.poll().equals(highPriority[0]));
        assertTrue(priorityQueue.poll().equals(mediumPriority[0]));
        assertTrue(priorityQueue.isEmpty());
    }

    // Does poll() work?
    // does isEmpty() return true?
    @Test
    @DisplayName("SingleItem: Dequeue")
    void singleItempoll(){
        singleItemSetup();
        assertFalse(priorityQueue.isEmpty());
        assertTrue(priorityQueue.poll().equals(mediumPriority[0]));
        assertTrue(priorityQueue.isEmpty());
        assertTrue(priorityQueue.poll() == (null));
    }

    // does add() work with low priority work(tail)?
    @Test
    @DisplayName("SingleItem: Enqueue Tail")
    void singleItemEnqueueTail(){
        singleItemSetup();
        priorityQueue.add(lowPriority[0]);
        assertTrue(priorityQueue.poll().equals(mediumPriority[0]));
        assertTrue(priorityQueue.poll().equals(lowPriority[0]));
        assertTrue(priorityQueue.isEmpty());
        assertTrue(priorityQueue.poll() == (null));
    }
}
