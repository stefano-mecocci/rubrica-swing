package io.stefano.view;

import io.stefano.domain.ContactsModel;
import io.stefano.domain.CsvStrategy;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends BaseFrame implements ActionListener {
    private JButton newContactBtn = new JButton("Nuovo");
    private JButton editContactBtn = new JButton("Modifica");
    private JButton deleteContactBtn = new JButton("Elimina");
    private JTable contactsTable = new JTable(new ContactsModel(new CsvStrategy()));

    public void initialize() {
        var panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));

        newContactBtn.addActionListener(this);
        editContactBtn.addActionListener(this);
        deleteContactBtn.addActionListener(this);

        panel.add(newContactBtn);
        panel.add(editContactBtn);
        panel.add(deleteContactBtn);

        add(panel);
        add(new JScrollPane(contactsTable));

        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        setVisible(true);
    }

    public MainWindow() {
        initialize();
    }

    private void onEditContact() {
        int selectedRowIndex = contactsTable.getSelectedRow();

        if (selectedRowIndex != -1) {
            new EditContactWindow(contactsTable);
        } else {
            JOptionPane.showMessageDialog(null, "Selezionare una persona");
        }
    }

    private void onDeleteContact() {
        int selectedRowIndex = contactsTable.getSelectedRow();

        if (selectedRowIndex != -1) {
            var model = (ContactsModel) contactsTable.getModel();
            String firstName = model.getValueAt(selectedRowIndex, 0);
            String lastName = model.getValueAt(selectedRowIndex, 1);
            int sure = JOptionPane.showConfirmDialog(null, "Eliminare la persona " + firstName + " " + lastName + "?", null,
                    JOptionPane.YES_NO_OPTION);

            if (sure == 0) {
                model.removeRow(selectedRowIndex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selezionare una persona");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newContactBtn) {
            new NewContactWindow(contactsTable);
        } else if (e.getSource() == editContactBtn) {
            onEditContact();
        } else if (e.getSource() == deleteContactBtn) {
            onDeleteContact();
        }
    }
}
