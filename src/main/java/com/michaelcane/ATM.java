package com.michaelcane;

import java.util.Calendar;

import static com.michaelcane.UserInputHandler.*;

public class ATM {

    private UserManager userManager = new UserManager();
    private Checking checking = new Checking();
    private Savings savings= new Savings();
    private Investment investment = new Investment();
    private User user;
    private boolean working = true;
    private String password;
    Calendar rightNow = Calendar.getInstance();

    public void transaction(String accountType, double amount) {
        userManager.transactionHistory.add(rightNow + " " + accountType + " for " + amount);
    }

    public void transaction(String accountType, String accountTo, double amount) {
        userManager.transactionHistory.add(rightNow + " " + accountType + " to " + accountTo + "for" + amount);
    }

    public void startupEngine() {
        while(working) {
            theBrandLogo();
            int choice = promptUserForInt("Welcome to the International Bank of Michael Cane\n" +
                    "How can we help you today\n" + "1.) Create Account with IBMC \n2.) Login - Returning Customer \n3.) Exit");
            switch (choice) {
                case 1:
                    String name = promptUserForString("What is your name?");
                    double startingBalance = promptUserForDouble("How much would you like to deposit as your starting balance for your Checking?");
                    password = promptUserForString("What would you like your password to be?");
                    userManager.passwordChecker(password);
                    user = new User(name);
                    userManager.userPasswordList.put(password, user);
                    userManager.userPasswordList.get(password).getChecking().credit(startingBalance);
                    businessEngine();
                    break;
                case 2:
                    String userName = promptUserForString("Please enter your whole name.");
                    String inputPassword = promptUserForString("Please enter your password.");
                    user = userManager.userLookUp(inputPassword);
                    businessEngine();
                    break;
                case 3:
                    System.out.println("Thank you for your patronage, enjoy the rest of your day.");
                    working = false;
                    break;
                default:
                    System.out.println("Sorry, that command is not in my operation systems. Please, try again.");
                    break;
            }
        }
    }

    private void businessEngine() {
        while(working) {
            int choice = promptUserForInt("How can IBMC assist you today?\n" + "1.) Deposit 2.) Withdrawal 3.) Transfer Funds 4.) Check Balance\n" + "5.) Close Your Account  6.) Change your Overdraft Protection status 7.) Change Account Status\n8.) Get Transaction History 9.) Exit");
            switch (choice) {
                case 1:
                    deposit();
                    break;
                case 2:
                    withdrawal();
                    break;
                case 3:
                    transfer();
                    break;
                case 4:
                    balance();
                    break;
                case 5:
                    closeAccount();
                    break;
                case 6:
                    overdraft();
                    break;
                case 7:
                    accountStatus();
                    break;
                case 8:
                    transactionHistory();
                case 9:
                    System.out.println("Thank you for your patronage, enjoy the rest of your day.");
                    working = false;
                    break;
                default:
                    System.out.println("Sorry, that command is not in my operation systems. Please, try again.");
                    break;
            }
        }
    }

    private void overdraft() {
        while(working) {
            int choice = promptUserForInt("Which account do you want to change your Overdraft Protection status of?\n" + "1.) Checking 2.) Savings 3.) Investment 4.) Back 5.) Exit.");
            switch (choice) {
                case 1:
                    int overdraft1 = promptUserForInt("What do you want to change it to? Please select 1.) Enabled, 2.) Automatic, or 3.) Disabled.");
                    if (overdraft1 == 1) {
                        user.getChecking().setOverdraftPrevention(OverdraftPrevention.ENABLED);
                    } else if (overdraft1 == 2) {
                        user.getChecking().setOverdraftPrevention(OverdraftPrevention.AUTOMATIC);
                    } else if (overdraft1 == 3) {
                        user.getChecking().setOverdraftPrevention(OverdraftPrevention.DISABLED);
                    } else {
                        System.out.println("I'm sorry, but that is not a valid input.");
                    }
                    break;
                case 2:
                    int overdraft2 = promptUserForInt("What do you want to change it to? Please select 1.) Enabled, 2.) Automatic, or 3.) Disabled.");
                    if (overdraft2 == 1) {
                        user.getSavings().setOverdraftPrevention(OverdraftPrevention.ENABLED);
                    } else if (overdraft2 == 2) {
                        user.getSavings().setOverdraftPrevention(OverdraftPrevention.AUTOMATIC);
                    } else if (overdraft2 == 3) {
                        user.getSavings().setOverdraftPrevention(OverdraftPrevention.DISABLED);
                    } else {
                        System.out.println("I'm sorry, but that is not a proper input.");
                    }
                    break;
                case 3:
                    int overdraft3 = promptUserForInt("What do you want to change it to? Please select 1.) Enabled, 2.) Automatic, or 3.) Disabled.");
                    if (overdraft3 == 1) {
                        user.getInvestment().setOverdraftPrevention(OverdraftPrevention.ENABLED);
                    } else if (overdraft3 == 2) {
                        user.getInvestment().setOverdraftPrevention(OverdraftPrevention.AUTOMATIC);
                    } else if (overdraft3 == 3) {
                        user.getInvestment().setOverdraftPrevention(OverdraftPrevention.DISABLED);
                    } else {
                        System.out.println("We're sorry, that is not a valid input.");
                    }
                    break;
                case 4:
                    businessEngine();
                    break;
                case 5:
                    working = false;
                    break;
                default:
                    System.out.println("Sorry, that is not a valid input.");
                    break;
            }
        }
    }

