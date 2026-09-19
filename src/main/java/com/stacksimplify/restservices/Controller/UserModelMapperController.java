package com.stacksimplify.restservices.Controller;

import com.stacksimplify.restservices.Dto.UserMmDto;
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
@RequestMapping("/modelmapper/users")
public class UserModelMapperController {

    @Autowired
    UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public UserMmDto getUserById(@PathVariable @Min(value = 1, message = "ID must be greater than 0") Long id) throws UserNotFoundException {

        Optional<Client> clientOptional = userService.getUserById(id);
        if (!clientOptional.isPresent()) {
            throw new UserNotFoundException("User not found");
        }

        Client client = clientOptional.get();

        UserMmDto userMmDto = modelMapper.map(client, UserMmDto.class);
        return userMmDto;

    }
}

