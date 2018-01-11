package models;

import org.junit.Test;

import static org.junit.Assert.*;


public class AddressTest {

    public Address setupNewAddress() {
        return new Address("400 SW 6th Ave. #800", "Portland", "Oregon", "97204");
    }

    @Test
    public void newAddress_InstantiatesCorrectly_true() throws Exception {
        Address testAddress = setupNewAddress();
        assertTrue(testAddress instanceof  Address);
    }

    @Test
    public void updateAddress_ChangesValuesCorrectly_true() throws Exception {
        Address testAddress = setupNewAddress();
        testAddress.updateAddress("street", "city", "state", "zip");
        assertEquals("street", testAddress.getStreetAddress());
        assertEquals("city", testAddress.getCity());
        assertEquals("state", testAddress.getState());
        assertEquals("zip", testAddress.getPostalCode());
    }
}