package jsf.Commands;


public class CommandResult<T> {
    private Boolean bool;
    private T t;

    public CommandResult(boolean bool){
        this.bool = bool;
    }

    public CommandResult(T t){
        this.t = t;
    }

    public Object getResult(){
        if (t == null) return bool;
        else return t;
    }
}
