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
        System.out.println(session.getUserProperties().toString());
        JSONObject jsonObject = new JSONObject();
        onlineSessions.put(session.getId(), session);
        jsonObject.put("onlineCount", onlineSessions.size());
        this.sendMessageToAll(jsonObject.toString());
        //System.out.println("Inside WebSocketChatServer ... onOpen");
    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr) {
        Gson g = new Gson();
        JSONObject jsonObject = new JSONObject();
        //jsonObject.put("data", "Sup dawggggg??? ... You have entered!!!");
        //Player p = g.fromJson(jsonString, Player.class)
        System.out.println("jsonStr is " + jsonStr);
        Message msg = g.fromJson(jsonStr, Message.class);
        switch(msg.getType()){

            case("ENTER"):
                try {
                    onlineSessions.put(msg.getUsername(), session);
                    //session.getBasicRemote().sendText(jsonObject.toString());
                    jsonObject.put("onlineCount", onlineSessions.size());
                    session.getBasicRemote().sendText(jsonObject.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            case("SPEAK"):
                jsonObject.put("type", "SPEAK");
                jsonObject.put("msg", msg.getUsername() + ": " + msg.getMsg());
                jsonObject.put("onlineCount", onlineSessions.size());
                //this.sendMessageToAll(msg.getUsername() + ": " + msg.getMsg());
                this.sendMessageToAll(jsonObject.toString());



        }
        //Message msg = new Message(jsonStr);

        System.out.println("Inside WebSocketChatServer ... msg.getAction(). ... " + msg.getType());
        System.out.println("Inside WebSocketChatServer ... msg.getMessage(). ... " + msg.getMsg());


    }

    /**
     * Close connection, 1) remove session, 2) update user.
     */
    @OnClose
    public void onClose(Session session) {
        System.out.println("onClose was called ... the session  id is " + session.getId());
        onlineSessions.remove(session.getId());
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

}
