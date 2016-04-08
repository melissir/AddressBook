package org.melissir;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.ResourceBundle;

/**
 * Created by mrhein on 4/7/16.
 */
public class AddressController implements Initializable {

    @FXML
    ListView<Contacts> contactslist;

    @FXML
    TextArea display;

    Collection<Integer> userContactsIds = new HashSet<>();

    public void initialize(URL url, ResourceBundle rb) {
        ContactsCellCallback studCellFactory = new ContactsCellCallback();
        contactslist.setCellFactory(studCellFactory);
        studCellFactory.contactsIds = userContactsIds;

        try {
            ORM.init();

            Collection<Contacts> contacts = ORM.findAll(Contacts.class, "order by last");
            for (Contacts contact : contacts) {
                contactslist.getItems().add(contact);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace(System.err);
            System.exit(1);
        }

    }

    @FXML
    void contactSelect(Event event){
        Contacts contact = contactslist.getSelectionModel().getSelectedItem();
        display.setText(contactInfo(contact));
    }

    static String contactInfo(Contacts contact){
        return String.format(
                        "name: %s %s\n"
                        + "email: %s\n"
                        + "address: %s %s %s\n"
                        + "phone number: %s\n",
                contact.getFirst(),
                contact.getLast(),
                contact.getEmail(),
                contact.getStreet(),
                contact.getState(),
                contact.getZip(),
                contact.getPhone()

        );
    }
}

