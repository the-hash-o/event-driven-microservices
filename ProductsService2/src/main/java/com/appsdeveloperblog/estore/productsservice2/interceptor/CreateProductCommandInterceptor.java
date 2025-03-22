package com.appsdeveloperblog.estore.productsservice2.interceptor;

import com.appsdeveloperblog.estore.productsservice2.command.CreateProductCommand;
import com.appsdeveloperblog.estore.productsservice2.data.entity.ProductLookupEntity;
import com.appsdeveloperblog.estore.productsservice2.data.repo.ProductLookupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.BiFunction;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    private final ProductLookupRepository productLookupRepository;

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

    private void validateCreateProductCommand(CommandMessage<?> command) {
        CreateProductCommand createProductCommand = (CreateProductCommand) command.getPayload();
        ProductLookupEntity productLookupEntity = productLookupRepository.findByProductIdOrTitle(createProductCommand.getProductId(), createProductCommand.getTitle());

        if (productLookupEntity != null) {
            throw new IllegalStateException(String.format("Product with productId: %s or title: %s already exists", createProductCommand.getProductId(), createProductCommand.getTitle()));
        }
    }
}
