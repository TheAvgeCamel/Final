public class Checking extends Account{

    private static String accountType = "Checking";

    public Checking(double initialDeposit) {
        super();
        this.setBalance(initialDeposit);
    }
    @Override
    public String toString(){
        return "Account Type: "+ accountType +"\nAccount Number: "+getAccNum()+"\nBalance: $"+getBalance();
    }
}