    private void closeAccount() {
        while(working) {
            int choice = promptUserForInt("Which account do you want to Close?\n" + "1.) Checking 2.) Savings 3.) Investment 4.) Back 5.) Exit.");
            switch (choice) {
                case 1:
                    boolean closable1 = checking.checkForClosable();
                    if(closable1) {
                        user.getChecking().setAccountStatus(AccountStatus.CLOSED);
                        return;
                    }
                    break;
                case 2:
                    boolean closable2 = savings.checkForClosable();
                    if(closable2) {
                        user.getSavings().setAccountStatus(AccountStatus.CLOSED);
                        return;
                    }
                    break;
                case 3:
                    boolean closable3 = investment.checkForClosable();
                    if(closable3) {
                        user.getInvestment().setAccountStatus(AccountStatus.CLOSED);
                        return;
                    }
                    break;
                case 4:
                    businessEngine();
                    break;
                case 5:
                    working = false;
                    break;
                default:
                    System.out.println("Sorry, that is not a valid input.");
                    break;
            }
        }
    }

    private void accountStatus() {
        while(working){
            int choice = promptUserForInt("Which account do you want to change the status of?\n" + "1.) Checking 2.) Savings 3.) Investment 4.) Back 5.) Exit.");
            switch (choice) {
                case 1:
                    int status1 = promptUserForInt("What do you want to change it to? Please select 1.) Open, 2.) Closed, 3.) Frozen, or 4.) OFAC.");
                    if (status1 == 1) {
                        user.getChecking().setAccountStatus(AccountStatus.OPEN);
                    } else if (status1 == 2) {
                        user.getChecking().setAccountStatus(AccountStatus.CLOSED);
                    } else if (status1 == 3) {
                        user.getChecking().setAccountStatus(AccountStatus.FROZEN);
                    } else if (status1 == 4) {
                        user.getChecking().setAccountStatus(AccountStatus.OFAC);
                    } else {
                        System.out.println("I'm sorry, but that is not a valid input.");
                    }
                    break;
                case 2:
                    int status2 = promptUserForInt("What do you want to change it to? Please select 1.) Open, 2.) Closed, 3.) Frozen, or 4.) OFAC.");
                    if (status2 == 1) {
                        user.getSavings().setAccountStatus(AccountStatus.OPEN);
                    } else if (status2 == 2) {
                        user.getSavings().setAccountStatus(AccountStatus.CLOSED);
                    } else if (status2 == 3) {
                        user.getSavings().setAccountStatus(AccountStatus.FROZEN);
                    } else if (status2 == 4) {
                        user.getSavings().setAccountStatus(AccountStatus.OFAC);
                    } else {
                        System.out.println("I'm sorry, but that is not a proper input.");
                    }
                    break;
                case 3:
                    int status3 = promptUserForInt("What do you want to change it to? Please select 1.) Open, 2.) Closed, 3.) Frozen, or 4.) OFAC.");
                    if (status3 == 1) {
                        user.getInvestment().setAccountStatus(AccountStatus.OPEN);
                    } else if (status3 == 2) {
                        user.getInvestment().setAccountStatus(AccountStatus.CLOSED);
                    } else if (status3 == 3) {
                        user.getInvestment().setAccountStatus(AccountStatus.FROZEN);
                    } else if (status3 == 4) {
                        user.getInvestment().setAccountStatus(AccountStatus.OFAC);
                    } else {
                        System.out.println("We're sorry, that is not a valid input.");
                    }
                    break;
                case 4:
                    businessEngine();
                    break;
                case 5:
                    working = false;
                    break;
                default:
                    System.out.println("Sorry, that is not a valid input.");
                    break;
            }
        }
    }

    private void balance() {
        while(working) {
            int choice = promptUserForInt("What account would you like check?\n" + "1.) Checking\n" + "2.) Savings\n" + "3.) Investments\n" + "4.) Back\n" + "5.) Exit");
            switch (choice) {
                case 1:
                    user.getChecking().printBalance();
                    break;
                case 2:
                    user.getSavings().printBalance();
                    break;
                case 3:
                    user.getInvestment().printBalance();
                    break;
                case 4:
                    businessEngine();
                    break;
                case 5:
                    working = false;
                    break;
                default:
                    System.out.println("Sorry, that is not a valid input.");
                    break;
            }
        }
    }

