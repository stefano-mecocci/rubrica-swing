package io.stefano.view;

import io.stefano.domain.ContactsModel;
import io.stefano.domain.Person;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class NewContactWindow extends BaseFrame implements ActionListener {
    protected JTextField firstNameField = new JTextField();
    protected JTextField lastNameField = new JTextField();
    protected JTextField addressField = new JTextField();
    protected JTextField phoneNumberField = new JTextField();
    protected JTextField ageField = new JTextField();
    protected JTable contactsTable;
    protected JFrame currentWindow;
    protected JButton saveButton = new JButton("Salva");
    protected JButton cancelButton = new JButton("Annulla");

    public void initialize() {
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        add(new JLabel("Nome: "));
        add(firstNameField);
        add(new JLabel("Cognome: "));
        add(lastNameField);
        add(new JLabel("Indirizzo: "));
        add(addressField);
        add(new JLabel("Nr. Telefono: "));
        add(phoneNumberField);
        add(new JLabel("Eta: "));
        add(ageField);

        this.currentWindow = this;

        saveButton.addActionListener(this);
        cancelButton.addActionListener(this);

        add(saveButton);
        add(cancelButton);

        setLayout(new GridLayout(6, 2, 20, 20));
        setVisible(true);
    }

    public NewContactWindow(JTable contactsTable) {
        this.contactsTable = contactsTable;
        initialize();
        setTitle("Rubrica: editor contatto");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            var newContact = new Person();

            if (!ageField.getText().matches("[0-9]+")) {
                JOptionPane.showMessageDialog(null, "Il campo età non è corretto");
                return;
            }

            newContact.setFirstName(firstNameField.getText());
            newContact.setLastName(lastNameField.getText());
            newContact.setAddress(addressField.getText());
            newContact.setPhoneNumber(phoneNumberField.getText());
            newContact.setAge(Integer.parseInt(ageField.getText()));

            var model = (ContactsModel) contactsTable.getModel();
            model.addRow(newContact);

            currentWindow.dispose();
        } else if (e.getSource() == cancelButton) {
            currentWindow.dispose();
        }
    }
}
