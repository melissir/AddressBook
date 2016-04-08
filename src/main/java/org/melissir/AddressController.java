package org.melissir;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

    @FXML
    void modifyContact(Event event){
        Contacts contact = contactslist.getSelectionModel().getSelectedItem();


        try{
            if (contact == null){
                throw new ExpectedException("must select contact");
            }

            URL fxml = getClass().getResource("ModifyContactDialog.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxml);
            fxmlLoader.load();
            ModifyContactController dialogController = fxmlLoader.getController();
            Scene scene = new Scene(fxmlLoader.getRoot());
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Modify Contact");
            dialogStage.setScene(scene);
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogController.mainController = this;
            dialogStage.show();

//            dialogController.studentLabel.setText(student.getName());
//            dialogController.tutorLabel.setText(tutor.getName());
//            dialogController.reportField.setText(interact.getReport().trim());
//            dialogController.interactToModify = interact;
            dialogController.contact = contact;


        }catch(ExpectedException ex){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(ex.getMessage());
            alert.show();
        }catch (Exception ex){
            ex.printStackTrace(System.err);
            System.exit(1);
        }
    }

//    @FXML
//    void addStudent(Event event){
//        try{
//            URL fxml = getClass().getResource("addStudentDialog.fxml");
//            FXMLLoader fxmlLoader = new FXMLLoader(fxml);
//            fxmlLoader.load();
//            AddStudentDialogController dialogController = fxmlLoader.getController();
//            Scene scene = new Scene(fxmlLoader.getRoot());
//            Stage dialogStage = new Stage();
//            dialogStage.setTitle("Add Student");
//            dialogStage.setScene(scene);
//            dialogStage.initModality(Modality.APPLICATION_MODAL);
//            dialogController.mainController = this;
//            dialogStage.show();
//        }catch(Exception ex){
//            ex.printStackTrace(System.err);
//            System.exit(1);
//        }
//    }



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

