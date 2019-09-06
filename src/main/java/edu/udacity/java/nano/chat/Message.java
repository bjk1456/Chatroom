package edu.udacity.java.nano.chat;

/**
 * WebSocket message model
 */
public class Message {

    private String action;

    private String message;

    public Message() {
    }

    public Message(String action) {
        this.action = action;
        System.out.println("Just created a new Message object and it's action is " + action);
    }

    public Message(String action, String message) {
        this.action = action;
        this.message = message;
        System.out.println("Just created a new Message object and it's action is " + action);
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAction() {
        return action;
    }

    public String getMessage() {
        return message;
    }
}

