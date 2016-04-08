package org.melissir;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.util.Collection;

/**
 * Created by mrhein on 4/8/16.
 */
public class ContactsCellCallback implements Callback<ListView<Contacts>, ListCell<Contacts>> {

    Collection<Integer> contactsIds;

    @Override
    public ListCell<Contacts> call(ListView<Contacts> p) {
        ListCell<Contacts> cell = new ListCell<Contacts>() {
            @Override
            protected void updateItem(Contacts contact, boolean empty) {
                super.updateItem(contact, empty);
                if (empty) {
                    this.setText(null);
                    return;
                }
                this.setText(contact.getLast() + ", " + contact.getFirst());

                String css = "-fx-text-fill:#606; -fx-font-weight:bold;";
                if (contactsIds.contains(contact.getId())) {
                    this.setStyle(css);
                } else {
                    this.setStyle(null);
                }
            }

        };
        return cell;
    }
}
