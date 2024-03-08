package com.example.kientructuan4.services;


import com.example.kientructuan4.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class EmployeeService {

    private static final String REDIS_KEY = "EMPLOYEE";

    @Autowired
    private RedisTemplate<String, Employee> redisTemplate;

    public void saveEmployee(Employee employee) {
        HashOperations<String, String, Employee> hashOperations = redisTemplate.opsForHash();
        hashOperations.put(REDIS_KEY, employee.getId(), employee);
    }

    public Employee getEmployee(String id) {
        HashOperations<String, String, Employee> hashOperations = redisTemplate.opsForHash();
        return hashOperations.get(REDIS_KEY, id);
    }

    public Map<String, Employee> getAllEmployees() {
        HashOperations<String, String, Employee> hashOperations = redisTemplate.opsForHash();
        return hashOperations.entries(REDIS_KEY);
    }

    public void updateEmployee(String id, Employee employee) {
        saveEmployee(employee);
    }

    public void deleteEmployee(String id) {
        HashOperations<String, String, Employee> hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(REDIS_KEY, id);
    }
}

