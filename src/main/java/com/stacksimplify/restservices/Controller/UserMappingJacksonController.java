package com.stacksimplify.restservices.Controller;

import com.stacksimplify.restservices.Entities.Client;
import com.stacksimplify.restservices.Exceptions.UserNotFoundException;
import com.stacksimplify.restservices.Service.UserService;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.ObjectWriter;
import tools.jackson.databind.ser.FilterProvider;
import tools.jackson.databind.ser.std.SimpleBeanPropertyFilter;
import tools.jackson.databind.ser.std.SimpleFilterProvider;

import java.util.Set;

@RestController
@RequestMapping("/jacksonfilter/users")
@Validated
public class UserMappingJacksonController {

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/{id}")
    public JsonNode getUserById(
            @PathVariable
            @Min(value = 1, message = "ID must be greater than 0")
            Long id) {

        try {
            Client client = userService.getUserById(id)
                    .orElseThrow(() ->
                            new UserNotFoundException("User not found with ID: " + id));

            Set<String> fields = Set.of("userid", "username", "ssn");

            FilterProvider filterProvider = new SimpleFilterProvider()
                    .addFilter(
                            "userfilter",
                            SimpleBeanPropertyFilter.filterOutAllExcept(fields)
                    );

            ObjectWriter writer = objectMapper.writer(filterProvider);

            return objectMapper.readTree(writer.writeValueAsString(client));

        } catch (UserNotFoundException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    ex.getMessage()
            );
        }
    }

    @GetMapping("/params/{id}")
    public JsonNode getUserById2(
            @PathVariable
            @Min(value = 1, message = "ID must be greater than 0") Long id,
            @RequestParam Set<String> fields) {

        try {
            Client client = userService.getUserById(id)
                    .orElseThrow(() ->
                            new UserNotFoundException("User not found with ID: " + id));

            FilterProvider filterProvider = new SimpleFilterProvider()
                    .addFilter(
                            "userfilter",
                            SimpleBeanPropertyFilter.filterOutAllExcept(fields)
                    );

            ObjectWriter writer = objectMapper.writer(filterProvider);

            return objectMapper.readTree(writer.writeValueAsString(client));

        } catch (UserNotFoundException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    ex.getMessage()
            );
        }
    }
}
