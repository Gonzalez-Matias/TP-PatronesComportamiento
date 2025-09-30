package org.src.Command;

public class CursoCommandInvoker {
    private Command command;
    public void setCommand(Command command){
        this.command = command;
    }
    public void executeCommand(){
        command.execute();
    }
}
