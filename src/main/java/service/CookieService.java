package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import model.Box;
import model.Cookie;
import model.repo.BoxRepository;
import model.repo.CookieRepository;

@Service
public class CookieService {
	
	private BoxRepository boxRepo;
	private CookieRepository cookieRepo;
	
	@Autowired
	public CookieService(BoxRepository boxRepo, CookieRepository cookieRepo) {
		this.boxRepo = boxRepo;
		this.cookieRepo = cookieRepo;
	}
	
	/* 
	 * User stories:
	 * I want to create Box
	 * I want to move cookie from one box to another.
	 * I want to put cookie in a box
	 * I want to create cookie
	 * 
	 */
		
	public Long createBox(Box b){
		return boxRepo.save(b);
	}
	
	public Box findBoxById(Long id){
		return boxRepo.findBoxbyId(id);
	}
	
	public Cookie findCookieById(Long id){
		return cookieRepo.findCookieById(id);
	}
	
	public Long createCookie(Cookie c){
		return cookieRepo.save(c);
	}
	
	/* use case */
	public void putCookieInABox(Cookie c, Box b){
		Assert.notNull(c, "cookie cannot null");
		Assert.notNull(b, "b cannot null");
		Assert.notNull(c.getId(), "cookie must have id");
		Assert.notNull(b.getId(), "box must have id");
		b.getCookies().add(c);
		c.setBox(b);
		cookieRepo.update(c);
	}
	
	public void moveCookieToAnotherBox(Box from, Box to, Cookie c){
		Assert.notNull(from.getId(),"from box must have id");
		Assert.notNull(to.getId(),"to box must have id");
		Assert.notNull(c.getId(),"cookie must have id");
		
		Assert.isTrue(from.getCookies().contains(c), "from box doesn't have this cookie");
		from.getCookies().remove(c);
		to.getCookies().add(c);
		c.setBox(to);
		cookieRepo.update(c);
	}
	
}
