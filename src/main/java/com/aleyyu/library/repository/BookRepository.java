package com.aleyyu.library.repository;

import com.aleyyu.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Book findById(int id);

    @Query(value = "select b from Book b, Customer c, Rent r where r.checkoutDate is not null and r.returnDate is null and c.libraryNo = :libraryNo and " +
            "r.customer = c and r.book = b")
    List<Book> findBookByLibraryNo(String libraryNo);

    List<Book> findByStatus(String status);

    @Query("select b from Book b, Rent r where b.id = r.book.id and r.customer.id = :id and r.returnDate is not null")
    List<Book> findBooksCustomerEverRead(int id);

    @Query("select b from Book b, Rent r where r.customer.id = :customerId and b.id = r.book.id and r.checkoutDate between :dateStart and :dateEnd")
    List<Book> findBooksBetweenDate(int customerId, LocalDate dateStart, LocalDate dateEnd);

}
