

public class ATMService {

    Balance bal = new Balance();

    public void deposit(String accountNumber, double amount){
        if(amount <= 0){
            System.out.println("Cannot deposit amount 0 or less than 0");
            return;
        }

        double currentBalance = bal.checkBalance(accountNumber);
        System.out.println(bal.updateBalance(accountNumber, currentBalance+amount));

    }

    public void withdraw(String accountNumber, double amount){
        double current = bal.checkBalance(accountNumber);
        if(amount <= 0 ){
            System.out.println("Invalid amount");
        } else if (amount <= current){
            bal.updateBalance(accountNumber,current-amount);
            System.out.println("Withdrawal successful.");
        }else{
            System.out.println("Insufficient Amount");
        }

    }

    public void checkBalance(String accountNumber) {
        double currBal = bal.checkBalance(accountNumber);
        if (currBal >= 0) {
            System.out.println("Balance: " + currBal);
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
            System.out.println("Transfer Successful");
        } else {
            System.out.println("Insufficient Balance");
        }
    }
}
