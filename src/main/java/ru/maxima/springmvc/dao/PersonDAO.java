package ru.maxima.springmvc.dao;

import org.springframework.stereotype.Component;
import ru.maxima.springmvc.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private Long PEOPLE_COUNT = 0L;


    private final String URL = "jdbc:postgresql://localhost:5432/first_db";
    private final String USERNAME = "postgres";
    private final String PASSWORD = "postgres";
    private Connection connection;

    public PersonDAO() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();        }

    }
    public List<Person> getAllPeople() {
        List<Person> people = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "select * from person";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(Long.valueOf(resultSet.getInt("id")));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));
                people.add(person);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

    public Person getPersonById(Long id) {
//        return people.stream()
//                .filter(person -> person.getId().equals(id))
//                .findAny()
//                .orElse(null);
        Person person = new Person();
        try {
            Statement statement = connection.createStatement();
            String query = "select * from person where id= " + id;
           ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                person.setId(Long.valueOf(resultSet.getInt("id")));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }


    public void save(Person person) {
        try {
            Statement statement = connection.createStatement();
            String query = "insert into person(id, name, age, email) values " +
                    "(" + ++PEOPLE_COUNT + " , '" + person.getName() +"', "+
                    person.getAge() +", '"+ person.getEmail() +"')";

            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Long id, Person person) {
//        Person updatedPerson = getPersonById(id);
//        updatedPerson.setName(person.getName());
//        updatedPerson.setLastName(person.getLastName());
//        updatedPerson.setAge(person.getAge());
//        updatedPerson.setEmail(person.getEmail());
        try {
            Statement statement = connection.createStatement();
            String query = "update person set name = "+ "'" + person.getName() + "'" + "," + " age = " + "'" + person.getAge() + "'" + "," +
                    "" + " email = " + "'" + person.getEmail() + "'" + " WHERE id=" + id;
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
//        people.removeIf(person -> person.getId().equals(id));
        try {
            Statement statement = connection.createStatement();
            String query = "delete from person where id =" + id;
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
