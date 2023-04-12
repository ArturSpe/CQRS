package com.example.cqrs.Rest.Controllers;

import com.example.cqrs.Core.CQDispathers.CommandDispatcherImpl;
import com.example.cqrs.Core.CQDispathers.QueryDispatcher;
import com.example.cqrs.Core.CQDispathers.QueryDispatcherImpl;
import com.example.cqrs.Core.Command.UserCommand.CreateUserCommand;
import com.example.cqrs.Core.Query.Query;
import com.example.cqrs.Core.Query.UserQuery.FindUserByNameQuery;
import com.example.cqrs.Rest.DTO.DTO;
import com.example.cqrs.Rest.DTO.UserDTO;
import com.example.cqrs.Rest.Validation.New;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WorkUser {

    private final CommandDispatcherImpl<Long> commandDispatcher;
    private final QueryDispatcher queryDispatcher;

    public WorkUser(CommandDispatcherImpl<Long> commandDispatcher, QueryDispatcherImpl queryDispatcher){
        this.commandDispatcher = commandDispatcher;
        this.queryDispatcher = queryDispatcher;
    }

    @PostMapping("/createUser")
    public ResponseEntity<Long> create(@Validated(New.class) @RequestBody UserDTO userDTO){
        CreateUserCommand <Long> createUserCommand = CreateUserCommand
                .<Long>builder()
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .build();
        Long id = commandDispatcher.execute(createUserCommand);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserDTO>> getUserByName(@RequestParam String name){
        FindUserByNameQuery<List<UserDTO>> listFindUserByNameQuery = FindUserByNameQuery
                .<List<UserDTO>>builder()
                .name(name)
                .build();

        List<UserDTO> userDTOList = queryDispatcher.ask(listFindUserByNameQuery);
        return ResponseEntity.ok()
                .body(userDTOList);
    }
}
