package nus.iss.csf.miniprojectserver.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import nus.iss.csf.miniprojectserver.models.Email;
import nus.iss.csf.miniprojectserver.repositories.AccountRepository;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private AccountRepository accRepo;

    public void sendEmail(Email content) {

        Optional<String> opt = accRepo.findEmailByUsername(content.getUsername());

        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo(opt.get());

        msg.setSubject("Your order %s has been placed!".formatted(content.getOrderId()));
        msg.setText("Dear %s, \n \n Thank you for your purchase. Kindly PayNow $%s to 91234567 to begin the order process. \n \n Thank you! \n \n Regards \n PokeMart".formatted(content.getName(), content.getAmount()));

        javaMailSender.send(msg);
    }
    
}
