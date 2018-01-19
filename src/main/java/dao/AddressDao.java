public interface AddressDao {

    //create
    void add (Address address);
    //read
    List<Address> getAll();

    Address findById(int id);
    //update
    //  void update(int id, String content);
    //delete
//   void deleteAddress();

//   void clearAllAddresses();

}