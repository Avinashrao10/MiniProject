package project;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {

	private String firstName;
	private String lastName;
	private String uuid;
	private byte pinHash[];
	private ArrayList<Account> accounts;
	
	
	public User(String firstName,String lastName,String pin ,Bank bank) {
		this.firstName = firstName;
		
		this.lastName = lastName;
//		Storing the pin using MD5 algorithm;
		try {
			MessageDigest md  = MessageDigest.getInstance("MD5");
			this.pinHash = md.digest(pin.getBytes());
		} catch (NoSuchAlgorithmException e) {
			System.out.println("error, caught NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}
		
		this.uuid = bank.getNewUserUUID();
		
		this.accounts = new ArrayList<Account>();
		
		System.out.printf("New User %s %s with ID %s created.\n",lastName,firstName,this.uuid);;
	}


	public void addAccount(Account acc) {
		this.accounts.add(acc);
		
	}


	public String getUUID() {
		// TODO Auto-generated method stub
		return this.uuid;
	}


	public boolean validatePin(String pin) {
		try {
			MessageDigest md  = MessageDigest.getInstance("MD5");
			return MessageDigest.isEqual(md.digest(pin.getBytes()),this.pinHash);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("error, caught NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}
		return false;
	}

/**
 * 
 * @return
 */
	public String getFirstName() {
		return this.firstName;
	}


public void printAccountSummary() {
	System.out.printf("\n\n%s's accounts summary \n",this.firstName);
	for (int i = 0; i < this.accounts.size(); i++) {
		System.out.printf("%d) %s",i+1,this.accounts.get(i).getSummaryLine());
		System.out.println();
	}
}


public int numAccounts() {
	return this.accounts.size();
}


public void printAcctTransHistory(int acctIdx) {
	this.accounts.get(acctIdx).printTransHistory();
	
}


public double getAcctBalance(int acctIdx) {
	return this.accounts.get(acctIdx).getBalance();
}


public String getAcctUUID(int acctIdx) {
	return this.accounts.get(acctIdx).getUUID();
}


public void addAccTransaction(int acctIdx, double amount, String memo) {
	this.accounts.get(acctIdx).addTransaction(amount,memo);
	
}






	
}
