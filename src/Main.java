/**
 * Created by HOME on 2018-11-10.
 */

import java.util.*;
public class Main {
	static Scanner scanner = new Scanner(System.in);
	static int choice;
	public static void main(String[] args){
		do{
		Main_menu();
			switch(choice){
			case 1:
				Contacts.main();
				break;
			case 2:
				Todo.main();
				break;
			case 3:
				Appointment.main();
				break;
			case 4:
				break;
			default:
                System.out.println("Wrong input");
			
			}
		}while(choice!=4);
		
	}
	
	public static void Main_menu(){
		System.out.println("<<Main menu>>");
		System.out.println("1. Contacts");
		System.out.println("2. To-do list ");
		System.out.println("3. Appointment");
		System.out.println("4. Exit");
		System.out.print("input: ");
		
        String tmp = scanner.nextLine();
        choice = Integer.parseInt(tmp);
		
		
	}
}
