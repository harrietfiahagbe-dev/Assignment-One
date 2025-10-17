public class BankAccount {
//Creating data fields for the Class

    //Making them private to protect how the data or information is accessed easily
    private AccountType acctType;
    private String acctID;
    private double balance;
    private int numWithdrawals;
    private boolean inTheRed;
    private double minBalance;
    private double interestRate;
    private double maintenanceFee;
    private int withdrawalLimit;

    //Making them public so other classes could access them, and also it is a convention
    public static final double CURRENT_ACCT_MIN_BALANCE = 1000.0;
    public static final double SAVINGS_ACCT_MIN_BALANCE= 850.0;
    public static final double SAVINGS_ACCT_INTEREST_RATE = 20.0;
    public static final double CURRENT_ACCT_MAINTENANCE_FEE= 200.0;
    public static final int  SAVINGS_WITHDRAWAL_LIMIT = 2;

    //CREATING CONSTRUCTORS

    //First Constructors
    public BankAccount(AccountType type, String acctID) {
        //Initializes the bank account
        this.acctType = type;
        this.acctID = acctID;
        this.balance = 0.0;
        this.numWithdrawals = 0;

        //Account Type and their corresponding values
        if (acctType == AccountType.CURRENT) {
            this.minBalance = CURRENT_ACCT_MIN_BALANCE;
            this.interestRate = 0.0;
            this.maintenanceFee = CURRENT_ACCT_MAINTENANCE_FEE;
            this.withdrawalLimit = -1;
        } else {
            this.minBalance = SAVINGS_ACCT_MIN_BALANCE;
            this.interestRate = SAVINGS_ACCT_INTEREST_RATE;
            this.maintenanceFee = 0.0;
            this.withdrawalLimit = SAVINGS_WITHDRAWAL_LIMIT;
        }


        //InThered conditions
        if (this.minBalance > this.balance) {
            this.inTheRed = true;
        } else {
            this.inTheRed = false;
        }
    }

    //Creating the Second Constructor
    public BankAccount(AccountType type, String acctID, double openingbalance) {
        this.acctType = type;
        this.acctID = acctID;
        this.balance = openingbalance;
        this.numWithdrawals = 0;
        this.withdrawalLimit = -1;

        //Account Type and their corresponding values
        if (acctType == AccountType.CURRENT) {
            this.minBalance = CURRENT_ACCT_MIN_BALANCE;
            this.interestRate = 0;
            this.maintenanceFee = CURRENT_ACCT_MAINTENANCE_FEE;
        } else {
            this.minBalance = SAVINGS_ACCT_MIN_BALANCE;
            this.interestRate = SAVINGS_ACCT_INTEREST_RATE;
            this.maintenanceFee = 0;
            this.withdrawalLimit = SAVINGS_WITHDRAWAL_LIMIT;
        }

        //InThered conditions
        if (this.minBalance > this.balance) {
            this.inTheRed = true;
        } else {
            this.inTheRed = false;
        }

    }

    //CREATING METHODS

    //Creating an Accessor for Balance
    public double getBalance() {
        return balance;
    }

    //Creating an Accessor for AccountType
    public AccountType getAccountType() {
        return acctType;
    }

    //Creating an Accessor for AccountId
    public String getAcctID() {
        return acctID;
    }

    //Creating an Accessor for Minimum Balance
    public double getMinBalance() {
        return minBalance;
    }

    //Creating a Method for withdraw
    public boolean withdraw(double amount) {
        if (this.withdrawalLimit != -1 && this.numWithdrawals >= this.withdrawalLimit) {
            System.out.println("Sorry, could not perform withdrawal: Insufficient balance due to limited withdrawal limit!");
            return false;
        }

        if (this.inTheRed) {
            System.out.println("Sorry, could not perform withdrawal: Insufficient balance due to the account being in the red!");
            return false;
        }

        if (this.balance - amount < this.minBalance) {
            System.out.println("Sorry, could not perform withdrawal: Insufficient balance due to insufficient balance!");
            return false;
        }

        this.balance -= amount;
        this.numWithdrawals++;
        inTheRed = (balance < minBalance);
        return true;

    }


    //Creating a Method for Deposit
    public void deposit(double amount) {
        this.balance += amount;
        this.inTheRed= (this.balance< this.minBalance);
    }

    //Creating a Method for Monthly Maintenance
    public void performMonthlyMaintenance() {
        //Computing Interest Rate on Current balance
        double interest_month = this.interestRate / 12;
        double new_interest = this.balance * interest_month;
        this.balance += new_interest;

        //Computing Monthly Maintenance Fee
        this.balance -= this.maintenanceFee;

        //Checking if account balance is less than the mimimum balance
        if (this.minBalance > this.balance) {
            this.inTheRed = true;
        } else {
            this.inTheRed = false;
        }

        //Setting number of withdrawals to 0
        this.numWithdrawals = 0;

        //Printing a summary of the monthly maintenance process
        System.out.println("Earned Interest: " + new_interest );
        System.out.println("Maintenance Fee: " + maintenanceFee );
        System.out.println("Updated Balance: " + balance);

        //Checking to see if the Account is red
        if (this.inTheRed) {
            System.out.println("WARNING: This account is in the red!");
        }
    }

    //Creating  a method to Transfer
    public boolean transfer(boolean transferTo, BankAccount otherAccount, double amount) {
        if (transferTo) {
            boolean allow_transfer = this.withdraw(amount);
            if (allow_transfer) {
                otherAccount.deposit(amount);
                return true;
            } else {
                System.out.println("Transaction failed, withdrawal cannot be done!");
                return false;
            }

        }
        else{
            boolean allow_transfer = otherAccount.withdraw(amount);
            if (allow_transfer) {
                this.deposit(amount);
                return true;
            }
            else{
                return false;
            }
        }

    }
}





















