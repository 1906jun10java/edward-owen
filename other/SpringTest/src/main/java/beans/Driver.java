package beans;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Driver {
	
	
	public static void main(String[] args) {
		SessionFactory sf = ConnectionUtil.getSessionFactory();
		
		addBook(sf);
	}
	
	public static void addBook(SessionFactory sf) {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
//		Book b1 = (Book) (ac).getBean("book");
//		b1.setAuthor("Wodehouse");
//		b1.setTitle("Hidy Ho, Mr. Jeeves");
//		
		DAOImp dimp = new DAOImp();
//		dimp.addBook(b1);
//		Monster m1 = (Monster) (ac).getBean("monster"); //remember class is camel case
//		m1.setARMOR(ArmorClass.HEAVY);
//		m1.setName("Uruk");
//		m1.setHp(13);
//		dimp.addMonster(m1);
//		ac.close();
		Monster m3 = (Monster) (ac).getBean("monster");
		Book b2 = new Book("Child", "Joy of Cooking");
		dimp.addBook(b2);
		m3.setARMOR(ArmorClass.NONE);
		m3.setName("Nerdy Orc");
		m3.setHp(9);
		m3.setFavBook(b2);
		dimp.addMonster(m3);
		List<Monster> testMonster = dimp.getMonsters();
		System.out.println(testMonster);
		
	}

}
