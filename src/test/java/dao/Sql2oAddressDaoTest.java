package dao;

import models.Address;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oAddressDaoTest {

    private Sql2oAddressDao addressDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        addressDao = new Sql2oAddressDao(sql2o);

        //keep connection open through entire test so it does not get erased.
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingAddressSetsId() throws Exception {
        Address address = new Address ("1955 SW Regency");
        int originalAddressId = address.getId();
        addressDao.add(address);
        assertNotEquals(originalAddressId, address.getId());
    }

    @Test
    public void existingAddressCanBeFoundById() throws Exception {
        Address address = new Address ("1955 SW Regency");
        addressDao.add(address);
        Address foundAddress = addressDao.findById(address.getId());
        assertEquals(address, foundAddress);
    }

    @Test
    public void noAddressesReturnsEmptyList() throws Exception {
        assertEquals(0, addressDao.getAll().size());
    }

    @Test
    public void updateChangesStreetAddress() throws Exception {
        String initialAddress = "1955 SW Regency";
        Address address = new Address (initialAddress);
        addressDao.add(address);

        addressDao.update(address.getId(), "2500 SW Fifth", 1);
        Address updatedAddress = addressDao.findById(address.getId());
        assertNotEquals(initialAddress, updatedAddress.getStreetAddress());
    }

    @Test
    public void deleteAddressDeletesCorrectAddress() throws Exception {
        Address address = new Address ("1955 SW Regency");
        addressDao.add(address);
        addressDao.deleteAddress(address.getId());
        assertEquals(0, addressDao.getAll().size());
    }

    @Test
    public void clearAllClearsAll() throws Exception {
        Address address = new Address("1955 SW Regency");
        Address nextAddress = new Address("2500 SW Fifth");
        addressDao.add(address);
        addressDao.add(nextAddress);
        int daoSize = addressDao.getAll().size();
        addressDao.clearAllAddresses();
        assertTrue(daoSize > 0 && daoSize > addressDao.getAll().size());
    }
}