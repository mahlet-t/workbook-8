import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
private DataSource dataSource;

    public DataManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public List<OrderHistory> CustOrderHistory(String id){
       List <OrderHistory> history=new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            CallableStatement stm = connection.prepareCall("{ Call CustOrderHist(?) }");
            stm.setString(1,id);
            ResultSet set= stm.executeQuery();
            while (set.next()){
                String productName=set.getString("ProductName");
                double total=set.getDouble("Total");
                OrderHistory orderHistory=new OrderHistory(productName,total);
                history.add(orderHistory);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
return history;
    }


}
