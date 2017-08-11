package sample;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;


@Component
public class EventSocketHandler extends TextWebSocketHandler {
    List sessions = new CopyOnWriteArrayList<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws InterruptedException, IOException {
        Map value = new Gson().fromJson(message.getPayload(), Map.class);
        session.sendMessage(new TextMessage("Hello " + value.get("type") + " !"));

        System.out.println("Message received:" + message.getPayload());

        Iterator<WebSocketSession> iter = sessions.iterator();

        while(iter.hasNext())
        {
            WebSocketSession current = iter.next();
            current.sendMessage(message);
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("Connection established");
        System.out.println("User with id " + session.getId() + " connected");

        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("Connection closed");
        System.out.println("User with id " + session.getId() + " disconnected");
        System.out.println(status.toString());
        sessions.remove(session);
    }
}