    private void withdrawal() {
        while(working) {
            int choice = promptUserForInt("What account would you like withdrawal from?\n" + "1.) Checking\n" + "2.) Savings\n" + "3.) Investments\n" + "4.) Back\n" + "5.) Exit");
            double withdrawalAmount = promptUserForDouble("How much would you like to withdrawal?");
            switch (choice) {
                case 1:
                    user.getChecking().debitOverdraftPreventionTest(withdrawalAmount);
                    transaction("Checking", withdrawalAmount);
                    break;
                case 2:
                    user.getSavings().debitOverdraftPreventionTest(withdrawalAmount);
                    transaction("Savings", withdrawalAmount);
                    break;
                case 3:
                    user.getInvestment().debitOverdraftPreventionTest(withdrawalAmount);
                    transaction("Investment", withdrawalAmount);
                    break;
                case 4:
                    businessEngine();
                    break;
                case 5:
                    System.out.println("Thank you for your patronage, enjoy the rest of your day.");
                    working = false;
                    break;
                default:
                    System.out.println("Sorry, that command is not in my operation systems. Please, try again.");
                    break;
            }
        }
    }

    private void deposit() {
        while (working) {
            int choice = promptUserForInt("What account would you like deposit into?\n" + "1.) Checking\n" + "2.) Savings\n" + "3.) Investments\n" + "4.) Back\n" + "5.) Exit");
            double depositAmount = promptUserForDouble("How much would you like to deposit?");
            switch (choice) {
                case 1:
                    user.getChecking().credit(depositAmount);
                    transaction("Checking", depositAmount);
                    break;
                case 2:
                    user.getSavings().credit(depositAmount);
                    transaction("Savings", depositAmount);
                    break;
                case 3:
                    user.getInvestment().credit(depositAmount);
                    transaction("Investment", depositAmount);
                    break;
                case 4:
                    businessEngine();
                    break;
                case 5:
                    System.out.println("Thank you for your patronage, enjoy the rest of your day.");
                    working = false;
                    break;
                default:
                    System.out.println("Sorry, that command is not in my operation systems. Please, try again.");
                    break;
            }
        }
    }

    private void transfer() {
        while(working) {
            int choice1 = promptUserForInt("What account would you like to transfer from?\n" + "1.) Checking\n" + "2.) Savings\n" + "3.) Investments\n" + "4.) Back\n" + "5.) Exit");
            int choice2 = promptUserForInt("What account would you like to transfer to?\n" + "1.) Checking\n" + "2.) Savings\n" + "3.) Investments");
            double transferAmount = promptUserForDouble("How much would you like to transfer?");
            switch (choice1) {
                case 1:
                    if (choice2 == 2) {
                        user.getChecking().debitOverdraftPreventionTest(transferAmount);
                        user.getSavings().credit(transferAmount);
                        transaction("Checking", "Savings", transferAmount);
                    } else if (choice2 == 3) {
                        user.getChecking().debitOverdraftPreventionTest(transferAmount);
                        user.getInvestment().credit(transferAmount);
                        transaction("Checking", "Investment", transferAmount);
                    } else if (choice2 == 1) {
                        System.out.println("Sorry, you can't transfer money into the same account you withdrawal it from.");
                    }else {
                        System.out.println("We're sorry, that is not a valid input.");
                    }
                    break;
                case 2:
                    if (choice2 == 3) {
                        user.getSavings().debitOverdraftPreventionTest(transferAmount);
                        user.getInvestment().credit(transferAmount);
                        transaction("Savings", "Investment", transferAmount);
                    } else if (choice2 == 1) {
                        user.getSavings().debitOverdraftPreventionTest(transferAmount);
                        user.getChecking().credit(transferAmount);
                        transaction("Savings", "Checking", transferAmount);
                    } else if (choice2 == 2) {
                        System.out.println("Sorry, you can't transfer money into the same account you withdrawal it from.");
                    }else {
                        System.out.println("We're sorry, that is not a valid input.");
                    }
                    break;
                case 3:
                    if (choice2 == 1) {
                        user.getInvestment().debitOverdraftPreventionTest(transferAmount);
                        user.getChecking().credit(transferAmount);
                        transaction("Investement", "Checking", transferAmount);
                    } else if (choice2 == 2) {
                        user.getInvestment().debitOverdraftPreventionTest(transferAmount);
                        user.getSavings().credit(transferAmount);
                        transaction("Investement", "Savings", transferAmount);
                    } else if (choice2 == 3) {
                        System.out.println("Sorry, you can't transfer money into the same account you withdrawal it from.");
                    }else {
                        System.out.println("We're sorry, that is not a valid input.");
                    }
                    break;
                case 4:
                    businessEngine();
                    break;
                case 5:
                    System.out.println("Thank you for your patronage, enjoy the rest of your day.");
                    working = false;
                    break;
                default:
                    System.out.println("Sorry, that command is not in my operation systems. Please, try again.");
                    break;
            }
        }
    }

    public void transactionHistory() throws NullPointerException {
        try {
            for(int i =0; i < userManager.transactionHistory.size(); i++) {
                System.out.println(userManager.transactionHistory.get(i));
            }
        } catch (NullPointerException e) {
            System.err.println("NullPointerException: " + e.getMessage());
            System.out.println("Sorry, there are no transactions to report at this time.");
        }
    }
}
