package jsf.Commands;

import jsf.PriorityQueue;

public class RemoveCommand<T> extends Command {
    private PriorityQueue receiver;
    private T item;

    public RemoveCommand(PriorityQueue receiver, T t){
        this.receiver = receiver;
        this.item = t;
    }
    public CommandResult<T> execute(){
        return new CommandResult(receiver.remove(item));
    }

    public CommandResult<T> undo() {
        return new CommandResult(receiver.add(item));
    }

}