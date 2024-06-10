package com.example.Building.App.Service;

import com.example.Building.App.Model.Project;
import com.example.Building.App.Model.ProjectDTO;
import com.example.Building.App.Repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository){this.projectRepository=projectRepository;}

    public List<Project> findByProjectName(String projectName){
        return  projectRepository.findByProjectNameContainingIgnoreCase(projectName);
    }

    public Project save(Project project){return projectRepository.save(project);}

    public List<Project> getAll()
    {
        return projectRepository.findAll();
    }

    public Project createProject (ProjectDTO projectDTO){
        var project = new Project(0L,projectDTO.projectName(),projectDTO.description(),projectDTO.start(),projectDTO.end(),projectDTO.status());
        return projectRepository.save(project);
    }

    public void deleteProject(Long id){projectRepository.deleteById(id);}

    public Project updateProject(Long id, Project updatedProjectData){
        return projectRepository.findById(id)
                .map(project -> {
                    project.setProjectName(updatedProjectData.getProjectName());
                    project.setDescription(updatedProjectData.getDescription());
                    project.setStart(updatedProjectData.getStart());
                    project.setEnd(updatedProjectData.getEnd());
                    project.setStatus(updatedProjectData.getStatus());
                    return projectRepository.save(project);
                })
                .orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + id));
    }
}
