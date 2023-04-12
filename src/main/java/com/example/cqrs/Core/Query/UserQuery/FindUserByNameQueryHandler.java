package com.example.cqrs.Core.Query.UserQuery;
import com.example.cqrs.Core.Query.QueryHandler;
import com.example.cqrs.Repository.UserRepository;
import com.example.cqrs.Rest.DTO.UserDTO;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class FindUserByNameQueryHandler implements QueryHandler<List<UserDTO>, FindUserByNameQuery<List<UserDTO>>> {

    private final UserRepository userRepository;

    public FindUserByNameQueryHandler (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> execute(FindUserByNameQuery<List<UserDTO>> query) {
        return userRepository
                .findUserByName(query
                        .getName())
                .stream()
                .map(user -> UserDTO.builder()
                        .name(user.getName())
                        .surname(user.getSurname())
                        .build())
                .toList();
    }
}
