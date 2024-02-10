package net.rishabh.ems.service;

import lombok.AllArgsConstructor;
import net.rishabh.ems.dto.DepartmentDto;
import net.rishabh.ems.entity.Department;
import net.rishabh.ems.exception.ResourceNotFoundException;
import net.rishabh.ems.mapper.DepartmentMapper;
import net.rishabh.ems.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.mapToDepartment(departmentDto);
        Department savedDepartment=  departmentRepository.save(department);
        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public DepartmentDto getDepartmentById(Long departmentId) {
     Department department = departmentRepository.findById(departmentId).orElseThrow(()->new ResourceNotFoundException("Department does not exist with given id"));
     return DepartmentMapper.mapToDepartmentDto(department);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
      List<Department> departments=departmentRepository.findAll();
      return departments.stream().map((department) -> DepartmentMapper.mapToDepartmentDto(department)).collect(Collectors.toList());
    }

    @Override
    public DepartmentDto updateDepartment(Long departmentId, DepartmentDto departmentDto) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(()->new ResourceNotFoundException("Department does not exist with given id"));
        department.setDepartmentName(departmentDto.getDepartmentName());
        department.setDepartmentDescription(departmentDto.getDepartmentDescription());
        Department updatedDepartment= departmentRepository.save(department);
        return DepartmentMapper.mapToDepartmentDto(updatedDepartment);
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        departmentRepository.findById(departmentId).orElseThrow(()->new ResourceNotFoundException("Department does not exist with given id"));
        departmentRepository.deleteById(departmentId);
    }
}
