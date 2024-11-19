package com.imedia24.productWatcher.service.implementation;

import com.imedia24.productWatcher.dao.model.ProductAction;
import com.imedia24.productWatcher.service.interfaces.IRabbitMQService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class RabbitMQService implements IRabbitMQService {

    private final AmqpTemplate rabbitTemplate;

    @Autowired
    public RabbitMQService(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${productwatcher.rabbitmq.exchange}")
    private String exchange;

    @Value("${productwatcher.rabbitmq.routingkey}")
    private String routingkey;

    @Override
    public void publish(Map<String, List<ProductAction>> actions) {
        log.info("Publishing actions ...");
        rabbitTemplate.convertAndSend(exchange, routingkey, actions);
        log.info("Actions published.");

    }
}
