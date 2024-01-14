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

    //return date'i null olan rent sayısı en çok olan customer
    @Query("select c, count(c) as rent_count from Customer c, Rent r " +
            "where r.returnDate is null and r.customer.id = c.id group by c order by rent_count desc limit 1")
    Customer findWhoHasMostBook();
}
