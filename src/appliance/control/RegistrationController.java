package appliance.control;
import java.io.Serializable;

import appliance.application.RegistrationService;
import appliance.domain.User;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class RegistrationController implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private RegistrationService registrationService;
    private User student = new User();

    public User getGuest() 
    {
        return student;
    }
    
    public void resetGuest()
    {
    	student = new User();
    }
    
    public String register() 
    {
    	registrationService.register(student);
        return "/registration/success.xhtml";
    }
}