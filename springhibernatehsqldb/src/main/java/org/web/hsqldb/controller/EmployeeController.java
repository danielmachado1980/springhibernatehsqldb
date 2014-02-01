package org.web.hsqldb.controller;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.web.hsqldb.dao.EmployeeDao;
import org.web.hsqldb.domain.Employee;
import org.web.hsqldb.model.HibernateUtil;

@Controller
public class EmployeeController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public String show(Locale locale, Model model) throws SQLException {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		try
		{
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		    session.beginTransaction();
		    
		    List<Employee> result = (List<Employee>)new EmployeeDao().listEmployees();
		    
		    model.addAttribute("employee",result);
		}
		catch(Exception exc)
		{ 
		    logger.info("ERROR!!! Connection failure.");
		    exc.printStackTrace();
		    return "home";
		}
		
		return "employees";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)  
	 public String addEmployee(@ModelAttribute("command")Employee employeeBean,  
	   BindingResult result, Model model) {  
	   Session session = HibernateUtil.getSessionFactory().getCurrentSession();
       session.beginTransaction();
    
       List<Employee> lstResult = (List<Employee>)new EmployeeDao().listEmployees();
	   model.addAttribute("employees", lstResult);  
	   return "addEmployee";  
	 }  

}
