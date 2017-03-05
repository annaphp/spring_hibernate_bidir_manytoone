package model.repo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Cookie;


/* Layers:
 * 
 * Test 
 * ===========
 * Service
 * -----------
 * Repository
 * -----------
 * Hibernate
 * -----------
 * 
 */
@Repository
@Transactional
public class CookieRepository {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public CookieRepository(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	public CookieRepository(){};
	
	private Session currentSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public Long save(Cookie cookie){
		Long id = (Long) currentSession().save(cookie);
		return id;
	}
	
	public void update (Cookie cookie){
		currentSession().update(cookie);
	}
	
	public Cookie findCookieById(Long id){
		Cookie cookie = (Cookie) currentSession().get(Cookie.class, id);
		return cookie;
	}
	
	public void delete(Cookie cookie){
		currentSession().delete(cookie);
	}

}
