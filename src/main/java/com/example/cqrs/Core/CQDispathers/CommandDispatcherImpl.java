package com.example.cqrs.Core.CQDispathers;

import com.example.cqrs.Core.Command.Command;
import com.example.cqrs.Core.Command.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class CommandDispatcherImpl<TResult> implements CommandDispatcher<TResult> {
    private final Map<Class<?>, String> commandClassMap = new HashMap<>();
    private final ApplicationContext applicationContext;

    public CommandDispatcherImpl (ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

    @Override
    @SuppressWarnings("unchecked")
    public TResult execute(Command<TResult> command) {
        return (TResult) applicationContext.getBean(CommandHandler.class, commandClassMap.get(command.getClass())).handle(command);

    }

    public List<TResult> execute(List<Command<TResult>> commandList){
        List<TResult> longList = new ArrayList<>();
        for(Command<TResult> command : commandList){
            longList.add(execute(command));
        }
        return longList;
    }

    @Override
    public void addCommandHandler(Class<?> classCommand, String nameBean) {
        commandClassMap.put(classCommand, nameBean);
    }
}
