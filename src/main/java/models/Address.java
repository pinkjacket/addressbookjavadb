package models;


public class Address {

    private String streetAddress;
//    private String city;
//    private String state;
//    private String postalCode;
    private int id;
    private int contactId;

    public Address(String streetAddress, int contactId) {
        this.streetAddress = streetAddress;
        this.contactId = contactId;
//        this.city = city;
//        this.state = state;
//        this.postalCode = postalCode;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public int getContactId() {
        return contactId;
    }

    //    public void setCity(String city) {
//        this.city = city;
//    }

//    public void setState(String state) {
//        this.state = state;
//    }

//    public void setPostalCode(String postalCode) {
//        this.postalCode = postalCode;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (id != address.id) return false;
        if (contactId != address.contactId) return false;
        return streetAddress != null ? streetAddress.equals(address.streetAddress) : address.streetAddress == null;
    }

    @Override
    public int hashCode() {
        int result = streetAddress != null ? streetAddress.hashCode() : 0;
        result = 31 * result + id;
        result = 31 * result + contactId;
        return result;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public int getId() {
        return id;
    }

    //    public String getCity() {
//        return city;
//    }
//
//    public String getState() {
//        return state;
//    }
//
//    public String getPostalCode() {
//        return postalCode;
//    }

//    public void updateAddress(String streetAddress, String city, String state, String postalCode) {
//        this.streetAddress = streetAddress;
//        this.city = city;
//        this.state = state;
//        this.postalCode = postalCode;
//    }
}
