
import java.util.List;
import java.util.Scanner;

public class Ui {
    private final DataManager dataManager;
    private final Scanner input = new Scanner(System.in);

    public Ui(DataManager dataManager) {
        this.dataManager = dataManager;
    }
    public void displayHomeScreen(){
        while (true) {
            System.out.println("What would you like to do ");
            System.out.println("1)Add new shipper");
            System.out.println("2)display all");
            System.out.println("3)Update phone");
            System.out.println("4)delete Shipper");
            System.out.println("5)exit");
            int choose = input.nextInt();
            input.nextLine();
            if (choose==1){
                System.out.println("enter company name");
                String name=input.nextLine();
                System.out.println("enter phone number");
                String phone=input.nextLine();
                dataManager.addNewShipper(name,phone);
            } else if (choose==2) {
               List<Shipper>list= dataManager.displayALlShippers();
                System.out.printf("%-5s %10s %-10s","ShipperId","CompanyName","Phone"+"\n");
                System.out.println(".............................................................\n");
                for (Shipper shipper:list){
                    System.out.println(shipper);
                }

            } else if (choose==3) {

                System.out.println("enter id ");
                int id = input.nextInt();
                input.nextLine();
                System.out.println("enter phone number ");
                String phone=input.nextLine();
                dataManager.updatePhone(phone,id);

                }else if (choose==4){
                System.out.println("enter shipper id to delete");
                int id =input.nextInt();
                input.nextLine();
                dataManager.deleteShipper(id);
            } else if (choose==5) {
                System.out.println("good bye");
                break;

            }
        }
        }
    }


