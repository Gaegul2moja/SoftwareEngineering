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
				scanner.close();
				break;
			default:
                System.out.println("잘못된 입력 값 입니다.");
			
			}
		}while(choice!=4);
		
	}
	
	public static void Main_menu(){
		System.out.println("<<메인 메뉴>>");
		System.out.println("1. 연락처 관리");
		System.out.println("2. to-do list 관리");
		System.out.println("3. 약속 관리");
		System.out.println("4. 종료");
		System.out.print("입력: ");
		
        String tmp = scanner.nextLine();
        choice = Integer.parseInt(tmp);
		
		
	}
}
