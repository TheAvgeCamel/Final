import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;

public class Menu{

    Scanner sc = new Scanner(System.in);
    Bank bank = new Bank();

    public static void main(String[] args) throws InvalidAccountTypeException {
        Menu menu = new Menu();
        menu.OpHeader();
        menu.Prompt();
    }

    public void OpHeader(){
        System.out.println("+----------------------------------------+");
        System.out.println("|            Welcome to Shemar's         |");
        System.out.println("|                 Bank App               |");
        System.out.println("+----------------------------------------+");
    }

    private void Header(String message){
        StringBuilder sb = new StringBuilder();
        sb.append("+");
        System.out.println();
        int width = message.length() + 10;

        for (int i = 0; i < width; ++i){
            sb.append("-");
        }
        sb.append("+");
        System.out.println(sb.toString());
        System.out.println("|      "+message+"    |");
        System.out.println(sb.toString());
    }

    private void Prompt() {
        int choice;

        do {
            System.out.println();
            System.out.println("1. Check Accounts");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Previous Transaction");
            System.out.println("5. Create Account");
            System.out.println("0. Finish");
            System.out.println();
            System.out.println("Enter number for selected option, from 0-5");
            choice = sc.nextInt();

            switch (choice)
            {
                case 1:
                    AccDetails();
                    break;

                case 2:
                    MakeDeposit();
                    break;

                case 3:
                    MakeWithdraw();

                    break;

                case 4:
                    getPrevAmount();
                    System.out.println();
                    break;

                case 5:
                    try {
                        createAccount();
                    } catch (InvalidAccountTypeException e) {
                        System.out.println("Account was not created successfully");
                    }
                    break;

                case 0:
                    break;

                default:
                    System.out.println();
                    System.out.println("Not a Valid Input! Please try again!");
                    break;
            }

        }while (choice != 0);
        System.out.println();
        System.out.println("Thank You for your services. Please use again.");

    }

    private void AccDetails(){
        int account = selectAccount();
        if (account >= 0) {
            System.out.println(bank.getCustomer(account).getAccount());
        }
        else {
            System.out.println("Invalid account selected!");
        }
    }

    private void MakeDeposit() {
        Header("MAKE A DEPOSIT");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        int account = selectAccount();
        if (account >= 0) {
            double amount = getAmount("How much money to deposit?");
            bank.getCustomer(account).getAccount().Deposit(amount);
            System.out.println("\nDate Deposited: "+sdf.format(date));
        }
    }

    private void MakeWithdraw(){
        Header("MAKE A WITHDRAW");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        int account = selectAccount();
        if (account >= 0) {
            double amount = getAmount("How much money to withdraw?");
            bank.getCustomer(account).getAccount().Withdraw(amount);
            System.out.println("\nDate Deposited: "+sdf.format(date));
        }
    }

    private void getPrevAmount(){

        int account = selectAccount();
        if (account >= 0) {
            System.out.print("Your previous Transaction is: $");
            bank.getCustomer(account).getAccount().getPrevAmount();
        }
    }

    private void createAccount() throws InvalidAccountTypeException {
        Header("CREATE AN ACCOUNT");
        //Get Account information
        String accType =  Quest("Please enter an Account type.", Arrays.asList("checking","savings"));
        String SSN = Quest("Enter SSN#:" , null);
        String Name = Quest("Enter an Alias:", null);
        String Address = Quest("Enter Address:", null);

        double initialDeposit = getDeposit(accType);

        Account account;
        if (accType.equalsIgnoreCase("Checking")) {
            account = new Checking(initialDeposit);
        }
        else if (accType.equalsIgnoreCase("Savings")) {
            account = new Savings(initialDeposit);
        }
        else {
            throw new InvalidAccountTypeException();
        }
        Customer customer = new Customer(SSN, Name, Address, account);
        bank.AddCustomer(customer);
    }

    private double getAmount(String question){
        System.out.println(question);
        double amount;
        try {
            amount = Double.parseDouble(sc.next());
        }catch (NumberFormatException e){
            amount = 0;
        }
        return amount;
    }

    private String Quest(String question, List<String> answer){
        Scanner input = new Scanner(System.in);
        String response = "";
        boolean choices = ((answer == null) || answer.size() == 0) ? false : true;
        boolean firstRun = true;

        do {
            if (!firstRun){
                System.out.println("Invalid selection! Please try again.");
            }
            System.out.print(question);
            if (choices){
                System.out.print("(");
                for (int i = 0; i < answer.size() - 1; ++i){
                    System.out.print(answer.get(i)+"/");
                }
                System.out.print(answer.get(answer.size()-1));
                System.out.println("): ");
            }
            response = input.nextLine();
            firstRun = false;
            if (!choices){
                break;
            }
        } while (!answer.contains(response));
        return response;
    }

    private double getDeposit(String accType){
        double initialDeposit = 0;
        boolean iDep = false;
        while (!iDep){
            System.out.print("Please enter an initial deposit:");
            initialDeposit = Double.parseDouble(sc.next());

            if (accType.equalsIgnoreCase("Checking")){
                if (initialDeposit < 150){
                    System.out.println("Minimum of $150 is required to open Checking.");
                }else{
                    iDep = true;
                }
            } else if (accType.equalsIgnoreCase("Savings")){
                if (initialDeposit < 50){
                    System.out.println("Minimum of $50 is required to open Savings.");
                }else {
                    iDep = true;
                }
            }
        }
        return initialDeposit;
    }

    private int selectAccount(){
        Header("CHOOSE ACCOUNT");

        ArrayList<Customer> customers = bank.getCustomers();
        if (customers.size() <=0){
            System.out.println("No customers");
            return -1;
        }
        System.out.println("Choose an account");
        for (int i = 0; i < customers.size(); i++){
            System.out.println((i+1)+".)"+customers.get(i).Info());
        }
        int account;
        System.out.println("Please enter your selection: ");
        try {
            account = Integer.parseInt(sc.next()) - 1;
        }catch (NumberFormatException e){
            account = -1;
        }
        return account;
    }


}

