package jsf.Commands;

public abstract class Command {
    abstract CommandResult execute();
    abstract CommandResult undo();
}
