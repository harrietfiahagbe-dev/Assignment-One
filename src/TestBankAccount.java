public class TestBankAccount {
    public static void main(String[] args) {

        System.out.println("------TESTING BEGINNING!------");
        //Testing the BankAccount Class

        //Checking and passing the opening balance of each account
        BankAccount current_money = new BankAccount(AccountType.CURRENT,"CM01", 1000);
        BankAccount current_savings = new BankAccount(AccountType.SAVINGS, "SM01", 850);


        //Getting the current balance, and ID with Current Account
        System.out.println("This is the current type of the account: " + current_money.getAccountType());
        System.out.println("This is your current ID for your current account: " + current_money.getAcctID());
        System.out.println("This is your current balance for your current account: " + current_money.getBalance());
        System.out.println("This is your minimum balance in this account: " + current_money.getMinBalance());
        System.out.println();

        //Getting the current balance, and ID with Current Savings
        System.out.println("This is the current type of the account: " + current_savings.getAccountType());
        System.out.println("This is your current ID for your saving account: " + current_savings.getAcctID());
        System.out.println("This is your current balance for your savings account: " + current_savings.getBalance());
        System.out.println("This is your minimum balance in this account: " + current_savings.getMinBalance());
        System.out.println();

        //Depositing Money in the Current Account
        current_money.deposit(200);
        System.out.println("This is your current balance for your current account: " + current_money.getBalance());
        System.out.println("This is your minimum balance in your current account: " + current_money.getMinBalance());
        System.out.println();

        //Depositing Money in the Savings Account
        current_savings.deposit(700);
        System.out.println("This is your current balance for your savings account: " + current_savings.getBalance());
        System.out.println("This is your minimum balance in your savings account: " + current_savings.getMinBalance());
        System.out.println();

        //Testing withdrawal
        //Testing withdrawal with success on Current Account
        boolean worked  = current_money.withdraw(150);
        System.out.println("Was it successful? " + worked);
        System.out.println("This is your current balance in your current account: " + current_money.getBalance());
        System.out.println();

        //Testing withdrawal with  no success Current Account
        worked  = current_money.withdraw(700);
        System.out.println("Was it successful? " + worked);
        System.out.println("This is your current balance in your current account: " + current_money.getBalance());
        System.out.println();

        //Testing withdrawal with  no success Savings Account
        //First Withdrawal
        current_savings.withdraw(500);
        System.out.println("Successful, one more withdrawal left!");
        System.out.println("This is your balance in your savings account: " + current_savings.getBalance());
        System.out.println();

        //Second Withdrawal
        current_savings.withdraw(50);
        System.out.println("Successful, exhausted all withdrawals!");
        System.out.println("This is your balance in your savings account: " + current_savings.getBalance());
        System.out.println();

        //Would fail, due to maximum being 2
        current_savings.withdraw(40);
        System.out.println("This is your balance in your savings account: " + current_savings.getBalance());
        System.out.println();

        //Testing account in the red
        BankAccount red_current_money = new BankAccount(AccountType.CURRENT,"CM01", 800);
        red_current_money.withdraw(50);
        System.out.println("This is your current balance in your current account: " + current_money.getBalance());
        System.out.println();

        //Testing For Monthly Current Account: Monthly Maintenance
        BankAccount current_money_main= new BankAccount(AccountType.CURRENT,"CM02", 2500);
        current_money_main.performMonthlyMaintenance();
        System.out.println();

        //Testing For Monthly Current Account: Monthly Maintenance
        BankAccount current_savings_main= new BankAccount(AccountType.CURRENT,"SM02", 1000);
        current_savings_main.performMonthlyMaintenance();
        System.out.println();

        //Testing Transfer (If True)
        current_money.transfer(true,current_savings, 50);
        System.out.println("This is your current balance in this account: " + current_money.getBalance());
        System.out.println("This is your current balance in this account: " + current_savings.getBalance());
        System.out.println();

        //Testing Transfer (If False)
        current_money.transfer(false,current_savings, 50);
        System.out.println("This is your current balance in this account: " + current_savings.getBalance());
        System.out.println("This is your current balance in this account: " + current_money.getBalance());
        System.out.println();

        //Done Testing, printing Final Balances
        System.out.println("This is your final current balance in your current account: " + current_savings.getBalance());
        System.out.println("This is your final current balance in your savings account: " + current_money.getBalance());
        //This would fail since the money from the savings account has been withdrawn twice, limit for withdrawal has reached!


        //Testing done
        System.out.println("------TESTING DONE!------");




    }
}
