package todolist.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import todolist.dto.ProjectDTO;
import todolist.entity.Project;
import todolist.entity.User;
import todolist.service.ProjectService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hello on 02/09/2018.
 */
@RestController()
@RequestMapping("/rest")
public class ProjectRestController {

    @Autowired
    ProjectService projectService;

    @GetMapping("/projects")
    public List<ProjectDTO> projects(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        List<Project> projects = projectService.getProjectForMainScreen(user.getUserNo());

        List<ProjectDTO> projectDTOs = toProjectDTOList(projects);
        projectDTOs.get(0).setSelected(true);

        return projectDTOs;
    }

    @PostMapping("/project")
    public ProjectDTO insertProject(Authentication authentication, @RequestBody ProjectDTO projectDTO) {
        User user = (User) authentication.getPrincipal();
        Project project = projectService.insertProject(user, projectDTO);
        return toProjectDTO(project);
    }

    @DeleteMapping("/project")
    public ResponseEntity<?> deleteProject(Authentication authentication, @RequestBody ProjectDTO projectDTO) {
        User user = (User) authentication.getPrincipal();
        projectService.deleteProject(projectDTO);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/project")
    public ProjectDTO updateProject(Authentication authentication, @RequestBody ProjectDTO projectDTO) {
        User user = (User) authentication.getPrincipal();
        Project project = projectService.updateProject(user, projectDTO);
        return toProjectDTO(project);
    }

    private ProjectDTO toProjectDTO(Project model) {
        ProjectDTO projectDTO = new ProjectDTO();
        BeanUtils.copyProperties(model, projectDTO);
        return projectDTO;
    }

    private List<ProjectDTO> toProjectDTOList(List<Project> model) {

        List<ProjectDTO> projectDTOs = new ArrayList<>();
        for (Project project : model) {
            ProjectDTO projectDTO = new ProjectDTO();
            BeanUtils.copyProperties(project, projectDTO);
            projectDTOs.add(projectDTO);
        }

        return projectDTOs;
    }
}
