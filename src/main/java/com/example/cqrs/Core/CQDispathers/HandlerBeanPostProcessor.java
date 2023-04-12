package com.example.cqrs.Core.CQDispathers;

import com.example.cqrs.Core.Command.CommandHandler;
import com.example.cqrs.Core.Query.QueryHandler;
import junit.framework.TestResult;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class HandlerBeanPostProcessor implements BeanPostProcessor {

    private final CommandDispatcher commandDispatcher;
    private final QueryDispatcher queryDispatcher;
    public HandlerBeanPostProcessor(CommandDispatcherImpl commandDispatcher, QueryDispatcherImpl queryDispatcher){
        this.commandDispatcher = commandDispatcher;
        this.queryDispatcher = queryDispatcher;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof CommandHandler || bean instanceof QueryHandler){
            List<Method> methodList = Arrays
                    .stream(bean.getClass().getDeclaredMethods())
                    .toList();
            for (Method beanMethod : methodList){
                if (bean instanceof CommandHandler)commandDispatcher.addCommandHandler(beanMethod.getParameterTypes()[0], beanName);
                else queryDispatcher.addQueryHandler(beanMethod.getParameterTypes()[0], beanName);

            }

        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
