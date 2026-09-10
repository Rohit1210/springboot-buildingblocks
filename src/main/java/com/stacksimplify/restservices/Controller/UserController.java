package com.stacksimplify.restservices.Controller;

import com.stacksimplify.restservices.Entities.Client;
import com.stacksimplify.restservices.Exceptions.UserExistsException;
import com.stacksimplify.restservices.Exceptions.UserNameNotFoundException;
import com.stacksimplify.restservices.Exceptions.UserNotFoundException;
import com.stacksimplify.restservices.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import jakarta.validation.constraints.Min;

import java.util.List;
import java.util.Optional;

@RestController
@Validated
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<Client> getAllUsersMethod() {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public ResponseEntity<Void> createUser(@Validated @RequestBody Client client, UriComponentsBuilder builder) {
        try {
            userService.createUser(client);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(builder.path("/users/{id}").buildAndExpand(client.getId()).toUri());
            return new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);
        } catch (UserExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @GetMapping("/users/{id}")
    public Optional<Client> getUserById(@PathVariable @Min(value = 1, message = "ID must be greater than 0") Long id) {
        try{
            return userService.getUserById(id);
        } catch (UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PutMapping("/users/{id}")
    public Client updateUserById(@RequestBody Client client, @PathVariable Long id) {
        try{
            return userService.updateUserById(client, id);
        } catch (UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
        }
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @GetMapping("/users/byusername/{username}")
    public Client getUserByUsername(@PathVariable String username) throws UserNameNotFoundException {
        Client client = userService.getUserByUsername(username);
        if (client == null)
            throw new UserNameNotFoundException("Username: " + username + " not found in User Repo");
        return client;
    }

}
