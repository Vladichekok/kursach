package com.example.Building.App.Repository;

import com.example.Building.App.Model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    List<Project> findByProjectNameContainingIgnoreCase(String projectName);
}
