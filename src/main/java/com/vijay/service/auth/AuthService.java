package com.vijay.service.auth;

import com.vijay.config.ApplicationProperties;
import com.vijay.entities.Users;
import com.vijay.models.AuthModel;
import com.vijay.repositories.UsersRepository;
import com.vijay.requests.LoginRequest;
import com.vijay.requests.RegisterRequest;
import com.vijay.utils.CryptoUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    ApplicationProperties applicationProperties;

    public AuthModel login(LoginRequest loginRequest) {

        Optional<Users> user = usersRepository.getUserByMail(loginRequest.getEmail());

        if (user.isPresent()) {

            try {
                if (user.get().getPassword().equals(CryptoUtils.hashSha256(loginRequest.getPassword()))) {
                    return new AuthModel(this.getAccessToken(user.get().getId()), true, "Success");
                }
            } catch (NoSuchAlgorithmException exception) {
                return new AuthModel("", false, "Cannot Process Login");
            }
        }
        return new AuthModel("", false, "Invalid Login");
    }

    public AuthModel register(RegisterRequest registerRequest) {
        Optional<Users> user = usersRepository.getUserByMail(registerRequest.getEmail());

        if (!user.isPresent()) {
            Users newUser = new Users();
            try {
                this.saveUser(newUser, registerRequest);

                if (newUser.getId() != null) {
                    return new AuthModel(this.getAccessToken(newUser.getId()), true, "User Register Successfully");
                }
            } catch (NoSuchAlgorithmException exception) {
                return new AuthModel("", false, "Cannot Process Login");
            }
        }
        return new AuthModel("", false, "Cannot Process Register");
    }

    private void saveUser(Users newUser, RegisterRequest registerRequest) throws NoSuchAlgorithmException {
         registerRequest.getUserObject(newUser);
         usersRepository.save(newUser);
    }

    public String getAccessToken(Long userId) {
        return Jwts.builder()
                .setSubject(""+userId)
                .setExpiration(new Date(System.currentTimeMillis() + applicationProperties.getJwtExpirationTime()))
                .signWith(SignatureAlgorithm.HS512, applicationProperties.getJwtSecret())
                .compact();
    }
}
