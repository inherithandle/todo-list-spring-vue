package todolist.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
