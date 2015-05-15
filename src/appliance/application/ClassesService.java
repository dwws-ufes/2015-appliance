package appliance.application;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import appliance.domain.Classes;

@Stateless
@LocalBean
public class ClassesService {
    @PersistenceContext
    private EntityManager entityManager;
    
    public List<Classes> getList()
    {
    	String str = "SELECT s FROM Classes s";
    	List<Classes> list = entityManager.createQuery(str, Classes.class).getResultList();
    	return list;
    }
}