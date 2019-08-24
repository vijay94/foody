package com.vijay.requests;

import com.vijay.entities.Users;
import com.vijay.utils.CryptoUtils;

import javax.validation.constraints.NotEmpty;
import java.security.NoSuchAlgorithmException;

public class RegisterRequest {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void getUserObject(Users newUser) throws NoSuchAlgorithmException {

        newUser.setPassword(CryptoUtils.hashSha256(this.password));
        newUser.setFirstName(this.firstName);
        newUser.setLastName(this.lastName);
        newUser.setEmail(this.email);
    }
}
