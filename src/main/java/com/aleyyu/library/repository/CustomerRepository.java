package com.aleyyu.library.repository;

import com.aleyyu.library.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findById(int id);
    Customer findByLibraryNo(String libraryNo);

    @Query("select c from Customer c LEFT JOIN Rent r ON c.id = r.customer.id where r.customer.id is null")
    List<Customer> findWhoHadNoBook();
}
