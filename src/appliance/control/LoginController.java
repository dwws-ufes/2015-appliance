package appliance.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import appliance.application.ClassesService;
import appliance.application.LoginService;
import appliance.domain.Classes;
import appliance.domain.User;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class LoginController implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
    private LoginService loginService;
	@EJB
    private ClassesService classesService;
    private User student = new User();
    private List<Classes> mtClassesList = new ArrayList<Classes>();
	private List<String> wtClassesList = new ArrayList<String>();
	private String[] selectedClasses;

    public User getGuest() 
    {
        return student;
    }
    
    public String login() 
    {
    	student = loginService.login(student);
    	mtClassesList = new ArrayList<Classes>();
    	wtClassesList = new ArrayList<String>();
        if(student != null)
        {
        	if(student.isAdmin())
        	{
        		return "/admin/addpage.xhtml"; 
        	}
        	System.out.println("passei, login");
        	return "/enroll/controll.xhtml";
        }
        return "/enroll/index.xhtml";
    }
	
    public List<Classes> getMtClassesList() 
    { 
		System.out.println(student.getName());
		mtClassesList = loginService.getClasses(student);
		if(mtClassesList != null)
		{
			return mtClassesList;
		}
		mtClassesList = new ArrayList<Classes>();
		return mtClassesList;
	}
	public void setMtClassesList(List<Classes> mtClassesList) {
		this.mtClassesList = mtClassesList;
	}

	public List<String> getWtClassesList() {
		if(mtClassesList != null)
		{
			for( Classes cls: mtClassesList)
			{
				wtClassesList.add(cls.getName());
			}
		}
		return wtClassesList;
	}

	public void setWtClassesList(List<String> wtClassesList) {
		System.out.println(wtClassesList.size());
		this.wtClassesList = wtClassesList;
	}
	
	public String addClasses()
	{
		return "/enroll/addclasses.xhtml";
	}
	
	public String removeClasses()
	{
		return "/enroll/removeclasses.xhtml";
	}
	
	public String matricula()
	{
		List<Integer> list = new ArrayList<Integer>();
		List<Classes> classe = new ArrayList<Classes>();
		classe = loginService.getList(wtClassesList);
		System.out.println("passei, matricula");
		System.out.println(wtClassesList.size());
		for(Classes clas : classe)
		{
			list.add(clas.getId().intValue());
		}
		student.setClasses(list);
		loginService.flush(this.student);
		return "/enroll/controll.xhtml";
	}

	public String[] getSelectedClasses() {
		return selectedClasses;
	}

	public void setSelectedClasses(String[] selectedClasses) {
		System.out.println(selectedClasses.length);
		this.selectedClasses = selectedClasses;
	}
}