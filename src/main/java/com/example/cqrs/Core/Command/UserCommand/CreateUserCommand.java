package com.example.cqrs.Core.Command.UserCommand;

import com.example.cqrs.Core.Command.Command;
import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Builder
public class CreateUserCommand<TResult> implements Command<TResult> {
    private final String name;
    private final String surname;
}
