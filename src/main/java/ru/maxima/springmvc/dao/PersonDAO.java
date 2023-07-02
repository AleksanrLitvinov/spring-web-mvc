package ru.maxima.springmvc.dao;

import org.springframework.stereotype.Component;
import ru.maxima.springmvc.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private Long PEOPLE_COUNT = 0L;
    private List<Person> people;

    public PersonDAO() {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Aram"));
        people.add(new Person(++PEOPLE_COUNT, "Bogdan"));
        people.add(new Person(++PEOPLE_COUNT, "Grigoriy"));
    }

    public List<Person> getAllPeople() {
        return people;
    }
    public Person getPersonById(Long id) {
        return people.stream()
                .filter(person -> person.getId().equals(id))
                .findAny()
                .orElse(null);
    }


    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(Long id, Person person) {
        Person updatedPerson = getPersonById(id);
        updatedPerson.setName(person.getName());

    }

    public void delete(Long id) {
        people.removeIf(person -> person.getId().equals(id));
    }
}
