/**
 * Created by HOME on 2018-11-07.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by HOME on 2018-11-07.
 */
public class Todo {
   
   static Vector<Todolists_Info> todo_vector;
    public static final String Todolists = "Todolists.txt";
    static int choice;
    static Scanner scanner = new Scanner(System.in);
    public static void main() {

        todo_vector = new Vector<Todolists_Info>();

        do {
            Todolist_menu();
            Print_menu();
            switch (choice) {
                case 1:
                    Todolist_create();
                    break;
                case 2:
                	Todolist_print();

                    System.out.print("Enter the number of the list you want to view: ");
                    int tmp = Integer.parseInt(scanner.nextLine());
                    Todolist_view(tmp,todo_vector);
                    break;
                case 3:
                    Todolist_update();
                    break;
                case 4:
                	
                    Todolist_delete();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("wrong input");
            }
        }while(choice!=5);
    }
    public static void Print_menu(){
    	 System.out.println("<<Todo Management Menu>>");
         System.out.println("1. CREATE");
         System.out.println("2. VIEW");
         System.out.println("3. UPDATE");
         System.out.println("4. DELETE");
         System.out.println("5. EXIT");
         System.out.print("Input: ");
         String tmp = scanner.nextLine();
         choice = Integer.parseInt(tmp);
  }
    private static void Todolist_menu(){
        try {
            File file = new File(Todolists);
            if(!file.exists()) {
            	file.createNewFile();
            }
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            todo_vector.clear();
            while((line = bufferedReader.readLine())!=null){
                int loc1 = line.indexOf('%');
                int loc2 = line.substring(loc1+1,line.length()).indexOf('%')+loc1+1;
                int loc3 = line.substring(loc2+1, line.length()).indexOf('%')+loc2+1;
                Todolists_Info tmp = new Todolists_Info(line.substring(0,loc1),line.substring(loc1+1,loc2),line.substring(loc2+1,loc3),line.substring(loc3+1, line.length()));
                todo_vector.add(tmp);
            }
            fileReader.close();
            bufferedReader.close();
        }catch(FileNotFoundException e){System.out.println("ERROR1");}catch(IOException e){System.out.println("ERROR2");}

       
    }
    private static void Todolist_delete() {

        Todolist_print();
        System.out.print("Please enter the number of the list to delete: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println("===================================================================================");
        System.out.println(String.format("%-10s","number")+String.format("%-15s","Title")+String.format("%-20s","Creation Date")+String.format("%-20s","Due Date")+String.format("%-40s","Discription"));
        System.out.println("===================================================================================");
        System.out.println(String.format("%-10s","["+choice+"]")+String.format("%-20s", todo_vector.elementAt(choice-1).title)+String.format("%-20s",todo_vector.elementAt(choice-1).createDate)+String.format("%-20s",todo_vector.elementAt(choice-1).dueDate)+String.format("%-40s",todo_vector.elementAt(choice-1).description));

        System.out.print("Do you really want to delete your list? (y/n): ");
        String delete_choice = scanner.nextLine();
        if(delete_choice.equals("y")) {
            todo_vector.remove(choice-1);
            System.out.println("Deleted successfully!");
        }
        else if(delete_choice.equals("n")) {
            System.out.println("Deletion canceled");
        }
        else {
            System.out.println("Wrong input");
        }
        Todolist_write();

    }

    private static void Todolist_create(){
       Calendar cal = Calendar.getInstance();
       int year = cal.get(cal.YEAR);
       int month = cal.get(cal.MONTH)+1;
       int date = cal.get(cal.DATE);
       
        Todolists_Info tmp = new Todolists_Info("","","","");
        System.out.print("Todo title: ");
        tmp.title = scanner.nextLine();
        System.out.print("Due date(xxxx/xx/xx): ");
        tmp.dueDate = scanner.nextLine();
        System.out.print("Description: ");
        tmp.description = scanner.nextLine();
        tmp.createDate = year+"/"+month+"/"+date;
        
        todo_vector.add(tmp);
            

        Todolist_write();
        System.out.println("Successfully created!");
    }

    public static String Todolist_view(int tmp, Vector<Todolists_Info> todo_vector){
    	String bindAnswer = String.format("%-10s","["+tmp+"]")
        		+String.format("%-20s",todo_vector.elementAt(tmp-1).title)
        		+String.format("%-20s",todo_vector.elementAt(tmp-1).createDate)
        		+String.format("%-20s",todo_vector.elementAt(tmp-1).dueDate)
        		+String.format("%-40s", todo_vector.elementAt(tmp-1).description);
        
        System.out.println("===================================================================================");
        System.out.println(String.format("%-10s","number")+String.format("%-15s","Title")+String.format("%-20s","Creation Date")+String.format("%-20s","Due Date")+String.format("%-40s","Discription"));
        System.out.println("===================================================================================");
        System.out.println(bindAnswer);
        
        return bindAnswer;
    }

    private static void Todolist_update() {

        Todolist_print();

        System.out.print("Enter the number of list you want to update: ");
        int choice = scanner.nextInt();
        System.out.println("===================================================================================");
        System.out.println(String.format("%-10s","number")+String.format("%-15s","Title")+String.format("%-20s","Creation Date")+String.format("%-20s","Due Date")+String.format("%-40s","Discription"));
        System.out.println("===================================================================================");
        System.out.println(String.format("%-10s","["+choice+"]")+String.format("%-20s", todo_vector.elementAt(choice-1).title)+String.format("%-20s",todo_vector.elementAt(choice-1).createDate)+String.format("%-20s",todo_vector.elementAt(choice-1).dueDate)+String.format("%-40s",todo_vector.elementAt(choice-1).description));

        scanner.nextLine();
        
        Todolists_Info tmp = todo_vector.elementAt(choice-1);
        
        System.out.println("The input for the update data");
        System.out.print("Title: ");
        tmp.title = scanner.nextLine();
        System.out.print("Due Date: ");
        tmp.dueDate = scanner.nextLine();
        System.out.print("Description: ");
        tmp.description = scanner.nextLine();

        Todolist_write();
        System.out.println("Successfully updated");
    }


    private static void Todolist_write() {
        try {
            File file = new File(Todolists);
            FileWriter fileWriter = new FileWriter(file);
            for (int i = 0; i < todo_vector.size(); i++) {
                fileWriter.write(todo_vector.elementAt(i).title + "%" + todo_vector.elementAt(i).createDate + "%" + todo_vector.elementAt(i).dueDate+"%"+todo_vector.elementAt(i).description);
                fileWriter.write("\r\n", 0, 2);
            }
            fileWriter.close();
        }catch (IOException e){}
    }
    private static void Todolist_print() {
        System.out.println("=================================");
        System.out.println(String.format("%-10s","number")+String.format("%-10s","Title"));
        System.out.println("=================================");

        for(int i=0;i<todo_vector.size();i++){
            System.out.println(String.format("%-10s","["+(i+1)+"]")+String.format("%-20s",todo_vector.elementAt(i).title));
        }
        System.out.println("=================================");
    }
}

class Todolists_Info {
    String  title, createDate, dueDate, description;
    Todolists_Info(String title, String createDate, String dueDate, String description) {
       this.title = title;
        this.createDate = createDate;
        this.dueDate = dueDate;
        this.description = description;
    }
   
}
