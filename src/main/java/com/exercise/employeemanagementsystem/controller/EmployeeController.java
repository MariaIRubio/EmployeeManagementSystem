package com.exercise.employeemanagementsystem.controller;

import com.exercise.employeemanagementsystem.entity.Employee;
import com.exercise.employeemanagementsystem.repo.EmployeeRepo;
import com.exercise.employeemanagementsystem.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/startpage")
    public String startPage(){
        return"start";
    }

    // FROM START PAGE
    // ADD NEW EMPLOYEE
    @GetMapping("/addemployee")
    public String addEmployee(Model model){
        model.addAttribute("employee", new Employee());
        return "add-form";
    }

    @PostMapping("/processAddForm")
    public String processAddForm(@Valid @ModelAttribute("employee") Employee employee, BindingResult binder){
        if(binder.hasErrors()){
            return "add-form";
        }else{
            employeeRepo.save(employee);
            return "add-confirmation";
        }
    }


    // UPDATE EMPLOYEE WITH ID
    @GetMapping("/updateemployee")
    public String updateEmployee_ID(Model model){
        model.addAttribute("employee", new Employee());
        return "processEmployeeID";
    }
    @GetMapping("/processEmployeeID")
    public String processID(@RequestParam("id") Long id, Model model){
        Employee existingEmployee = employeeService.getEmployee(id);
        if(existingEmployee == null) {return "employee-not-found";}
        model.addAttribute("employee", existingEmployee);
        return "update-form";
    }
    @PostMapping("/processUpdateForm")
    public String processUpdateInfo(@Valid @ModelAttribute("employee") Employee employee, BindingResult binder){
        if(binder.hasErrors()){
            return "update-form";
        }
        Long id = employee.getId();
        Employee existingEmployee =  employeeService.getEmployee(id);
        if(existingEmployee == null){
            return "update-form";
        }
        employeeService.updateEmployee(existingEmployee,employee);
        employeeRepo.save(existingEmployee);
        return "confirmation";
    }
    // DISPLAY ALL EMPLOYEES
    @GetMapping("/displayallemployees")
    public String displayEmployees(Model model){
        List<Employee> employeeList = employeeRepo.findAll();

        if(employeeList.isEmpty()){
            return "employee-not-found";
        }
        model.addAttribute("employeeList", employeeList);
        return "display-all-employees";
    }

    // DELETE EMPLOYEE WITH ID
    @GetMapping("/deleteemployee")
    public String deleteEmployee_ID(Model model){
        model.addAttribute("employee", new Employee());
        return "processDeleteEmployeeID";
    }
    @PostMapping("/processDeleteEmployeeID")
    public String processDeleteEmployee_ID(@RequestParam("id") Long id){
        Employee employee = employeeService.getEmployee(id);
        if(employee != null){
            employeeRepo.deleteById(id);
            return "confirmation";
        }else {
            return "employee-not-found";

        }
    }

    // FIND EMPLOYEE WITH ID
    @GetMapping("/findemployee")
    public String displayEmployeeForm(Model model){
        model.addAttribute("employee", new Employee());
        return "getEmployeeID";
    }

    @PostMapping("/processGetEmployeeID")
    public String displayEmployee(@RequestParam("id") Long id, Model model){
       Employee employee = employeeService.getEmployee(id);
        if(employee != null) {
            model.addAttribute("employee", employee);
            return "display-employee";
        }else{return "employee-not-found";}
    }

    // DELETE ALL EMPLOYEES
    @RequestMapping("/deleteallemployees")
    public String deleleteAllEmployees(){
        employeeRepo.deleteAll();
        return "confirmation";
    }


    // FROM EMPLOYEE PAGE
    // UPDATE EMPLOYEE
    @RequestMapping("/updateemployee/{id}")
    public String updateEmployee(@PathVariable Long id, Model model){
        Employee employee = employeeService.getEmployee(id);
        if(employee != null) {
            model.addAttribute("employee", employee);
            return "update-form2";
        }else{return "error-page";}
    }

    @PostMapping("/processUpdateForm/{id}")
    public String processUpdateForm(@PathVariable Long id, @Valid @ModelAttribute("employee") Employee employee, BindingResult binder){
        if(binder.hasErrors()){
            return "update-form2";
        }
        Employee existingEmployee =  employeeService.getEmployee(id);
        if(existingEmployee == null){
            return "update-form2";
        }
       employeeService.updateEmployee(existingEmployee, employee);
        employeeRepo.save(existingEmployee);
        return "confirmation";
    }

    @RequestMapping("/deleteemployee/{id}")
    public String deleteEmployee(@PathVariable Long id){
        employeeRepo.deleteById(id);
        return "confirmation";
    }


    @GetMapping("/manageEmployees")
    public String manageEmployees(){
        return "manage-employees";
    }


}
