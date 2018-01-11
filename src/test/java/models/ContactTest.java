package models;

import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;


public class ContactTest {
    public Address setupNewAddress() {
        return new Address("400 SW 6th Ave. #800", "Portland", "Oregon", "97204");
    }
    public Contact setupNewContact() {
        return new Contact("Mista Bob Dobalina", "(503)-555-5555", "bobdobalina@email.com", setupNewAddress());
    }

    @After
    public void tearDown() {
        Contact.deleteAllContacts();
    }


    @Test
    public void newContact_instantiatesCorrectly_true() throws Exception {
        Contact testContact = setupNewContact();
        assertTrue(testContact instanceof Contact);
    }

    @Test
    public void updateContact_changesAllContactValuesCorrectly_true() throws Exception {
        Contact testContact = setupNewContact();
        Address updatedAddress = new Address("street", "city", "state", "zip");
        testContact.updateContact("name", "phone", "email", updatedAddress);
        assertEquals("name", testContact.getName());
        assertEquals("phone", testContact.getPhone());
        assertEquals("email", testContact.getEmail());
        assertEquals("street", testContact.getAddress().getStreetAddress());
        assertEquals("city", testContact.getAddress().getCity());
        assertEquals("state", testContact.getAddress().getState());
        assertEquals("zip", testContact.getAddress().getPostalCode());
        assertEquals(1, testContact.getId());
    }

    @Test
    public void getAll_returnsAllContacts_true() throws Exception {
        Contact firstTestContact = setupNewContact();
        Contact secondTestContact = setupNewContact();
        Contact thirdTestContact = setupNewContact();
        assertEquals(3, Contact.getAllContacts().size());
    }

    @Test
    public void deleteOnePost() throws Exception {
        Contact testContact = setupNewContact();
        Contact testContact2 = setupNewContact();
        testContact.deleteContact();
        assertEquals(1, Contact.getAllContacts().size());
        assertEquals(2, Contact.getAllContacts().get(0).getId());
    }
}