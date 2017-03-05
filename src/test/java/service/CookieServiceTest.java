package service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.Config;
import model.Box;
import model.Cookie;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=Config.class)
public class CookieServiceTest {

	@Autowired
	CookieService service;
	
	Box box;
	Cookie cookie;
	
	@Before
	public void setUp(){
		box = new Box("Large", "Birthday Box");
		cookie = new Cookie("Madeline", "large");		
	}	
	
	@Test
	public void shouldCreateCookie(){
		Long id = service.createCookie(cookie);
		assertNotNull(service.findCookieById(id));
	}

	@Test
	public void shouldCreateBox() {
		Long id = service.createBox(box);
		assertNotNull(service.findBoxById(id));
	}
	
	@Test
	public void shouldPutCookieInABox(){
		// arrange
		Long cookieId = service.createCookie(cookie);
		Long boxId = service.createBox(box);
		// act
		service.putCookieInABox(cookie, box);
		// assert
		Box b = service.findBoxById(boxId);
		Cookie c = service.findCookieById(cookieId);
		assertTrue(b.getCookies().contains(c));
		assertEquals(c.getBox(), b);
	}
	
	@Test
	public void shouldMoveCookieToAnotherBox(){
		Box sunBox = new Box("Small", "Sunday Box");
		// arrange
		Long cookieId = service.createCookie(cookie);
		Long boxId = service.createBox(box);
		Long boxId2 =service.createBox(sunBox);
		// act
		service.putCookieInABox(cookie, box);
		service.moveCookieToAnotherBox(box, sunBox, cookie);
		//assert
		Box b1 = service.findBoxById(boxId);
		Box b2 = service.findBoxById(boxId2);
		Cookie c = service.findCookieById(cookieId);
		
		assertTrue(b2.getCookies().contains(c));
		assertFalse(b1.getCookies().contains(c));
		assertEquals(cookie.getBox(), b2);	
	}

}
