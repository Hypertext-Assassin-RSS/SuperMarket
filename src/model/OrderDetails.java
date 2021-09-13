package model;

public class OrderDetails {
    private String orderId;
    private String itemNo;
    private int QTY;
    private double discount;


    public OrderDetails() {
    }

    public OrderDetails(String orderId, String itemNo, int QTY, double discount) {
        this.setOrderId(orderId);
        this.setItemNo(itemNo);
        this.setQTY(QTY);
        this.setDiscount(discount);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public int getQTY() {
        return QTY;
    }

    public void setQTY(int QTY) {
        this.QTY = QTY;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "orderId='" + orderId + '\'' +
                ", itemNo='" + itemNo + '\'' +
                ", QTY=" + QTY +
                ", discount=" + discount +
                '}';
    }
}
