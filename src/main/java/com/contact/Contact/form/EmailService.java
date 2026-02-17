package com.contact.Contact.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendEmail(ContactForm contactForm) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("subhkaranjaiswal41@gmail.com");
        message.setSubject("New Contact Form Submission from " + contactForm.getName());
        message.setText("Name: " + contactForm.getName() +
                "\nEmail: " + contactForm.getEmail() +
                "\nMessage: " + contactForm.getMessage());

        mailSender.send(message);
    }

    public void sendHtmlEmail(ContactForm contactForm) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        // Prepare the evaluation context
        Context context = new Context();
        context.setVariable("name", contactForm.getName());
        context.setVariable("email", contactForm.getEmail());
        context.setVariable("message", contactForm.getMessage());

        // Create the HTML body using Thymeleaf
        String htmlContent = templateEngine.process("email-template", context);

        helper.setTo("subhkaranjaiswal41@gmail.com");
        helper.setSubject("New Contact Form Submission from " + contactForm.getName());
        helper.setText(htmlContent, true);

        mailSender.send(mimeMessage);
    }
}