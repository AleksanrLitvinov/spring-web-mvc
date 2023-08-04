package ru.maxima.springmvc.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.maxima.springmvc.models.Person;


import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

//    private Long PEOPLE_COUNT = 0L;


//    private JdbcTemplate jdbcTemplate;

    private final SessionFactory sessionFactory;
    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
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

    @Transactional(readOnly = true)
    public List<Person> getAllPeople() {

        Session session = sessionFactory.getCurrentSession();
        List<Person> people = session.createQuery("select p from Person p").getResultList();
        System.out.println(people);
        return people;
    }
    @Transactional(readOnly = true)
    public Person getPersonById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
    }

    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }
    @Transactional
    public void update(Long id, Person person) {
        Session session = sessionFactory.getCurrentSession();
        Person personToBeUpdated = session.get(Person.class, id);

        personToBeUpdated.setName(person.getName());
        personToBeUpdated.setAge(person.getAge());
        personToBeUpdated.setEmail(person.getEmail());

    }
    @Transactional
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Person.class, id));
    }

    @Transactional
    public List<Person> searchByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        List<Person> people = session.createQuery("select cat.name from Person cat where cat.name like name %").getResultList();
        return people;
    }

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

//    return jdbcTemplate.query("select * from person where name like ?",
//                new Object[]{name},
//                new PersonMapper());
}
