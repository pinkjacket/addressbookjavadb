import dao.Sql2oAddressDao;
import dao.Sql2oContactDao;
import models.Contact;
import models.Address;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/todolist.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oContactDao contactDao = new Sql2oContactDao(sql2o);
        Sql2oAddressDao addressDao = new Sql2oAddressDao(sql2o);

        //get: show a form to create a new contact
        get("/contacts/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();

            List<Contact> contacts = contactDao.getAll();
            model.put("contacts", contacts);

            return new ModelAndView(model, "contact-form.hbs");
        }, new HandlebarsTemplateEngine());



        //post: process a form to create a new contact
        post("/contacts", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            Contact newContact = new Contact(name);
            contactDao.add(newContact);

            List<Contact> contacts = contactDao.getAll();
            model.put("contacts", contacts);

            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a contact
        get("/contacts/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();

            model.put("editContact", true);

            List<Contact> allContacts = contactDao.getAll();
            model.put("contacts", allContacts);

            return new ModelAndView(model, "contact-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process contact update form
        post("/contacts/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfContactToEdit = Integer.parseInt(request.queryParams("editContactId"));
            String newName = request.queryParams("newContactName");
            contactDao.update(contactDao.findById(idOfContactToEdit).getId(), newName);

            List<Contact> contacts = contactDao.getAll();
            model.put("contacts", contacts);

            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete all contacts and addresses
        get("/contacts/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            addressDao.clearAllAddresses();
            contactDao.clearAllContacts();

            List<Contact> allContacts = contactDao.getAll();
            model.put("contacts", allContacts);

            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show all addresses in all contacts, and all contacts
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            List<Contact> allContacts = contactDao.getAll();
            model.put("contacts", allContacts);

            List<Address> addresses = addressDao.getAll();
            model.put("addresses", addresses);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete all addresses
        get("/addresses/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            List<Contact> allContacts = contactDao.getAll();
            model.put("contacts", allContacts);

            addressDao.clearAllAddresses();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show new address form
        get("/addresses/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            List<Contact> allContacts = contactDao.getAll();
            model.put("contacts", allContacts);

            return new ModelAndView(model, "address-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process new address form
        post("/addresses/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();

            List<Contact> allContacts = contactDao.getAll();
            model.put("contacts", allContacts);
            String streetAddress = request.queryParams("streetAddress");
            int newContactId = Integer.parseInt(request.queryParams("contactId"));
            Address newAddress = new Address(streetAddress, newContactId);
            addressDao.add(newAddress);
            model.put("address", newAddress);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show a form to update an address
        get("/addresses/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            List<Contact> allContacts = contactDao.getAll();
            model.put("contacts", allContacts);

            List<Address> allAddresses = addressDao.getAll();
            model.put("addresses", allAddresses);

            model.put("editAddress", true);
            return new ModelAndView(model, "address-form.hbs");
        }, new HandlebarsTemplateEngine());

//
        //post: process a form to update an address
        post("/addresses/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            List<Contact> allContacts = contactDao.getAll();
            model.put("contacts", allContacts);

            String newContent = req.queryParams("streetAddress");
            int newContactId = Integer.parseInt(req.queryParams("contactId"));
            int addressToEditId = Integer.parseInt(req.queryParams("addressToEditId"));
            Address editAddress = addressDao.findById(addressToEditId);
            addressDao.update(addressToEditId, newContent, newContactId);

            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show an individual address that is nested in a contact
        get("/contacts/:contact_id/addresses/:address_id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfAddressToFind = Integer.parseInt(req.params("address_id"));
            Address foundAddress = addressDao.findById(idOfAddressToFind);
            model.put("address", foundAddress);
            return new ModelAndView(model, "address-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //get a specific contact (and the addresses it contains)
        get("/contacts/:contact_id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfContactToFind = Integer.parseInt(request.params("contact_id"));

            List<Contact> contacts = contactDao.getAll();
            model.put("contacts", contacts);

            Contact foundContact = contactDao.findById(idOfContactToFind);
            model.put("contact", foundContact);
            List<Address> allAddressesByContact = contactDao.getAllAddressesByContact(idOfContactToFind);
            model.put("addresses", allAddressesByContact);

            return new ModelAndView(model, "contact-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete an individual address
        get("contacts/:contact_id/addresses/:address_id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfAddressToDelete = Integer.parseInt(req.params("address_id"));
            Address deleteAddress = addressDao.findById(idOfAddressToDelete);
            addressDao.deleteAddress(idOfAddressToDelete);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
