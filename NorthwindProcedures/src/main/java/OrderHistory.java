public class OrderHistory {
    private String productName;
    private double total;

    public OrderHistory(String productName, double total) {
        this.productName = productName;
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderHistory{" +
                "productName='" + productName + '\'' +
                ", total=" + total +
                '}';
    }
}
