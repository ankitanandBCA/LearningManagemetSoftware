package com.lms.example.service;

import com.lms.example.entity.User;
import com.lms.example.repositry.UserRepo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class UserService
{

    @Autowired
    UserRepo userRepo;

    @Autowired
    private JavaMailSender mailSender;

    public User adduser(User user)
    {
        User save = userRepo.save(user);

        try {
            // student ka email aur name bhejna hai
            sendRegistrationEmail(save.getEmail(), save.getName());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return save;
    }

    public void sendRegistrationEmail(String toEmail, String name) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(toEmail);
        helper.setSubject("Registration Successful");

        String content = "<h1>Welcome, " + name + " 🎉</h1>"
                + "<p>Your registration was successful.</p>"
                + "<p>Sending Email by <b>MyLMS</b></p>"
                + "<br>"
               ;

        helper.setText(content, true); // true = HTML enabled

        mailSender.send(message);
    }


    public User loginuser(String email,String password)
    {
        return userRepo.findByEmailAndPassword(email, password);
    }

}
