package com.example.socialnetwork_javafx.domain;

import java.util.Objects;

public class User extends Entity<Integer>{
    private String username;
    private String password;
    private String email;

    public User(int id, String username, String password, String email) {
        super.setId(id);
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return super.getId() == user.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email);
    }

    @Override
    public String toString() {
        return "Id: " + super.getId() + ", Username: " + username  +", Email: " + email;
    }
}
