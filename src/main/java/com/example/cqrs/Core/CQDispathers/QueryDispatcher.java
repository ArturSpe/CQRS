package com.example.cqrs.Core.CQDispathers;

import com.example.cqrs.Core.Command.Command;
import com.example.cqrs.Core.Query.Query;
import com.example.cqrs.Core.Query.QueryHandler;

public interface QueryDispatcher {
    <TResult> TResult ask (Query<TResult> query);
    void addQueryHandler(Class<?> classQuery, String nameBean);

}
