package controller;

import db.DbConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class customerController {

        public List<String> getCustomerID() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT * FROM Customer").executeQuery();
        List<String> cusID = new ArrayList<>();
        while (rst.next()){
            cusID.add(
                    rst.getString(1)
            );
        }
        return cusID;
    }
}

