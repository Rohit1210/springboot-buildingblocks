package com.stacksimplify.restservices.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.stacksimplify.restservices.Entities.Client;
import com.stacksimplify.restservices.Entities.Views;
import com.stacksimplify.restservices.Exceptions.UserNotFoundException;
import com.stacksimplify.restservices.Service.UserService;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@Validated
@RequestMapping("/jsonview/users")
public class UserJsonViewController {

    @Autowired
    UserService userService;

    @JsonView(Views.External.class)
    @GetMapping("/external/{id}")
    public Optional<Client> getUserById(@PathVariable @Min(value = 1, message = "ID must be greater than 0") Long id) {
        try{
            return userService.getUserById(id);
        } catch (UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @JsonView(Views.Internal.class)
    @GetMapping("/internal/{id}")
    public Optional<Client> getUserById2(@PathVariable @Min(value = 1, message = "ID must be greater than 0") Long id) {
        try{
            return userService.getUserById(id);
        } catch (UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

}
