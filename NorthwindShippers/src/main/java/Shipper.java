public class Shipper {
    int shipperId;
    String companyName;
    String phone;

    public Shipper(int shipperId, String companyName, String phone) {
        this.shipperId = shipperId;
        this.companyName = companyName;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("%-5d %-10s %-10s",shipperId,companyName,phone);


    }
}
