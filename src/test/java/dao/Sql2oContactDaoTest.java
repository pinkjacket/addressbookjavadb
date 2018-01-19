package dao;

import models.Contact;
import models.Address;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;


public class Sql2oContactDaoTest {

    private Sql2oContactDao contactDao;
    private Sql2oAddressDao addressDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        contactDao = new Sql2oContactDao(sql2o);
        addressDao = new Sql2oAddressDao(sql2o);

        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingContactSetsId() throws Exception {
        Contact contact = setupNewContact();
        int originalContactId = contact.getId();
        contactDao.add(contact);
        assertNotEquals(originalContactId, contact.getId());
    }

    @Test
    public void existingContactsCanBeFoundById() throws Exception {
        Contact contact = setupNewContact();
        contactDao.add(contact);
        Contact foundContact = contactDao.findById(contact.getId());
        assertEquals(contact, foundContact);
    }

    @Test
    public void addedContactsAreReturnedFromGetAll() throws Exception {
        Contact contact = setupNewContact();
        contactDao.add(contact);
        assertEquals(1, contactDao.getAll().size());
    }

    @Test
    public void noContactsReturnsEmptyList() throws Exception {
        assertEquals(0, contactDao.getAll().size());
    }

    @Test
    public void getAllAddressesByContactReturnsThemCorrectly() throws Exception {
        Contact contact = setupNewContact();
        contactDao.add(contact);
        int contactId = contact.getId();
        Address newAddress = new Address("the beach", contactId);
        Address otherAddress = new Address("your place", contactId);
        Address thirdAddress = new Address("at work", contactId);
        addressDao.add(newAddress);
        addressDao.add(otherAddress);


        assertTrue(contactDao.getAllAddressesByContact(contactId).size() == 2);
        assertTrue(contactDao.getAllAddressesByContact(contactId).contains(newAddress));
        assertTrue(contactDao.getAllAddressesByContact(contactId).contains(otherAddress));
        assertFalse(contactDao.getAllAddressesByContact(contactId).contains(thirdAddress));
    }

    @Test
    public void updateChangesContactName() throws Exception {
        String initialName = "Jean";
        Contact contact = new Contact (initialName);
        contactDao.add(contact);

        contactDao.update(contact.getId(),"Isaac");
        Contact updatedContact = contactDao.findById(contact.getId());
        assertNotEquals(initialName, updatedContact.getName());
    }

    public Contact setupNewContact(){
        return new Contact("Jean");
    }
}