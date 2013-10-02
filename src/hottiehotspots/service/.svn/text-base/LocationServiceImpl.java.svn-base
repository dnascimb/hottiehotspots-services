package hottiehotspots.service;

import hottiehotspots.model.GEOCities;
import hottiehotspots.model.Label;
import hottiehotspots.model.Location;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class LocationServiceImpl implements LocationService{
	 private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
    public Location find(int id) {
        return em.find(Location.class, id);
    }
    
	public void save(Location location) throws Exception {
		
		if (location.getLocationId() == null) {
            
			//stash labels until after persist
			HashSet<Label> theLabels = new HashSet<Label>();
			theLabels = (HashSet<Label>)location.getLabels();
			location.setLabels(null);
			
			// check to make it is not a duplicate entry
			if(isDuplicate(location))
				throw new Exception("duplicate");
			
			// new
            em.persist(location);
            
            location.setLabels(theLabels);
            
            em.merge(location);
            
            
            
        } else {
            // update
            em.merge(location);
        }
	}

/*  COMMENTED BECAUSE WE ARE MOVING AWAY FROM AREAS
 
		// associate with proper Area
		AreaService a = new AreaServiceImpl();
		Area area = new Area(location.getCountry(), location.getCounty(),
							location.getPostalCode(), location.getState(), location.getCity());
		Area foundArea = null;
		
		foundArea = a.find(area);
		
		
		// Because an Area can be used between multiple entities, we 
		// have to see if one exists and then save it.  First, we 
		// have to call persist() on the location if its new so that we have its ID
		// when we return, which calling merge() will not do
		// so...
		// 1) find appropriate Area
		// 2) if new Location, persist
		// 3) check to make sure we have the best Area on the Location
		// 4) update the Location, with Area on it
		
      if (location.getLocationId() == null) {
            // new
    	    em.persist(location);
    	    
    	    if(location.getArea() == null || location.getArea().getCityId() == null)
    	    {
    	    	// there is no associated Area on the Location, or
    	    	// there is no existing Area associated with the Location
    	    	if(foundArea != null)
    	    		location.setArea(foundArea);
    	    	else
    	    		location.setArea(new Area(location.getCountry(), location.getCounty(),
							location.getPostalCode(), location.getState(), location.getCity()));
    	    }
    	    
    	    em.merge(location);
            
        } else {
            // update
        	
        	if(foundArea != null && location.getArea() != null &&
    				!foundArea.getId().equals(location.getArea().getCityId()))
    		{
    			// we find a matching Area and its not the same 
    			// as what we already have assocatied with the location
    			location.setArea(foundArea);
    		}
            em.merge(location);
        }
	}
*/
		
    public void remove(int id) {
    	
    	Location loc = findById(id);
    	
    	em.remove(loc);
    	
    }
    
    public boolean isDuplicate(Location location) {
    	boolean isDuplicate = false;
    	StringBuffer hql = new StringBuffer();
    	
    	hql.append("from Location WHERE"
		+ " address1 = '" + location.getAddress1() + "'"
		+ " AND type = '" + location.getType() + "'");
    	
    /* HAVEN'T IMPLEMENTED EVENTS YET
     * 
     */
    /*  	if(location.getType().equals("Event")){
     
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		hql.append(" AND eventDate = '" + sdf.format(location.getEventDate()) + "'");
    	}
    */	
    	if(location.getAddress2() != null && location.getAddress2().trim().length() > 0)
    		hql.append(" AND address2 = '" + location.getAddress2() + "'");
    	
    	Query query = getEntityManager().createQuery(hql.toString());
    	isDuplicate = !query.getResultList().isEmpty();
    	
    	return isDuplicate;
    }
    
    private EntityManager getEntityManager() {
        return em;
    }
    
    public Location findById(int id)
    {    	
    	Query query = getEntityManager().createQuery("from Location where location_id = :location_id");
    	query.setParameter("location_id", id);
    	List list = query.getResultList();
    	
    	if(list.size() > 1)
    		System.out.println("Problem... more than one entry returned from LocationServiceImpl.findById");
    	else if(list.size() == 1)
    		return (Location) list.get(0);
    	
    	return null;
    }
    
    public Location find(Location location)
    {    	
    	Session session = null;
    	SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    	session = sessionFactory.openSession();
    	    
    	Criteria criteria = session.createCriteria(Location.class);
    	
    	if (location.getLocationId() != null) {
    			criteria.add(Restrictions.eq("locationId", location.getLocationId()));
    	}   
    	if(location.getName() != null) {
    			criteria.add(Restrictions.eq("name", location.getName()));
    	}
    	if(location.getAddress1() != null) {
			criteria.add(Restrictions.eq("address1", location.getAddress1()));
    	}
    	if(location.getCity() != null) {
			criteria.add(Restrictions.eq("city", location.getCity()));
    	}
    	if(location.getCountry() != null) {
			criteria.add(Restrictions.eq("country", location.getCountry()));
    	}
    	if(location.getPostalCode() != null) {
			criteria.add(Restrictions.eq("postalCode", location.getPostalCode()));
    	}
    	if(location.getType() != null) {
			criteria.add(Restrictions.eq("type", location.getType()));
    	}

    	List list = criteria.list();
    	

    	
    	//Query query = getEntityManager().createQuery("Select location from Location location");
    	//query.setParameter("location_id", location.getLocationId());
    	//List list = query.getResultList();
    	
    	if(list.size() > 1)
    		System.out.println("Problem... more than one entry returned from LocationServiceImpl.find");
    	else if(list.size() == 1)
    		return (Location) list.get(0);
    	
    	return null;
    }
    
    public List findLocationsByCity(GEOCities area)
    {
    	// TODO change to fetch based on county
    	// get all the locations that belong to an Area and have Hotspots associated with them
    	Query query = getEntityManager().createQuery("from Location where area.cityId = :area_id and hotspot = NULL");
    	query.setParameter("area_id", area.getCityId());
    	List list = query.getResultList();
    	
    	return list;
    }
    
}
