package repo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Box;

@Repository
@Transactional
@Component
public class BoxRepository {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public BoxRepository(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	public BoxRepository(){}
	
	
	private Session currentSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public Long save(Box box){
		Long id = (Long) currentSession().save(box);
		return id;
	}
	
	public Box findBoxbyId(Long id){
		Box box = (Box) currentSession().get(Box.class, id);
		return box;
	}
	
	public void update(Box box){
		currentSession().update(box);
	}
	
	public void delete(Box box){
		currentSession().delete(box);
	}
	
}
