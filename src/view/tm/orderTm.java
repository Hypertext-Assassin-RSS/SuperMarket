package view.tm;

public class orderTm {
    private String orderId;
    private String itemCode;
    private int QTY;
    private Double discount;

    public orderTm() {
    }

    public orderTm(String orderId, String itemCode, int QTY, Double discount) {
        this.setOrderId(orderId);
        this.setItemCode(itemCode);
        this.setQTY(QTY);
        this.setDiscount(discount);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQTY() {
        return QTY;
    }

    public void setQTY(int QTY) {
        this.QTY = QTY;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
