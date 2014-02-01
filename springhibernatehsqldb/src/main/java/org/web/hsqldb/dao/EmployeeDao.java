package org.web.hsqldb.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.web.hsqldb.dao.interfaces.EmployeeDaoImpl;
import org.web.hsqldb.domain.Employee;
import org.web.hsqldb.model.HibernateUtil;


public class EmployeeDao implements EmployeeDaoImpl{
	
	 @Autowired  
	 private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();  
	   
	 public void addEmployee(Employee employee) {  
	   sessionFactory.getCurrentSession().saveOrUpdate(employee);  
	 }  
	  
	 @SuppressWarnings("unchecked")  
	 public List<Employee> listEmployees() {  
	  return (List<Employee>) sessionFactory.getCurrentSession().createCriteria(Employee.class).list();  
	 }  
	  
	 public Employee getEmployee(int empid) {  
	  return (Employee) sessionFactory.getCurrentSession().get(Employee.class, empid);  
	 }  
	  
	 public void deleteEmployee(Employee employee) {  
	  sessionFactory.getCurrentSession().createQuery("DELETE FROM Employee WHERE empid = " + employee.getEmpid()).executeUpdate();  
	 }  

}
