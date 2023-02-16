package com.employee.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.employee.entities.*;
import com.employee.request.EmployeeDropdownResponse;

public interface EmployeeRepository  extends JpaRepository<Employee, Integer>
{

@Query(value= "SELECT employee_id as employeeId,first_name as firstName,email_id as emailId from employee",nativeQuery = true)
	public List<Map<String, Object>> findNameAndEmail();
}
