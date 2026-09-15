package com.stacksimplify.restservices.Controller;

import com.stacksimplify.restservices.Entities.Client;
import com.stacksimplify.restservices.Entities.Order;
import com.stacksimplify.restservices.Exceptions.UserNotFoundException;
import com.stacksimplify.restservices.Repo.OrderRespository;
import com.stacksimplify.restservices.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hateoas/users")
@Validated
public class OrderHateoasController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRespository orderRespository;

    @GetMapping("/{userid}/orders")
    public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {
        Optional<Client> client = userRepository.findById(userid);
        if(!client.isPresent()){
            throw new UserNotFoundException("User not Found");
        }
        return client.get().getOrder();
    }


}
