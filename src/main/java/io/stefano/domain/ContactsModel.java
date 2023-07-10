package io.stefano.domain;

import javax.swing.table.AbstractTableModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactsModel extends AbstractTableModel {
    private final static String[] COLUMN_NAMES = { "Nome", "Cognome", "Nr. Telefono" };
    private final static String CONTACTS_FILENAME = "rubrica.txt";
    private ArrayList<Person> contacts = new ArrayList<>();
    private SerializationStrategy strategy;

    public ContactsModel(SerializationStrategy strat) {
        this.strategy = strat;
        this.loadContacts();
    }

    private void loadContacts() {
        try {
            var scanner = new Scanner(new File(CONTACTS_FILENAME));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (!line.equals("")) {
                    var person = strategy.load(line);
                    contacts.add(person);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Il file di rubrica non esiste");
        }
    }

    private void saveContacts() {
        try {
            var output = new PrintStream(CONTACTS_FILENAME);

            for (Person person : contacts) {
                output.println(strategy.save(person));
            }

            output.close();
        } catch (FileNotFoundException e) {
            System.out.println("Il file di rubrica non esiste");
        }
    }

    @Override
    public int getRowCount() {
        return contacts.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public String getColumnName(int col) {
        return COLUMN_NAMES[col];
    }

    public void addRow(Person p) {
        contacts.add(p);
        fireTableRowsInserted(contacts.size() - 1, contacts.size() - 1);
        saveContacts();
    }

    public void removeRow(int rowIndex) {
        contacts.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
        saveContacts();
    }

    public void editRow(Person q, int rowIndex) {
        var p = contacts.get(rowIndex);

        p.setFirstName(q.getFirstName());
        p.setLastName(q.getLastName());
        p.setAddress(q.getAddress());
        p.setAge(q.getAge());
        p.setPhoneNumber(q.getPhoneNumber());

        fireTableRowsUpdated(rowIndex, rowIndex);
        saveContacts();
    }

    public Person getPerson(int rowIndex) {
        return contacts.get(rowIndex);
    }

    @Override
    public String getValueAt(int rowIndex, int columnIndex) {
        Person row = contacts.get(rowIndex);

        if (columnIndex == 0) {
            return row.getFirstName();
        } else if (columnIndex == 1) {
            return row.getLastName();
        } else { // last column
            return row.getPhoneNumber();
        }
    }

}
