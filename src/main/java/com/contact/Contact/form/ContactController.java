package com.contact.Contact.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<?> submitContactForm(@Valid @RequestBody ContactForm contactForm,
                                               BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        try {
            contactService.processContactForm(contactForm);
            return ResponseEntity.ok().body("Message sent successfully!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("An error occurred while sending your message. Please try again later.");
        }
    }
}