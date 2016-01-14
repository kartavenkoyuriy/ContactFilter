package com.ardas.controller;

import com.ardas.entity.Contact;
import com.ardas.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hello")
public class ContactController {

    @Autowired
    ContactService contactService;

//    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
//    public Contact getByFilter(@RequestParam(value = "nameFilter", defaultValue = "1") String filter) {
//        return null;
//    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public Contact getById(@RequestParam(value = "nameFilter", defaultValue = "1") Long id) {
        return contactService.getContactById(id);
    }

    @RequestMapping(value = "/contactsAll", method = RequestMethod.GET)
    public List<Contact> getAll() {
        return (List<Contact>) contactService.getAllContacts();
    }

}
