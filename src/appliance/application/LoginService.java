package appliance.application;

import java.util.ArrayList;
import java.util.List;

import appliance.domain.Classes;
import appliance.domain.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class LoginService {
    @PersistenceContext
    private EntityManager entityManager;

    public User login(User student)
    {
    	String str = "SELECT s FROM User s WHERE s.login LIKE :student" ;
    	List<User> list = entityManager.createQuery(str, User.class).setParameter("student", student.getLogin()).getResultList();
    	for(User st : list)
    	{
    		if(st.getPassword().equals(student.getPassword()))
    		{
    			return st;
    		}
    	}
    	return null;
    }
    
    public List<Classes> getClasses(User student)
    {
    	List<Classes> classes = new ArrayList<Classes>();
    	List<Integer> classesID = student.getClasses();
    	String query = "SELECT c FROM Classes c WHERE c.id LIKE :classe";
    	for (Integer id : classesID)
    	{
    		Long idx = new Long(id.longValue());
    		System.out.println(id);
    		System.out.println(idx);
    		List<Classes> list = entityManager.createQuery(query, Classes.class).setParameter("classe", idx).getResultList();
    		for(Classes cls : list)
    		{
    			classes.add(cls);
    		}
    	}
    	return classes;
    }
    
    public void flush(User student)
    {
    	User aux = new User();
    	String str = "SELECT s FROM User s WHERE s.login LIKE :student" ;
    	List<User> list = entityManager.createQuery(str, User.class).setParameter("student", student.getLogin()).getResultList();
    	for(User st : list)
    	{
    		if(st.getName().equals(student.getName()))
    		{
    			aux = st;
    		}
    	}
    	aux.setClasses(student.getClasses());
    	entityManager.flush();
    }
    
    public List<Classes> getList(List<String> list)
    {
    	List<Classes> classes = new ArrayList<Classes>();
    	String query = "SELECT s FROM Classes s WHERE s.name LIKE :name";
    	for (String id : list)
    	{
    		List<Classes> aux = entityManager.createQuery(query, Classes.class).setParameter("name", id).getResultList();
    		for(Classes cls : aux)
    		{
    			classes.add(cls);
    		}
    	}
    	return classes;
    }
}