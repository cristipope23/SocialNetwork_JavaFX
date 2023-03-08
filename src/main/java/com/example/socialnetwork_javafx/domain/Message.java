package com.example.socialnetwork_javafx.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message extends Entity<Integer>{
    private int id_user1;
    private int id_user2;
    private int id_sender;
    private String message;
    private LocalDateTime date;

    public Message(int id, int id_user1, int id_user2, int id_sender, String message, LocalDateTime date){
        super.setId(id);
        this.id_user1 = id_user1;
        this.id_user2 = id_user2;
        this.id_sender = id_sender;
        this.message = message;
        this.date = date;
    }

    public int getId_user1() {
        return id_user1;
    }

    public void setId_user1(int id_user1) {
        this.id_user1 = id_user1;
    }

    public int getId_user2() {
        return id_user2;
    }

    public void setId_user2(int id_user2) {
        this.id_user2 = id_user2;
    }

    public int getId_sender() {
        return id_sender;
    }

    public void setId_sender(int id_sender) {
        this.id_sender = id_sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return id_user1 == message1.id_user1 && id_user2 == message1.id_user2 && id_sender == message1.id_sender && Objects.equals(message, message1.message) && Objects.equals(date, message1.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_user1, id_user2, id_sender, message, date);
    }
}
