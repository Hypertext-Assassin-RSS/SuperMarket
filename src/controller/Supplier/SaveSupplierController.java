package controller.Supplier;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.Company;
import model.Supplier;

import java.sql.*;

public class SaveSupplierController {
    public JFXTextField txtCompanyName;
    public JFXTextField txtCompanyContact;
    public JFXTextField txtCompanyAddress;
    public JFXTextField txtCompanyID;
    public JFXTextField txtSupplierID;
    public JFXTextField txtSupplierContact;
    public JFXTextField txtSupplierName;
    public JFXTextField txtSupplierAddress;
    public JFXButton btnClear;
    public JFXButton btnSaveCompany;
    public JFXButton btnSaveSupplier;
    public JFXButton btnDeleteCompany;
    public JFXButton btnDelete1;

    public void ClearData(ActionEvent actionEvent) {
    }

    public void DeleteSupplier(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {

        if (deleteSupplier(txtSupplierID.getText())){


            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }

    }


    public void SearchSupplier(ActionEvent actionEvent) {
        try {

            PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM  Supplier WHERE  supplierID = '"+txtSupplierID.getText()+"'");
            ResultSet rst = statement.executeQuery();
            if (rst.next()){
                Supplier s1 = new Supplier(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4)
                );
                setData(s1);
            }else{
                new Alert(Alert.AlertType.WARNING,"Empty Result").show();
            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public void SaveSupplier(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Supplier supplier = new Supplier(
                txtSupplierID.getText(),
                txtSupplierName.getText(),
                txtSupplierContact.getText(),
                txtSupplierAddress.getText()
        );



        if(saveSupplier(supplier))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();

    }

    public void SaveCompany(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        Company company = new Company(
                txtCompanyID.getText(),
                txtCompanyName.getText(),
                txtCompanyContact.getText(),
                txtCompanyAddress.getText()
        );

        if(saveCompany(company))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
    }

    public void DeleteCompany(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {

        if (deleteCompany(txtCompanyID.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }

    }

    public void SearchCompany(ActionEvent actionEvent) {
        try {

            PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM  Company WHERE  companyID = '"+txtCompanyID.getText()+"'");

            ResultSet rst = statement.executeQuery();
            if (rst.next()){
                Company c1 = new Company(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4)
                );
                setData(c1);
            }else{
                new Alert(Alert.AlertType.WARNING,"Empty Result").show();
            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    boolean saveSupplier(Supplier supplier) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();

        String query = "INSERT INTO Supplier VALUES(?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, supplier.getId());
        stm.setObject(2, supplier.getName());
        stm.setObject(3, supplier.getContact());
        stm.setObject(4, supplier.getAddress());


        return stm.executeUpdate() > 0;

    }

    boolean saveCompany(Company company) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();

        String query = "INSERT INTO Company VALUES(?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, company.getId());
        stm.setObject(2, company.getName());
        stm.setObject(3, company.getContact());
        stm.setObject(4, company.getAddress());


        return stm.executeUpdate() > 0;

    }

    void setData(Supplier s1){
        txtSupplierID.setText(s1.getId());
        txtSupplierName.setText(s1.getName());
        txtSupplierContact.setText(s1.getContact());
        txtSupplierAddress.setText(s1.getAddress());
    }

    void setData(Company c1){
        txtCompanyID.setText(c1.getId());
        txtCompanyName.setText(c1.getName());
        txtCompanyContact.setText(c1.getContact());
        txtCompanyAddress.setText(c1.getAddress());
    }


    boolean deleteSupplier(String supplierID) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Supplier WHERE supplierID='"+supplierID+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    boolean deleteCompany(String companyID) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Company WHERE companyID='"+companyID+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    public void updateSupplier(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Supplier supplier1= new Supplier(
                txtSupplierID.getText(),
                txtSupplierName.getText(),
                txtSupplierContact.getText(),
                txtSupplierAddress.getText()
        );


        if (updateSupplier(supplier1))
            new Alert(Alert.AlertType.CONFIRMATION,"Updated..").show();
        else
            new Alert(Alert.AlertType.WARNING,"Try Again").show();



    }

    boolean updateSupplier(Supplier supplier1) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Supplier SET  supplierName =?, supplierContact=? ,supplierAddress=? WHERE supplierID=?");

        stm.setObject(1,supplier1.getName());
        stm.setObject(2,supplier1.getContact());
        stm.setObject(3,supplier1.getAddress());
        stm.setObject(4,supplier1.getId());
        return stm.executeUpdate()>0;
    }


    public void updateCompany(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        {
            Company company1= new Company(
                    txtCompanyID.getText(),
                    txtCompanyName.getText(),
                    txtCompanyContact.getText(),
                    txtCompanyAddress.getText()
            );


            if (updateCompany(company1))
                new Alert(Alert.AlertType.CONFIRMATION,"Updated..").show();
            else
                new Alert(Alert.AlertType.WARNING,"Try Again").show();



        }
    }

    boolean updateCompany(Company company1) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Company SET  companyName=?, companyContact=? ,companyAddress=? WHERE companyID=?");

        stm.setObject(1,company1.getName());
        stm.setObject(2,company1.getContact());
        stm.setObject(3,company1.getAddress());
        stm.setObject(4,company1.getId());
        return stm.executeUpdate()>0;
    }
}




