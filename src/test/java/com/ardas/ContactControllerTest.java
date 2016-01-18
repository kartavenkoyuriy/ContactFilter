package com.ardas;

import com.ardas.entity.Contact;
import com.ardas.repository.ContactRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
@Transactional
public class ContactControllerTest {

    private ContactRepository contactRepository;

    @Before
    public void setUp() {
        contactRepository = Mockito.mock(ContactRepository.class);
    }

    @Test
    public void shouldReturnCorrectList() throws Exception {//TODO: add logic

        contactRepository.save(new Contact("asd"));
        List<Contact> contactList = contactRepository.findAll();
        System.out.println(contactList == null);
        System.out.println(contactList.size());

//        Contact contact = contactRepository.save(new Contact("asd"));
//        System.out.println(contact == null);
//

//        Assert.assertNotNull(contact);
//        Assert.assertEquals("asd", contact.getName());

    }

}
