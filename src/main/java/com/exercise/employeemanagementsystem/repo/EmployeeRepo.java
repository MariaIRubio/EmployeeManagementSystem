package com.exercise.employeemanagementsystem.repo;

import com.exercise.employeemanagementsystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
}
