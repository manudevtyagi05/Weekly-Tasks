
class Customer {
    private String name;
    private String customerId;

    public Customer(String name, String customerId){
        this.name = name;
        this.customerId = customerId;
    }

    public String getName(){
        return name;
    }

    public String getCustomerId(){
        return customerId;
    }

}

abstract class Account {
    protected String accountNo;
    protected double balance;

    public Account(String accountNo, double balance){
        this.accountNo = accountNo;
        this.balance = balance;

    }

    public void deposit(double amount){
        balance += amount;
    }

    abstract void withdraw(double amount);
}

class SavingAccount extends Account {
    private double interestRate;

    public SavingAccount(String accountNo, double balance , double interestRate){
        super( accountNo, balance);
        this.interestRate = interestRate;
    }

    @Override
    public void withdraw(double amount){
        if (amount > 0 && amount <= balance){
            balance -= amount;
        } else {
            System.out.println("Not enough Balance");
        }
        balance += balance * interestRate;
    }
}

class CurrentAccount extends Account{

    public CurrentAccount(String accountNo, double balance ){
        super(accountNo,balance);
    }

    @Override
    public void withdraw(double amount){
        if(amount <= balance){
            balance -= amount;
        }else {
            System.out.println("Not enough Balance");
        }
    }
}

public class BankSystem {
    public static void main(String [] args){
        Customer c = new Customer("Manu", "001");
        SavingAccount SA = new SavingAccount("SBI1SA",500, 0.5);
        CurrentAccount CA = new CurrentAccount("SBI1CA",50);
        SA.deposit(5000);
        SA.withdraw(3000);
        CA.deposit(4000);
        CA.withdraw(550);
        System.out.println("Name: "+c.getName());
        System.out.println("CustomerId: "+c.getCustomerId());
        System.out.println("SavingAccount No: "+SA.accountNo);
        System.out.println("SavingAccount Balance: "+SA.balance);
        System.out.println("CurrentAccount No: "+CA.accountNo);
        System.out.println("CurrentAccount Balance: "+CA.balance);

    }
}