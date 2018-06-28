package com.fecund.shcart.entity;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.Session;
import javax.mail.Transport;
public class Javamail {
	 public static void email(String password, String EmailId, String userName, String id) {

  Properties props = new Properties();
  props.put("mail.smtp.auth", "true");
  props.put("mail.smtp.starttls.enable", "true");
  props.put("mail.smtp.host", "smtp.gmail.com");
  props.put("mail.smtp.port", "587");//465
  props.put("mail.smtp.socketFactory.class",
          "javax.net.ssl.SSLSocketFactory"); 
  Session session = Session.getInstance(props,
    new javax.mail.Authenticator() {
   protected PasswordAuthentication getPasswordAuthentication() {
    return new PasswordAuthentication("sujataghodake0@gmail.com", "Ghod@ke123");
   }
    });
  try {

   Message message = new MimeMessage(session);
   message.setFrom(new InternetAddress("sujataghodake0@gmail.com"));
   message.setRecipients(Message.RecipientType.TO,
    InternetAddress.parse(EmailId));
   message.setSubject("Authorised!!!");
   message.setText("hi there....!!!");
   Transport.send(message);
   System.out.println("Dear,"+userName
     + "\n\n Your Password is: "+password);

  } catch (MessagingException e) {
   throw new RuntimeException(e);
  }
 }

}

