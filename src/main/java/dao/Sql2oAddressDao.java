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
        String sql = "INSERT INTO addresses (streetAddress) VALUES (:streetAddress)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql)
                    .bind(address)
                    .executeUpdate()
                    .getKey();
            address.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex); //oops we have an error!
        }
    }

    @Override
    public List<Address> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM addresses") //raw sql
                    .executeAndFetch(Address.class); //fetch a list
        }
    }

    @Override
    public Address findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM addresses WHERE id = :id")
                    .addParameter("id", id) //key/value pair, key must match above
                    .executeAndFetchFirst(Address.class); //fetch an individual item
        }
    }
}