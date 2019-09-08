package edu.udacity.java.nano.chat;

/**
 * WebSocket message model
 */
public class Message {

    private String type;

    private String msg;

    private String username;

    public Message() {
    }

    public Message(String type) {
        this.type = type;
        System.out.println("Just created a new Message object and it's action is " + type);
    }

    public Message(String type, String msg) {
        this.type = type;
        this.msg = msg;
        System.out.println("Just created a new Message object and it's action is " + type);
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public String getMsg() {
        return msg;
    }

    public String getUsername() {
        return username;
    }
}

