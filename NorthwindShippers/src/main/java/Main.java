import org.apache.commons.dbcp2.BasicDataSource;

public class Main {
    public static void main(String[] args) {
        String username=args[0];
        String password=args[1];
        BasicDataSource dataSource=new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        DataManager dataManager=new DataManager(dataSource);
        Ui ui=new Ui(dataManager);
        ui.displayHomeScreen();
    }

}
