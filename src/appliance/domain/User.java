package appliance.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
    private String name;
    private String email;
    private String login;
    private String password;
    private boolean admin;
    @Temporal(TemporalType.DATE) private Date birthDate;
    private int[] classes;
    
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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public List<Integer> getClasses() {
		List<Integer> list;
		if(this.classes == null)
		{
			System.out.println("Passei Aqui, getClasses, USER, NULL");
			this.classes = new int[0];
		}
		System.out.println("Passei Aqui, getClasses, USER, NOT NULL");
		if(this.classes.length == 0)
		{
			System.out.println("Passei Aqui, getClasses, USER, Vazia");
		}
		for(Integer classe : this.classes)
		{
			System.out.println(classe);
		}
		list = new ArrayList<Integer>();
		for(int i: this.classes)
		{
			list.add(i);
		}
		return list;
	}
	public void setClasses(List<Integer> classes) {
		System.out.println("setClasses????");
		if(classes != null)
		{
			System.out.println(classes.size());
			this.classes = new int[classes.size()];
			int j = 0;
			for(Integer i : classes)
			{
				this.classes[j] = i;
				j++;
			}
		}
	}
}