package org.web.hsqldb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web.hsqldb.dao.EmployeeDao;
import org.web.hsqldb.domain.Employee;
import org.web.hsqldb.service.interfaces.EmployeeService;

@Service("employeeService")  
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired  
	private EmployeeDao employeeDao;  
	
	public void addEmployee(Employee employee) {
		employeeDao.addEmployee(employee);  
		
	}

	@Override
	public List<Employee> listEmployees() {
		return employeeDao.listEmployees(); 
	}

	@Override
	public Employee getEmployee(int empid) {
		return employeeDao.getEmployee(empid);  
	}

	@Override
	public void deleteEmployee(Employee employee) {
		employeeDao.deleteEmployee(employee); 
		
	}

}

