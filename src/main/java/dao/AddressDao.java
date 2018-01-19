package dao;

import models.Address;

import java.util.List;

public interface AddressDao {

    //create
    void add (Address address);

    //read
    List<Address> getAll();

    Address findById(int id);
    //update
      void update(int id, String newStreetAddress);
    //delete
   void deleteAddress(int id);

//   void clearAllAddresses();

}