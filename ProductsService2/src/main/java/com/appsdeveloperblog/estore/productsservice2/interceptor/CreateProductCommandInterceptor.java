package com.appsdeveloperblog.estore.productsservice2.interceptor;

import com.appsdeveloperblog.estore.productsservice2.command.CreateProductCommand;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.BiFunction;

@Component
@Slf4j
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    @Nonnull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@Nonnull List<? extends CommandMessage<?>> messages) {

        return (index, command) -> {
            log.info("Intercepted command: {}", command);

            if (CreateProductCommand.class.equals(command.getPayloadType())) {
                validateCreateProductCommand(command);
            }
            return command;
        };
    }

    private static void validateCreateProductCommand(CommandMessage<?> command) {
        CreateProductCommand createProductCommand = (CreateProductCommand) command.getPayload();

        if (createProductCommand.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        if (createProductCommand.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title must not be empty.");
        }
    }
}
