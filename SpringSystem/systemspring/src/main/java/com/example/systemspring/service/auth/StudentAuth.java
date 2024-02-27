package com.example.systemspring.service.auth;

import org.springframework.stereotype.Service;

@Service

public class StudentAuth {

    int authenticatedId = -1;
    boolean isAuthenticated = false;


    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }




    public int getAuthenticatedId() {
        return authenticatedId;
    }

    public void setAuthenticatedId(int authenticatedId) {
        this.authenticatedId = authenticatedId;
    }


}

