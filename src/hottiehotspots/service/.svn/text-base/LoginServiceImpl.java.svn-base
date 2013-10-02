package hottiehotspots.service;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import hottiehotspots.model.User;

@Transactional
public class LoginServiceImpl implements LoginService {
    private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    private EntityManager getEntityManager() {
        return em;
    }

    public User login(User user) {
    	User newUser = null; // stores returned user entry
    	
    	Query query = getEntityManager().createQuery("from User WHERE userName = '" + user.getUserName() + "' AND " +
    			"password = '" + user.getPassword() + "'");
    	
    	if(!query.getResultList().isEmpty())
    	{
    		newUser = (User)query.getResultList().get(0);
    	}
    		
        return newUser;
    }
    
   
}
