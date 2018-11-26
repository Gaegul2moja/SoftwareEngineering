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
                    System.out.println("잘못된 입력 값 입니다.");
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

        System.out.println("<<약속 관리 메뉴>>");
        System.out.println("1. CREATE");
        System.out.println("2. VIEW");
        System.out.println("3. UPDATE");
        System.out.println("4. DELETE");
        System.out.println("5. EXIT");
        System.out.print("입력: ");
        String tmp = scanner.nextLine();
        choice = Integer.parseInt(tmp);
      
    }
    private static void Appointment_delete() {

    	Appointment_print();
        System.out.print("삭제할 약속의 번호를 입력하세요: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println("====================================================");
        System.out.println(String.format("%3s","번호")+String.format("%10s","제목")+String.format("%20s","날짜")+String.format("%27s","위치")+String.format("%30s", "사람"));
        System.out.println("====================================================");
        System.out.println(String.format("%3s","["+choice+"]")
        		+String.format("%6s",appt_vector.elementAt(choice-1).title)
        		+String.format("%20s",appt_vector.elementAt(choice-1).date)
        		+String.format("%27s",appt_vector.elementAt(choice-1).location)
        		+String.format("%30s", appt_vector.elementAt(choice-1).person));

        System.out.print("정말로 삭제하시겠습니까?(y/n): ");
        String delete_choice = scanner.nextLine();
        if(delete_choice.equals("y")) {
            appt_vector.remove(choice-1);
            System.out.println("성공적으로 삭제되었습니다");
        }
        else if(delete_choice.equals("n")) {
            System.out.println("삭제 작업을 취소하였습니다.");
        }
        else {
            System.out.println("잘못된 입력값 입니다.");
        }
        Appointment_write();

    }

    private static void Appointment_create(){
    		
            Appointment_Info tmp = new Appointment_Info("","","","");
            System.out.println("세부 데이터를 입력하세요( title, date, location,person)");
            System.out.print("제목: ");
            tmp.title = scanner.nextLine();
            System.out.print("날짜: ");
            tmp.date = scanner.nextLine();
            System.out.print("위치: ");
            tmp.location = scanner.nextLine();
            System.out.print("사람: ");
            tmp.person = scanner.nextLine();
            

            appt_vector.add(tmp);
            Appointment_write();
            System.out.println("성공적으로 생성되었습니다.");
    }

    private static void Appointment_view(){

    	Appointment_print();

       
        System.out.print("자세히 보고 싶은 약속의 번호를 입력해주세요: ");
        int tmp = Integer.parseInt(scanner.nextLine()); //사용자의 선택
        System.out.println("====================================================");
        System.out.println(String.format("%3s","번호")+String.format("%10s","제목")+String.format("%20s","날짜")+String.format("%27s","위치")+String.format("%30s", "사람"));
        System.out.println("====================================================");
        System.out.println(String.format("%3s","["+tmp+"]")
        		+String.format("%6s",appt_vector.elementAt(tmp-1).title)
        		+String.format("%20s",appt_vector.elementAt(tmp-1).date)
        		+String.format("%27s",appt_vector.elementAt(tmp-1).location)
        		+String.format("%30s", appt_vector.elementAt(tmp-1).person));
    }

    private static void Appointment_update() {

    	Appointment_print();

       
        System.out.print("수정할 약속의 번호를 입력하세요: ");
        int choice = scanner.nextInt(); 
        System.out.println("====================================================");
        System.out.println(String.format("%3s","번호")+String.format("%10s","제목")+String.format("%20s","날짜")+String.format("%27s","위치")+String.format("%30s", "사람"));
        System.out.println("====================================================");
        System.out.println(String.format("%3s","["+choice+"]")
        		+String.format("%6s",appt_vector.elementAt(choice-1).title)
        		+String.format("%20s",appt_vector.elementAt(choice-1).date)
        		+String.format("%27s",appt_vector.elementAt(choice-1).location)
        		+String.format("%30s", appt_vector.elementAt(choice-1).person));
        scanner.nextLine();
        
        
        Appointment_Info tmp = appt_vector.elementAt(choice-1);
        
        System.out.println("수정 할 연락처의 세부 데이터를 입력 해 주세요.");
        System.out.print("제목: ");
        tmp.title = scanner.nextLine();
        System.out.print("날짜: ");
        tmp.date = scanner.nextLine();
        System.out.print("위치: ");
        tmp.location = scanner.nextLine();
        System.out.print("사람: ");
        tmp.person = scanner.nextLine();


        Appointment_write();
        System.out.println("성공적으로 수정되었습니다.");

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
        System.out.println(String.format("%3s","번호")+String.format("%10s","제목")+String.format("%10s", "날짜"));
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