import models.Contact;
import models.Address;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
//        staticFileLocation("/public");
//
//        //get: landing page
//        get("/", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            return new ModelAndView(model, "index.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        //get: show new contact form
//        get("/contacts/new", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            return new ModelAndView(model, "form.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        //post: process new contact form
//        post("/contacts/new", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            String name = request.queryParams("name");
//            String phone = request.queryParams("phone");
//            String email = request.queryParams("email");
//            String streetAddress = request.queryParams("streetAddress");
//            String city = request.queryParams("city");
//            String state = request.queryParams("state");
//            String postalCode = request.queryParams("postalCode");
//            Address newAddress = new Address(streetAddress, city, state, postalCode);
//            Contact newContact = new Contact(name, phone, email, newAddress);
//            model.put("contact", newContact);
//            return new ModelAndView(model, "success.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        //get: show all contacts
//        get("/contacts", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            ArrayList<Contact> contacts = Contact.getAllContacts();
//            model.put("contacts", contacts);
//            return new ModelAndView(model, "show-all.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        //get: show update contact form
//        get("/contacts/:id/update", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            int idOfContactToEdit = Integer.parseInt(req.params("id"));
//            Contact editContact = Contact.findById(idOfContactToEdit);
//            model.put("editContact", editContact);
//            return new ModelAndView(model, "form.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        //post: process update contact form
//        post("/contacts/:id/update", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            String newName = request.queryParams("name");
//            String newPhone = request.queryParams("phone");
//            String newEmail = request.queryParams("email");
//            String newStreetAddress = request.queryParams("streetAddress");
//            String newCity = request.queryParams("city");
//            String newState = request.queryParams("state");
//            String newPostalCode = request.queryParams("postalCode");
//            Address updateAddress = new Address(newStreetAddress, newCity, newState, newPostalCode);
//            int idOfContactToEdit = Integer.parseInt(request.params("id"));
//            Contact editContact = Contact.findById(idOfContactToEdit);
//            editContact.updateContact(newName, newPhone, newEmail, updateAddress); //don’t forget me
//            return new ModelAndView(model, "success.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        //get: delete contact
//        get("contacts/:id/delete", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            int idOfContactToDelete = Integer.parseInt(request.params("id"));
//            Contact deleteContact = Contact.findById(idOfContactToDelete);
//            deleteContact.deleteContact();;
//            return new ModelAndView(model, "success.hbs");
//        }, new HandlebarsTemplateEngine());
//
    }
}
