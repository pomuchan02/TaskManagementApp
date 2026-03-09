package com.example.taskManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.taskManagement.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}