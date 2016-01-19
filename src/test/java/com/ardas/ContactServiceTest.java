package com.ardas;

import com.ardas.entity.Contact;
import com.ardas.repository.ContactRepository;
import com.ardas.service.ContactService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class ContactServiceTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactService contactService;

    @Test
    public void shouldReturnCorrectListOfAllContacts() throws Exception {//TODO: add logic
        ArrayList<Contact> contactArrayList = new ArrayList<>();
        contactArrayList.add(new Contact(1L, "asd"));
        when(contactRepository.findAll()).thenReturn(contactArrayList);
        contactService.init();
        List<Contact> allContacts = contactService.getAllContacts();

        assertEquals(1, allContacts.size());
        assertEquals("asd", allContacts.get(0).getName());

        contactArrayList.add(new Contact(2L, "dsa"));
        contactArrayList.add(new Contact(3L, "sad"));
        contactService.init();
        allContacts = contactService.getAllContacts();

        assertEquals(3, allContacts.size());
        assertEquals("sad", allContacts.get(2).getName());
    }

    @Test
    public void shouldCorrectFindContactById(){
        ArrayList<Contact> contactArrayList = new ArrayList<>();
        contactArrayList.add(new Contact(1L, "asd"));
        contactArrayList.add(new Contact(2L, "sad"));
        when(contactRepository.findAll()).thenReturn(contactArrayList);
        contactService.init();

        assertEquals("asd", contactService.getContactById(1).getName());
        assertEquals("sad", contactService.getContactById(2).getName());
    }

    @Test
    public void shouldCorrectAddNewContact(){
        ArrayList<Contact> contactArrayList = new ArrayList<>();
        contactArrayList.add(new Contact(1L, "asd"));
        contactArrayList.add(new Contact(2L, "sad"));
        when(contactRepository.findAll()).thenReturn(contactArrayList);
        contactService.init();
        List<Contact> allContacts = contactService.getAllContacts();

        assertEquals(2, allContacts.size());

        when(contactRepository.save(new Contact(3L, "das"))).thenReturn(new Contact(3L, "das"));
        Contact contact = contactService.createContact(new Contact(3L, "das"));
        contactArrayList.add(contact);

        assertEquals("das", contactArrayList.get(2).getName());
    }

    @Ignore
    @Test
    public void shouldCorrectRemoveContact(){
        ArrayList<Contact> contactArrayList = new ArrayList<>();
        contactArrayList.add(new Contact(1L, "asd"));
        contactArrayList.add(new Contact(2L, "sad"));
        when(contactRepository.findAll()).thenReturn(contactArrayList);
        contactService.init();
        List<Contact> allContacts = contactService.getAllContacts();

        assertEquals(2, allContacts.size());
        Contact c = contactArrayList.get(1);
        contactService.deleteContact(c);

        //TODO: complete end of test

    }

    @Ignore
    @Test
    public void shouldCorrectUpdateContact(){
        ArrayList<Contact> contactArrayList = new ArrayList<>();
        contactArrayList.add(new Contact(1L, "asd"));
        contactArrayList.add(new Contact(2L, "sad"));
        when(contactRepository.findAll()).thenReturn(contactArrayList);
        contactService.init();
        List<Contact> allContacts = contactService.getAllContacts();

        assertEquals(2, allContacts.size());

        when(contactRepository.save(new Contact(2L, "das"))).thenReturn(new Contact(2L, "das"));
        Contact contact = contactService.updateContact(new Contact(2L, "das"));
        contactArrayList.add(contact);

        assertEquals("das", contactArrayList.get(2).getName());

        //TODO: contactRepository.save in create & update
    }

    @Test
    public void shouldCorrectReturnContactListByFilter(){
        ArrayList<Contact> contactArrayList = new ArrayList<>();
        contactArrayList.add(new Contact(1L, "asd"));
        contactArrayList.add(new Contact(2L, "sad"));
        when(contactRepository.findAll()).thenReturn(contactArrayList);
        contactService.init();

        assertEquals(1, contactService.getContactsByFilter("^a.*$").size());
        assertEquals("sad", contactService.getContactsByFilter("^a.*$").get(0).getName());

        assertEquals(2, contactService.getContactsByFilter("^z.*$").size());
    }

}
