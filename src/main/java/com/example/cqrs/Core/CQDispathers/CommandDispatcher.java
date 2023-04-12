package com.example.cqrs.Core.CQDispathers;

import com.example.cqrs.Core.Command.Command;

public interface CommandDispatcher<TResult> {

    TResult execute (Command<TResult> command);
    void addCommandHandler(Class<?> classCommand, String nameBean);

}
