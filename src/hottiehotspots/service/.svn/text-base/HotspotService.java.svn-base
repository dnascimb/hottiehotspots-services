package hottiehotspots.service;

import hottiehotspots.model.GEOCities;
import hottiehotspots.model.Hotspot;
import hottiehotspots.model.HotspotRating;

import java.util.List;

import javax.persistence.EntityManager;

public interface HotspotService {
	
	public void setEntityManager(EntityManager em);
	
	public void save(Hotspot hh);

    public void remove(int id);
    
    public double calculateTemperature(Hotspot h, HotspotRating latestRating);
    
    public List findHotSpotsByCity(GEOCities area);
    public List findHotSpotsByCity(GEOCities area, String searchText);
    
    public List findHotSpotsByCoordinates(double latitude, double longitude);
    public List findHotSpotsByCoordinates(double latitude, double longitude, String searchText);
    
    public List findNewestHotspots(int numberToRetrieve);
    public List findNewestHotspots(int numberToRetrieve, GEOCities area);
    public List findNewestHotspots(int numberToRetrieve, double latitude, double longitude);
    
    public List findTopRatedHotspots(int numberToRetrieve);
    public List findTopRatedHotspots(int numberToRetrieve, GEOCities area);
    public List findTopRatedHotspots(int numberToRetrieve, double latitude, double longitude);
}
