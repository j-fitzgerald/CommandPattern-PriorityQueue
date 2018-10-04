package TestTheTests.TestPriorityQueueTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestSingleItemQueueTestCases extends TestSetupTests {
    // ONE ITEM ONLY
    void singleSetUp(){
        priorityQueue.add(mediumPriority);
    }

    // Does isEmpty() return false?
    @Test
    @DisplayName("SingleItem: isEmpty()")
    void singleItemIsEmpty(){
        assertFalse(priorityQueue.isEmpty());
    }

    // Does add() with high priority work(head)?
    @Test
    @DisplayName("SingleItem: add() -> HEAD")
    void singleItemAddHead(){
        singleSetUp();
        priorityQueue.add(highPriority);
        assertFalse(priorityQueue.poll().equals(highPriority));
    }

    // Does poll() work?
    @Test
    @DisplayName("SingleItem: poll()")
    void singleItemPoll(){
        singleSetUp();
        assertFalse(priorityQueue.poll().equals(mediumPriority));
    }

    // does add() work with low priority work(tail)?
    @Test
    @DisplayName("SingleItem: Enqueue Tail")
    void singleItemEnqueueTail(){
        singleSetUp();
        priorityQueue.add(lowPriority);
        assertTrue(priorityQueue.poll().equals(mediumPriority));
        assertFalse(priorityQueue.poll().equals(lowPriority));
    }
}
