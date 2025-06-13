import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataManager {
   final private DataSource dataSource;

    public DataManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    Scanner input = new Scanner(System.in);


    public List<Actor> getAllActor() {
        List<Actor> actors = new ArrayList<>();
        String query = (""" 
                Select * from actor
                 where last_name=?
                """);
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            System.out.println("enter actor's last name");
            String name = input.nextLine();
            statement.setString(1, name);
            ResultSet resultSet1 = statement.executeQuery();
            boolean found = false;
            while (resultSet1.next()) {
                found = true;
                int id = resultSet1.getInt("actor_id");
                String firstName = resultSet1.getString("first_name");
                String lastName = resultSet1.getString("last_name");
                Actor actor = new Actor(id, firstName, lastName);
                actors.add(actor);
            }
            if (!found) {
                System.out.println("Film not found");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return actors;
    }

    public List<Film> getAllFilms() {
        List<Film> films = new ArrayList<>();
        String query2 = ("""
                select film.film_id,film.title,film.description,film.release_year,film.length
                from actor
                join film_actor on actor.actor_id=film_actor.actor_id
                join film  on film_actor.film_id=film.film_id
                where first_name =? and last_name= ?;
                """);

        ResultSet resultSet2;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query2);
            System.out.println("Enter actor's first name");
            String fName = input.nextLine();
            System.out.println("Enter actor's last name ");
            String lName = input.nextLine();
            preparedStatement.setString(1, fName);
            preparedStatement.setString(2, lName);
            resultSet2 = preparedStatement.executeQuery();
            boolean found = false;
            System.out.printf("%-5s %-20s %50s %100s %70s", "id", "Film Title", "Description", "Release year", "Length");
            System.out.println();
            System.out.println(".....................................................................................................................................................");
            while (resultSet2.next()) {
                found = true;
                int id = resultSet2.getInt("film_id");
                String title = resultSet2.getString("title");
                String description = resultSet2.getString("description");
                int releaseYear = resultSet2.getInt("release_year");
                int length = resultSet2.getInt("length");
                Film film = new Film(id, title, description, releaseYear, length);
                films.add(film);
            }
            if (!found) {
                System.out.println("No films found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return films;
    }
}
