package project;
import java.util.Date;

public class Transaction {

	private double amount;
	
	private Date time;
	
	private String memo;
	
	private Account inAccount;
	
	public Transaction(double amount,Account inAccount) {
		
		this.amount = amount;
		this.inAccount = inAccount;
		this.time = new Date();
		this.memo = "";
	}
	
	public Transaction(double amount,String memo,Account inAccount) {
		this(amount,inAccount);
		this.memo = memo;
	}
/**
 * 
 * @return the amount
 */
	public double getAmount() {
		return this.amount;
	}

public String getSummaryLine() {
	Date date = new Date();
	if(this.amount>=0) {
		return String.format("$%.02f : %s",this.amount,this.memo);
	}
	else {
		return String.format("$(%.02f) : %s",-this.amount,this.memo);
	}
	
}
}
