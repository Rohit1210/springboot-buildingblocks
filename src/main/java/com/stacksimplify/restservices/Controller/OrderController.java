package com.stacksimplify.restservices.Controller;

import com.stacksimplify.restservices.Entities.Client;
import com.stacksimplify.restservices.Entities.Order;
import com.stacksimplify.restservices.Exceptions.UserNotFoundException;
import com.stacksimplify.restservices.Repo.OrderRespository;
import com.stacksimplify.restservices.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class OrderController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRespository orderRespository;

    @GetMapping("/{userid}/orders")
    public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException{
        Optional<Client> client = userRepository.findById(userid);
        if(!client.isPresent()){
            throw new UserNotFoundException("User not Found");
        }
        return client.get().getOrder();
    }

    @PostMapping("/{userid}/orders")
    public Order createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFoundException {
        Optional<Client> client = userRepository.findById(userid);
        if(!client.isPresent()){
            throw new UserNotFoundException("User not Found");
        }
        Client client1 = client.get();
        order.setClient(client1);
        return orderRespository.save(order);
    }
    
    @GetMapping("/{userid}/orders/{orderid}")
    public Order getOrderByOrderId(@PathVariable Long userid, @PathVariable Long orderid) throws UserNotFoundException {
        Client client = userRepository.findById(userid)
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));
        return client.getOrder()
                .stream()
                .filter(order -> order.getOrderid().equals(orderid))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Order not found for User"));
    }



}
