import java.sql.*;


public class Customer {

    public void CreateCustomer(String name, String account_number, String mobile_number){

        try (Connection con = DBConnection.getConnection()){
            String query = "INSERT INTO  customer(name, account_number, mobile_number) VALUES (?,?,?)";


        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
