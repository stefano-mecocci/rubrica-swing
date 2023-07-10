package io.stefano.domain;

public class CsvStrategy implements SerializationStrategy {
    private final static char SEPARATOR = ';';

    @Override
    public String save(Person p) {
        var builder = new StringBuilder();

        builder.append(p.getFirstName());
        builder.append(SEPARATOR);
        builder.append(p.getLastName());
        builder.append(SEPARATOR);
        builder.append(p.getAddress());
        builder.append(SEPARATOR);
        builder.append(p.getPhoneNumber());
        builder.append(SEPARATOR);
        builder.append(p.getAge());

        return builder.toString();
    }

    @Override
    public Person load(String s) {
        String[] rowData = s.split(SEPARATOR + "");
        var person = new Person();

        person.setFirstName(rowData[0]);
        person.setLastName(rowData[1]);
        person.setAddress(rowData[2]);
        person.setPhoneNumber(rowData[3]);
        person.setAge(Integer.parseInt(rowData[4]));

        return person;
    }

}


