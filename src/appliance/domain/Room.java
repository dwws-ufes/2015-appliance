package appliance.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Room {
	@Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
    private int number;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
    private Set<Classes> classes;
    
    protected Long getId() {
        return id;
    }
    protected void setId(Long id) {
        this.id = id;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
	public Set<Classes> getClasses() {
		return classes;
	}
	public void setClasses(Classes classes) {
		this.classes.add(classes);
	}
    
}
