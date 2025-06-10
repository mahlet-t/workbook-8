

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args)  {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Scanner input = new Scanner(System.in);
        String username = args[0];
        String password = args[1];
        try (BasicDataSource dataSource = new BasicDataSource()) {
            dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            String query = "Select productId, productName, unitPrice,unitsInStock from products;";
            String query2 = "Select contactName,companyName,city,country,phone from customers";
            String query3 = "Select categoryId,categoryName from categories";
            String query4 = "Select productID, productName, unitPrice,unitsInStock from products " +
                    "where categoryId=?";
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query);
                 PreparedStatement preparedStatement = connection.prepareStatement(query2);
                 PreparedStatement stm = connection.prepareStatement(query3)
            ) {

                ResultSet resultSet = statement.executeQuery();
                ResultSet resultSet1 = preparedStatement.executeQuery();
                ResultSet resultSet2 = stm.executeQuery();
                while (true) {
                    System.out.println("what do you want to do ");
                    System.out.println("1) Display all products");
                    System.out.println("2) Display all customer");
                    System.out.println("3) Display all categories");
                    System.out.println("4) Exit");
                    int userInput = input.nextInt();
                    input.nextLine();
                    if (userInput == 1) {
                        System.out.printf("%-5s %-15s %-10s %-5s%n", "ID", "Name", "Price", "stock");
                        System.out.println("....................................................");
                        while (resultSet.next()) {
                            int p = resultSet.getInt("productId");
                            String name = resultSet.getString("productName");
                            double unitPrice = resultSet.getDouble("unitPrice");
                            int unitStock = resultSet.getInt("unitsInStock");
                            System.out.printf("%-5d %-15s %-10.2f %-5d%n", p, name, unitPrice, unitStock);
                            System.out.println();
                        }
                    } else if (userInput == 2) {
                        while (resultSet1.next()) {
                            String name = resultSet1.getString("ContactName");
                            String companyName = resultSet1.getNString("companyName");
                            String city = resultSet1.getString("city");
                            String country = resultSet1.getString("country");
                            String number = resultSet1.getString("phone");

                            System.out.println("Contact name:    " + name);
                            System.out.println("Company name" + companyName);
                            System.out.println("City:     " + city);
                            System.out.println("Country:     " + country);
                            System.out.println("Phone number:     " + number);
                            System.out.println(".............................");
                        }
                    } else if (userInput == 3) {
                        System.out.printf("%-5s %-10s%n", "Category Id", "Category name");
                        System.out.println(".........................................");
                        while (resultSet2.next()) {
                            int id = resultSet2.getInt("categoryId");
                            String name = resultSet2.getString("categoryName");
                            System.out.printf("%-5d %-10s", id, name);
                            System.out.println();
                        }
                            System.out.println("Enter category id to display all product in that category");
                            int userInputId = input.nextInt();
                            input.nextLine();
                            PreparedStatement pStm = connection.prepareStatement(query4);
                            pStm.setInt(1, userInputId);
                            ResultSet resultSet3 = pStm.executeQuery();
                            System.out.printf("%-5s %-15s %-10s %-5s%n", "ID", "Name", "Price", "stock");
                            System.out.println("....................................................");
                            while (resultSet3.next()) {
                                int p = resultSet3.getInt("productId");
                                String productName = resultSet3.getString("productName");
                                double unitPrice = resultSet3.getDouble("unitPrice");
                                int unitStock = resultSet3.getInt("unitsInStock");
                                System.out.printf("%-5d %-15s %-10.2f %-5d%n", p, productName, unitPrice, unitStock);
                                System.out.println();
                            }

                    } else if (userInput == 4) {
                        System.out.println("good bye");
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
