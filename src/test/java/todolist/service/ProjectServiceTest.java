package todolist.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import todolist.dto.ProjectDTO;
import todolist.entity.Project;
import todolist.entity.User;
import todolist.repository.ProjectRepository;
import todolist.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by hello on 29/08/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectServiceTest {

    public long JOMA_USER_NO = 1;

    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void firstProjectTest() {
        Project firstProject = projectService.getFirstProjectBy(JOMA_USER_NO);

        assertThat(firstProject.getProjectName(), is("inbox"));
        assertThat(firstProject.getTodos().size(), is(2));
        assertThat(firstProject.getTodos().get(0).getText(), is("get my hands dirty"));
    }

    @Test
    public void dataTestForTodoScreen() {
        List<Project> projects = projectService.getProjectForMainScreen(JOMA_USER_NO);
        Project firstProject = projects.get(0);

        assertThat(projects.size(), is(2));
        assertThat(firstProject.getProjectName(), is("inbox"));
        assertThat(firstProject.getTodos().size(), is(2));
        assertThat(firstProject.getTodos().get(0).getText(), is("get my hands dirty"));

    }

    @Test
    public void addProjectTest() {
        String projectName = "new PRoject";

        Optional<User> user = userRepository.findById(JOMA_USER_NO);

        projectService.addProject(projectName, user.get());
    }

    @Test
    public void updateProjectTest() {
        User user = new User();
        user.setUserNo(JOMA_USER_NO);
        String projectName = "edited";
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectName(projectName);
        projectDTO.setProjectNo(1L);

        projectService.updateProject(user, projectDTO);

        Project firstProject = projectService.getFirstProjectBy(JOMA_USER_NO);
        assertThat(firstProject.getProjectName(), is("edited"));
    }

}