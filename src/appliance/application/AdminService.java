package appliance.application;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;

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
    	String str = "SELECT s FROM Teacher s WHERE s.name LIKE :variable" ;
    	List<Teacher> list3 = entityManager.createQuery(str, Teacher.class).setParameter("variable", classes.getTeachername()).getResultList();
    	
    	String str2 = "SELECT x FROM Room x WHERE x.number LIKE :variable" ;
    	List<Room> list2 = entityManager.createQuery(str2, Room.class).setParameter("variable", classes.getRoomnumber()).getResultList();
    	
    	System.out.println("Passou aqui");
    	
    	for(Room r :list2)
    	{
    		if(r.getNumber() == classes.getRoomnumber())
    		{
	    		r.setClasses(classes);
	        	classes.setRoom(r);
	    	}
    	}
    	
    	for(Teacher t : list3)
    	{
    		if(t.getName().equals(classes.getTeachername()))
    		{
    			t.setClasses(classes);
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
    
    public void suggestDescription(Classes classes) 
    {
    	String name = classes.getName();
    	//System.out.println(name);
    	if (name != null && name.length() > 3) 
    	{
	    	String query = "PREFIX dbpedia-res: <http://dbpedia.org/resource/> PREFIX dbpedia-owl: <http://dbpedia.org/ontology/> select ?abstract where { dbpedia-res:" + name +" dbpedia-owl:abstract ?abstract. filter(langMatches(lang(?abstract),\"pt\"))}";
    		QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
	    	ResultSet results = queryExecution.execSelect();
	    	if (results.hasNext()) 
	    	{
		    	QuerySolution querySolution = results.next();
		    	Literal literal = querySolution.getLiteral("abstract");
		    	String desc = "" + literal.getValue();
		    	if(desc.length() > 255)
		    	{
		    		desc = desc.substring(0, 255);
		    	}
		    	classes.setDescription(desc);
	    	}
    	}
    }
}
