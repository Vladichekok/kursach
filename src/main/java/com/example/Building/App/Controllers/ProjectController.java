package com.example.Building.App.Controllers;

import com.example.Building.App.Model.Project;
import com.example.Building.App.Model.ProjectDTO;
import com.example.Building.App.Service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController{

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService){this.projectService = projectService;}

    @GetMapping
    public List<Project> getAll(){return projectService.getAll();}

    @PostMapping
    public Project createProject(@RequestBody ProjectDTO project){return  projectService.createProject(project);}

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project project){
        Project updatedProject = projectService.updateProject(id,project);
        if(updatedProject != null)
        {return  ResponseEntity.ok(updatedProject);}
        else {return ResponseEntity.notFound().build();}
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id)
    {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

}