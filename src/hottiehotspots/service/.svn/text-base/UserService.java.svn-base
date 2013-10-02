package hottiehotspots.service;

import java.util.List;

import javax.persistence.EntityManager;

import hottiehotspots.model.User;

public interface UserService {
	
	public void setEntityManager(EntityManager em);
	
    public List<User> findAll();

    public void save(User user);

    public void remove(int id);

    public User find(int id);
    
    public User find(User model);
    
    public boolean userExists(String username);
}
