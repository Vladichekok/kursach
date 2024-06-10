package com.example.Building.App.Repository;

import com.example.Building.App.Model.Resources;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ResourcesRepository extends JpaRepository<Resources, Long> {
    List<Resources> findByResourcesNameContainingIgnoreCase(String name);
}
