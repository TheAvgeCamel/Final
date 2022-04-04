public class Customer {

    private final String ssn;
    private final String name;
    private final String address;
    private final Account account;

    public Customer(String ssn, String name, String address, Account account) {
        this.ssn = ssn;
        this.name = name;
        this.address = address;
        this.account = account;
    }


    @Override
    public String toString(){
        return "\nProfile\n" + "SSN#: " +ssn+ "\n"+ "Name: " + getName() + "\n" + "Address: " + getAddress() + "\n" +account;
    }

    public String Info(){
        return "\nAccount Number: " +account.getAccNum()+ "\n" + "SSN#: " +ssn+ "\n" + "Name: " + getName() + "\n" + "Address: " + getAddress() + "\n";
    }

    Account getAccount(){
        return account;
    }

    public String getName() {
        return name;
    }


    public String getAddress() {
        return address;
    }
}
