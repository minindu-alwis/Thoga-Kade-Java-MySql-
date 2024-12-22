/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;
import DBConnection.DBConnection;
import GUI.AddCustomerForm;
import Main_GUI_Thoga_Kade.mainThogaKade;
import java.sql.*;


public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        new AddCustomerForm().setVisible(true);
        /*new SearchCustomerForm().setVisible(true);
        new DeleteCustomerForm().setVisible(true);
        new UpdateCustomerForm().setVisible(true);
        new ViewCustomerForm().setVisible(true);*/
        new mainThogaKade().setVisible(true);
        Connection a1=DBConnection.getInstance().getConnection();
        System.out.println(a1);

        
        
    }
         
}
