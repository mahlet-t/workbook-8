

import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String username="root";
        String password="yearup";
        String url="jdbc:mysql://localhost:3306/northwind";
        String query="Select productName from products;";
        Connection connection=  DriverManager.getConnection(url,
                username,password);
        Statement statement=connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()){
            String product=resultSet.getString("productName");
            System.out.println(product);
        }
        connection.close();
    }
}
