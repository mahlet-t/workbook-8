import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    final private DataSource dataSource;

    public DataManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public void addNewShipper(String name,String phone){
        String query="insert into Shippers (companyName,phone) value (?,?)";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,name);
            statement.setString(2,phone);
            int row= statement.executeUpdate();
            System.out.println("inserted row"+row);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public  List<Shipper> displayALlShippers(){
        List<Shipper> shippers=new ArrayList<>();
        String query="select shipperId,companyName,phone from shippers";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet= statement.executeQuery();
            while (resultSet.next()){
            int id= resultSet.getInt("ShipperId");
            String name=resultSet.getString("companyName");
            String phone=resultSet.getString("phone");
            Shipper shipper=new Shipper(id,name,phone);
            shippers.add(shipper);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return shippers;
    }
    public void updatePhone(String phone,int id){
        String query="update shippers SET phone=? Where shipperId=?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,phone);
            statement.setInt(2,id);
            int row=statement.executeUpdate();
            if (row>0){
                System.out.println("Updated "+row+"rows");
            }
            else {
                System.out.println("Shipper not found");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public void deleteShipper(int id){
        String query="Delete FROM Shippers WHERE shipperId=?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            int row=statement.executeUpdate();
            System.out.println("Deleted row"+row);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
