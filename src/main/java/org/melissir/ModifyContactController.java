package org.melissir;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by mrhein on 4/8/16.
 */
public class ModifyContactController implements Initializable {

    @FXML
    Node top;

    AddressController mainController;

    Contacts contact;


    @FXML
    TextField firstField;

    @FXML
    TextField lastField;

    @FXML
    TextField streetField;

    @FXML
    TextField stateField;

    @FXML
    TextField zipField;

    @FXML
    TextField emailField;

    @FXML
    TextField phoneField;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        firstField.setText(contact.getFirst());
        lastField.setText(contact.getLast());
        streetField.setText(contact.getStreet());
        stateField.setText(contact.getState());
        zipField.setText(contact.getZip());
        emailField.setText(contact.getEmail());
        phoneField.setText(contact.getPhone());

    }

    @FXML
    void modify(Event event){
        try{
            String first = firstField.getText().trim();
            String last = lastField.getText().trim();
            String street = streetField.getText().trim();
            String state = stateField.getText().trim();
            String zip = zipField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();

            contact.setFirst(first);
            contact.setLast(last);
            contact.setStreet(street);
            contact.setState(state);
            contact.setZip(zip);
            contact.setEmail(email);
            contact.setPhone(phone);
            ORM.store(contact);

            ListView<Contacts> contactlist = mainController.contactslist;
            TextArea display = mainController.display;
            display.setText(AddressController.contactInfo(contact));
        }catch(Exception ex){
            ex.printStackTrace(System.err);
            System.exit(1);
        }

        top.getScene().getWindow().hide();
    }

    @FXML
    void cancel(Event event){
        top.getScene().getWindow().hide();
    }



}
