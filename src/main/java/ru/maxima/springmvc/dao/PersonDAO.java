package ru.maxima.springmvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.maxima.springmvc.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private Long PEOPLE_COUNT = 0L;


    private JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    public PersonDAO() {
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

//        try {
//            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//        } catch (SQLException e) {
//            e.printStackTrace();        }

//    }
    public List<Person> getAllPeople() {
//        List<Person> people = new ArrayList<>();
//        try {
//            Statement statement = connection.createStatement();
//            String query = "select * from person";
//            ResultSet resultSet = statement.executeQuery(query);
//            while (resultSet.next()) {
//                Person person = new Person();
//                person.setId(Long.valueOf(resultSet.getInt("id")));
//                person.setName(resultSet.getString("name"));
//                person.setAge(resultSet.getInt("age"));
//                person.setEmail(resultSet.getString("email"));
//                people.add(person);
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return people;

        return jdbcTemplate.query("select * from person", new PersonMapper());
    }

    public Person getPersonById(Long id) {
//        return people.stream()
//                .filter(person -> person.getId().equals(id))
//                .findAny()
//                .orElse(null);
//        Person person = new Person();
//        try {
//            Statement statement = connection.createStatement();
//            String query = "select * from person where id= " + id;
//           ResultSet resultSet = statement.executeQuery(query);
//            PreparedStatement preparedStatement = connection.prepareStatement("select * from person where id= ?");
//            preparedStatement.setLong(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//
//                person.setId(Long.valueOf(resultSet.getInt("id")));
//                person.setName(resultSet.getString("name"));
//                person.setAge(resultSet.getInt("age"));
//                person.setEmail(resultSet.getString("email"));
//                PEOPLE_COUNT++;
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return person;

        return jdbcTemplate.query("select * from person where id = ?",new Object[]{id}, new PersonMapper())
                .stream().findAny().orElse(null);
    }


    public void save(Person person) {
//        try {
//            Statement statement = connection.createStatement();
//            String query = "insert into person(id, name, age, email) values " +
//                    "(" + ++PEOPLE_COUNT + " , '" + person.getName() +"', "+
//                    person.getAge() +", '"+ person.getEmail() +"')";
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement("insert into person(id, name, age, email) values (?, ?, ?, ?)");
//            preparedStatement.setLong(1, Long.valueOf(getAllPeople().size()));
//            preparedStatement.setString(2, person.getName());
//            preparedStatement.setInt(3, person.getAge());
//            preparedStatement.setString(4, person.getEmail());
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
            jdbcTemplate.update("insert into person(id, name, age, email) values (?,?,?,?)"
                    , Long.valueOf(getAllPeople().size() + 1)
                    , person.getName()
                    , person.getAge()
                    , person.getEmail());
    }

    public void update(Long id, Person person) {
//        Person updatedPerson = getPersonById(id);
//        updatedPerson.setName(person.getName());
//        updatedPerson.setLastName(person.getLastName());
//        updatedPerson.setAge(person.getAge());
//        updatedPerson.setEmail(person.getEmail());
//        try {
//            Statement statement = connection.createStatement();
//            String query = "update person set name = "+ "'" + person.getName() + "'" + "," + " age = " + "'" + person.getAge() + "'" + "," +
//                    "" + " email = " + "'" + person.getEmail() + "'" + " WHERE id=" + id;
//            statement.execute(query);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try {
//            Statement statement = connection.createStatement();
//            String query = "insert into person(id, name, age, email) values " +
//                    "(" + ++PEOPLE_COUNT + " , '" + person.getName() +"', "+
//                    person.getAge() +", '"+ person.getEmail() +"')";
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement("update person set name= ?, age= ?, email= ? where id = ?");
//
//            preparedStatement.setString(1, person.getName());
//            preparedStatement.setInt(2, person.getAge());
//            preparedStatement.setString(3, person.getEmail());
//            preparedStatement.setLong(4, id);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        jdbcTemplate.update("update person set name = ?, age = ?, email = ? where id = ?"
                , person.getName()
                , person.getAge()
                , person.getEmail()
                , id
        );

    }

    public void delete(Long id) {
//        people.removeIf(person -> person.getId().equals(id));
//        try {
//            Statement statement = connection.createStatement();
//            String query = "delete from person where id =" + id;
//            statement.execute(query);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    try {
//        PreparedStatement preparedStatement =
//                connection.prepareStatement("delete from person where id = ?");
//        preparedStatement.setLong(1, id);
//        preparedStatement.executeUpdate();
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }

        jdbcTemplate.update("delete from person where id = ?"
                , id
        );
    }
}
