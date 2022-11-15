package nus.iss.csf.miniprojectserver.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.iss.csf.miniprojectserver.models.Registration;
import nus.iss.csf.miniprojectserver.repositories.AccountRepository;

@Service
public class RegistrationService {
    
    @Autowired
    private AccountRepository accountRepo;

    public void registerUser(Registration registration) throws RegistrationException {
        Optional<Registration> opt = accountRepo.findUserByEmail(registration.getEmail(), registration.getUsername());
        if (opt.isPresent()) {
            throw new RegistrationException("Username/Email is already registered in the system.");
        } else {
            accountRepo.newRegistration(registration);
        }
    }
}
