package com.example.cqrs.Core.CQDispathers;

import com.example.cqrs.Core.Command.Command;
import com.example.cqrs.Core.Query.Query;
import com.example.cqrs.Core.Query.QueryHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class QueryDispatcherImpl implements QueryDispatcher {

    private final Map<Class<?>, String> classMap = new HashMap<>();;
    private final ApplicationContext applicationContext;

    private QueryDispatcherImpl(ApplicationContext applicationContext1){
        applicationContext = applicationContext1;

    }
    @Override
    @SuppressWarnings("unchecked")
    public <TResult> TResult ask(Query<TResult> query) {
        return (TResult) applicationContext
                .getBean(QueryHandler.class, classMap.get(query))
                .execute(query);
    }

    @Override
    public void addQueryHandler(Class<?> classCommand, String nameBean) {
        classMap.put(classCommand, nameBean);
    }
}
