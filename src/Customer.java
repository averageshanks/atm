import java.sql.*;


public class Customer {

    public void createCustomer(String name, String account_number, String mobile_number){

        try (Connection con = DBConnection.getConnection()){
            String query = "INSERT INTO  customer(name, account_number, mobile_number) VALUES (?,?,?)";
            assert con != null;
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2,account_number);
            preparedStatement.setString(3,mobile_number);
            preparedStatement.executeUpdate();


            // create a balance starting with 0
            String query2 = "INSERT INTO balance(account_number, balance) VALUES (?, 0.0)";
            preparedStatement = con.prepareStatement(query2);
            preparedStatement.setString(1, account_number);
            preparedStatement.executeUpdate();

            System.out.println("Customer created successfully.");


        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
