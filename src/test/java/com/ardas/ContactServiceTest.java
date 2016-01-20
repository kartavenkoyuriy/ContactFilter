package com.ardas;

import com.ardas.entity.Contact;
import com.ardas.repository.ContactRepository;
import com.ardas.service.ContactService;
import org.junit.Before;
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
import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class ContactServiceTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactService contactService;

    @Before
    public void setUp() throws Exception {
        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact(1L, "John"));
        contacts.add(new Contact(2L, "Max"));
        contacts.add(new Contact(3L, "Alex"));
        contacts.add(new Contact(4L, "Nick"));
        contacts.add(new Contact(5L, "Obama"));
        contacts.add(new Contact(6L, "Bob"));
        when(contactRepository.findAll()).thenReturn(contacts);
        contactService.init();
    }

    @Test
    public void shouldReturnCorrectListOfAllContacts() throws Exception {
        //Run
        List<Contact> allContacts = contactService.getAllContacts();

        //Verify
        assertEquals(6, allContacts.size());
        assertEquals("John", allContacts.get(0).getName());
    }

    @Test
    public void shouldCorrectFindContactById() {
        //Verify
        assertEquals("John", contactService.getContactById(1).getName());
        assertEquals("Max", contactService.getContactById(2).getName());
    }

    @Test
    public void shouldCorrectUpdateNewContact() {
        //Setup
        Contact contactToUpdate = new Contact(6L, "Bob1");

        //Run
        when(contactRepository.save(contactToUpdate)).thenReturn(contactToUpdate);
        Contact newContact = contactService.updateContact(contactToUpdate);
        List<Contact> allContacts = contactService.getAllContacts();

        //Verify
        assertEquals(contactToUpdate, newContact);
        assertEquals(6, allContacts.size());
        assertEquals("Bob1", newContact.getName());
    }

    @Test
    public void shouldCorrectCreateNewContact() {
        //Setup
        Contact contactToAdd = new Contact(7L, "Jane");

        //Run
        when(contactRepository.save(contactToAdd)).thenReturn(contactToAdd);
        contactService.createContact(contactToAdd);
        List<Contact> allContacts = contactService.getAllContacts();

        //Verify
        assertEquals(7, allContacts.size());
        assertEquals("Jane", allContacts.get(6).getName());
    }

    @Test
    public void shouldCorrectDeleteContact() {
        // Setup
        Contact contactToDelete = new Contact(3L, "Alex");

        // Run execute
        contactService.deleteContact(contactToDelete);

        // Verify
        verify(contactRepository, times(1)).delete(any(Contact.class));
        assertEquals(5, contactService.getAllContacts().size());
        assertFalse(contactService.getAllContacts().contains(contactToDelete));
    }

    @Test
    public void shouldCorrectReturnContactListByFilter() {
        //Verify
        assertEquals(5, contactService.getContactsByFilter("^A.*$").size());
        assertEquals("John", contactService.getContactsByFilter("^a.*$").get(0).getName());
        assertEquals(6, contactService.getContactsByFilter("^z.*$").size());
    }
}