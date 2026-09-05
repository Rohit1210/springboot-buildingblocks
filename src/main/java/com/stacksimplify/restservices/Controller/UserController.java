package com.stacksimplify.restservices.Controller;

import com.stacksimplify.restservices.Entities.Client;
import com.stacksimplify.restservices.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<Client> getAllUsersMethod() {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public Client createUser(@RequestBody Client client) {
        return userService.createUser(client);
    }

    @GetMapping("/users/{id}")
    public Optional<Client> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/users/{id}")
    public Client updateUserById(@RequestBody Client client, @PathVariable Long id) {
        return userService.updateUserById(client, id);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @GetMapping("/users/byusername/{username}")
    public Client getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

}
