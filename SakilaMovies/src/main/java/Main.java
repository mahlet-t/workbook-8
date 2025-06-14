import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.SQLException;
import java.util.List;
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
            DataManager dataManager = new DataManager(dataSource);
            while (true) {
                System.out.println("what would you like to do");
                System.out.println("1) Display all actors");
                System.out.println("2) Display all film");
                int userInput = input.nextInt();
                input.nextLine();
                if (userInput == 1) {
                    List<Actor> actors = dataManager.getAllActor();
                    for (Actor actor:actors) {
                        System.out.println(actor);
                    }
                } else if (userInput == 2) {

                    List<Film> films = dataManager.getAllFilms();
                    for (Film film:films) {
                        System.out.println(film);
                    }
                } else {
                    System.out.println("GoodBye");
                    break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}