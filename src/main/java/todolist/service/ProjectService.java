package todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import todolist.dto.ProjectDTO;
import todolist.entity.Project;
import todolist.entity.User;
import todolist.repository.ProjectRepository;
import todolist.repository.TodoRepository;
import todolist.repository.UserRepository;

import java.util.List;

/**
 * Created by hello on 28/08/2018.
 */
@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;
    
    @Autowired
    TodoRepository todoRepository;

    @Autowired
    UserRepository userRepository;


    /**
     * the todo property of the first project is initialized with List<Todo>.
     * the rest of the list is initialized on demand, when user clicks on that.
     * @param userNo
     * @return
     */
    @Transactional(readOnly = true)
    public List<Project> getProjectForMainScreen(Long userNo) {
        List<Project> projects = projectRepository.findByUserNo(userNo);
        projects.get(0).getTodos().size();
        return projects;
    }

    @Transactional(readOnly = true)
    public Project getFirstProjectBy(Long userNo) {
        PageRequest page = PageRequest.of(0, 1);
        List<Project> firstProject = projectRepository.findFirstProjectBy(userNo, page);
        return firstProject.get(0);
    }

    @Transactional
    public Project addProject(String projectName, User user) {
        Project project = new Project();
        project.setProjectName(projectName);
        project.setUser(user);

        projectRepository.save(project);

        return project;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Project updateProject(User user, ProjectDTO projectDTO) {
        Project project = projectRepository.findById(projectDTO.getProjectNo()).orElseThrow(RuntimeException::new);
        project.setProjectName(projectDTO.getProjectName());
        return project;
    }

    @Transactional
    public Project insertProject(User user, ProjectDTO projectDTO) {
        Project project = new Project();
        project.setProjectName(projectDTO.getProjectName());
        project.setUser(userRepository.getOne(user.getUserNo()));

        return projectRepository.save(project);
    }

    @Transactional
    public int deleteProject(ProjectDTO projectDTO) {
        todoRepository.deleteTodosByProjectNo(projectDTO.getProjectNo());
        return projectRepository.deleteProjectByProjectNo(projectDTO.getProjectNo());
    }
}
