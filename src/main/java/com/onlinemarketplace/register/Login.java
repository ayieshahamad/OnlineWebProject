package com.onlinemarketplace.register;

import com.onlinemarketplace.database.Database;
import com.onlinemarketplace.model.User;
import com.onlinemarketplace.util.BCrypt;

public class Login {
    private String email_Id;
    private String password;
    private boolean status;
    private boolean verify;

    public Login() {
        status = false;
        verify = false;
    }
    public Login(String email_Id, String password) {
        this.email_Id = email_Id;
        this.password = password;
    }
    public String getEmail_Id() {
        return email_Id;
    }

    public void setEmail_Id(String email_Id) {
        this.email_Id = email_Id;
    }

    public String getPassword() {
        return password;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean validate(User user){//public static final String SALT = "$2a$10$DOWSDz/CyKaJ40hslrk5fe";//BCrypt.gensalt(12)
        status = Database.getInstance().login(email_Id,BCrypt.hashpw(password,BCrypt.SALT) ,user);
        return status;
    }
}
