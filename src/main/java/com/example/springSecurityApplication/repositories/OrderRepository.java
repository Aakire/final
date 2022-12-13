package com.example.springSecurityApplication.repositories;

import com.example.springSecurityApplication.models.Order;
import com.example.springSecurityApplication.models.Person;

import com.example.springSecurityApplication.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByPerson(Person person);

    @Override
    Optional<Order> findById(Integer integer);

    @Query(value = "select *  from orders where RIGHT(number, 4)  LIKE ?1", nativeQuery = true)
    List<Order> findByLastNumberSymbols(String lastSymbols);
}
