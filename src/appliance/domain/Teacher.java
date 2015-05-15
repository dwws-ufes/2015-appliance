package appliance.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Teacher {
	@Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
    private String name;
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher") private Set<Classes> classes;
    
    protected Long getId() {
        return id;
    }
    protected void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
	public Set<Classes> getClasses() {
		return classes;
	}
	public void setClasses(Classes classes) {
		this.classes.add(classes);
	}
}
