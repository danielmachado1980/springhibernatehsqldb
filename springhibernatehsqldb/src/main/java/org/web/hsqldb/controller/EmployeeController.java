package org.web.hsqldb.controller;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.web.hsqldb.service.EmployeeServiceImpl;
import org.web.hsqldb.service.interfaces.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired  
	private EmployeeServiceImpl employeeService;
	
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
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)  
	public ModelAndView editEmployee(@ModelAttribute("command")Employee employeeBean,  
      BindingResult result) {  
	  //employeeService.deleteEmployee(prepareModel(employeeBean));  
	  Map<String, Object> model = new HashMap<String, Object>();  
//	  model.put("employee", null);  
	  //model.put("employees",  prepareListofBean(employeeService.listEmployeess()));  
	  return new ModelAndView("addEmployee", model);  
	 }  
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)  
	public ModelAndView deleteEmployee(@ModelAttribute("command")Employee employeeBean,  
	   BindingResult result) {  
	  Map<String, Object> model = new HashMap<String, Object>();  
//	  model.put("employee", prepareEmployeeBean(employeeService.getEmployee(employeeBean.getEmpid())));  
//	  model.put("employees",  prepareListofBean(employeeService.listEmployees()));  
//	  
	  return new ModelAndView("addEmployee", model);  
	 }  
	
	private Employee prepareModel(Employee employeeBean){  
		  Employee employee = new Employee();  
		  employee.setAddress(employeeBean.getAddress());  
		  employee.setEmpage(employeeBean.getEmpage());  
		  employee.setEmpname(employeeBean.getEmpname());  
		  employee.setSalary(employeeBean.getSalary());  
		  employee.setEmpid(employeeBean.getEmpid());  
		  employee.setEmpid(null);  
		  return employee;  
		 } 
	
	private List<Employee> prepareListofBean(List<Employee> employees){  
		  List<Employee> beans = null;  
		  if(employees != null && !employees.isEmpty()){  
		   beans = new ArrayList<Employee>();  
		   Employee bean = null;  
		   for(Employee employee : employees){  
		    bean = new Employee();  
		    bean.setEmpname(employee.getEmpname());  
		    bean.setEmpid(employee.getEmpid());  
		    bean.setAddress(employee.getAddress());  
		    bean.setSalary(employee.getSalary());  
		    bean.setEmpage(employee.getEmpage());  
		    beans.add(bean);  
		   }  
		  }  
		  return beans;  
		 }  
		   
		 private Employee prepareEmployeeBean(Employee employee){  
		  Employee bean = new Employee();  
		  bean.setAddress(employee.getAddress());  
		  bean.setEmpage(employee.getEmpage());  
		  bean.setEmpname(employee.getEmpname());  
		  bean.setSalary(employee.getSalary());  
		  bean.setEmpid(employee.getEmpid());  
		  return bean;  
		 }  

}
