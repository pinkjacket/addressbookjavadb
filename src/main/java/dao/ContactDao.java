package dao;

import models.Address;
import models.Contact;

import java.util.List;

public interface ContactDao {
    //create
    void add (Contact contact);

    //read
    List<Contact> getAll();
    List<Address> getAllAddressesByContact(int contactId);
    Contact findById(int id);

    //update
    void update(int id, String name);

    //delete
    void deleteById(int id);
    void clearAllContacts();

}