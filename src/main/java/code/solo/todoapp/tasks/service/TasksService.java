package code.solo.todoapp.tasks.service;

import code.solo.todoapp.tasks.entity.Tasks;
import code.solo.todoapp.tasks.repository.TasksRepository;
import code.solo.todoapp.utils.CustomUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TasksService {

    private final TasksRepository tasksRepository;
    private final CustomUtils<Tasks> customUtils;

    public Tasks createTasks(Tasks tasks) {

        Tasks savedTasks = tasksRepository.save(tasks);
        return savedTasks;
    }

    public Tasks updateTasks(Tasks tasks) {
        Tasks findTasks = verifyExistTasks(tasks.getId());
        Tasks updatingTasks = customUtils.copyNotnullProperties(findTasks, tasks);
        Tasks updatedTasks = tasksRepository.save(updatingTasks);
        return updatedTasks;
    }

    public Tasks findTasks(Long tasksId) {

        Tasks findTasks = verifyExistTasks(tasksId);
        return findTasks;
    }

    public List<Tasks> findTasksList() {
        List<Tasks> tasksList = tasksRepository.findAll();
        return tasksList;
    }


    public Tasks verifyExistTasks(Long tasksId) {
        return tasksRepository.findById(tasksId).orElseThrow(()-> {throw new RuntimeException("존재하지 않는 Tasks");});
    }

    public void eraseTasks(Long tasksId) {

        tasksRepository.deleteById(tasksId);
    }

    public void eraseAllTasks() {

        tasksRepository.deleteAll();

    }


}
