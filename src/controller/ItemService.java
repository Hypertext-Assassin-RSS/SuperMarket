package controller;

import model.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemService {
    public boolean saveItem(Item item) throws SQLException, ClassNotFoundException;
    public ArrayList<Item> loadItem();
    public boolean deleteItem(String itemNo);

}
