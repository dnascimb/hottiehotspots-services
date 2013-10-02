package hottiehotspots.service;

import hottiehotspots.model.User;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UserServiceImpl implements UserService {
    private EntityManager em;
    private static final Log log = LogFactory.getLog(UserServiceImpl.class);

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        Query query = getEntityManager().createQuery("select p FROM User p");
        return query.getResultList();
    }

    public void save(User user) {

		if (user.getId() == null) {
            // new
            em.persist(user);
        } else {
            // update
            em.merge(user);
        }
    }
/*   COMMENTED TO REMOVE AREAS 		
    public void save(User user) {
    	if(user.getDefaultCity() == null)
    	{
    		if (user.getId() == null) {
                // new
                em.persist(user);
            } else {
                // update
                em.merge(user);
            }
    	}
    	else
    	{
	    	// associate with proper Area
			AreaService a = new AreaServiceImpl();
			Area tempArea = new Area(user.getDefaultArea().getCountry(), user.getDefaultArea().getCounty(),
								user.getDefaultArea().getPostalCode(), user.getDefaultArea().getState(), 
								user.getDefaultArea().getCity());
			Area foundArea = null;
			
			foundArea = a.find(tempArea);
			
			
			// Because an Area can be used between multiple entities, we 
			// have to see if one exists and then save it.  First, we 
			// have to call persist() on the User if its new so that we have its ID
			// when we return, which calling merge() will not do
			// so...
			// 1) find appropriate Area
			// 2) if new Location, persist
			// 3) check to make sure we have the best Area on the Location
			// 4) update the Location, with Area on it
			
	      if (user.getId() == null) {
	            // new
	    	    em.persist(user);
	    	    
	    	    if(user.getDefaultArea() == null || user.getDefaultArea().getId() == null)
	    	    {
	    	    	// there is no associated Area on the User, or
	    	    	// there is no existing Area associated with the User
	    	    	if(foundArea != null)
	    	    		user.setDefaultArea(foundArea);
	    	    	else
	    	    		user.setDefaultArea(tempArea);
	    	    }
	    	    
	    	    em.merge(user);
	            
	        } else {
	            // update
	        	
	        	if(foundArea != null && user.getDefaultArea() != null &&
	    				!foundArea.getId().equals(user.getDefaultArea().getId()))
	    		{
	    			// we find a matching Area and its not the same 
	    			// as what we already have assocatied with the User
	    			user.setDefaultArea(foundArea);
	    		}
	            em.merge(user);
	        }
    	}
      
    }
*/
    public void remove(int id) {
        User user = find(id);
        if (user != null) {
            em.remove(user);
        }
    }
    
    public boolean userExists(String username)
    {
    	Query query = getEntityManager().createQuery("from User WHERE userName = '" + username + "'");
    	boolean exists = query.getResultList().isEmpty();
        return !exists;
    }

    private EntityManager getEntityManager() {
        return em;
    }

    public User find(int id) {
        return em.find(User.class, id);
    }
    
    public User find(User model) {
    	Session session = null;
    	SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    	session = sessionFactory.openSession();
    	    
    	Criteria criteria = session.createCriteria(User.class);
    	
    	if (model.getEmail() != null) {
    			criteria.add(Restrictions.eq("email", model.getEmail()));
    	}
    	if (model.getUserName() != null) {
			criteria.add(Restrictions.eq("userName", model.getUserName()));
    	}
    	
    	List list = criteria.list();
    	
    	if(list.size() > 1)
    		log.error("Problem... more than one entry returned from UserServiceImpl.find() when looking for:" +
    				model.toString());
    	else if(list.size() == 1)
    		return (User) list.get(0);
    	
    	return null;
    }
   
}
