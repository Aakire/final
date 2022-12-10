package com.example.springSecurityApplication.repositories;


import com.example.springSecurityApplication.models.Person;
import com.example.springSecurityApplication.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByLogin(String login);

    @Override
    List<Person> findAll();

    @Query(value="update Person set role='ROLE_ADMIN' where id=?1", nativeQuery = true)
    List<Product> upRole (int id);


}
