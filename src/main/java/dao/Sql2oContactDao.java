package dao;

import models.Contact;
import models.Address;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oContactDao implements ContactDao {

    private final Sql2o sql2o;

    public Sql2oContactDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Contact contact) {
        String sql = "INSERT INTO contacts (name) VALUES (:name)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .bind(contact)
                    .executeUpdate()
                    .getKey();
            contact.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public Contact findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM contacts WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Contact.class);
        }
    }

    @Override
    public List<Contact> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM contacts")
                    .executeAndFetch(Contact.class);
        }
    }

    @Override
    public List<Address> getAllAddressesByContact(int contactId) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM addresses WHERE contactId = :contactId")
                    .addParameter("contactId", contactId)
                    .executeAndFetch(Address.class);
        }
    }

    @Override
    public void update(int id, String newName){
        String sql = "UPDATE contacts SET name = :name WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("name", newName)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from contacts WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAllContacts() {
        String sql = "DELETE from contacts";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}