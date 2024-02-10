package net.rishabh.ems.mapper;

import net.rishabh.ems.dto.DepartmentDto;
import net.rishabh.ems.entity.Department;

public class DepartmentMapper {
    /*  To convert department jpa entity into department dto */

    public static DepartmentDto mapToDepartmentDto(Department department){
        return new DepartmentDto(
         department.getId(),
         department.getDepartmentName(),
         department.getDepartmentDescription()
        );
    }

    /*  To convert department dto object into department jpa entity */

    public static Department mapToDepartment(DepartmentDto departmentDto){
        return new Department(
          departmentDto.getId(),
          departmentDto.getDepartmentName(),
          departmentDto.getDepartmentDescription()
        );
    }
}
