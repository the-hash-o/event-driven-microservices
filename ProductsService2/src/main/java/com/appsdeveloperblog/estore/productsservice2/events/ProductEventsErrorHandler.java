package com.appsdeveloperblog.estore.productsservice2.events;

import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.EventMessageHandler;
import org.axonframework.eventhandling.ListenerInvocationErrorHandler;

import javax.annotation.Nonnull;

public class ProductEventsErrorHandler implements ListenerInvocationErrorHandler {
    @Override
    public void onError(@Nonnull Exception exception, @Nonnull EventMessage<?> event, @Nonnull EventMessageHandler eventHandler) throws Exception {
        throw exception;
    }
    // this is the way to do a transaction rollback
}
