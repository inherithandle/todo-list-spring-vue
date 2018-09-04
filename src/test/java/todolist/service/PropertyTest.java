package todolist.service;

import org.junit.Test;
import org.springframework.beans.BeanUtils;
import todolist.dto.ProjectDTO;
import todolist.entity.Project;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by hello on 04/09/2018.
 */
public class PropertyTest {

    @Test
    public void propertyTest() {
        List<ProjectDTO> projectDTOs = new ArrayList<>();

        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectName("asdfdasf");


        ProjectDTO projectDTO2 = new ProjectDTO();
        projectDTO2.setProjectName("2222");

        ProjectDTO projectDTO3 = new ProjectDTO();
        projectDTO3.setProjectName("3333");

        projectDTOs.add(projectDTO);
        projectDTOs.add(projectDTO2);
        projectDTOs.add(projectDTO3);

        List<Project> projects = new ArrayList<>();
        BeanUtils.copyProperties(projectDTOs, projects);

        for (int i = 0; i < projectDTOs.size(); i++) {
            assertThat(projects.get(i).getProjectName(), is(projectDTOs.get(i).getProjectName()));
        }

        System.out.println(projects);


    }
}
