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
				//Todo.main();
				break;
			case 3:
//				Appointment.main();
				break;
			case 4:
				scanner.close();
				break;
			default:
                System.out.println("�߸��� �Է� �� �Դϴ�.");
			
			}
		}while(choice!=4);
		
	}
	
	public static void Main_menu(){
		System.out.println("<<���� �޴�>>");
		System.out.println("1. ����ó ����");
		System.out.println("2. to-do list ����");
		System.out.println("3. ��� ����");
		System.out.println("4. ����");
		System.out.print("�Է�: ");
		
        String tmp = scanner.nextLine();
        choice = Integer.parseInt(tmp);
		
		
	}
}
