package com.ardas.repository;

import com.ardas.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{

//    @Query("SELECT c FROM Contact c WHERE c.name LIKE :regex")
//    List<Contact> getContactsByFilter(@Param("regex")String regex);

}
