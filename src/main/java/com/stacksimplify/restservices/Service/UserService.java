package com.stacksimplify.restservices.Service;

import com.stacksimplify.restservices.Entities.Client;
import com.stacksimplify.restservices.Exceptions.UserExistsException;
import com.stacksimplify.restservices.Exceptions.UserNotFoundException;
import com.stacksimplify.restservices.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<Client> getAllUsers() {
        return userRepository.findAll();
    }

    public Client createUser(Client client) throws UserExistsException {
        //if user exist using username
        Client existingUser = userRepository.findByUsername(client.getUsername());
        //if not exists throw UserExistsException
        if(existingUser != null) {
            throw new UserExistsException("User already Exist in Repo");
        }
        return userRepository.save(client);
    }

    public Optional<Client> getUserById(Long id) throws UserNotFoundException {
        Optional<Client> client = userRepository.findById(id);
        if(!client.isPresent()){
            throw new UserNotFoundException("Client not found in user repository");
        }
        return client;
    }

    public Client updateUserById(Client client, Long id) throws UserNotFoundException{
        Optional<Client> optionalClient = userRepository.findById(id);
        if(!optionalClient.isPresent()){
            throw new UserNotFoundException("Client not found in user repository, provide the correct user id");
        }
        client.setId(id);
        return userRepository.save(client);
    }

    public void deleteUserById(Long id) {
        Optional<Client> optionalClient = userRepository.findById(id);
        if(!optionalClient.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Client not found in user repository, provide the correct user id");
        }
            userRepository.deleteById(id);
        }

    public Client getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
