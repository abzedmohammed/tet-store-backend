package com.javasoft.house_inn.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailDispatcher {

    private static JavaMailSender emailSender;

    public EmailDispatcher(JavaMailSender emailSender){
        this.emailSender = emailSender;
    }

    public static void dispatchEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@abzedtetz.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        System.out.println("SEND EMAIL------------" + message);
        emailSender.send(message);
    }
}
