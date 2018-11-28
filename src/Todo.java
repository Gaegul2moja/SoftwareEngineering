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
            switch (choice) {
                case 1:
                    Todolist_create();
                    break;
                case 2:
                    Todolist_view();
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
                    System.out.println("�߸��� �Է� �� �Դϴ�.");
            }
        }while(choice!=5);
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
                //System.out.println(con_vector.elementAt(0).name+" "+con_vector.elementAt(0).phone+" "+con_vector.elementAt(0).email);
            }
            fileReader.close();
            bufferedReader.close();
        }catch(FileNotFoundException e){System.out.println("ERROR1");}catch(IOException e){System.out.println("ERROR2");}
        //��ü ����Ʈ ����

       // Scanner scanner = new Scanner(System.in);
        System.out.println("<<�� �� ���� �޴�>>");
        System.out.println("1. CREATE");
        System.out.println("2. VIEW");
        System.out.println("3. UPDATE");
        System.out.println("4. DELETE");
        System.out.println("5. EXIT");
        System.out.print("�Է�: ");
        String tmp = scanner.nextLine();
        choice = Integer.parseInt(tmp);
      //  scanner.close();
    }
    private static void Todolist_delete() {

        Todolist_print();
       // Scanner scanner = new Scanner(System.in);
        System.out.print("������ ����ó�� ��ȣ�� �Է��ϼ���: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println("====================================================");
        System.out.println(String.format("%3s","��ȣ")+String.format("%20s","�� �� ����")+String.format("%20s","���� ��¥")+String.format("%20s","���� ����")+String.format("%40s","����"));
        System.out.println("====================================================");
        System.out.println(String.format("%3s","["+choice+"]")+String.format("%20s", todo_vector.elementAt(choice-1).title)+String.format("%20s",todo_vector.elementAt(choice-1).createDate)+String.format("%20s",todo_vector.elementAt(choice-1).dueDate)+String.format("%40s",todo_vector.elementAt(choice-1).description));

        System.out.print("������ �����Ͻðڽ��ϱ�?(y/n): ");
        String delete_choice = scanner.nextLine();
        if(delete_choice.equals("y")) {
            todo_vector.remove(choice-1);
            System.out.println("���������� �����Ǿ����ϴ�");
        }
        else if(delete_choice.equals("n")) {
            System.out.println("���� �۾��� ����Ͽ����ϴ�.");
        }
        else {
            System.out.println("�߸��� �Է°� �Դϴ�.");
        }
        Todolist_write();

    }

    private static void Todolist_create(){
       Calendar cal = Calendar.getInstance();
       int year = cal.get(cal.YEAR);
       int month = cal.get(cal.MONTH)+1;
       int date = cal.get(cal.DATE);
       
        Todolists_Info tmp = new Todolists_Info("","","","");
        System.out.print("������ ���ÿ�: ");
        tmp.title = scanner.nextLine();
        System.out.print("���� ������ �Է����ּ���(xxxx/xx/xx): ");
        tmp.dueDate = scanner.nextLine();
        System.out.print("����: ");
        tmp.description = scanner.nextLine();
        tmp.createDate = year+"/"+month+"/"+date;
        
        todo_vector.add(tmp);
            

        Todolist_write();
        System.out.println("���������� �����Ǿ����ϴ�.");
    }

    private static void Todolist_view(){

        Todolist_print();

        //Scanner scanner = new Scanner(System.in);
        System.out.print("�ڼ��� ���� ���� ����ó�� ��ȣ�� �Է����ּ���: ");
        int tmp = Integer.parseInt(scanner.nextLine()); //������� ����
        System.out.println("====================================================");
        System.out.println(String.format("%3s","��ȣ")+String.format("%10s","�� �� ����")+String.format("%20s","���� ��¥")+String.format("%20s","���� ����")+String.format("%40s", "����"));
        System.out.println("====================================================");
        System.out.println(String.format("%3s","["+tmp+"]")+String.format("%10s",todo_vector.elementAt(tmp-1).title)+String.format("%20s",todo_vector.elementAt(tmp-1).createDate)+String.format("%20s",todo_vector.elementAt(tmp-1).dueDate)+String.format("%40s", todo_vector.elementAt(tmp-1).description));
        //������ ����Ʈ�� �ڼ��� ������ ���
       // scanner.close();
    }

    private static void Todolist_update() {

        Todolist_print();

       // Scanner scanner = new Scanner(System.in);
        System.out.print("������ �� ���� ��ȣ�� �Է��ϼ���: ");
        int choice = scanner.nextInt(); //������� ����
        System.out.println("====================================================");
        System.out.println(String.format("%3s","��ȣ")+String.format("%10s","�� �� ����")+String.format("%20s","���� ��¥")+String.format("%20s","���� ����")+String.format("%40s","����"));
        System.out.println("====================================================");
        System.out.println(String.format("%3s","["+choice+"]")+String.format("%20s", todo_vector.elementAt(choice-1).title)+String.format("%10s",todo_vector.elementAt(choice-1).createDate)+String.format("%20s",todo_vector.elementAt(choice-1).dueDate)+String.format("%40s",todo_vector.elementAt(choice-1).description));

        scanner.nextLine();
        
        Todolists_Info tmp = todo_vector.elementAt(choice-1);
        
        System.out.println("������ �� ���� ���� �����͸� �Է� �� �ּ���.");
        System.out.print("����: ");
        tmp.title = scanner.nextLine();
        System.out.print("���� ����: ");
        tmp.dueDate = scanner.nextLine();
        System.out.print("����: ");
        tmp.description = scanner.nextLine();
      //  scanner.close();

        Todolist_write();
        System.out.println("���������� �����Ǿ����ϴ�.");
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
        System.out.println(String.format("%3s","��ȣ")+String.format("%10s","����"));
        System.out.println("=================================");
       // System.out.println(con_vector.elementAt(1).name);
        for(int i=0;i<todo_vector.size();i++){
            System.out.println(String.format("%3s","["+(i+1)+"]")+String.format("%10s",todo_vector.elementAt(i).title));
        }
        System.out.println("=================================");
        //������ ��ü ����Ʈ ���
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
