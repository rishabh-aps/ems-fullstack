package net.rishabh.ems.service;

import lombok.AllArgsConstructor;
import net.rishabh.ems.dto.EmployeeDto;
import net.rishabh.ems.entity.Department;
import net.rishabh.ems.entity.Employee;
import net.rishabh.ems.exception.ResourceNotFoundException;
import net.rishabh.ems.mapper.EmployeeMapper;
import net.rishabh.ems.repository.DepartmentRepository;
import net.rishabh.ems.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    private DepartmentRepository departmentRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Department department=departmentRepository.findById(employeeDto.getDepartmentId()).orElseThrow(()->new ResourceNotFoundException("Department does not exist with given id:"+employeeDto.getDepartmentId()));
        employee.setDepartment(department);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
     Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("Employee does not exist with given id"));
     return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
       List<Employee> employeeList = employeeRepository.findAll();
       return employeeList.stream().map((employee)->EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("Employee does not exist with given id"));
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());
        Department department=departmentRepository.findById(updatedEmployee.getDepartmentId()).orElseThrow(()->new ResourceNotFoundException("Department does not exist with given id:"+updatedEmployee.getDepartmentId()));
        employee.setDepartment(department);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("Employee does not exist with given id"));
        employeeRepository.deleteById(employeeId);
    }


}
