package appliance.application;

import appliance.domain.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Stateless
@LocalBean
public class RegistrationService {
    @PersistenceContext
    private EntityManager entityManager;
    @Transactional
    public void register(User student)
    {
    	student.setAdmin(false);
        entityManager.persist(student);
        entityManager.flush();
    }
}