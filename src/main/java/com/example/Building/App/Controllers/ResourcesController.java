package com.example.Building.App.Controllers;

import com.example.Building.App.Model.Resources;
import com.example.Building.App.Model.ResourcesDTO;
import com.example.Building.App.Service.ResourcesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/resources")
public class ResourcesController {

    private final ResourcesService resourcesService;

    public ResourcesController(ResourcesService resourcesService) {
        this.resourcesService = resourcesService;
    }

    @GetMapping
    public List<Resources> getAll() {
        return resourcesService.getAll();
    }

    @PostMapping
    public Resources createResource(@RequestBody ResourcesDTO resourcesDTO) {
        return resourcesService.createResource(resourcesDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resources> updateResource(@PathVariable Long id, @RequestBody Resources resources) {
        Resources updatedResource = resourcesService.updateResource(id, resources);
        if (updatedResource != null) {
            return ResponseEntity.ok(updatedResource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable Long id) {
        resourcesService.deleteResource(id);
        return ResponseEntity.noContent().build();
    }
}