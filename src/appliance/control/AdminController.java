package appliance.control;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import appliance.application.AdminService;
import appliance.domain.Classes;
import appliance.domain.Room;
import appliance.domain.Teacher;


@Named
@SessionScoped
public class AdminController implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private AdminService adminService;
	private Teacher teacher = new Teacher();
	private Classes classes = new Classes();
	private Room room = new Room();

    public Teacher getTeacher() 
    {
        return teacher;
    }
    public Classes getClasses() 
    {
        return classes;
    }
    public Room getRoom() 
    {
        return room;
    }

    public String registerteacher() 
    {
        adminService.register(teacher);
        return "/admin/addpage.xhtml";
    }
    public String registerclasses() 
    {
    	adminService.register(classes);
        return "/admin/addpage.xhtml";
    }
    public String registerroom() 
    {
    	adminService.register(room);
        return "/admin/addpage.xhtml";
    }
    
    public String teacher() 
    {
    	this.teacher = new Teacher();
        return "/admin/addteacher.xhtml";
    }
    public String classes() 
    {
    	this.classes = new Classes();
       return "/admin/addclasses.xhtml";
    }
    public String room() 
    {
    	this.room = new Room();
        return "/admin/addroom.xhtml";
    }
    
    public void suggestDescription() 
    {
    	if( this.classes != null)
    	{
    		System.out.println(this.classes.getTeachername());
    		adminService.suggestDescription(this.classes);
    	}
    }
    public void printTeacher()
    {
    	System.out.println(this.classes.getTeachername());
    }
    public void printRoom()
    {
    	System.out.println(this.classes.getRoomnumber());
    }
}