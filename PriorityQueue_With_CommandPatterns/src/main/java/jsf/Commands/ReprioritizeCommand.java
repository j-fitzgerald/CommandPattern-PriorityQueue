package jsf.Commands;
import jsf.PriorityQueue;

import java.util.Comparator;

public class ReprioritizeCommand<T> extends Command {

    private PriorityQueue receiver;
    private Comparator oldComparator;
    private Comparator newComparator;

    public ReprioritizeCommand(PriorityQueue receiver,
                               Comparator comparator){
        this.receiver = receiver;
        this.newComparator = comparator;
    }

    public CommandResult<T> execute(){
        oldComparator = receiver.comparator();
        Object[] asList = receiver.toArray();
        receiver.comparator = newComparator;
        receiver.clear();
        for (Object o : asList){
            receiver.add(o);
        }
        return null;
    }

    public CommandResult<T> undo(){
        Object[] asList = receiver.toArray();
        newComparator = receiver.comparator();
        receiver.clear();
        receiver.comparator = oldComparator;
        for (Object o : asList){
            receiver.add(o);
        }
        return null;
    }
}
