package com.example.cqrs.Core.Query.UserQuery;

import com.example.cqrs.Core.Query.Query;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindUserByNameQuery<TResult> implements Query<TResult> {
    private String name;
    public String getName(){
        return name;
    }
}
