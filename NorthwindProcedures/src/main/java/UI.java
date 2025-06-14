import java.util.List;
import java.util.Scanner;

public class UI {
    private DataManager dataManager;
    private Scanner input = new Scanner(System.in);

    public UI(DataManager dataManager) {
        this.dataManager = dataManager;
    }
    public void DisplayHomeScreen(){
        System.out.println("what would you like to do ");
        System.out.println("1) order History");
        System.out.println("2) Sales by year");
        System.out.println("3) Sales by Category");
        int choose=input.nextInt();
        input.nextLine();
        if (choose==1){
            System.out.println("Enter customer id ");
            String id=input.nextLine();
           List<OrderHistory> orderHistories= dataManager.CustOrderHistory(id);
           for (OrderHistory history:orderHistories){
               System.out.println(history);
           }

        }

    }
}
