package com.example.Building.App.User;

import com.example.Building.App.Controllers.ResourcesController;
import com.example.Building.App.Model.Resources;
import com.example.Building.App.Model.ResourcesDTO;
import com.example.Building.App.Service.ResourcesService;
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

public class ResourcesControllerTest {

    @Mock
    private ResourcesService resourcesService;

    @InjectMocks
    private ResourcesController resourcesController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(resourcesController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetAll() throws Exception {
        List<Resources> resources = Arrays.asList(
                new Resources(1L, "Resource1", 10, 100, 1),
                new Resources(2L, "Resource2", 20, 200, 2)
        );

        when(resourcesService.getAll()).thenReturn(resources);

        mockMvc.perform(get("/api/resources"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].resourcesName").value("Resource1"))
                .andExpect(jsonPath("$[1].resourcesName").value("Resource2"));
    }

    @Test
    public void testCreateResource() throws Exception {
        ResourcesDTO newResourceDTO = new ResourcesDTO("NewResource", 30, 300, 3);
        Resources newResource = new Resources(1L, "NewResource", 30, 300, 3);

        when(resourcesService.createResource(any(ResourcesDTO.class))).thenReturn(newResource);

        mockMvc.perform(post("/api/resources")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newResourceDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.resourcesName").value("NewResource"))
                .andExpect(jsonPath("$.quantity").value(30));
    }

    @Test
    public void testUpdateResource() throws Exception {
        Resources updatedResource = new Resources(1L, "UpdatedResource", 40, 400, 4);

        when(resourcesService.updateResource(eq(1L), any(Resources.class))).thenReturn(updatedResource);

        mockMvc.perform(put("/api/resources/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedResource)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.resourcesName").value("UpdatedResource"))
                .andExpect(jsonPath("$.quantity").value(40));
    }

    @Test
    public void testDeleteResource() throws Exception {
        mockMvc.perform(delete("/api/resources/1"))
                .andExpect(status().isNoContent());
    }
}

