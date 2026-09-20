package com.stacksimplify.restservices.Controller;

import com.stacksimplify.restservices.Dto.UserMsDto;
import com.stacksimplify.restservices.Entities.Client;
import com.stacksimplify.restservices.Mappers.UserMapper;
import com.stacksimplify.restservices.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mapstruct/users")
public class UserMapStructController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public List<UserMsDto> getAllUserDtos() {
        return userMapper.userstoUserDtos(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public UserMsDto getUserById(@PathVariable Long id){
        Optional<Client> clientOptional = userRepository.findById(id);
        Client client = clientOptional.get();
        return UserMapper.INSTANCE.usertoUserDto(client);
    }
}
