package org.web.hsqldb.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.web.hsqldb.domain.Person;
import org.web.hsqldb.model.HibernateUtil;

@Controller
public class PersonController {

	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
	
	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public String person(Locale locale, Model model){
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		try{
		    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		    session.beginTransaction();
		    List<Person> result = (List<Person>)session.createQuery("from Person").list();
		    model.addAttribute("persons",result);
		    session.getTransaction().commit();
		    
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.info("ERROR!!!" + e.getMessage());
		}
	    return "person";

}
}


