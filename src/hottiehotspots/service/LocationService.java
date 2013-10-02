package hottiehotspots.service;

import hottiehotspots.model.GEOCities;
import hottiehotspots.model.Location;

import java.util.List;

import javax.persistence.EntityManager;

public interface LocationService {
	
	public void setEntityManager(EntityManager em);
	
    public Location find(int id);
    
	public void save(Location hh) throws Exception;

    public void remove(int id);
    
    public boolean isDuplicate(Location hh);
    
    public Location findById(int id);
    
    public Location find(Location location);
    
    public List findLocationsByCity(GEOCities area);
}