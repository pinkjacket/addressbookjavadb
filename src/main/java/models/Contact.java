package models;


import java.util.ArrayList;

public class Contact {
    private String name;
//    private String phone;
//    private String email;
    private int id;

    public Contact(String name) {
        this.name = name;
//        this.phone = phone;
//        this.email = email;
//        this.address = address;
    }

//    public static Contact findById(int id) { return instances.get(id-1);}

    public String getName() {
        return name;
    }

//    public String getPhone() {
//        return phone;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public Address getAddress() {
//        return address;
//    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (id != contact.id) return false;
        return name.equals(contact.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + id;
        return result;
    }

//    public static ArrayList<Contact> getAllContacts() {
//        return instances;
//    }

//    public void updateContact(String name, String phone, String email, Address address) {
//        this.name = name;
//        this.phone = phone;
//        this.email = email;
//        this.address = address;
//    }
//    public static void deleteAllContacts() {
//        instances.clear();
//    }
//    public void deleteContact() {
//        instances.remove(id-1);
//    }
}
