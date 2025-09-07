package com.example.EmployeeManagementSystem.service.Impl;

import com.example.EmployeeManagementSystem.dto.EmployeeRequest;
import com.example.EmployeeManagementSystem.dto.EmployeeResponse;
import com.example.EmployeeManagementSystem.entity.Department;
import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.exceptions.ResourceNotFoundException;
import com.example.EmployeeManagementSystem.mapper.EmployeeMapper;
import com.example.EmployeeManagementSystem.repository.DepartmentRepository;
import com.example.EmployeeManagementSystem.repository.EmployeeRepository;
import com.example.EmployeeManagementSystem.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final DepartmentRepository departmentRepository;

    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Override
    @Transactional
    public EmployeeResponse create(EmployeeRequest request) {
        log.debug("Creating employee: {}", request);
        Department dept = null;
        if (request.getDepartmentId() != null) {
            dept = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department with ID " + request.getDepartmentId() + " not found"));
        }
        Employee employee = EmployeeMapper.toEntity(request, dept);
        Employee saved = employeeRepository.save(employee);
        log.info("Created employee id={}", saved.getId());
        return EmployeeMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EmployeeResponse> getAll(String departmentName, Pageable pageable) {
        log.debug("Fetching employees. departmentFilter={}, pageable={}", departmentName, pageable);
        Page<Employee> page;
        if (departmentName == null || departmentName.isBlank()) {
            page = employeeRepository.findAll(pageable);
        } else {
            page = employeeRepository.findByDepartment_Name(departmentName, pageable);
        }
        return page.map(EmployeeMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeResponse getById(Long id) {
        log.debug("Fetching employee by id {}", id);
        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with ID " + id + " not found"));
        return EmployeeMapper.toResponse(emp);
    }



}
