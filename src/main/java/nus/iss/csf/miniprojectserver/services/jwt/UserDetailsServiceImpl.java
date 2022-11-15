package nus.iss.csf.miniprojectserver.services.jwt;

import nus.iss.csf.miniprojectserver.models.Registration;
import nus.iss.csf.miniprojectserver.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepo;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Registration> opt = accountRepo.findUserByJustEmail(s);
        if (opt == null)
            throw new UsernameNotFoundException("Username not found",null);
        return new org.springframework.security.core.userdetails.User(opt.get().getEmail(), opt.get().getUsername(), new ArrayList<>());
    }

}
