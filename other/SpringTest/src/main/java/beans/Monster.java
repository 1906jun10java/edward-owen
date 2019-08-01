package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
@Table(name="MONSTER")
@Component
@Scope(value = "prototype")
public class Monster {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="bookSequence")
	@SequenceGenerator(allocationSize=1, name="bookSequence", sequenceName="SQ_BOOK_PK")
	@Column(name="MONSTER_ID")
	private int id;
	@Column(unique = true, nullable = false, name="MONSTER_NAME")
	private String name;
	@Column(name="MONSTER_HP")
	private int hp;
	@Column(name="MONSTER_ARMOR")
	private ArmorClass ARMOR;
	@OneToOne
	@JoinColumn(name="MONSTER_BOOK")
	private Book favBook;
	
	public Monster() {}
	
	public Monster(String name, int hp, ArmorClass aRMOR) {
		super();
		this.name = name;
		this.hp = hp;
		ARMOR = aRMOR;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public ArmorClass getARMOR() {
		return ARMOR;
	}
	public void setARMOR(ArmorClass aRMOR) {
		ARMOR = aRMOR;
	}

	public Book getFavBook() {
		return favBook;
	}

	public void setFavBook(Book favBook) {
		this.favBook = favBook;
	}

	@Override
	public String toString() {
		return "Monster [id=" + id + ", name=" + name + ", hp=" + hp + ", ARMOR=" + ARMOR + ", favBook=" + favBook
				+ "]";
	}

}
