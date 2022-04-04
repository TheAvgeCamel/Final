public class Savings extends Account{

    private static String accountType = "Savings";

    public Savings(double initialDeposit) {
        super();
        this.setBalance(initialDeposit);
    }
    @Override
    public String toString(){
        return "Account Type: "+ accountType +"\nAccount Number: "+getAccNum()+"\nBalance: $"+getBalance();
    }
}
