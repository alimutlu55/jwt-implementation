package com.example.springsecurity.controller;


import com.example.springsecurity.models.Project;

import com.example.springsecurity.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @PostMapping
    public void addProject(@RequestBody Project project) {
        projectRepository.save(project);
    }

    @GetMapping("/getAll")
    public List<Project> getProjects() {
        System.out.println("getProjectse geldi");
        return projectRepository.findAll();
    }

    @PostMapping("/{id}")
    public void editProject(@PathVariable long id, @RequestBody Project project) {
        Project existingProject = projectRepository.getOne(id);
        Assert.notNull(existingProject, "Project not found");
        existingProject.setDescription(project.getDescription());
        projectRepository.save(existingProject);
    }

}