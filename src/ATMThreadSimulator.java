public class ATMThreadSimulator {

    public static void main(String[] args) {
        ATMService atm = new ATMService();
        String accountNumber = "test";


        for (int i = 0; i < 5; i++) {
            Thread depositThread = new Thread(() -> {
                atm.deposit(accountNumber, 1000.0);
                try {
                    Thread.sleep((int)(Math.random() * 1000)); // simulate delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            depositThread.start();
        }


        for (int i = 0; i < 5; i++) {
            Thread withdrawThread = new Thread(() -> {
                atm.withdraw(accountNumber, 500.0);
                try {
                    Thread.sleep((int)(Math.random() * 1000)); // simulate delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            withdrawThread.start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Balance: ");
        atm.checkBalance(accountNumber);
    }
}
