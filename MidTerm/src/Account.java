public class Account{
    private int accNum;
    private static int numOfAcc = 1000;
    private int prevAmount;
    private static double balance = 0;

    public Account() {
        accNum = numOfAcc;
        numOfAcc++;
    }

    public void Deposit(double amount) {
        if (amount != 0) {
            balance += amount;
           System.out.println("You have deposited $"+amount+" dollars.");
            System.out.print("You now have a balance of $"+balance);
            prevAmount = (int) amount;
        }
    }

    public void Withdraw(double amount) {
        if (amount != 0) {
            balance -= amount;
            System.out.println("You have withdrawn $"+amount+" dollars.");
            System.out.print("You now have a balance of $"+balance);
            prevAmount = (int) -amount;
        }
    }

    public void getPrevAmount() {
        if (prevAmount > 0) {
            System.out.println(prevAmount);
        } else if (prevAmount < 0) {
            System.out.println(prevAmount);
        } else {
            System.out.println("No Transaction rendered");
        }
    }

    public int getAccNum() {
        return accNum;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        Account.balance = balance;
    }

}
