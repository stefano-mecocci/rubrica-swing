package io.stefano.domain;

/*
 * You can change the persistency format of the data using
 * the Strategy pattern. The default one is CsvStrategy.
 *
 * save method to save Person objects
 * load method to load the data of the contact in a Person object
 * */

public interface SerializationStrategy {
    String save(Person p);
    Person load(String s);
}

