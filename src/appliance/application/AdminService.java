package appliance.application;


import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import appliance.domain.Classes;
import appliance.domain.Room;
import appliance.domain.Teacher;

@Stateless
@LocalBean
public class AdminService {
    @PersistenceContext
    private EntityManager entityManager;
    @Transactional
    public void register(Teacher teacher)
    {
        entityManager.persist(teacher);
        entityManager.flush();
    }
    
    public void register(Classes classes)
    {
    	/*String query = "SELECT x FROM Classes x WHERE x.name LIKE :variable";
    	List<Classes> list = entityManager.createQuery(query).setParameter("varable", classes.getName()).getResultList();
    	*/
    	String str = "SELECT s FROM Teacher s WHERE s.name LIKE :variable" ;
    	List<Teacher> list3 = entityManager.createQuery(str, Teacher.class).setParameter("variable", classes.getTeachername()).getResultList();
    	
    	String str2 = "SELECT x FROM Room x WHERE x.number LIKE :variable" ;
    	List<Room> list2 = entityManager.createQuery(str2, Room.class).setParameter("variable", classes.getRoomnumber()).getResultList();
    	
    	/*for( Classes c : list)
    	{
    		if(c.getName().equals(classes.getName()))
    		{
    			return;
    		}
    	}*/
    	
    	for(Room r :list2)
    	{
    		if(r.getNumber() == classes.getRoomnumber())
    		{
	    		r.setClasses(classes);
	        	//entityManager.refresh(r);
	        	classes.setRoom(r);
	    	}
    	}
    	
    	for(Teacher t : list3)
    	{
    		if(t.getName().equals(classes.getTeachername()))
    		{
    			t.setClasses(classes);
            	//entityManager.refresh(t);
            	classes.setTeacher(t);
    		}
    	}    	
    	entityManager.persist(classes);
        entityManager.flush();
    }
    
    public void register(Room room)
    {
        entityManager.persist(room);
        entityManager.flush();
    }
}
