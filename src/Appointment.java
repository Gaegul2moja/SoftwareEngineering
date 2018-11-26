import java.io.*;
import java.util.*;

/**
 * Created by HOME on 2018-11-07.
 */
public class Appointment {
    static Vector<Appointment_Info> appt_vector;
    public static final String Appointment = "Appointments.txt";
    static int choice;
    static Scanner scanner = new Scanner(System.in);
    public static void main() {

        appt_vector = new Vector<Appointment_Info>();

        do {
            Appointment_menu();
            switch (choice) {
                case 1:
                	Appointment_create();
                    break;
                case 2:
                	Appointment_view();
                    break;
                case 3:
                	Appointment_update();
                    break;
                case 4:
                	Appointment_delete();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("�߸��� �Է� �� �Դϴ�.");
            }
        }while(choice!=5);
    }
    private static void Appointment_menu(){
        try {
            File file = new File(Appointment);
            if(!file.exists()) {
            	file.createNewFile();
            }
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            appt_vector.clear();
            while((line = bufferedReader.readLine())!=null){
                int loc1 = line.indexOf('%');
                int loc2 = line.substring(loc1+1,line.length()).indexOf('%')+loc1+1;
                int loc3 = line.substring(loc2+1,line.length()).indexOf('%')+loc2+1;
                Appointment_Info tmp = new Appointment_Info(line.substring(0,loc1),line.substring(loc1+1,loc2),line.substring(loc2+1,loc3),line.substring(loc3+1,line.length()));
                appt_vector.add(tmp);
            }

            fileReader.close();
            bufferedReader.close();
        }
        catch(FileNotFoundException e){System.out.println("ERROR1");}
        catch(IOException e){System.out.println("ERROR2");}

        System.out.println("<<��� ���� �޴�>>");
        System.out.println("1. CREATE");
        System.out.println("2. VIEW");
        System.out.println("3. UPDATE");
        System.out.println("4. DELETE");
        System.out.println("5. EXIT");
        System.out.print("�Է�: ");
        String tmp = scanner.nextLine();
        choice = Integer.parseInt(tmp);
      
    }
    private static void Appointment_delete() {

    	Appointment_print();
        System.out.print("������ ����� ��ȣ�� �Է��ϼ���: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println("====================================================");
        System.out.println(String.format("%3s","��ȣ")+String.format("%10s","����")+String.format("%20s","��¥")+String.format("%27s","��ġ")+String.format("%30s", "���"));
        System.out.println("====================================================");
        System.out.println(String.format("%3s","["+choice+"]")
        		+String.format("%6s",appt_vector.elementAt(choice-1).title)
        		+String.format("%20s",appt_vector.elementAt(choice-1).date)
        		+String.format("%27s",appt_vector.elementAt(choice-1).location)
        		+String.format("%30s", appt_vector.elementAt(choice-1).person));

        System.out.print("������ �����Ͻðڽ��ϱ�?(y/n): ");
        String delete_choice = scanner.nextLine();
        if(delete_choice.equals("y")) {
            appt_vector.remove(choice-1);
            System.out.println("���������� �����Ǿ����ϴ�");
        }
        else if(delete_choice.equals("n")) {
            System.out.println("���� �۾��� ����Ͽ����ϴ�.");
        }
        else {
            System.out.println("�߸��� �Է°� �Դϴ�.");
        }
        Appointment_write();

    }

    private static void Appointment_create(){
    		
            Appointment_Info tmp = new Appointment_Info("","","","");
            System.out.println("���� �����͸� �Է��ϼ���( title, date, location,person)");
            System.out.print("����: ");
            tmp.title = scanner.nextLine();
            System.out.print("��¥: ");
            tmp.date = scanner.nextLine();
            System.out.print("��ġ: ");
            tmp.location = scanner.nextLine();
            System.out.print("���: ");
            tmp.person = scanner.nextLine();
            

            appt_vector.add(tmp);
            Appointment_write();
            System.out.println("���������� �����Ǿ����ϴ�.");
    }

    private static void Appointment_view(){

    	Appointment_print();

       
        System.out.print("�ڼ��� ���� ���� ����� ��ȣ�� �Է����ּ���: ");
        int tmp = Integer.parseInt(scanner.nextLine()); //������� ����
        System.out.println("====================================================");
        System.out.println(String.format("%3s","��ȣ")+String.format("%10s","����")+String.format("%20s","��¥")+String.format("%27s","��ġ")+String.format("%30s", "���"));
        System.out.println("====================================================");
        System.out.println(String.format("%3s","["+tmp+"]")
        		+String.format("%6s",appt_vector.elementAt(tmp-1).title)
        		+String.format("%20s",appt_vector.elementAt(tmp-1).date)
        		+String.format("%27s",appt_vector.elementAt(tmp-1).location)
        		+String.format("%30s", appt_vector.elementAt(tmp-1).person));
    }

    private static void Appointment_update() {

    	Appointment_print();

       
        System.out.print("������ ����� ��ȣ�� �Է��ϼ���: ");
        int choice = scanner.nextInt(); 
        System.out.println("====================================================");
        System.out.println(String.format("%3s","��ȣ")+String.format("%10s","����")+String.format("%20s","��¥")+String.format("%27s","��ġ")+String.format("%30s", "���"));
        System.out.println("====================================================");
        System.out.println(String.format("%3s","["+choice+"]")
        		+String.format("%6s",appt_vector.elementAt(choice-1).title)
        		+String.format("%20s",appt_vector.elementAt(choice-1).date)
        		+String.format("%27s",appt_vector.elementAt(choice-1).location)
        		+String.format("%30s", appt_vector.elementAt(choice-1).person));
        scanner.nextLine();
        
        
        Appointment_Info tmp = appt_vector.elementAt(choice-1);
        
        System.out.println("���� �� ����ó�� ���� �����͸� �Է� �� �ּ���.");
        System.out.print("����: ");
        tmp.title = scanner.nextLine();
        System.out.print("��¥: ");
        tmp.date = scanner.nextLine();
        System.out.print("��ġ: ");
        tmp.location = scanner.nextLine();
        System.out.print("���: ");
        tmp.person = scanner.nextLine();


        Appointment_write();
        System.out.println("���������� �����Ǿ����ϴ�.");

    }


    private static void Appointment_write() {
        try {
            File file = new File(Appointment);
            FileWriter fileWriter = new FileWriter(file);
            for (int i = 0; i < appt_vector.size(); i++) {
                fileWriter.write(appt_vector.elementAt(i).title + "%" + appt_vector.elementAt(i).date + "%" + appt_vector.elementAt(i).location+ "%" +appt_vector.elementAt(i).person );
                fileWriter.write("\r\n", 0, 2);
            }
            fileWriter.close();
        }catch (IOException e){}
    }
    private static void Appointment_print() {
        System.out.println("=================================");
        System.out.println(String.format("%3s","��ȣ")+String.format("%10s","����")+String.format("%10s", "��¥"));
        System.out.println("=================================");
        for(int i=0;i<appt_vector.size();i++){
            System.out.println(String.format("%3s","["+(i+1)+"]")+String.format("%10s",appt_vector.elementAt(i).title)+String.format("%10s",appt_vector.elementAt(i).date));
        }
        System.out.println("=================================");
    }
}

class Appointment_Info {
    String  title, date, location,person;
    Appointment_Info(String title, String date, String location,String person) {
        this.title = title;
        this.date = date;
        this.location = location;
        this.person = person;
    }
}