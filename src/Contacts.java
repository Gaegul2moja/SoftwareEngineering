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
            Contact_menu();
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
                    System.out.println("잘못된 입력 값 입니다.");
            }
        }while(choice!=5);
    }
    private static void Contact_menu(){
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

            fileReader.close();
            bufferedReader.close();
        }catch(FileNotFoundException e){System.out.println("ERROR1");}catch(IOException e){System.out.println("ERROR2");}
        //전체 리스트 읽음

        System.out.println("<<연락처 관리 메뉴>>");
        System.out.println("1. CREATE");
        System.out.println("2. VIEW");
        System.out.println("3. UPDATE");
        System.out.println("4. DELETE");
        System.out.println("5. EXIT");
        System.out.print("입력: ");
        String tmp = scanner.nextLine();
        choice = Integer.parseInt(tmp);
    }
    private static void Contact_delete() {

        Contact_print();
        System.out.print("삭제할 연락처의 번호를 입력하세요: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println("====================================================");
        System.out.println(String.format("%3s","번호")+String.format("%20s","이름")+String.format("%20s","핸드폰번호")+String.format("%25s","이메일"));
        System.out.println("====================================================");
        System.out.println(String.format("%3s","["+choice+"]")+String.format("%10s",con_vector.elementAt(choice-1).name)+String.format("%20s",con_vector.elementAt(choice-1).phone)+String.format("%25s",con_vector.elementAt(choice-1).email));

        System.out.print("정말로 삭제하시겠습니까?(y/n): ");
        String delete_choice = scanner.nextLine();
        if(delete_choice.equals("y")) {
            con_vector.remove(choice-1);
            System.out.println("성공적으로 삭제되었습니다");
        }
        else if(delete_choice.equals("n")) {
            System.out.println("삭제 작업을 취소하였습니다.");
        }
        else {
            System.out.println("잘못된 입력값 입니다.");
        }
        Contact_write();

    }

    private static void Contact_create(){
    		
            Contacts_Info tmp = new Contacts_Info("","","");
            System.out.println("세부 데이터를 입력하세요( name, phone-number, email)");
            System.out.print("이름: ");
            tmp.name = scanner.nextLine();
            System.out.print("핸드폰 번호: ");
            tmp.phone = scanner.nextLine();
            System.out.print("이메일: ");
            tmp.email = scanner.nextLine();
            
            //추가할 연락처를 입력받는다
            con_vector.add(tmp);
            

            Contact_write();
        System.out.println("성공적으로 생성되었습니다.");
    }

    private static void Contact_view(){

        Contact_print();

        System.out.print("자세히 보고 싶은 연락처의 번호를 입력해주세요: ");
        int tmp = Integer.parseInt(scanner.nextLine()); //사용자의 선택
        System.out.println("====================================================");
        System.out.println(String.format("%3s","번호")+String.format("%20s","이름")+String.format("%20s","핸드폰번호")+String.format("%25s","이메일"));
        System.out.println("====================================================");
        System.out.println(String.format("%3s","["+tmp+"]")+String.format("%10s",con_vector.elementAt(tmp-1).name)+String.format("%20s",con_vector.elementAt(tmp-1).phone)+String.format("%25s",con_vector.elementAt(tmp-1).email));
        //선택한 리스트의 자세한 데이터 출력
    }

    private static void Contact_update() {

        Contact_print();

        System.out.print("수정할 연락처의 번호를 입력하세요: ");
        int choice = scanner.nextInt(); //사용자의 선택
        System.out.println("====================================================");
        System.out.println(String.format("%3s","번호")+String.format("%20s","이름")+String.format("%20s","핸드폰번호")+String.format("%25s","이메일"));
        System.out.println("====================================================");
        System.out.println(String.format("%3s","["+choice+"]")+String.format("%10s",con_vector.elementAt(choice-1).name)+String.format("%20s",con_vector.elementAt(choice-1).phone)+String.format("%25s",con_vector.elementAt(choice-1).email));
        scanner.nextLine();
        
        
        Contacts_Info tmp = con_vector.elementAt(choice-1);
        
        System.out.println("수정 할 연락처의 세부 데이터를 입력 해 주세요.");
        System.out.print("이름: ");
        tmp.name = scanner.nextLine();
        System.out.print("핸드폰 번호: ");
        tmp.phone = scanner.nextLine();
        System.out.print("이메일: ");
        tmp.email = scanner.nextLine();

        Contact_write();
        System.out.println("성공적으로 수정되었습니다.");

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
        System.out.println(String.format("%3s","번호")+String.format("%20s","이름"));
        System.out.println("=================================");
        for(int i=0;i<con_vector.size();i++){
            System.out.println(String.format("%3s","["+(i+1)+"]")+String.format("%10s",con_vector.elementAt(i).name));
        }
        System.out.println("=================================");
        //간략한 전체 리스트 출력
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