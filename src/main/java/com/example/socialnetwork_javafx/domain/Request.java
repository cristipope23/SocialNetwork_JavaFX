package com.example.socialnetwork_javafx.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Request extends Entity<Integer>{
    private String username1;
    private String username2;
    private LocalDateTime requestTime;
    private String status;

    public Request(Integer idRequest,String username1, String username2, LocalDateTime time, String status) {
        super.setId(idRequest);
        this.username1 = username1;
        this.username2 = username2;
        this.requestTime = time;
        this.status = status;
    }

    public String getUsername1() {
        return username1;
    }

    public void setUsername1(String username1) {
        this.username1 = username1;
    }

    public String getUsername2() {
        return username2;
    }

    public void setUsername2(String username2) {
        this.username2 = username2;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(username1, request.username1) && Objects.equals(username2, request.username2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username1, username2);
    }
}

