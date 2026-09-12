package com.stacksimplify.restservices.Repo;

import com.stacksimplify.restservices.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRespository extends JpaRepository<Order, Long> {

}
