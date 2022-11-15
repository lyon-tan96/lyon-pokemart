package nus.iss.csf.miniprojectserver.controllers;

import java.io.IOException;
import java.util.Optional;

import nus.iss.csf.miniprojectserver.models.Registration;
import nus.iss.csf.miniprojectserver.services.jwt.UserDetailsServiceImpl;
import nus.iss.csf.miniprojectserver.util.JwtUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import nus.iss.csf.miniprojectserver.models.Login;
import nus.iss.csf.miniprojectserver.models.Response;
import nus.iss.csf.miniprojectserver.repositories.AccountRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping
public class LoginRestController {

    Login loginDetails;
    Response resp;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AccountRepository accountRepo;
    

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    @PostMapping(path = "/authenticate/{email}/{password}")
    public void createAuthenticationToken(@PathVariable String email, @PathVariable String password, HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException, JSONException, ServletException {

        final UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        if(userDetails.getPassword().equals(password)){
            Optional<Registration> opt = accountRepo.findUserByJustEmail(email);


            final String jwt = jwtUtil.generateToken(userDetails);

            response.getWriter().write(new JSONObject()
                    .put("username", opt.get().getUsername())
                    .toString()
            );
            response.addHeader("Access-Control-Expose-Headers", "Authorization");
            response.addHeader("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, X-Custom-header");
            response.addHeader(HEADER_STRING, TOKEN_PREFIX + jwt);
        }else{
            throw new BadCredentialsException("Incorrect username or password");
        }

    }

}
