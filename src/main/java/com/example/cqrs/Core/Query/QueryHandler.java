package com.example.cqrs.Core.Query;

public interface QueryHandler<TResult, TQuery extends Query<TResult>> {

    TResult execute (TQuery query);

}
