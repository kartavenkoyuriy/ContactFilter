package com.ardas.controller;

import com.ardas.entity.Contact;
import com.ardas.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hello")
public class ContactController {

    @Autowired
    ContactService contactService;

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public List<Contact> getByFilter(@RequestParam("nameFilter") String filter) {
        return contactService.getContactsByFilter(filter);
    }

    @RequestMapping(value = "/contactsById", method = RequestMethod.GET)
    public Contact getById(@RequestParam("id") Long id) {
        return contactService.getContactById(id);
    }

    @RequestMapping(value = "/contactsAll", method = RequestMethod.GET)
    public List<Contact> getAll() {
        return contactService.getAllContacts();
    }

}
