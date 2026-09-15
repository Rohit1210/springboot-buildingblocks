package com.stacksimplify.restservices.Controller;

import com.stacksimplify.restservices.Entities.Client;
import com.stacksimplify.restservices.Exceptions.UserNotFoundException;
import com.stacksimplify.restservices.Repo.OrderRespository;
import com.stacksimplify.restservices.Repo.UserRepository;
import com.stacksimplify.restservices.Service.UserService;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.ControllerLinkRelationProvider;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/hateoas/users")
@Validated
public class UserHateoasController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Client> getAllUsersMethod() {
        return userService.getAllUsers();}

    @GetMapping("/{id}")
    public EntityModel<Client> getUserById(@PathVariable @Min(value = 1, message = "ID must be greater than 0") Long id) {
        try{
              Optional<Client> clientOptional = userService.getUserById(id);
              Client client = clientOptional
                        .orElseThrow(() -> new UserNotFoundException("User not found"));
              Long userid = client.getUserid();
              Link selfLink = linkTo(methodOn(UserHateoasController.class).getUserById(userid)
            ).withSelfRel();
            client.add(selfLink);
            EntityModel<Client> finalResource = EntityModel.of(client, selfLink);
            return finalResource;

        } catch (UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

}
