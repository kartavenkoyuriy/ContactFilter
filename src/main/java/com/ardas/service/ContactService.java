package com.ardas.service;

import com.ardas.entity.Contact;
import com.ardas.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    public Contact getContactById(long id){
        return contactRepository.findOne(id);
    }

    public Iterable<Contact> getAllContacts(){
        return contactRepository.findAll();
    }

    @Transactional
    public Contact createContact(Contact contact){
        return contactRepository.save(contact);
    }

    @Transactional
    public Contact updateContact(Contact contact){
        return contactRepository.save(contact);
    }

    @Transactional
    public void deleteContact(Contact contact){
        contactRepository.delete(contact);
    }

}
