package com.task.first;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="home")
public class Home {
	
    private String logUser = ""; 
    private String logPassword = "";

    public String getlogUser() {
        return logUser;
    }

    public void setlogUser(String user) {
        this.logUser = user;
    }

    public String getlogPassword() {
        return logPassword;
    }

    public void setlogPassword(String password) {
        this.logPassword = password;
    }
    
    public String getUser() {
		return "Administrator:" + " " + logUser;
    }
}