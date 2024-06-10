package com.example.Building.App.Service;

import com.example.Building.App.Model.Resources;
import com.example.Building.App.Model.ResourcesDTO;
import com.example.Building.App.Repository.ResourcesRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ResourcesService {
   private final ResourcesRepository resourcesRepository;

   public  ResourcesService(ResourcesRepository resourcesRepository) {this.resourcesRepository = resourcesRepository;}

    public List<Resources> findResourcesByName(String name){
       return  resourcesRepository.findByResourcesNameContainingIgnoreCase(name);
    }

    public Resources save (Resources resources){
       return resourcesRepository.save(resources);
    }

    public  List<Resources> getAll(){
       return  resourcesRepository.findAll();
    }

    public Resources createResource(ResourcesDTO resourcesDTO){
       var resource = new Resources(0L,resourcesDTO.resourcesName(),resourcesDTO.quantity(),resourcesDTO.cost(),resourcesDTO.projectId());
       return resourcesRepository.save(resource);
    }

    public void deleteResource(Long id){resourcesRepository.deleteById(id);}

    public Resources updateResource(Long id, Resources updatedResourceData){
       return resourcesRepository.findById(id)
               .map(resources-> {
                   resources.setResourcesName(updatedResourceData.getResourcesName());
                   resources.setQuantity(updatedResourceData.getQuantity());
                   resources.setCost(updatedResourceData.getCost());
                   resources.setProjectId(updatedResourceData.getProjectId());
                   return resourcesRepository.save(resources);
               })
               .orElseThrow(() ->new IllegalArgumentException("Resource not found with id: " + id));
    }
}

