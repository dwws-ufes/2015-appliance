package appliance.control;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import appliance.application.ClassesService;
import appliance.domain.Classes;

@Named
@SessionScoped
public class ClassesController implements Serializable {
	private List<Classes> classesList;
	private Classes selectedClasses;
	private static final long serialVersionUID = 1L;
	@EJB
    private ClassesService classesService;
	public List<Classes> getClassesList() {
		classesList = classesService.getList();
		return classesList;
	}
	public void setClassesList(List<Classes> classesList) {
		this.classesList = classesList;
	}
	public Classes getSelectedClasses() {
		return selectedClasses;
	}
	public void setSelectedClasses(Classes selectedClasses) {
		this.selectedClasses = selectedClasses;
	}
}
