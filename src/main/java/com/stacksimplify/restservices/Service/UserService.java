package com.stacksimplify.restservices.Service;

import com.stacksimplify.restservices.Entities.Client;
import com.stacksimplify.restservices.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<Client> getAllUsers() {
        return userRepository.findAll();
    }

    public Client createUser(Client client) {
        return userRepository.save(client);
    }

    public Optional<Client> getUserById(Long id) {
        Optional<Client> client = userRepository.findById(id);
        return client;
    }

    public Client updateUserById(Client client, Long id) {
        client.setId(id);
        return userRepository.save(client);
    }

    public void deleteUserById(Long id) {
        if(userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
        }
    }

    public Client getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
