/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CustomerController;

import Customer.Customer;
import DBConnection.DBConnection;
import List.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerController {
    
    public static boolean addCustomer(Customer customer) throws SQLException, ClassNotFoundException {
    Connection connection = DBConnection.getInstance().getConnection();
    String SQL = "INSERT INTO customer (id, name, address, salary) VALUES (?, ?, ?, ?)";
    try (PreparedStatement stm = connection.prepareStatement(SQL)) {
        stm.setObject(1, customer.getId());
        stm.setObject(2, customer.getName());
        stm.setObject(3, customer.getAddress());
        stm.setObject(4, customer.getSalary());
        return stm.executeUpdate() > 0;
    }
}

    
   public static String generateCustomerId() throws SQLException, ClassNotFoundException {
    Connection connection = DBConnection.getInstance().getConnection();
    String lastId = null;

    String SQL = "SELECT id FROM customer WHERE id LIKE 'C%' ORDER BY id DESC LIMIT 1";
    try (PreparedStatement stm = connection.prepareStatement(SQL); 
         var resultSet = stm.executeQuery()) {
        if (resultSet.next()) {
            lastId = resultSet.getString("id");
        }
    }

    if (lastId == null) {
        return "C001";
    } else {
        String idPart = lastId.substring(1);
        int lastIdNumber = Integer.parseInt(idPart);
        return String.format("C%03d", lastIdNumber + 1);
    }
}


   
   public static Customer searchCustomer(String id)throws IOException{
		BufferedReader br=new BufferedReader(new FileReader("Customer.txt"));
		String line=br.readLine();
		boolean isExist=false;
		while(line!=null){
			String rowId=line.substring(0,9);
			if(rowId.equalsIgnoreCase(id)){
				isExist=true;
				break;
			}
			line=br.readLine();
		}	
		br.close();
		if(isExist){
			String[] rowData=line.split(",");
			Customer customer=new Customer(rowData[0],rowData[1],rowData[2],Double.parseDouble(rowData[3]));
			return customer;
		}else{
			return null;
		}
	
	}
   
   public static boolean deleteCustomer(String id)throws IOException{
		BufferedReader br=new BufferedReader(new FileReader("Customer.txt"));
		List customerlist=new List();
		String line=br.readLine();
		while(line!=null){
			String[] rowData=line.split(",");
			Customer customer=new Customer(rowData[0],rowData[1],rowData[2],Double.parseDouble(rowData[3]));
			customerlist.add(customer);
			line=br.readLine();
		}	
		boolean isDeleted=customerlist.remove(new Customer(id,null,null,0));
		
		FileWriter fw=new FileWriter("Customer.txt");
		for(int i=0;i<customerlist.size();i++){
			
			Customer customer=customerlist.get(i);
			fw.write(customer.toString()+"\n");
			
			}
			fw.close();
			return isDeleted;
		
		
		}

   
   public static boolean updateCustomer(Customer customer)throws IOException{
		BufferedReader br=new BufferedReader(new FileReader("Customer.txt"));
		List customerlist=new List();
		String line=br.readLine();
		while(line!=null){
			String[] rowData=line.split(",");
			Customer c1=new Customer(rowData[0],rowData[1],rowData[2],Double.parseDouble(rowData[3]));
			customerlist.add(c1);
			line=br.readLine();
		}	
		
		int index=customerlist.search(customer);
		if(index!=-1){
				boolean isUpdate=customerlist.set(index,customer);
				
				if(isUpdate){
					
				FileWriter fw=new FileWriter("Customer.txt");
				for(int i=0;i<customerlist.size();i++){
					
					Customer c1=customerlist.get(i);
					fw.write(c1.toString()+"\n");
					
					}
					fw.close();
					return isUpdate;
				}
				
		}
	return false;
    }
   
   public static List getAllCustomer()throws IOException{
		BufferedReader br=new BufferedReader(new FileReader("Customer.txt"));
		List customerlist=new List();
		String line=br.readLine();
		while(line!=null){
			String[] rowData=line.split(",");
			Customer customer=new Customer(rowData[0],rowData[1],rowData[2],Double.parseDouble(rowData[3]));
			customerlist.add(customer);
			line=br.readLine();
		}	
		return customerlist;
		
		}


    
}
