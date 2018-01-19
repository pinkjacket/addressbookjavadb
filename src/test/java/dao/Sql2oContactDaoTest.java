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

    public Contact setupNewContact(){
        return new Contact("Jean");
    }
}