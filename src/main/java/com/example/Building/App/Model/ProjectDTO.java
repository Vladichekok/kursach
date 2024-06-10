package com.example.Building.App.Model;

public record ProjectDTO(

        String projectName,
        String description,
        int start,
        int end,
        String status
) {

}
