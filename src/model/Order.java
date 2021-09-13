package model;

import java.util.ArrayList;
import java.util.Objects;

public class Order {
    private String id;
    private String date;
    private String cusId;
    private ArrayList<ItemDetails> items;

    public Order() {
    }

    public Order(String id, String date, String cusId, ArrayList<ItemDetails> items) {
        this.setId(id);
        this.setDate(date);
        this.setCusId(cusId);
        this.setItems(items);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public ArrayList<ItemDetails> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemDetails> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
