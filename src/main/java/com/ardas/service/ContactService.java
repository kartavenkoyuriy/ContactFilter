package com.ardas.service;

import com.ardas.entity.Contact;
import com.ardas.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public List<Contact> getContactsByFilter(String filter){
        List<Contact> allContacts = (List<Contact>) getAllContacts();

        Pattern pattern = Pattern.compile(filter);
        Matcher matcher;

        List<Contact> resultList = new ArrayList<>();
        for (Contact contact : allContacts) {
            matcher = pattern.matcher(contact.getName());
            if(!matcher.find()){
                resultList.add(contact);
            }
        }
        return resultList;
//        return contactRepository.getContactsByFilter(filter);
    }

}
