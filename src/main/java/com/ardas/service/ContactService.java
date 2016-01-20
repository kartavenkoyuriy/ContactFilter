package com.ardas.service;

import com.ardas.entity.Contact;
import com.ardas.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    private ConcurrentHashMap<Long, Contact> allContacts;

    public Contact getContactById(long id) {
        return allContacts.get(id);
    }

    @PostConstruct
    public void init() {
        initializeContactMap();
    }

    public List<Contact> getAllContacts() {
        return new ArrayList<>(allContacts.values());
    }

    @Transactional
    public Contact createContact(Contact contact) {
        Contact c = contactRepository.save(contact);

        allContacts.putIfAbsent(c.getId(), c);
        return c;
    }

    @Transactional
    public Contact updateContact(Contact contact) {
        Contact c = contactRepository.save(contact);

        if (allContacts.containsValue(contact)) {
            allContacts.replace(c.getId(), allContacts.get(c.getId()), c);
            return c;
        } else {
            allContacts.put(c.getId(), c);
            return c;
        }
    }

    @Transactional
    public void deleteContact(Contact contact) {
        contactRepository.delete(contact);

        if (allContacts.containsValue(contact)) {
            allContacts.remove(contact.getId());
        }
    }

    public List<Contact> getContactsByFilter(String filter) {
        Pattern pattern = Pattern.compile(filter);
        Matcher matcher;

        List<Contact> resultList = new ArrayList<>();
        for (Contact contact : allContacts.values()) {
            matcher = pattern.matcher(contact.getName());
            if (!matcher.find()) {
                resultList.add(contact);
            }
        }
        return resultList;
    }

    private void initializeContactMap() {
        List<Contact> contactList = contactRepository.findAll();
        allContacts = new ConcurrentHashMap<>();
        for (Contact contact : contactList) {
            allContacts.put(contact.getId(), contact);
        }
    }

    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
}
