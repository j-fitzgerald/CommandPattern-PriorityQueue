package jsf.Commands;
import java.util.Stack;
public class CommandProcessor {
    private Stack<Command> commandStack;
    private Stack<Command> undoStack;
    private Stack<Command> redoStack;
    private static CommandProcessor ourInstance =
            new CommandProcessor();
    public static CommandProcessor getInstance() {
        return ourInstance;
    }

    public CommandProcessor(){
        commandStack = new Stack();
        undoStack = new Stack();
        redoStack = new Stack();
    }

    public void queueCommand(Command command){
        commandStack.push(command);
    }

    public CommandResult doIt(){
        if (commandStack.empty()) return null;
        Command command = commandStack.pop();
        return doIt(command);
    }

    public CommandResult doIt(Command command){
        undoStack.push(command);
        return command.execute();
    }

    public CommandResult undoIt(){
        if (undoStack.empty()) return null;
        Command command = undoStack.pop();
        redoStack.push(command);
        return command.undo();
    }

    public CommandResult redoIt(){
        if (redoStack.empty()) return null;
        Command command = redoStack.pop();
        undoStack.push(command);
        return command.execute();
    }
}
