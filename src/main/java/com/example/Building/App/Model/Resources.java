package com.example.Building.App.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Resources {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String resourcesName;
    private long quantity;
    private long cost;
    private long projectId;

    public Resources(){}
    public Resources(long id, String resourcesName, long quantity, long cost, long projectId){
        this.id = id;
        this.resourcesName = resourcesName;
        this.quantity = quantity;
        this.cost = cost;
        this.projectId = projectId;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getResourcesName() {
        return resourcesName;
    }

    public void setResourcesName(String resourcesName) {
        this.resourcesName = resourcesName;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }
}
