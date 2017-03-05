import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.PersistenceConfig;
import model.Box;
import model.Cookie;
import repo.BoxRepository;
import repo.CookieRepository;

public class Main {
	
	public static void main(String[] args) {
	
	ApplicationContext context = new AnnotationConfigApplicationContext(PersistenceConfig.class);
	
	//get repository beans
	BoxRepository boxRepo = context.getBean("boxRepository", BoxRepository.class);
	CookieRepository cookieRepo = context.getBean("cookieRepository", CookieRepository.class);
	
	//create instance of model
	Box box = new Box("Large", "Birthday Box");
	Cookie cookie = new Cookie("Madeline", "large");
	
	//save objects 
	Long boxId = boxRepo.save(box);
	Long cookieId = cookieRepo.save(cookie);
	Long anotherCookie = cookieRepo.save(new Cookie("Chocolate", "large"));
	
	Box savedBox = boxRepo.findBoxbyId(boxId);
	Cookie savedCookie = cookieRepo.findCookieById(cookieId);
	Cookie cookieTwo = cookieRepo.findCookieById(anotherCookie);
	savedCookie.setBox(savedBox);
	cookieTwo.setBox(savedBox);
	
	savedBox.getCookies().add(savedCookie);
	
	cookieRepo.update(savedCookie);
    cookieRepo.update(cookieTwo);
   
    cookieRepo.delete(cookieTwo);
	
	System.out.println("Cookies after save and update " + cookieRepo.findCookieById(cookieId));
	System.out.println("Box after save and update " + boxRepo.findBoxbyId(boxId));
	}

}
