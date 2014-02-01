package org.web.hsqldb.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws SQLException 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws SQLException {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		try
		{
			//Starting up the driver
            Class.forName("org.hsqldb.jdbcDriver");
            //Class.forName("oracle.jdbc.driver.OracleDriver");
        }
		catch(Exception exc)
		{ 
		    logger.info("ERROR!!! Connection failure.");
		    exc.printStackTrace();
		    return "home";
		}
		//Connecting...
        Connection conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:59999/test","sa","");
		//Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","HR","hr");
        logger.info("SUCCESS!!! Connection on.");
		return "home";
	}
	
}
