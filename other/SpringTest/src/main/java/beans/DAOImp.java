package beans;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaQuery;

public class DAOImp {
	
	private SessionFactory sf = ConnectionUtil.getSessionFactory();
	
	public void addBook(Book b) {
		try(Session s = sf.openSession()){
			Transaction tx = s.beginTransaction();
			s.persist(b);
			tx.commit();
		}
	}
	
	public void addMonster(Monster m) {
		try(Session s = sf.openSession()){
			Transaction tx = s.beginTransaction();
			s.persist(m);
			tx.commit();
		}
	}

	public List<Monster> getMonsterByName(String monsterName) {
		List<Monster> monsterList = new ArrayList<>();
		try (Session s = sf.openSession()) {
			monsterList = s.createQuery("from Monster where Monster.name = :monsterName").setString(0, monsterName).getResultList();
		    
		}
		return monsterList;
	}

	public List<Monster> getMonsters() {
		List<Monster> monsterList = new ArrayList<>();
		try (Session s = sf.openSession()) {
			monsterList = s.createQuery("from Monster").getResultList();
		    
		}
		return monsterList;
	}

}
