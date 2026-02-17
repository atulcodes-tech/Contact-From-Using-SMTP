package com.contact.Contact.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ContactService {

    @Autowired
    private EmailService emailService;

    public void processContactForm(ContactForm contactForm) {
        // Here you could add additional business logic
        // such as saving to database, logging, etc.

        // Send email notification
        emailService.sendEmail(contactForm);

        // For HTML email (uncomment if you want HTML emails)
         try {
             emailService.sendHtmlEmail(contactForm);
         } catch (Exception e) {
             // Fallback to simple email if HTML fails
             emailService.sendEmail(contactForm);
         }
    }
}
