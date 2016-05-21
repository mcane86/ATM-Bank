package com.michaelcane;

import static com.michaelcane.UserInputHandler.*;

public class ATM {

    private UserManager userManager = new UserManager();
    private Checking checking = new Checking();
    private Savings savings= new Savings();
    private Investment investment = new Investment();
    private User user;
    private boolean working = true;

    public void startupEngine() {
        while(working) {
            theBrandLogo();
            int choice = promptUserForInt("Welcome to the International Bank of Michael Cane\n" +
                    "How can we help you today\n" + "1.) Create Account with IBMC \n2.) Login - Returning Customer \n3.) Exit");
            switch (choice) {
                case 1:
                    String name = promptUserForString("What is your name?");
                    double startingBalance = promptUserForDouble("How much would you like to deposit as your starting balance for your Checking?");
                    String password = promptUserForString("What would you like your password to be?");
                    userManager.passwordChecker(password);
                    user = new User(name);
                    userManager.userPasswordList.put(password, user);
                    checking.setBalance(startingBalance);
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

    public void businessEngine() {
        while(working) {
            int choice = promptUserForInt("How can IBMC assist you today?\n" + "1.) Deposit 2.) Withdrawal 3.) Transfer Funds\n" + "4.) Close Your Account  5.) Change your Overdraft Protection status 6.) Change Account Status .7) Exit");
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
                    closeAccount();
                    break;
                case 5:
                    overdraft();
                    break;
                case 6:
                    accountStatus();
                    break;
                case 7:
                    System.out.println("Thank you for your patronage, enjoy the rest of your day.");
                    working = false;
                    break;
                default:
                    System.out.println("Sorry, that command is not in my operation systems. Please, try again.");
                    break;
            }
        }
    }

    public void overdraft() {
        while(working) {
            int choice = promptUserForInt("Which account do you want to change your Overdraft Protection status of?\n" + "1.) Checking 2.) Savings 3.) Investment 4.) Back 5.) Exit.");
            switch (choice) {
                case 1:
                    String overdraft1 = promptUserForString("What do you want to change it to? Please type Enabled, Automatic, or Disabled.");
                    if (overdraft1.toLowerCase().equals("enabled")) {
                        checking.setOverdraftPrevention(OverdraftPrevention.ENABLED);
                    } else if (overdraft1.toLowerCase().equals("automatic")) {
                        checking.setOverdraftPrevention(OverdraftPrevention.AUTOMATIC);
                    } else if (overdraft1.toLowerCase().equals("disabled")) {
                        checking.setOverdraftPrevention(OverdraftPrevention.DISABLED);
                    } else {
                        System.out.println("I'm sorry, but that is not a valid input.");
                    }
                    break;
                case 2:
                    String overdraft2 = promptUserForString("What do you want to change it to? Please type Enabled, Automatic, or Disabled.");
                    if (overdraft2.toLowerCase().equals("enabled")) {
                        savings.setOverdraftPrevention(OverdraftPrevention.ENABLED);
                    } else if (overdraft2.toLowerCase().equals("automatic")) {
                        savings.setOverdraftPrevention(OverdraftPrevention.AUTOMATIC);
                    } else if (overdraft2.toLowerCase().equals("disabled")) {
                        savings.setOverdraftPrevention(OverdraftPrevention.DISABLED);
                    } else {
                        System.out.println("I'm sorry, but that is not a proper input.");
                    }
                    break;
                case 3:
                    String overdraft3 = promptUserForString("What do you want to change it to? Please type Enabled, Automatic, or Disabled.");
                    if (overdraft3.toLowerCase().equals("enabled")) {
                        investment.setOverdraftPrevention(OverdraftPrevention.ENABLED);
                    } else if (overdraft3.toLowerCase().equals("automatic")) {
                        investment.setOverdraftPrevention(OverdraftPrevention.AUTOMATIC);
                    } else if (overdraft3.toLowerCase().equals("disabled")) {
                        investment.setOverdraftPrevention(OverdraftPrevention.DISABLED);
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

    public void closeAccount() {
        while(working) {
            int choice = promptUserForInt("Which account do you want to Close?\n" + "1.) Checking 2.) Savings 3.) Investment 4.) Back 5.) Exit.");
            switch (choice) {
                case 1:
                    boolean closable1 = checking.checkForClosable();
                    if(closable1) {
                        checking.setAccountStatus(AccountStatus.CLOSED);
                    }
                    break;
                case 2:
                    boolean closable2 = savings.checkForClosable();
                    if(closable2) {
                        savings.setAccountStatus(AccountStatus.CLOSED);
                    }
                    break;
                case 3:
                    boolean closable3 = investment.checkForClosable();
                    if(closable3) {
                        investment.setAccountStatus(AccountStatus.CLOSED);
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

    public void accountStatus() {
        while(working){
            int choice = promptUserForInt("Which account do you want to change the status of?\n" + "1.) Checking 2.) Savings 3.) Investment 4.) Back 5.) Exit.");
            switch (choice) {
                case 1:
                    String status1 = promptUserForString("What do you want to change it to? Please type Open, Closed, Frozen, or OFAC.");
                    if (status1.toLowerCase().equals("open")) {
                        checking.setAccountStatus(AccountStatus.OPEN);
                    } else if (status1.toLowerCase().equals("closed")) {
                        checking.setAccountStatus(AccountStatus.CLOSED);
                    } else if (status1.toLowerCase().equals("frozen")) {
                        checking.setAccountStatus(AccountStatus.FROZEN);
                    } else if (status1.toLowerCase().equals("ofac")) {
                        checking.setAccountStatus(AccountStatus.OFAC);
                    } else {
                        System.out.println("I'm sorry, but that is not a valid input.");
                    }
                    break;
                case 2:
                    String status2 = promptUserForString("What do you want to change it to? Please type Open, Closed, Frozen, or OFAC.");
                    if (status2.toLowerCase().equals("open")) {
                        savings.setAccountStatus(AccountStatus.OPEN);
                    } else if (status2.toLowerCase().equals("closed")) {
                        savings.setAccountStatus(AccountStatus.CLOSED);
                    } else if (status2.toLowerCase().equals("frozen")) {
                        savings.setAccountStatus(AccountStatus.FROZEN);
                    } else if (status2.toLowerCase().equals("ofac")) {
                        savings.setAccountStatus(AccountStatus.OFAC);
                    } else {
                        System.out.println("I'm sorry, but that is not a proper input.");
                    }
                    break;
                case 3:
                    String status3 = promptUserForString("What do you want to change it to? Please type Open, Closed, Frozen, or OFAC.");
                    if (status3.toLowerCase().equals("open")) {
                        investment.setAccountStatus(AccountStatus.OPEN);
                    } else if (status3.toLowerCase().equals("closed")) {
                        investment.setAccountStatus(AccountStatus.CLOSED);
                    } else if (status3.toLowerCase().equals("frozen")) {
                        investment.setAccountStatus(AccountStatus.FROZEN);
                    } else if (status3.toLowerCase().equals("ofac")) {
                        investment.setAccountStatus(AccountStatus.OFAC);
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


    public void deposit() {
        while(working) {
            int choice = promptUserForInt("What account would you like deposit into?\n" + "1.) Checking\n" + "2.) Savings\n" + "3.) Investments\n" + "4.) Back\n" + "5.) Exit");
            double depositAmount = promptUserForDouble("How much would you like to deposit?");
            switch (choice) {
                case 1:
                    checking.credit(depositAmount);
                    break;
                case 2:
                    savings.credit(depositAmount);
                    break;
                case 3:
                    investment.credit(depositAmount);
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

    public void withdrawal() {
        while (working) {
            int choice = promptUserForInt("What account would you like withdrawal from?\n" + "1.) Checking\n" + "2.) Savings\n" + "3.) Investments\n" + "4.) Back\n" + "5.) Exit");
            double withdrawalAmount = promptUserForDouble("How much would you like to withdrawal?");
            switch (choice) {
                case 1:
                    checking.debitOverdraftPreventionTest(withdrawalAmount);
                    break;
                case 2:
                    savings.debitOverdraftPreventionTest(withdrawalAmount);
                    break;
                case 3:
                    investment.debitOverdraftPreventionTest(withdrawalAmount);
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

    public void transfer() {
        while(working) {

        }
    }
}
