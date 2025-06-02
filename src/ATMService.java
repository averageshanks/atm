import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ATMService {

    Balance bal = new Balance();
    public void deposit(String accountNumber, double amount){
        if(amount <= 0){
            System.out.println("Cannot deposit amount 0 or less than 0");
            return;
        }

        double currentBalance = bal.checkBalance(accountNumber);

        if(bal.updateBalance(accountNumber, currentBalance + amount) && currentBalance != -1) {
            try (Connection con = DBConnection.getConnection()) {
                String query = "INSERT INTO transaction_log(account_number, type, amount, note) VALUES (?, 'deposit', ?, ?)";
                assert con != null;
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, accountNumber);
                ps.setDouble(2, amount);
                ps.setString(3, "Deposit done Successfully");
                ps.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Account not found");
        }


    }

    public void withdraw(String accountNumber, double amount){
        double current = bal.checkBalance(accountNumber);
        if(current == -1){
            System.out.println("Account not found");
        } else if (amount <= 0) {
            System.out.println("Invalid Amount");
        } else if (amount <= current){
            bal.updateBalance(accountNumber,current-amount);
            try(Connection con = DBConnection.getConnection()){
                String query = "INSERT INTO transaction_log(account_number, type, amount, note) VALUES (?, 'withdraw', ?, ?)";
                assert con != null;
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, accountNumber);
                ps.setDouble(2, amount);
                ps.setString(3,"Withdrawn Successfully");
                ps.executeUpdate();



            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
            System.out.println("Withdrawal successful.");
        }else if(amount > current){
            System.out.println("Insufficient Amount");
        }


    }

    public void checkBalance(String accountNumber) {
        double currBal = bal.checkBalance(accountNumber);
        if (currBal >= 0) {
            System.out.println("Balance: " + currBal);
        }else {
            System.out.println("Account not found");
        }
    }

    public void transferAmount(String fromAcc, String toAcc, double amount){
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        double currentSending = bal.checkBalance(fromAcc);
        double currentReceiving = bal.checkBalance(toAcc);

        if (currentSending < 0 || currentReceiving < 0) {
            System.out.println("One of the accounts doesn't exist.");
            return;
        }

        if(amount <= currentSending){
            bal.updateBalance(fromAcc,currentSending-amount);
            bal.updateBalance(toAcc,currentReceiving+amount);
            try(Connection con = DBConnection.getConnection()){
                String query = "INSERT INTO transaction_log(account_number, type, amount, note) VALUES (?, 'transfer', ?, ?)";
                assert con != null;
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, toAcc);
                ps.setDouble(2, amount);
                ps.setString(3,"Transferred to this account successfully");
                ps.executeUpdate();


            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
            System.out.println("Transfer Successful");
        } else {
            System.out.println("Insufficient Balance");
        }

    }
}
