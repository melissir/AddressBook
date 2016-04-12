package org.melissir;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.commons.validator.routines.EmailValidator;


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

            EmailValidator validator = EmailValidator.getInstance();
            if (!validator.isValid(email)) {
                throw new ExpectedException("e-mail address is not valid");
            }

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
            contactlist.getSelectionModel().clearSelection();
            mainController.userContactsIds.clear();
            contactlist.refresh();
            contactlist.getSelectionModel().select(contact);
            contactlist.scrollTo(contact);
            display.setText(AddressController.contactInfo(contact));

            top.getScene().getWindow().hide();
        }catch(ExpectedException ex){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(ex.getMessage());
            alert.show();
        }catch(Exception ex){
            ex.printStackTrace(System.err);
            System.exit(1);
        }

    }

    @FXML
    void cancel(Event event){
        top.getScene().getWindow().hide();
    }



}

