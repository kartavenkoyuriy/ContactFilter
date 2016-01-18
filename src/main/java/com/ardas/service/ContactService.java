package com.ardas.service;

import com.ardas.entity.Contact;
import com.ardas.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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

    private volatile List<Contact> allContacts;

    public Contact getContactById(long id){
        isContactListInitialized();

        for (Contact contact : allContacts) {//TODO:bad logic
            if(contact.getId() == id){
                return contact;
            }
        }

        return null;//TODO:null?
    }

    public Iterable<Contact> getAllContacts(){
        return contactRepository.findAll();
    }

    @Transactional
    public Contact createContact(Contact contact){
        Contact c = contactRepository.save(contact);

        isContactListInitialized();
        allContacts.add(c);
        return c;
    }

    @Transactional
    public Contact updateContact(Contact contact){
        Contact c = contactRepository.save(contact);

        isContactListInitialized();
        if(allContacts.contains(contact)) {
            allContacts.set(allContacts.indexOf(contact), c);//TODO:bad logic
            return c;
        } else {
            return null;//TODO:null?
        }
    }

    @Transactional
    public void deleteContact(Contact contact){
        contactRepository.delete(contact);

        allContacts.indexOf(contact);
        if(allContacts.contains(contact)){
            allContacts.remove(contact);//TODO:add some logic or exceptions or
        }
    }

    public List<Contact> getContactsByFilter(String filter){
        Pattern pattern = Pattern.compile(filter);
        Matcher matcher;

        List<Contact> resultList = new ArrayList<>();
        isContactListInitialized();
        for (Contact contact : allContacts) {
            matcher = pattern.matcher(contact.getName());
            if(!matcher.find()){
                resultList.add(contact);
            }
        }
        return resultList;
    }

    private void isContactListInitialized(){
        if(allContacts == null){
            allContacts = new ArrayList<>();
        }
    }

}
