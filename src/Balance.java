import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Balance {
    public double checkBalance(String accountNumber){
        try(Connection con = DBConnection.getConnection()){
            String query = "SELECT balance FROM balance WHERE account_number = ?";
            assert con != null;
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,accountNumber);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getDouble("balance");
            } else{
                return -1;
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return -1;

    }


    public boolean updateBalance(String account_number, double balance){
        try(Connection con = DBConnection.getConnection()){
            String query = "UPDATE balance SET balance = ? WHERE account_number = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setDouble(1,balance);
            ps.setString(2, account_number);
            return ps.executeUpdate() > 0;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return false;
    }
}
