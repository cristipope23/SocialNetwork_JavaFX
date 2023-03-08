package com.example.socialnetwork_javafx.domain;

import java.util.Objects;

public class Friendship extends Entity<Integer>{
    private int idUser1;
    private int idUser2;
    private String date;

    public Friendship(int id, int idUser1, int idUser2, String date) {
        super.setId(id);
        this.idUser1 = idUser1;
        this.idUser2 = idUser2;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Friendship that = (Friendship) o;
        return Objects.equals(super.getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser1, idUser2);
    }

    @Override
    public String toString() {
        return "Users with id's " + idUser1 + " and " + idUser2 + " are friends since " + date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate(){
        return this.date;
    }

    public int getIDU1() {
        return idUser1;
    }
    public int getIDU2() {
        return idUser2;
    }
}
