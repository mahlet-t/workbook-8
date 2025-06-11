import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String username = args[0];
        String password = args[1];
        try (BasicDataSource dataSource = new BasicDataSource()) {
            dataSource.setUrl("jdbc:mysql://localhost:3306/sakila");
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            String query = (""" 
                    Select * from actor
                     where last_name=?
                    """);
            String query2 = ("""
                    select title
                    from actor
                    join film_actor on actor.actor_id=film_actor.actor_id
                    join film  on film_actor.film_id=film.film_id
                    where first_name =? and last_name= ?;
                    """);
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query);
                 PreparedStatement preparedStatement = connection.prepareStatement(query2)
            ) {
                while (true) {
                    System.out.println("what would you like to do");
                    System.out.println("1) Display all actors");
                    System.out.println("2) Display all film");
                    int userInput = input.nextInt();
                    input.nextLine();
                    if (userInput == 1) {

                        System.out.println("enter actor's last name");
                        String name = input.nextLine();
                        statement.setString(1, name);
                        ResultSet resultSet1 = statement.executeQuery();
                        boolean found=false;
                        while (resultSet1.next()) {
                            found=true;
                            int id = resultSet1.getInt("actor_id");
                            String firstName = resultSet1.getString("first_name");
                            String lastName = resultSet1.getString("last_name");
                            System.out.printf("%-5d %-10s %-10s", id, firstName, lastName);
                            System.out.println(".......................................");
                            System.out.println();
                        } if (!found){
                            System.out.println("Film not found");
                        }
                    } else if (userInput == 2) {
                        System.out.println("Enter actor's first name");
                        String fName = input.nextLine();
                        System.out.println("Enter actor's last name ");
                        String lName=input.nextLine();
                        preparedStatement.setString(1, fName);
                        preparedStatement.setString(2,lName);
                        ResultSet resultSet2 = preparedStatement.executeQuery();
                        boolean found=false;
                        while (resultSet2.next()) {
                            found=true;
                            String title = resultSet2.getString("title");
                            System.out.println("Film Title");
                            System.out.println(title);
                            System.out.println("....................");
                            System.out.println();
                        }
                        if (!found){
                            System.out.println("No films found");
                        }
                    } else {
                        System.out.println("GoodBye");
                        break;
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
