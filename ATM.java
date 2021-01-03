package project;

import java.util.Scanner;

public class ATM {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Bank bank = new Bank("BOI");
		User user = bank.addUser("Avinash","Thandra", "1234");
		Account newAccount = new Account("Checking",user,bank);
		user.addAccount(newAccount);
		bank.addAccount(newAccount);
		
		User currUser;
		while(true) {
			
			
			currUser  = ATM.mainMenuPrompt(bank,sc);
			
			ATM.printUserMenu(currUser,sc);
			
		}
		
	}

	private static void printUserMenu(User currUser, Scanner sc) {
		currUser.printAccountSummary();
		
		int choice;
		do {
			System.out.printf("Welcome %s ,Choose a option\n",currUser.getFirstName());
			System.out.println("  1) Show account transaction history");
			System.out.println("  2) Withdraw");
			System.out.println("  3) Deposit");
			System.out.println("  4) Transfer");
			System.out.println("  5) Quit");
			System.out.println("Enter Choice:");
			choice = sc.nextInt();
			if(choice<1 || choice>5) {
				System.out.println("Invalid choice. Please choose 1-5");
			}
		}while(choice<1 || choice>5);
		
		switch(choice) {
		
		case 1:
			ATM.showTransHistory(currUser,sc);
			break;
		case 2:
			ATM.withdrawFunds(currUser,sc);
			break;
		case 3:
			ATM.depositFunds(currUser,sc);
			break;
		case 4:
			ATM.transferFunds(currUser,sc);
			break;
		case 5:
			sc.nextLine();
			break;
		}
		if(choice!=5) {
			ATM.printUserMenu(currUser, sc);
		}
	}


/**
 * 
 * @param currUser
 * @param sc
 */
	private static void showTransHistory(User currUser, Scanner sc) {
		int theAcct;
		
		do {
			System.out.printf("Enter the number (1-%d) of the account \n"+
		"whose transactions you want to see: ",currUser.numAccounts());
			theAcct = sc.nextInt()-1;
			if(theAcct<0 || theAcct>=currUser.numAccounts()) {
				System.out.println("Invalid account .Please try again.");
			}
		}while(theAcct<0 || theAcct>=currUser.numAccounts());
		
		currUser.printAcctTransHistory(theAcct);
		
	}
	
	/**
	 * 
	 * @param currUser
	 * @param sc
	 */
	private static void withdrawFunds(User currUser, Scanner sc) {
		int fromAcct;
		double amount;
		double acctBal;
		String memo;
		do {
			System.out.printf("Enter the number(1-%d) of the account\n" + " to withdraw from : ",currUser.numAccounts());
			fromAcct = sc.nextInt()-1;
			if(fromAcct<0 || fromAcct>=currUser.numAccounts()) {
				System.out.println("Invalid account .Please try again.");
			}
	}while(fromAcct<0 || fromAcct>=currUser.numAccounts());
		
	acctBal = currUser.getAcctBalance(fromAcct);
		
	do {
		System.out.printf("Enter the amount to winthdraw (max $%.02f): $",acctBal);
		amount = sc.nextDouble();
		if(amount<0) {
			System.out.printf("Amount must be greater than zero");
		}
		else if(amount>acctBal) {
			System.out.printf("Amount must not be greater than\n" + "balance of $%.02f.\n",acctBal);
		}
	}while(amount<0 || amount>acctBal);
	
	sc.nextLine();
	System.out.println("Enter a memo :");
	memo = sc.nextLine();
	currUser.addAccTransaction(fromAcct, -1*amount, memo);
	}
	
/**
 * 
 * @param currUser
 * @param sc
 */
	private static void depositFunds(User currUser, Scanner sc) {
		int toAcct;
		double amount;
		double acctBal;
		String memo;
		do {
			System.out.printf("Enter the number(1-%d) of the account\n" + " to depsoit in : ",currUser.numAccounts());
			toAcct = sc.nextInt()-1;
			if(toAcct<0 || toAcct>=currUser.numAccounts()) {
				System.out.println("Invalid account .Please try again.");
			}
	}while(toAcct<0 || toAcct>=currUser.numAccounts());
		
	acctBal = currUser.getAcctBalance(toAcct);
		
	do {
		System.out.printf("Enter the amount to tansfer (max $%.02f): $",acctBal);
		amount = sc.nextDouble();
		if(amount<0) {
			System.out.printf("Amount must be greater than zero");
		}
	}while(amount<0);
	
	sc.nextLine();
	System.out.println("Enter a memo :");
	memo = sc.nextLine();
	currUser.addAccTransaction(toAcct, amount, memo);
		
	}
	private static void transferFunds(User currUser, Scanner sc) {
		int fromAcct;
		int toAcct;
		double amount;
		double acctBal;
		
		do {
			System.out.printf("Enter the number(1-%d) of the account\n" + " to tansfer from : ",currUser.numAccounts());
			fromAcct = sc.nextInt()-1;
			if(fromAcct<0 || fromAcct>=currUser.numAccounts()) {
				System.out.println("Invalid account .Please try again.");
			}
	}while(fromAcct<0 || fromAcct>=currUser.numAccounts());
		
	acctBal = currUser.getAcctBalance(fromAcct);
//		get the account to transfer to
	do {
		System.out.printf("Enter the number(1-%d) of the account\n" + " to tansfer: ",currUser.numAccounts());
		toAcct = sc.nextInt()-1;
		if(toAcct<0 || toAcct>=currUser.numAccounts()) {
			System.out.println("Invalid account .Please try again.");
		}
	}while(toAcct<0 || toAcct>=currUser.numAccounts());
	
	do {
		System.out.printf("Enter the amount to tansfer (max $%.02f): $",acctBal);
		amount = sc.nextDouble();
		if(amount<0) {
			System.out.printf("Amount must be greater than zero");
		}
		else if(amount>acctBal) {
			System.out.printf("Amount must not be greater than\n" + "balance of $%.02f.\n",acctBal);
		}
	}while(amount<0 || amount>acctBal);
	
//	finally doing transfer
	currUser.addAccTransaction(fromAcct,-1*amount,String.format("Tansfer to account %s", currUser.getAcctUUID(toAcct)));
	
	currUser.addAccTransaction(toAcct,amount,String.format("Received from account %s", currUser.getAcctUUID(fromAcct)));
	}

		

/**
 * 
 * @param bank
 * @param sc
 * @return
 */
	private static User mainMenuPrompt(Bank bank, Scanner sc) {
		
		String userId;
		String pin;
		User authUser;
		do {
			System.out.printf("\n\nWelcome to %s\n\n", bank.getName());
			System.out.print("Enter user ID:");
			userId = sc.nextLine();
			System.out.printf("Enter pin:");
			pin = sc.nextLine();
			
			authUser = bank.userLogin(userId, pin);
			if(authUser==null) {
				System.out.println("Incorrect user ID/pin. Please try again");
			}
		}while(authUser==null);
		return authUser;
	}
}
