package todolist.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import todolist.entity.User;

import java.util.List;

/**
 * Created by hello on 03/09/2018.
 */
public class ProjectDTO {

    private Long projectNo;

    private String projectName;

    @JsonIgnore
    private User user;

    private List<TodoDTO> todos;

    private boolean selected;

    private boolean edited;

    public Long getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(Long projectNo) {
        this.projectNo = projectNo;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<TodoDTO> getTodos() {
        return todos;
    }

    public void setTodos(List<TodoDTO> todos) {
        this.todos = todos;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isEdited() {
        return edited;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
    }
}
