package dao;

import models.Address;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oAddressDao implements AddressDao {

    private final Sql2o sql2o;

    public Sql2oAddressDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Address address) {
        String sql = "INSERT INTO addresses (streetAddress, contactId) VALUES (:streetAddress, :contactId)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql)
                    .addParameter("streetAddress", address.getStreetAddress())
                    .addParameter("contactId", address.getContactId())
                    .addColumnMapping("STREETADDRESS", "streetAddress")
                    .addColumnMapping("CONTACTID", "contactId")
                    .executeUpdate()
                    .getKey();
            address.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Address> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM addresses")
                    .executeAndFetch(Address.class);
        }
    }

    @Override
    public Address findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM addresses WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Address.class);
        }
    }

    @Override
    public void update(int id, String newAddress, int contactId){
        String sql = "UPDATE addresses SET streetAddress = :streetAddress WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("streetAddress", newAddress)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteAddress(int id) {
        String sql = "DELETE from addresses WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAllAddresses() {
        String sql = "DELETE from addresses";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}