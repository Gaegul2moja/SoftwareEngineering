import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

import org.junit.jupiter.api.*;

public class MainTest {
	
	@Test
	void test_Contacts_create(){
		Contacts_Info aaaa = new Contacts_Info("","","");
		Vector<Contacts_Info> con_vector = new Vector<Contacts_Info>();
		Contacts contact = new Contacts();
		assertTrue(contact.Contact_menu(contact.Contacts,con_vector));
	}
	@Test
	void test_Contacts_delete(){
		Contacts_Info aaaa = new Contacts_Info("이기용교수님","010-1234-5678","kylee@sm.ac.kr");
		Vector<Contacts_Info> con_vector = new Vector<Contacts_Info>();
		
		con_vector.add(aaaa);
		
		Contacts contact = new Contacts();
		assertTrue(contact.Contact_deleteInput("y",con_vector,1)==0);
	}
	@Test
	void test_Todo_view(){
		Todolists_Info aaaa = new Todolists_Info("소웨공과제","2018/11/11","2018/12/21","교수님찾아뵈고...");
		Vector<Todolists_Info> todo_vector = new Vector<Todolists_Info>();
		
		todo_vector.add(aaaa);
		
		String bindAnswer = String.format("%-10s","["+1+"]")
        		+String.format("%-20s",todo_vector.elementAt(0).title)
        		+String.format("%-20s",todo_vector.elementAt(0).createDate)
        		+String.format("%-20s",todo_vector.elementAt(0).dueDate)
        		+String.format("%-40s", todo_vector.elementAt(0).description);
	
    	
		Todo todo = new Todo();
		assertTrue(todo.Todolist_view(1,todo_vector).equals(bindAnswer)==true);
	}
	
	
	
	
	

	@Test
	void test_Appointment_create(){
		Appointment_Info aaaa = new Appointment_Info("","","","");
		Vector<Appointment_Info> appt_vector = new Vector<Appointment_Info>();
		Appointment apt = new Appointment();
		assertTrue(apt.Appointment_menu(apt.Appointment,appt_vector));
	}
	
	@Test
	void test_Appointment_delete(){
		Appointment_Info aaaa = new Appointment_Info("면담","18/04/04","숙명여자대학교","이기용 교수님");
		Vector<Appointment_Info> appt_vector = new Vector<Appointment_Info>();
		
		appt_vector.add(aaaa);
		
		Appointment apt = new Appointment();
		assertTrue(apt.Appointment_deleteInput("y",appt_vector,1)==0);
	}
	@Test
	void test_Appointment_view(){
		Appointment_Info aaaa = new Appointment_Info("면담","18/04/04","숙명여자대학교","이기용 교수님");
		Vector<Appointment_Info> appt_vector = new Vector<Appointment_Info>();
		
		appt_vector.add(aaaa);
		
		String bindAnswer = String.format("%3s","["+1+"]")
        		+String.format("%6s",appt_vector.elementAt(0).title)
        		+String.format("%20s",appt_vector.elementAt(0).date)
        		+String.format("%27s",appt_vector.elementAt(0).location)
        		+String.format("%30s", appt_vector.elementAt(0).person);
	
    	
		Appointment apt = new Appointment();
		assertTrue(apt.Appointment_view(1,appt_vector).equals(bindAnswer)==true);
	}
	
}
