package project;
import java.util.*;
import java.util.ArrayList;

public class Bank {

	private String name;
	private ArrayList<User> users;
	private ArrayList<Account> accounts;
	public Bank(String name) {
		this.name = name;
		this.accounts = new ArrayList<Account>();
		this.users = new ArrayList<User>();
	}
	public String getNewUserUUID() {
		String uuid;
		Random rn = new Random();
		int len = 6;
		boolean nonUnique;
		do {
			uuid = "";
			nonUnique = false;
			for (int i = 0; i < len; i++) {
				uuid+=((Integer)rn.nextInt(10)).toString();
			}
			for (User u : this.users) {
				if(uuid.compareTo(u.getUUID())==0){
					nonUnique = true;
					break;
				}
			}
			
		}while(nonUnique);
		
		return uuid;
	}
	
	public String getNewAccountUUID() {
		String uuid;
		Random rn = new Random();
		int len = 10;
		boolean nonUnique;
		do {
			uuid = "";
			nonUnique = false;
			for (int i = 0; i < len; i++) {
				uuid+=((Integer)rn.nextInt(10)).toString();
			}
			for (Account a : this.accounts) {
				if(uuid.compareTo(a.getUUID())==0){
					nonUnique = true;
					break;
				}
			}
			
		}while(nonUnique);
		
		return uuid;
	}

	public void addAccount(Account acc) {
		this.accounts.add(acc);
		
	}
	
	public User addUser(String firstName,String lastName,String pin) {
		
		User newUser = new User(firstName, lastName, pin,this);
		this.users.add(newUser);
//		creating a savings account;
		Account newAccount = new Account("Savings",newUser,this);
		newUser.addAccount(newAccount);
		this.accounts.add(newAccount);
		
		return newUser;
	}
	
	public User userLogin(String userID,String pin) {
//		searching through list of users
		for(User u:this.users) {
			if(u.getUUID().compareTo(userID)==0 && u.validatePin(pin)) {
				return u;
			}
		}
		return null;
	}

	public String getName() {
		return this.name;
	}
}
