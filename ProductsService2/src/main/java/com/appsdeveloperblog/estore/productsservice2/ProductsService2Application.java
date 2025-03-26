package com.appsdeveloperblog.estore.productsservice2;

import com.appsdeveloperblog.estore.productsservice2.config.AxonXstreamConfig;
import com.appsdeveloperblog.estore.productsservice2.events.ProductEventsErrorHandler;
import com.appsdeveloperblog.estore.productsservice2.interceptor.CreateProductCommandInterceptor;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDiscoveryClient
@Import({AxonXstreamConfig.class})
public class ProductsService2Application {

    public static void main(String[] args) {
        SpringApplication.run(ProductsService2Application.class, args);
    }

    @Autowired
    public void registerCreateProductCommandInterceptor(ApplicationContext context, CommandBus commandBus) {
        commandBus.registerDispatchInterceptor(context.getBean(CreateProductCommandInterceptor.class));
    }

    @Autowired
    public void registerEventErrorHandler(EventProcessingConfigurer config) {
        config.registerListenerInvocationErrorHandler("product-group",
                conf -> new ProductEventsErrorHandler());

        // can be done also with the one provided by axon
//        config.registerListenerInvocationErrorHandler("product-group",
//                conf -> PropagatingErrorHandler.instance());
    }

}
