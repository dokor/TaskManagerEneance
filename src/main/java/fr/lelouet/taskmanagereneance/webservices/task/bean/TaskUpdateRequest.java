package fr.lelouet.taskmanagereneance.webservices.task.bean;

import fr.lelouet.taskmanagereneance.model.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TaskUpdateRequest {
    public Long taskId;
    public Task newTask;
}
