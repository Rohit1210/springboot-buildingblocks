package com.stacksimplify.restservices.Controller;

import com.stacksimplify.restservices.Dto.UserDtoV1;
import com.stacksimplify.restservices.Dto.UserDtoV2;
import com.stacksimplify.restservices.Entities.Client;
import com.stacksimplify.restservices.Exceptions.UserNotFoundException;
import com.stacksimplify.restservices.Service.UserService;
import jakarta.validation.constraints.Min;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/versioning/mediatype/users")
public class UserMediaTypeVersioningController {

    @Autowired
    UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "/{id}", produces = "application/vnd.stacksimplify.app-v1+json")
    public UserDtoV1 getUserById2(@PathVariable @Min(value = 1, message = "ID must be greater than 0") Long id) throws UserNotFoundException {

        Optional<Client> clientOptional = userService.getUserById(id);
        if (!clientOptional.isPresent()) {
            throw new UserNotFoundException("User not found");
        }

        Client client = clientOptional.get();

        UserDtoV1 userDtoV1 = modelMapper.map(client, UserDtoV1.class);
        return userDtoV1;

    }

    @GetMapping(value = "/{id}", produces = "application/vnd.stacksimplify.app-v2+json")
    public UserDtoV2 getUserById(@PathVariable @Min(value = 1, message = "ID must be greater than 0") Long id) throws UserNotFoundException {

        Optional<Client> clientOptional = userService.getUserById(id);
        if (!clientOptional.isPresent()) {
            throw new UserNotFoundException("User not found");
        }

        Client client = clientOptional.get();

        UserDtoV2 userDtoV2 = modelMapper.map(client, UserDtoV2.class);
        return userDtoV2;

    }

}

