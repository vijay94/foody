package com.vijay.controllers.auth;

import com.vijay.config.ApplicationProperties;
import com.vijay.exception.InvalidRequestException;
import com.vijay.models.AuthModel;
import com.vijay.requests.LoginRequest;
import com.vijay.requests.RegisterRequest;
import com.vijay.responses.GenericResponse;
import com.vijay.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    ApplicationProperties applicationProperties;

    @PostMapping(value = "/login")
    public ResponseEntity login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) throws InvalidRequestException {
        if (bindingResult.hasErrors())
            throw new InvalidRequestException(bindingResult);

        AuthModel authModel = authService.login(loginRequest);

        if (authModel.isSuccess()) {
            HttpHeaders headers = new HttpHeaders();
            headers.set(applicationProperties.getJwtTokenName(), new StringBuilder(applicationProperties.getJwtBearer()).append(" ").append(
                    authModel.getJwt()).toString());
            return new ResponseEntity(new GenericResponse(200, authModel.getMessage()), headers, HttpStatus.OK );
        }

        return new ResponseEntity(new GenericResponse<>(401 , authModel.getMessage()), HttpStatus.UNAUTHORIZED);
    }


    @PostMapping(value = "/register")
    public ResponseEntity register(@Valid @RequestBody RegisterRequest registerRequest, BindingResult bindingResult) throws InvalidRequestException {
        if (bindingResult.hasErrors())
            throw new InvalidRequestException(bindingResult);

        AuthModel authModel = authService.register(registerRequest);

        if (authModel.isSuccess()) {
            HttpHeaders headers = new HttpHeaders();
            headers.set(applicationProperties.getJwtTokenName(), new StringBuilder(applicationProperties.getJwtBearer()).append(" ").append(
                    authModel.getJwt()).toString());
            return new ResponseEntity(new GenericResponse(200, authModel.getMessage()), headers, HttpStatus.OK );
        }

        return new ResponseEntity(new GenericResponse<>(401 , authModel.getMessage()), HttpStatus.UNAUTHORIZED);

    }
}
