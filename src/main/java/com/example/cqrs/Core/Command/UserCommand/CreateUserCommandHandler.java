package com.example.cqrs.Core.Command.UserCommand;


import com.example.cqrs.Core.Command.CommandHandler;
import com.example.cqrs.Models.Entitys.User;
import com.example.cqrs.Repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateUserCommandHandler implements CommandHandler<Long, CreateUserCommand<Long>> {

    private final UserRepository userRepository;

    public CreateUserCommandHandler(UserRepository userRepository){
        this.userRepository = userRepository;

    }

    @Override
    public Long handle(CreateUserCommand<Long> command) {
        User user = User
                .builder()
                .name(command.getName())
                .surname(command.getSurname())
                .build();
        return userRepository.save(user).getId();
    }
}
