package com.onlinemarketplace.register;

public class Register {
    private EmailValidator emailValidator;

    public Register() {
        emailValidator = new EmailValidator();
    }

    public boolean validEmailID(String email_Id){
        return emailValidator.validate(email_Id);
    }

    public boolean sendEmail(String name, String email, String hashCode, int userID){
        return emailValidator.sendEmail(name,email,hashCode,userID);
    }

    public boolean sendEmail_ForgotPassword(String name, String email, String hashCode, int userID){
        return emailValidator.sendEmail_ForgotPassword(name,email,hashCode,userID);
    }
    public boolean hidePassword(){

        return true;
    }

    public boolean confirmPassword(String password,String confirmPassword){
        if(password.equals(confirmPassword))
            return true;
        return false;
    }

}

