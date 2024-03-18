package com.exercise.employeemanagementsystem.service;

import com.exercise.employeemanagementsystem.entity.Employee;
import com.exercise.employeemanagementsystem.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    public Employee getEmployee(Long employeeId){
        return employeeRepo.findById(employeeId).orElse(null);
    }

    public void deleteEmployee(Long employeeId){
         employeeRepo.deleteById(employeeId);
    }

//    public List<Employee> displayAllEmployees(){
//        List<Employee> employeeList = employeeRepo.findAll();
//        return employeeList;
//    }

    public void updateEmployee(Employee existingEmployee, Employee updatedEmployee) {
        existingEmployee.setFirstName(updatedEmployee.getFirstName());
        existingEmployee.setLastName(updatedEmployee.getLastName());
        existingEmployee.setGender(updatedEmployee.getGender());
        existingEmployee.setEmail(updatedEmployee.getEmail());
        existingEmployee.setPhoneNumber(updatedEmployee.getPhoneNumber());
        existingEmployee.setPosition(updatedEmployee.getPosition());
        existingEmployee.setContract(updatedEmployee.getContract());
    }


}
