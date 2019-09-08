package edu.udacity.java.nano.chat;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session   WebSocket Session
 */

@Component
@ServerEndpoint("/chat")
public class WebSocketChatServer {

    /**
     * All chat sessions.
     */
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

    private static void sendMessageToAll(String msg) {
        onlineSessions.forEach((k,v ) -> {
            try {
                v.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Open connection, 1) add session, 2) add user.
     */
    @OnOpen
    public void onOpen(Session session) {
        JSONObject jsonObject = new JSONObject();
        onlineSessions.put(session.getId(), session);
        jsonObject.put("onlineCount", onlineSessions.size());
        jsonObject.put("type", "ENTER");
        this.sendMessageToAll(jsonObject.toString());
    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr) {
        Gson g = new Gson();
        JSONObject jsonObject = new JSONObject();
        Message msg = g.fromJson(jsonStr, Message.class);
        switch(msg.getType()){
            case("SPEAK"):
                jsonObject.put("type", "SPEAK");
                jsonObject.put("msg", msg.getUsername() + ": " + msg.getMsg());
                jsonObject.put("onlineCount", onlineSessions.size());
                this.sendMessageToAll(jsonObject.toString());
                break;

            default:
                this.onError(session, new IllegalArgumentException("INVALID type"));
        }
    }

    /**
     * Close connection, 1) remove session, 2) update user.
     */
    @OnClose
    public void onClose(Session session) {
        System.out.println("onClose was called ... the session  id is " + session.getId());
        onlineSessions.remove(session.getId());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("onlineCount", onlineSessions.size());
        jsonObject.put("type", "CLOSE");
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.sendMessageToAll(jsonObject.toString());
    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

}
