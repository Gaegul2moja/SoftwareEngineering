import java.io.*;
import java.util.*;

/**
 * Created by HOME on 2018-11-07.
 */
public class Contacts {
	static Vector<Contacts_Info> con_vector;
	public static final String Contacts = "Contacts.txt";
	static int choice;
	static Scanner scanner = new Scanner(System.in);
	public static void main() {

		con_vector = new Vector<Contacts_Info>();

		do {
			Contact_menu(Contacts,con_vector);
			Print_menu();
			switch (choice) {
			case 1:
				Contact_create();
				break;
			case 2:
				Contact_view();
				break;
			case 3:
				Contact_update();
				break;
			case 4:
				Contact_delete();
				break;
			case 5:
				break;
			default:
				System.out.println("Wrong input value.");
			}
		}while(choice!=5);
	}
	public static void Print_menu(){
		System.out.println("<<Contacts Manage Menu>>");
		System.out.println("1. CREATE");
		System.out.println("2. VIEW");
		System.out.println("3. UPDATE");
		System.out.println("4. DELETE");
		System.out.println("5. EXIT");
		System.out.print("INPUT: ");
		String tmp = scanner.nextLine();
		choice = Integer.parseInt(tmp);
	}
	public static boolean Contact_menu(String Contacts,Vector<Contacts_Info> con_vector){
		boolean check=false;
		try {
			File file = new File(Contacts);
			if(!file.exists()) {
				file.createNewFile();
			}
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = "";
			con_vector.clear();
			while((line = bufferedReader.readLine())!=null){
				int loc1 = line.indexOf('%');
				int loc2 = line.substring(loc1+1,line.length()).indexOf('%')+loc1+1;
				Contacts_Info tmp = new Contacts_Info(line.substring(0,loc1),line.substring(loc1+1,loc2),line.substring(loc2+1,line.length()));
				con_vector.add(tmp);
			}
			check=true;
			fileReader.close();
			bufferedReader.close();
		}catch(FileNotFoundException e){System.out.println("ERROR1");}catch(IOException e){System.out.println("ERROR2");}

		return check;
	}
	private static void Contact_delete() {

		Contact_print();
		System.out.print("Enter the number of contact to delete: ");
		int choice = scanner.nextInt();
		scanner.nextLine();
		System.out.println("====================================================");
		System.out.println(String.format("%3s","Number")+String.format("%20s","Name")+String.format("%20s","Phone-number")+String.format("%25s","E-mail"));
		System.out.println("====================================================");
		System.out.println(String.format("%3s","["+choice+"]")+String.format("%10s",con_vector.elementAt(choice-1).name)+String.format("%20s",con_vector.elementAt(choice-1).phone)+String.format("%25s",con_vector.elementAt(choice-1).email));

		System.out.print("Do you really want to delete?(y/n): ");
		String delete_choice = scanner.nextLine();
		Contact_deleteInput(delete_choice,con_vector,choice);
		Contact_write();

	}
	public static int Contact_deleteInput(String delete_choice, Vector<Contacts_Info> con_vector,int choice){

		if(delete_choice.equals("y")) {
			con_vector.remove(choice-1);
			System.out.println("Successfully deleted.");
		}
		else if(delete_choice.equals("n")) {
			System.out.println("Canceled deleting.");
		}
		else {
			System.out.println("Wrong input value.");
		}
		return con_vector.size();
	}
	private static void Contact_create(){

		Contacts_Info tmp = new Contacts_Info("","","");
		System.out.println("Enter specific data values ( name, phone-number, email)");
		System.out.print("Name: ");
		tmp.name = scanner.nextLine();
		System.out.print("Phone number: ");
		tmp.phone = scanner.nextLine();
		System.out.print("E-mail: ");
		tmp.email = scanner.nextLine();

		con_vector.add(tmp);

		Contact_write();
		System.out.println("Successfully created.");
	}

	private static void Contact_view(){

		Contact_print();

		System.out.print("Enter Contact number to view specifically: ");
		int tmp = Integer.parseInt(scanner.nextLine());
		System.out.println("====================================================");
		System.out.println(String.format("%3s","Number")+String.format("%20s","Name")+String.format("%20s","Phone-number")+String.format("%25s","E-mail"));
		System.out.println("====================================================");
		System.out.println(String.format("%3s","["+tmp+"]")+String.format("%10s",con_vector.elementAt(tmp-1).name)+String.format("%20s",con_vector.elementAt(tmp-1).phone)+String.format("%25s",con_vector.elementAt(tmp-1).email));
	}

	private static void Contact_update() {

		Contact_print();

		System.out.print("Enter Contact number to update: ");
		int choice = scanner.nextInt();

		System.out.println("====================================================");
		System.out.println(String.format("%3s","Number")+String.format("%20s","Name")+String.format("%20s","Phone-number")+String.format("%25s","E-mail"));
		System.out.println("====================================================");
		System.out.println(String.format("%3s","["+choice+"]")+String.format("%10s",con_vector.elementAt(choice-1).name)+String.format("%20s",con_vector.elementAt(choice-1).phone)+String.format("%25s",con_vector.elementAt(choice-1).email));
		scanner.nextLine();


		Contacts_Info tmp = con_vector.elementAt(choice-1);

		System.out.println("Enter Specific data to update.");
		System.out.print("Name: ");
		tmp.name = scanner.nextLine();
		System.out.print("Phone-number: ");
		tmp.phone = scanner.nextLine();
		System.out.print("E-mail: ");
		tmp.email = scanner.nextLine();

		Contact_write();
		System.out.println("Successfully Updated.");

	}


	private static void Contact_write() {
		try {
			File file = new File(Contacts);
			FileWriter fileWriter = new FileWriter(file);
			for (int i = 0; i < con_vector.size(); i++) {
				fileWriter.write(con_vector.elementAt(i).name + "%" + con_vector.elementAt(i).phone + "%" + con_vector.elementAt(i).email );
				fileWriter.write("\r\n", 0, 2);
			}
			fileWriter.close();
		}catch (IOException e){}
	}
	private static void Contact_print() {
		System.out.println("=================================");
		System.out.println(String.format("%3s","Number")+String.format("%20s","Name"));
		System.out.println("=================================");
		for(int i=0;i<con_vector.size();i++){
			System.out.println(String.format("%3s","["+(i+1)+"]")+String.format("%10s",con_vector.elementAt(i).name));
		}
		System.out.println("=================================");
	}
}

class Contacts_Info {
	String  name, phone, email;
	Contacts_Info(String name, String phone, String email) {
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
}