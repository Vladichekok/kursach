package com.example.Building.App.User;

import com.example.Building.App.Controllers.ProjectController;
import com.example.Building.App.Model.Project;
import com.example.Building.App.Model.ProjectDTO;
import com.example.Building.App.Service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ProjectControllerTest {

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(projectController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetAll() throws Exception {
        List<Project> projects = Arrays.asList(
                new Project(1L, "Project1", "Description1", 2023, 2024, "InProgress"),
                new Project(2L, "Project2", "Description2", 2022, 2023, "Completed")
        );

        when(projectService.getAll()).thenReturn(projects);

        mockMvc.perform(get("/api/projects"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].projectName").value("Project1"))
                .andExpect(jsonPath("$[1].projectName").value("Project2"));
    }

    @Test
    public void testCreateProject() throws Exception {
        ProjectDTO newProjectDTO = new ProjectDTO("NewProject", "NewDescription", 2023, 2024, "InProgress");
        Project newProject = new Project(1L, "NewProject", "NewDescription", 2023, 2024, "InProgress");

        when(projectService.createProject(any(ProjectDTO.class))).thenReturn(newProject);

        mockMvc.perform(post("/api/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newProjectDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.projectName").value("NewProject"))
                .andExpect(jsonPath("$.description").value("NewDescription"));
    }

    @Test
    public void testUpdateProject() throws Exception {
        Project updatedProject = new Project(1L, "UpdatedProject", "UpdatedDescription", 2023, 2024, "InProgress");

        when(projectService.updateProject(eq(1L), any(Project.class))).thenReturn(updatedProject);

        mockMvc.perform(put("/api/projects/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedProject)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.projectName").value("UpdatedProject"))
                .andExpect(jsonPath("$.description").value("UpdatedDescription"));
    }

    @Test
    public void testDeleteProject() throws Exception {
        mockMvc.perform(delete("/api/projects/1"))
                .andExpect(status().isNoContent());
    }
}

