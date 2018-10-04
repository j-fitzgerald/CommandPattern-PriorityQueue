package jsf.Commands;

import jsf.PriorityQueue;

public class PollCommand<T> extends Command {
    private PriorityQueue receiver;
    private T polledItem;

    public PollCommand(PriorityQueue receiver){
        this.receiver = receiver;
    }

    @Override
    CommandResult<T> execute() {
        polledItem = (T)receiver.poll();
        return new CommandResult(polledItem);
    }

    @Override
    CommandResult<T> undo() {
        return new CommandResult(receiver.add(polledItem));
    }

}
