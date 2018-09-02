package todolist.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import todolist.entity.Project;
import todolist.entity.User;
import todolist.service.ProjectService;

import java.util.List;

/**
 * Created by hello on 29/08/2018.
 */
@Controller
@RequestMapping("/projects")
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(value= "", method = RequestMethod.GET)
    public String showUserTodos(Authentication authentication, Model model) {
        User user = (User) authentication.getPrincipal();
        List<Project> projects = projectService.getProjectForMainScreen(user.getUserNo());
        model.addAttribute("projects", projects);
        return "todoList";
    }

    @PostMapping("")
    public Project addProjects(Authentication authentication, @RequestParam String projectName) {
        User user = (User) authentication.getPrincipal();
        return projectService.addProject(projectName, user);
    }
}
