import java.sql.*;


public class Customer {

    public void createCustomer(String name, String account_number, String mobile_number){

        try (Connection con = DBConnection.getConnection()){
            String query = "INSERT INTO  customer(name, account_number, mobile_number) VALUES (?,?,?)";
            assert con != null;
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2,account_number);
            ps.setString(3,mobile_number);
            ps.executeUpdate();


            // create a balance starting with 0
            String query2 = "INSERT INTO balance(account_number, balance) VALUES (?, 0.0)";
            ps = con.prepareStatement(query2);
            ps.setString(1, account_number);
            ps.executeUpdate();

            System.out.println("Customer created successfully.");


        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public void searchCustomer(String nameNumber) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT * FROM customer WHERE name LIKE ? OR mobile_number LIKE ?";
            PreparedStatement ps = con.prepareStatement(query);

            // Add wildcards for partial match
            String searchPattern = "%" + nameNumber + "%";
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);

            ResultSet rs = ps.executeQuery();

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Account Number: " + rs.getString("account_number"));
                System.out.println("Mobile Number: " + rs.getString("mobile_number"));
                System.out.println("------------------------");
            }

            if (!found) {
                System.out.println("No customer found with given input.");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public void getCustomerByAccountNumber(String account_number){
        try(Connection con = DBConnection.getConnection()){
            String query = "SELECT * FROM customer WHERE account_number = ?";
            assert con != null;
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, account_number);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Customer: " + rs.getString("name") + ", Mobile: " + rs.getString("mobile_number"));
            } else {
                System.out.println("Customer not found.");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void updateCustomer(String accountNumber, String newName, String updatedNumber){
        try(Connection con = DBConnection.getConnection()){
            String query = "UPDATE customer SET name = ?, mobile_number = ? WHERE account_number = ?";
            assert con != null;
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, newName);
            ps.setString(2,updatedNumber);
            ps.setString(3,accountNumber);
            int rows = ps.executeUpdate();

            if (rows>0){
                System.out.println("Customer table updated");
            }else {
                System.out.println("Customer not found");
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteCustomer(String account_number){
        try(Connection con = DBConnection.getConnection()){
            String query = "DELETE FROM balance WHERE account_number = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,account_number);
            ps.executeUpdate();

            query = "DELETE FROM customer WHERE account_number = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, account_number);
            ps.executeUpdate();

            System.out.println("Customer deleted.");


        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
