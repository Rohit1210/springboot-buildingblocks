package com.stacksimplify.restservices.Repo;

import com.stacksimplify.restservices.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Client, Long> {

    Client findByUsername(String username);

}
