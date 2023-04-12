package com.example.cqrs.Core.Command;

public interface CommandHandler<TResult, TCommand extends Command<TResult>> {
    TResult handle(TCommand command);

}
