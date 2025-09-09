package com.example.EmployeeManagementSystem.service.Impl;

import com.example.EmployeeManagementSystem.dto.DepartmentRequest;
import com.example.EmployeeManagementSystem.dto.DepartmentResponse;
import com.example.EmployeeManagementSystem.entity.Department;
import com.example.EmployeeManagementSystem.exceptions.ResourceNotFoundException;
import com.example.EmployeeManagementSystem.mapper.DepartmentMapper;
import com.example.EmployeeManagementSystem.repository.DepartmentRepository;
import com.example.EmployeeManagementSystem.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger log = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    private final DepartmentRepository departmentRepository;

    @Override
    @Transactional
    public DepartmentResponse create(DepartmentRequest request) {
        log.debug("Creating department: {}", request);

        Department department = DepartmentMapper.toEntity(request);
        Department saved = departmentRepository.save(department);

        log.info("Created department id={}", saved.getId());
        return DepartmentMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentResponse> getAll() {
        log.debug("Fetching all departments");

        return departmentRepository.findAll()
                .stream()
                .map(DepartmentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public DepartmentResponse getById(Long id) {
        log.debug("Fetching department id={}", id);

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department with ID " + id + " not found"));

        return DepartmentMapper.toResponse(department);
    }

    @Override
    @Transactional
    public DepartmentResponse update(Long id, DepartmentRequest request) {
        log.debug("Updating department id={} with {}", id, request);
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department with ID " + id + " not found"));

        // Only update mutable fields
        department.setName(request.getName());
        department.setLocation(request.getLocation());

        Department updated = departmentRepository.save(department);
        log.info("Updated department id={}", updated.getId());
        return DepartmentMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.debug("Deleting department id={}", id);

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department with ID " + id + " not found"));

        departmentRepository.delete(department);
        log.info("Deleted department id={}", id);
    }

}
