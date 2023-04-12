package com.example.cqrs.Rest.DTO;

import com.example.cqrs.Rest.Validation.New;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO implements DTO {

    @NotEmpty(groups = {New.class})
    private String name;
    @Size(min = 5, message = "Тест")
    private String surname;

}
