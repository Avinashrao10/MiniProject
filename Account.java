package project;
import java.util.*;
public class Account {

	private String name;
	private String uuid;
	private User holder;
	private ArrayList<Transaction> trasactions;
	
	public Account(String name,User holder,Bank bank) {
		this.name = name;
		this.holder = holder;
	
		this.uuid = bank.getNewAccountUUID();
		
		this.trasactions = new ArrayList<Transaction>();
	}

	public String getUUID() {
		return this.uuid;
	}

	public String getSummaryLine() {
		double balance  = this.getBalance();

//		format the summary line depending on the whether the blance is negative
		if(balance>=0) {
			return String.format("%s : $%.02f : %s",this.uuid,balance,this.name );
		}
		else {
			return String.format("%s : $(%.02f) : %s",this.uuid,balance,this.name );
		}
	}
	
	public double getBalance() {
		double balance = 0;
		for(Transaction t : this.trasactions) {
			balance+=t.getAmount();
		}
		return balance;
	}

	public void printTransHistory() {
		System.out.printf("\n Transaction history of account %s\n",this.uuid);
		for (int i = this.trasactions.size()-1;i>=0; i--) {
			System.out.println(this.trasactions.get(i).getSummaryLine());
		}
		System.out.println();
	}

	public void addTransaction(double amount, String memo) {
		Transaction newTrans = new Transaction(amount,memo,this);
		this.trasactions.add(newTrans);
		
	}
	
	
	
}
