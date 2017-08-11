package sample;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
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
@EnableAutoConfiguration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer
{

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry)
    { 
        registry.addHandler(echoWebSocketHandler(), "/event");
        registry.addHandler(echoWebSocketHandler(), "/webclient").setAllowedOrigins("*").withSockJS();
    }

    @Bean
    public EchoService echoService() {
        return new DefaultEchoService("Did you say \"%s\"?");
    }

    @Bean
    public WebSocketHandler echoWebSocketHandler() {
        return new EchoWebSocketHandler(echoService());
    }
}
