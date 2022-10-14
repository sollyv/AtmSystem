package atmsystem;

import java.util.Scanner;

public class AtmSystem {
	static String username[]= {"Jessica","Vinson","Melissa","Solly","Serena"};
	static String user_pin[]= {"0101","0102","0103","0104","0105"};
	static double balance[]= {1000.34,1500.87,1500.76,4500.76,1200.76};
	static String overdraft[]= {"Yes","No","Yes","No","No"};
	static int customer_no=0;
	static Scanner atmObj = new Scanner(System.in);
	static int login_attempt=0;

	public static void main(String[] args) {	
		AtmSystem.printcustomers();		
	}
	
	public static void printcustomers(){
		System.out.println("******************************************************");
		System.out.println("*                  ATM Users Details                 *");
		System.out.println("******************************************************");
		System.out.println("Cust \tName \t\tBalance \tOverdraft ");
		System.out.println("------------------------------------------------------");
		for( int i = 0; i < user_pin.length; i++) {
			System.out.print(i +  "\t"+ username[i] + "\t\t"+ balance[i] +"\t\t"+ overdraft[i] +"\n");  	
		}		
		welcome();
	}
	
	public static void welcome() {
		System.out.println("******************************************************");
		System.out.println("\n\t\tWelcome");
		System.out.print("\nPlease enter your 4-digit PIN: ");
		Scanner atm_pin_input = new Scanner(System.in);
		String input_pin=atm_pin_input.nextLine();
		if(input_pin.isEmpty()) {
			System.out.println("Please provide PIN :");
			Scanner empty_pin = new Scanner(System.in);
			String empty_inputpin=empty_pin.nextLine();
			validatepin(empty_inputpin);
		}
		else {validatepin(input_pin);}
		
	}
	
	public static void validatepin(String i_pin) {
		
		boolean validlogin=false;	
		for(int i=0;i<user_pin.length;i++) {
			if(i_pin.equals(user_pin[i])) {
				validlogin=true;
				customer_no=i;				
				break;
			}
			else {
				
				validlogin=false;
			}				
		}		
		if(validlogin) {
			System.out.println("Login was successful");
			System.out.println("\n\t\tWelcome "+username[customer_no]);
			System.out.println("\t\tPos:"+customer_no);
			displaymenu();}
		if(validlogin==false){
			login_attempt++;
			if(login_attempt>=3) {
				System.out.println(login_attempt);
				System.out.println("3 unsucessful attempts");
				exit();
			}
			else {
				System.out.println("Invalid PIN");
				System.out.println("Please try again.");
				welcome();
			}
		}
		
		
	}
	
	public static void displaymenu() {
		boolean menu_valid=true;
		while(menu_valid) {
		try {		
			System.out.println("\n*****************************************");
			System.out.println("*                  Menu                 *");
			System.out.println("*****************************************");
			System.out.println("\t1 - View Bank Statement");
			System.out.println("\t2 - Change PIN");
			System.out.println("\t3 - Deposit Money");
			System.out.println("\t4 - Withdraw Money");
			System.out.println("\t5 - Exit");
			System.out.println("-----------------------------------------");
			System.out.println("\nPlease select an option from the menu above(1-5):");
			Scanner menu_option_input = new Scanner(System.in);
			int menu_option=menu_option_input.nextInt();
			if(menu_option>=1 && menu_option<=5) 
				{validatemenu(menu_option);}
			else {System.out.println("\nInvalid Input.....Please try again.");
			menu_valid=true;
		}
		
		}
		catch(Exception e) {
			System.out.println("\nInvalid Input.....Please try again.");
			menu_valid=true;
		}
		}
	}
	
