package com.onlinemarketplace.register;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class EmailValidator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    /**
     * Validate hex with regular expression
     *
     * @param hex
     *            hex for validation
     * @return true valid hex, false invalid hex
     */
    public boolean validate(String hex) {

        matcher = pattern.matcher(hex);
        return matcher.matches();

    }
    public boolean sendEmail_ForgotPassword(String name, String email, String hashCode, int userID){
        String link = "http://localhost:8080/EmailVerification";

        //MAIL_REGISTRATION_SITE_LINK+"?scope=activation&userId="+id+"&hash="+hash;
        String host = "smtp.gmail.com";
        String port = "587";
        String message = "Dear "+name+"," +
                "\nPlease click on the following link to reset your password:" +
                "\n" + link +"?scope=forgotpassword&hash="+hashCode+"&userID="+userID+
                "\n";
        String subject = "Online MarketPlace - Reset your password";
        try {//aisha-tariq@live.com//aaliyaa2050@gmail.com//online.marketplace.id@gmail.com//ayeshahamad2015
            sendPlainTextEmail(host,port,"online.marketplace.id@gmail.com","Ayesha1234",email,subject,message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean sendEmail(String name, String email, String hashCode, int userID){
        String link = "http://localhost:8080/EmailVerification";

        //MAIL_REGISTRATION_SITE_LINK+"?scope=activation&userId="+id+"&hash="+hash;
        String host = "smtp.gmail.com";
        String port = "587";
        String message = "Dear "+name+"," +
                "\nThank you for registering Online MarketPlace. In order to verify your account please click the following link:" +
                "\n" + link +"?scope=activation&hash="+hashCode+"&userID="+userID+
                "\n";
        String subject = "Welcome to Online MarketPlace - Please verify your account";
        try {//aisha-tariq@live.com//aaliyaa2050@gmail.com//online.marketplace.id@gmail.com//ayeshahamad2015
            sendPlainTextEmail(host,port,"online.marketplace.id@gmail.com","Ayesha1234",email,subject,message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void sendPlainTextEmail(String host, String port,
                                          final String userName, final String password, String toAddress,
                                          String subject, String message) throws AddressException,
            MessagingException {

        System.out.println("d " + message);
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");

// *** BEGIN CHANGE
        properties.put("mail.smtp.user", userName);

        // creates a new session, no Authenticator (will connect() later)
        Session session = Session.getDefaultInstance(properties);
// *** END CHANGE

        // creates a new e-mail message
        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        // set plain text message
        msg.setText(message);

        System.out.println("stuck here");
// *** BEGIN CHANGE
        // sends the e-mail
        Transport t = session.getTransport("smtp");
        t.connect(userName, password);
        t.sendMessage(msg, msg.getAllRecipients());
        t.close();
// *** END CHANGE

        System.out.println("done: email sent");
    }
}