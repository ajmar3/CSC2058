package Main;

import java.util.Scanner;

public class Menu {
	private String options[];	
	private String title;		
	
	public Menu(String title, String options[]) {
		this.title = title;
		copyOptions(options);
	}

	public int getChoice() {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter choice: ");
		int choice = input.nextInt();
		return choice;
	}
	
	public void display() {
		if (title != null && options !=null) {
			System.out.println(title);
			for(int i=0;i<title.length();i++) {
				System.out.print("+");
			}
			System.out.println("\n");
			int count = 1;
			for(String str : options) {
				System.out.println(count+". "+str);
				count++;
			}
			System.out.println();
		}
		else {
			System.out.println("Menu has not been properly defined...");
		}
	}
	
	private void copyOptions(String data[]) {
		if ( data != null) {
			options = new String[data.length];
			for(int index=0;index<data.length;index++) {
				options[index] = data[index];
			}
		}
		else {
			options = null;
		}
	}
}

