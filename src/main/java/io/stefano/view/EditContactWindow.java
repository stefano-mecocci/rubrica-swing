package io.stefano.view;

import io.stefano.domain.ContactsModel;
import io.stefano.domain.Person;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class EditContactWindow extends NewContactWindow implements ActionListener {
    private void populateFields() {
        var model = (ContactsModel) contactsTable.getModel();
        var currentPerson = model.getPerson(contactsTable.getSelectedRow());

        firstNameField.setText(currentPerson.getFirstName());
        lastNameField.setText(currentPerson.getLastName());
        addressField.setText(currentPerson.getAddress());
        phoneNumberField.setText(currentPerson.getPhoneNumber());
        ageField.setText(currentPerson.getAge() + "");
    }

    public EditContactWindow(JTable contactsTable) {
        super(contactsTable);
        populateFields();
    }

    private void onSave(ActionEvent e) {
        int rowIndex = contactsTable.getSelectedRow();
        var editedContact = new Person();

        if (!ageField.getText().matches("[0-9]+")) {
            JOptionPane.showMessageDialog(null, "Il campo età non è corretto");
            return;
        }

        editedContact.setFirstName(firstNameField.getText());
        editedContact.setLastName(lastNameField.getText());
        editedContact.setAddress(addressField.getText());
        editedContact.setPhoneNumber(phoneNumberField.getText());
        editedContact.setAge(Integer.parseInt(ageField.getText()));

        var model = (ContactsModel) contactsTable.getModel();
        model.editRow(editedContact, rowIndex);

        currentWindow.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            onSave(e);
        } else if (e.getSource() == cancelButton) {
            currentWindow.dispose();
        }
    }
}
