package net.rishabh.ems.controller;

import lombok.AllArgsConstructor;
import net.rishabh.ems.dto.DepartmentDto;
import net.rishabh.ems.entity.Department;
import net.rishabh.ems.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/departments")
public class DepartmentController {

    private DepartmentService departmentService;

    // Add Department REST API

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto){
       DepartmentDto departmentDto1= departmentService.createDepartment(departmentDto);
       return new ResponseEntity<>(departmentDto1, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable("id") Long departmentId){
       DepartmentDto departmentDto= departmentService.getDepartmentById(departmentId);
       return ResponseEntity.ok(departmentDto);
    }

    @GetMapping()
    public ResponseEntity<List<DepartmentDto>> getAllDepartments(){
      List<DepartmentDto> departmentDtoList=departmentService.getAllDepartments();
      return ResponseEntity.ok(departmentDtoList);
    }

    @PutMapping("{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable("id") Long departmentId, @RequestBody DepartmentDto departmentDto){
      DepartmentDto departmentDto1=  departmentService.updateDepartment(departmentId,departmentDto);
      return ResponseEntity.ok(departmentDto1);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long departmentId){
        departmentService.deleteDepartment(departmentId);
        return ResponseEntity.ok("Department deleted successfully!");
    }
}
