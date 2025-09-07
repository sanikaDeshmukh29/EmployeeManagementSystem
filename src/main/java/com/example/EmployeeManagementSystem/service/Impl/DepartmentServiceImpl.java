package com.example.EmployeeManagementSystem.service.Impl;

import com.example.EmployeeManagementSystem.entity.Department;
import com.example.EmployeeManagementSystem.exceptions.ResourceNotFoundException;
import com.example.EmployeeManagementSystem.repository.DepartmentRepository;
import com.example.EmployeeManagementSystem.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger log = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    private final DepartmentRepository departmentRepository;

    @Override
    @Transactional
    public Department create(Department department) {
        log.debug("Creating department: {}", department);
        Department saved = departmentRepository.save(department);
        log.info("Created department id={}", saved.getId());
        return saved;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Department> getAll() {
        log.debug("Fetching all departments");
        return departmentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Department getById(Long id) {
        log.debug("Fetching department id={}", id);
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department with ID " + id + " not found"));
    }
}
