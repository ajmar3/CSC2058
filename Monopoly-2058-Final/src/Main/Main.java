package Main;

import Main.transaction.MenuTransaction;
import PlayerManagement.transactions.LoadPlayerTransaction;


public class Main {
	
	static LoadPlayerTransaction lp = new LoadPlayerTransaction();
	static MenuTransaction m = new MenuTransaction();
	
	public static void main(String[] args) {
		
		m.loadMenu();
		//this is a test comment
	}
	
}
