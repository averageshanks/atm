import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Customer customer = new Customer();
        ATMService atm = new ATMService();

        while (true) {
            System.out.println("\n-------- ATM Menu --------");
            System.out.println("1. Register Customer");
            System.out.println("2. Search Customer");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Check Balance");
            System.out.println("6. Transfer");
            System.out.println("7. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Name: "); sc.nextLine(); String name = sc.nextLine();
                    System.out.print("Account Number: "); String acc = sc.nextLine();
                    System.out.print("Mobile: "); String mob = sc.nextLine();
                    customer.createCustomer(name, acc, mob);
                    break;
                case 2:
                    System.out.print("Enter name or mobile number: "); sc.nextLine(); String nameNumber = sc.nextLine();
                    customer.searchCustomer(nameNumber);
                    break;

                case 3:
                    System.out.print("Account Number: "); sc.nextLine(); String dacc = sc.nextLine();
                    System.out.print("Amount: "); double depAmt = sc.nextDouble();
                    atm.deposit(dacc, depAmt);
                    break;
                case 4:
                    System.out.print("Account Number: "); sc.nextLine(); String wacc = sc.nextLine();
                    System.out.print("Amount: "); double withdrawAmt = sc.nextDouble();
                    atm.withdraw(wacc, withdrawAmt);
                    break;
                case 5:
                    System.out.print("Account Number: "); sc.nextLine(); String checkAcc = sc.nextLine();
                    atm.checkBalance(checkAcc);
                    break;
                case 6:
                    System.out.print("From Account: "); sc.nextLine(); String fromAcc = sc.nextLine();
                    System.out.print("To Account: "); String toAcc = sc.nextLine();
                    System.out.print("Amount: "); double totAmt = sc.nextDouble();
                    atm.transferAmount(fromAcc, toAcc, totAmt);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
