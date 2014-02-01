package org.web.hsqldb.dao.interfaces;

import java.util.List;

import org.web.hsqldb.domain.Employee;

public interface EmployeeDaoImpl {
	 public void addEmployee(Employee employee);  
	  
	 public List<Employee> listEmployees();  
	   
	 public Employee getEmployee(int empid);  
	   
	 public void deleteEmployee(Employee employee);  

}
