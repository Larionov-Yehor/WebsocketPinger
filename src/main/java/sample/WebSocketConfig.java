package sample;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;

/*
 * WebSocket configuration.
 *
 * @Author Jay Sridhar
 */

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer
{
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry)
    { 
        registry.addHandler(new SocketHandler(), "/event");
    }
}


/*
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer
{
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config)
    {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry)
    {
        registry.addEndpoint("/chat").setAllowedOrigins("*").withSockJS();
        registry.addEndpoint("/event");
    }
}
*/
