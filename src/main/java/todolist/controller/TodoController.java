package todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import todolist.service.ProjectService;

/**
 * Created by hello on 20/08/2018.
 */
@Controller
@RequestMapping("/todo")
public class TodoController {

    private ProjectService projectService;

    @Autowired
    public TodoController(ProjectService projectService) {
        this.projectService = projectService;
    }

}
