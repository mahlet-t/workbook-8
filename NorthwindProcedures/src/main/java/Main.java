import org.apache.commons.dbcp2.BasicDataSource;

public class Main {
    public static void main(String[] args) {
        BasicDataSource dataSource=new BasicDataSource();
        String username=args[0];
        String password=args[1];
        dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        dataSource.setUsername(username);
        dataSource.setPassword(password);
DataManager dataManager=new DataManager(dataSource);
UI ui =new UI(dataManager);
ui.DisplayHomeScreen();
    }
}