	public static void validatemenu(int option_no) {
		switch(option_no) {
		case 1:
			viewStatement();
			break;		
		case 2:
			changePin();
			break;
		case 3:
			deposit();
			break;
		case 4:
			withdraw();
			break;
		case 5:
			exit();
			break;
		default:
			System.out.println("Invalid Option"); 
		}	
	}
	public static void viewStatement() {
		System.out.println("\n\n");
		System.out.println("******************************************************");
		System.out.println("*                  Account Details                 *");
		System.out.println("******************************************************");
		System.out.println("Cust \tName \t\tBalance \tOverdraft ");
		System.out.println("------------------------------------------------------");
		System.out.print(customer_no +  "\t"+ username[customer_no] + "\t\t€ "+ String.format("%.2f",balance[customer_no]) +"\t"+ overdraft[customer_no] +"\n");  	
		System.out.println("------------------------------------------------------");
		continueMenu();
	}
	public static void changePin() {
		System.out.println("\n\n");
		System.out.println("******************************************************");
		System.out.println("Enter the new PIN");
		Scanner new_pin_input = new Scanner(System.in);
		String new_pin=new_pin_input.next();
		 if (new_pin.matches("[0-9]+") && new_pin.length() == 
				 4  ) {
			 if(!new_pin.equals(user_pin[customer_no])){
				 user_pin[customer_no]=new_pin;
				 System.out.println("\nPIN has been changed successfully\n");				 
				 System.out.println("******************************************************");
			 }
			 else {
				 System.out.println("Cannot use the old PIN..Please Try again");
				 changePin();
			 }			 
		 }
		 else {
			 System.out.println("Invalid input...Please try again.");
			 changePin();
		 }
		continueMenu();
	}
	
	public static void deposit() {	
		try {
		System.out.println("******************************************************");
		System.out.println("How much money are you depositing?");
		Scanner deposit_amnt = new Scanner(System.in);
		double deposit_amt=deposit_amnt.nextDouble();
			if(deposit_amt>0) {
				balance[customer_no]=balance[customer_no]+deposit_amt;
				System.out.println("\nYou have deposited €"+ deposit_amt);
				System.out.println("Your new balance is €"+String.format("%.2f",balance[customer_no]));	
				System.out.println("******************************************************");
			}
			else {
				System.out.println("\nPlease enter a valid amount.Try again\n");
				deposit();
			}
		}
	
		catch(Exception e){
			System.out.println("\nPlease enter amount in figures.\n");
			deposit();
		}
		
		continueMenu();
	}
	public static void withdraw() {
		try {
		System.out.println("\n******************************************************");
		System.out.println("Please enter the amount you wish to withdraw:");
		Scanner wdraw_amnt = new Scanner(System.in);
		double withdraw_amt=wdraw_amnt.nextDouble();
		if(withdraw_amt>0 && (withdraw_amt%10==0)) {
			if(withdraw_amt>balance[customer_no]) {
				if(overdraft[customer_no].equals("Yes")){
					balance[customer_no]=balance[customer_no]-withdraw_amt;					
					System.out.println("\nYou have withdrawn €"+ String.format("%.2f", withdraw_amt));
					System.out.println("Your balance is now €" + String.format("%.2f", balance[customer_no]));
					System.out.println("Do not forget to take the money!");
					System.out.println("******************************************************");
				}
				else {
					System.out.println("\nInsufficient Balance. Please enter smaller amount.");
					withdraw();
				}
			}
			else {
				balance[customer_no]=balance[customer_no]-withdraw_amt;
				System.out.println("\nYou have withdrawn €"+ String.format("%.2f", withdraw_amt));
				System.out.println("Your balance is now €" + String.format("%.2f", balance[customer_no]));
				System.out.println("Do not forget to take the money!");
			}
		}
		else {
			System.out.println("\nPlease enter a valid amount.(e.g-10/50/100/200)");
			withdraw();
		}
			
		}
		catch(Exception e) {
			System.out.println("Please enter amount in figures.");
			withdraw();
		}
		
		continueMenu();	
	}
	
	public static void exit() {
		System.out.println("\n\tThank you for banking with us!!");
		System.out.println("\n\n");
		welcome();
	}
	
	public static void continueMenu() {
		System.out.println("\nDo you want to continue(Y/N)?");
		Scanner result = new Scanner(System.in);
		String cont_result=result.next();
		String yes_string="Y";
		String no_string="N";
		if (yes_string.equalsIgnoreCase(cont_result)) {
			displaymenu();
		}
		if(no_string.equalsIgnoreCase(cont_result)) {
			exit();
		}
	}
	
	
	
	
}
