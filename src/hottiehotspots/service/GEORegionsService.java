package hottiehotspots.service;

import hottiehotspots.model.GEORegions;

import java.util.List;

import javax.persistence.EntityManager;

public interface GEORegionsService {

	public void setEntityManager(EntityManager em);
    public GEORegions findById(int id);
    public GEORegions find(GEORegions model);
    public List findRegions(GEORegions model);
    
}
